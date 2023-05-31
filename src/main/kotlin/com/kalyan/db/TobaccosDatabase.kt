package com.kalyan.db

import com.kalyan.model.dto.tobacco.TobaccoFeedResponse
import com.kalyan.model.dto.tobacco.TobaccoInfoResponse
import com.kalyan.model.dto.tobacco.TobaccoVoteRequest
import com.kalyan.model.dto.tobacco.TobaccoVoteRequest.VoteType.Aroma
import com.kalyan.model.dto.tobacco.TobaccoVoteRequest.VoteType.Rating
import com.kalyan.model.dto.tobacco.TobaccoVoteRequest.VoteType.Smokiness
import com.kalyan.model.dto.tobacco.TobaccoVoteRequest.VoteType.Strength
import com.kalyan.model.dto.tobacco.TobaccoVoteRequest.VoteType.Taste
import com.kalyan.model.table.Companies
import com.kalyan.model.table.Lines
import com.kalyan.model.table.Tobacco
import com.kalyan.model.table.TobaccoRating
import com.kalyan.model.table.TobaccoRatings
import com.kalyan.model.table.Tobaccos
import com.kalyan.model.table.User
import com.kalyan.util.Answer
import com.kalyan.util.getCompanyLogo
import com.kalyan.util.toUUID
import io.ktor.http.HttpStatusCode
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update

class TobaccosDatabaseImpl : TobaccosDatabase {

    override suspend fun getTobaccos(): List<TobaccoFeedResponse> {
        return transaction {
            SchemaUtils.create(Companies, Lines, Tobaccos, TobaccoRatings)
            Tobacco.all().map { tobacco ->
                TobaccoFeedResponse(
                    id = tobacco.id.toString(),
                    taste = tobacco.taste,
                    company = tobacco.company.name,
                    line = tobacco.line.name,
                    image = getCompanyLogo(tobacco.company.name),
                    rating = if (tobacco.ratings.count() == 0L) 0f else (tobacco.ratings.sumOf { it.rate } / tobacco.ratings.count()).toFloat(),
                    votes = tobacco.ratings.count()
                )
            }
        }
    }

    override suspend fun getTobaccoById(tobaccoId: String, userId: String): TobaccoInfoResponse? {
        return transaction {
            val tobacco = Tobacco.findById(tobaccoId.toUUID()) ?: return@transaction null
            val user = User.findById(userId.toUUID()) ?: return@transaction null
            val ratingByUser =
                TobaccoRating.find { TobaccoRatings.user eq user.id }.firstOrNull() ?: return@transaction null

            TobaccoInfoResponse(
                id = tobacco.id.toString(),
                taste = tobacco.taste,
                company = tobacco.company.name,
                line = tobacco.line.name,
                strength = tobacco.strength,

                ratingByUsers = if (tobacco.ratings.count() == 0L) 0f else (tobacco.ratings.sumOf { it.rate } / tobacco.ratings.count()).toFloat(),

                ratingByUser = ratingByUser.rate,
                strengthByUser = ratingByUser.strength,
                smokinessByUser = ratingByUser.smokiness,
                aromaByUser = ratingByUser.aroma,
                tasteByUser = ratingByUser.taste,

                votes = tobacco.ratings.count()
            )
        }
    }

    //TODO Отрефакторить
    override suspend fun insertOrUpdateTobaccoRating(request: TobaccoVoteRequest, userId: String): Answer {
        return transaction {
            SchemaUtils.create(TobaccoRatings)
            val tobaccoFromDb = Tobacco.findById(request.tobaccoId.toUUID()) ?: return@transaction Answer(
                HttpStatusCode.BadRequest,
                "Tobacco doesn't exist"
            )
            val userFromDb = User.findById(userId.toUUID()) ?: return@transaction Answer(
                HttpStatusCode.BadRequest,
                "Tobacco doesn't exist"
            )
            val rating = TobaccoRating.find {
                TobaccoRatings.user eq userId.toUUID() and
                        (TobaccoRatings.tobacco eq request.tobaccoId.toUUID())
            }.firstOrNull()

            if (rating == null) {
                TobaccoRatings.insert {
                    it[tobacco] = tobaccoFromDb.id
                    it[user] = userFromDb.id
                    when (request.type) {
                        Rating -> it[rate] = request.value
                        Strength -> it[strength] = request.value
                        Smokiness -> it[smokiness] = request.value
                        Aroma -> it[aroma] = request.value
                        Taste -> it[taste] = request.value
                    }
                }
            } else {
                TobaccoRatings.update {
                    it[tobacco] = tobaccoFromDb.id
                    it[user] = userFromDb.id
                    when (request.type) {
                        Rating -> it[rate] = request.value
                        Strength -> it[strength] = request.value
                        Smokiness -> it[smokiness] = request.value
                        Aroma -> it[aroma] = request.value
                        Taste -> it[taste] = request.value
                    }
                }
            }

            Answer(HttpStatusCode.OK, "")
        }
    }
}

interface TobaccosDatabase {
    suspend fun getTobaccos(): List<TobaccoFeedResponse>
    suspend fun getTobaccoById(tobaccoId: String, userId: String): TobaccoInfoResponse?
    suspend fun insertOrUpdateTobaccoRating(request: TobaccoVoteRequest, userId: String): Answer
}
