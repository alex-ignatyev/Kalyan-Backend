package com.kalyan.db

import com.kalyan.model.dto.tobacco.TobaccoFeedResponse
import com.kalyan.model.dto.tobacco.TobaccoInfoResponse
import com.kalyan.model.dto.tobacco.TobaccoVoteRequest
import com.kalyan.model.table.Companies
import com.kalyan.model.table.Lines
import com.kalyan.model.table.Tobacco
import com.kalyan.model.table.TobaccoRating
import com.kalyan.model.table.TobaccoRatings
import com.kalyan.model.table.Tobaccos
import com.kalyan.model.table.User
import com.kalyan.util.getCompanyLogo
import com.kalyan.util.toUUID
import java.util.UUID
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update

class TobaccosDatabaseImpl : TobaccosDatabase {

    override suspend fun getTobaccos(): List<TobaccoFeedResponse> {
        return transaction {
            SchemaUtils.create(Companies, Lines, Tobaccos, TobaccoRatings)
            Tobacco.all().map {
                TobaccoFeedResponse(
                    id = it.id.value.toString(),
                    taste = it.taste,
                    company = it.company.company,
                    line = it.line.line,
                    image = getCompanyLogo(it.company.company),
                    rating = if (it.ratings.count() == 0L) 0f else (it.ratings.sumOf { it.value } / it.ratings.count()).toFloat(),
                    votes = it.ratings.count().toInt()
                )
            }
        }
    }

    override suspend fun getTobaccoById(tobaccoId: String): TobaccoInfoResponse? {
        return transaction {
            val tobacco = Tobacco.findById(UUID.fromString(tobaccoId)) ?: return@transaction null

            TobaccoInfoResponse(
                id = tobacco.id.value.toString(),
                taste = tobacco.taste,
                company = tobacco.company.company,
                line = tobacco.line.line,
                strengthByCompany = tobacco.strengthByCompany,
                votes = tobacco.ratings.count().toInt()
            )
        }
    }

    override suspend fun voteTobacco(request: TobaccoVoteRequest) {
        transaction {
            SchemaUtils.create(TobaccoRatings)
            val tobacco = Tobacco.findById(request.tobaccoId.toUUID()) ?: return@transaction
            val user = User.findById(request.userId.toUUID()) ?: return@transaction
            val rating =
                TobaccoRating.find { TobaccoRatings.user eq request.userId.toUUID() and (TobaccoRatings.tobacco eq request.tobaccoId.toUUID()) }
                    .firstOrNull()

            if (rating == null) {
                TobaccoRatings.insert {
                    it[value] = request.rating.toLong()
                    it[this.tobacco] = tobacco.id
                    it[this.user] = user.id
                }
            } else {
                TobaccoRatings.update {
                    it[value] = request.rating.toLong()
                    it[this.tobacco] = tobacco.id
                    it[this.user] = user.id
                }
            }
        }
    }
}

interface TobaccosDatabase {
    suspend fun getTobaccos(): List<TobaccoFeedResponse>
    suspend fun getTobaccoById(tobaccoId: String): TobaccoInfoResponse?
    suspend fun voteTobacco(request: TobaccoVoteRequest)
}
