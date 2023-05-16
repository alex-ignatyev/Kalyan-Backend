package com.kalyan.repository

import com.kalyan.db.TobaccosDatabase
import com.kalyan.model.dto.tobacco.TobaccoFeedResponse
import com.kalyan.model.dto.tobacco.TobaccoInfoResponse
import com.kalyan.model.dto.tobacco.TobaccoVoteRequest
import com.kalyan.util.getCompanyLogo

class TobaccoRepositoryImpl(private val db: TobaccosDatabase) : TobaccoRepository {

    override suspend fun getFeed(): List<TobaccoFeedResponse> {
/*        val tobaccos = db.getTobaccos().associateBy { it.id }
        val tobaccoByUsers = db.getTobaccosVotedByUsers()
        val result = tobaccoByUsers.filter { tobaccos[it.tobaccoId] != null }.map { tobaccoByUser ->
            tobaccos[tobaccoByUser.tobaccoId]?.let { tobacco ->
                TobaccoFeedResponse(
                    id = tobacco.id.toString(),
                    taste = tobacco.taste,
                    company = tobacco.company,
                    line = tobacco.line,
                    image = getCompanyLogo(tobacco.company),
                    rating = tobaccoByUser.ratingByUsers,
                    votes = tobaccoByUser.votes
                )
            } ?: return listOf()
        }*/
        return emptyList()
    }

    override suspend fun getTobaccoInfo(userId: String, tobaccoId: String): TobaccoInfoResponse? {
        /*   val tobaccos = db.getTobaccoById(tobaccoId) ?: return null
           val tobaccoByUser =
               db.getTobaccoVotedByUserWithId(userId)?.votedTobaccos?.find { it.tobaccoId.toString() == tobaccoId }
           val tobaccoByUsers = db.getTobaccoVotedByUsersWithId(tobaccoId)
           return mapToTobaccoInfoResponse(tobaccos, tobaccoByUsers, tobaccoByUser)*/
        return null
    }

    override suspend fun insertTobaccoProperties(request: TobaccoVoteRequest): Boolean {
        val isRatingInserted = db.insertTobaccoProperties(request)
        if (isRatingInserted) db.insertTobaccoRatingForUsers(request.tobaccoId)
        return isRatingInserted
    }
}

interface TobaccoRepository {
    suspend fun getFeed(): List<TobaccoFeedResponse>
    suspend fun getTobaccoInfo(userId: String, tobaccoId: String): TobaccoInfoResponse?

    suspend fun insertTobaccoProperties(request: TobaccoVoteRequest): Boolean
}
