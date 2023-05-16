package com.kalyan.db

import com.kalyan.model.dto.tobacco.TobaccoVoteRequest
import com.kalyan.model.table.TobaccoRatingGeneralTable
import com.kalyan.model.table.TobaccoRatingUserTable
import com.kalyan.model.table.TobaccoTable
import org.jetbrains.exposed.sql.Database

class TobaccosDatabaseImpl : TobaccosDatabase {

    override suspend fun getTobaccos(): List<TobaccoTable> {
        return emptyList()
    }

    override suspend fun getTobaccoById(tobaccoId: String): TobaccoTable? {
        return null
    }

    override suspend fun getTobaccosVotedByUser(): List<TobaccoRatingUserTable> {
        return emptyList()
    }

    override suspend fun getTobaccoVotedByUserWithId(userId: String): TobaccoRatingUserTable? {
        return null //tobaccoRatingUserTable.findOne(TobaccoRatingUserTable::userId eq ObjectId(userId))
    }

    override suspend fun getTobaccosVotedByUsers(): List<TobaccoRatingGeneralTable> {
        return emptyList()
    }

    override suspend fun getTobaccoVotedByUsersWithId(tobaccoId: String): TobaccoRatingGeneralTable? {
        return null //tobaccoVotedByUsersTable.findOne(TobaccoRatingGeneralTable::tobaccoId eq ObjectId(tobaccoId))
    }

    override suspend fun insertTobaccoRatingForUsers(tobaccoId: String): Boolean {
        //val results = tobaccoVotedByUserTable.aggregate<TobaccoVotedByUserTable>(TobaccoVotedByUserTable::votedTobaccos contains tobaccoId)


/*        val a = tobaccoVotedByUsersTable.findOne(TobaccoVotedByUsersTable::tobaccoId eq ObjectId(tobaccoId))

        return if (a == null) {
            TobaccoVotedByUsersTable(
                tobaccoId = ObjectId(tobaccoId),
                strengthByUsers = results.,
                smokinessByUsers =,
                aromaByUsers =,
                tastePowerByUsers =,
                ratingByUsers =,
                votes = 1
            )
            true
        } else {
            val b = a.copy(
                strengthByUsers =,
                smokinessByUsers =,
                aromaByUsers =,
                tastePowerByUsers =,
                ratingByUsers =,
                votes =
            )
            true
        }*/

        return true
    }

    override suspend fun insertTobaccoRatingForUsers(request: TobaccoVoteRequest): Boolean {
        /*val user = userTable.findOneById(ObjectId(request.userId)) ?: return false
        val tobacco = tobaccoTable.findOneById(ObjectId(request.tobaccoId)) ?: return false
        val tobaccoByUsers = tobaccoByUsersTable.findOne(TobaccoByUsersTable::tobaccoId eq ObjectId(request.tobaccoId)) ?: kotlin.run {
                val newTable = TobaccoByUsersTable(tobaccoId = ObjectId(request.tobaccoId))
                tobaccoByUsersTable.insertOne(newTable)
                newTable
            }
        val tobaccoByUser = tobaccoByUserTable.findOne(TobaccoByUserTable::userId eq ObjectId(request.userId))

        val newTobaccoByUser = TobaccoByUser(
            tobaccoId = ObjectId(request.tobaccoId),
            strengthByUser = request.strength,
            smokinessByUser = request.smokiness,
            aromaByUser = request.aroma,
            tastePowerByUser = request.tastePower,
            ratingByUser = request.rating
        )


        //TODO после 1го головсования не переголосовывается
        val newTobaccos = tobaccoByUser.votedTobaccos.toMutableList()
        val dropped = newTobaccos.dropWhile { it.tobaccoId.toString() == request.tobaccoId }.toMutableList()
        dropped.add(newTobaccoByUser)
        val newTable = tobaccoByUser.copy(votedTobaccos = dropped)
        tobaccoByUserTable.updateOne(newTable).wasAcknowledged()

        val newTobaccoByUserTable = TobaccoByUserTable(
            userId = user.id,
            votedTobaccos = listOf(newTobaccoByUser)
        )
        val isTobaccoByUserTableInserted = tobaccoByUserTable.insertOne(newTobaccoByUserTable).wasAcknowledged()
        if (isTobaccoByUserTableInserted) {
            val newVotes = tobaccoByUsers.votes + 1
            val newTobaccoByUsersTable = tobaccoByUsers.copy(
                strengthByUsers = (tobaccoByUsers.strengthByUsers + request.strength) / newVotes,
                smokinessByUsers = (tobaccoByUsers.smokinessByUsers + request.smokiness) / newVotes,
                aromaByUsers = (tobaccoByUsers.aromaByUsers + request.aroma) / newVotes,
                tastePowerByUsers = (tobaccoByUsers.tastePowerByUsers + request.tastePower) / newVotes,
                ratingByUsers = (tobaccoByUsers.ratingByUsers + request.rating) / newVotes,
                votes = newVotes
            )
            return tobaccoByUsersTable.updateOne(newTobaccoByUsersTable).wasAcknowledged()
        }*/

        return false
    }

    override suspend fun insertTobaccoProperties(request: TobaccoVoteRequest): Boolean {
        /*val tobacco = tobaccoRatingUserTable.findOne(TobaccoRatingUserTable::userId eq ObjectId(request.userId))
        if (tobacco == null) {
            createTobaccoVotedByUser(request)
        } else {
            val votedTobaccos = tobacco.votedTobaccos.find { it.tobaccoId.toString() == request.tobaccoId }
            val newVotedTobaccos = tobacco.votedTobaccos.toMutableList()
            if (votedTobaccos == null) {
                newVotedTobaccos.add(
                    TobaccoByUser(
                        tobaccoId = ObjectId(request.tobaccoId),
                        strengthByUser = request.strength,
                        smokinessByUser = request.smokiness,
                        aromaByUser = request.aroma,
                        tastePowerByUser = request.tastePower,
                        ratingByUser = request.rating
                    )
                )
                tobaccoRatingUserTable.updateOne(tobacco.copy(votedTobaccos = newVotedTobaccos))
            } else {
                val droppedList =
                    newVotedTobaccos.dropWhile { it.tobaccoId.toString() == request.tobaccoId }.toMutableList()
                droppedList.add(
                    TobaccoByUser(
                        tobaccoId = ObjectId(request.tobaccoId),
                        strengthByUser = request.strength,
                        smokinessByUser = request.smokiness,
                        aromaByUser = request.aroma,
                        tastePowerByUser = request.tastePower,
                        ratingByUser = request.rating
                    )
                )
                return tobaccoRatingUserTable.updateOne(tobacco.copy(votedTobaccos = droppedList)).wasAcknowledged()
            }
        }*/
        return false
    }

    private suspend fun createTobaccoVotedByUser(request: TobaccoVoteRequest): Boolean {
/*        val user = userTable.findOneById(ObjectId(request.userId)) ?: return false
        return tobaccoRatingUserTable.insertOne(
            TobaccoRatingUserTable(
                userId = ObjectId(request.userId),
                userName = user.name,
                votedTobaccos = listOf(
                    TobaccoByUser(
                        tobaccoId = ObjectId(request.tobaccoId),
                        strengthByUser = request.strength,
                        smokinessByUser = request.smokiness,
                        aromaByUser = request.aroma,
                        tastePowerByUser = request.tastePower,
                        ratingByUser = request.rating
                    )
                )
            )
        ).wasAcknowledged()*/
        return true
    }

    override suspend fun insertTobaccoFavorite(userId: String, tobaccoId: String): Boolean {
        /*val favoritesTable = favorites.findOne(FavoriteTobaccoTable::userId eq userId)
        return if (favoritesTable?.favoritesTobaccoIds?.contains(tobaccoId) == true) {
            val newFavorites = favoritesTable.favoritesTobaccoIds.dropWhile { it == tobaccoId }
            val tobaccosToBeUpdate = favoritesTable.copy(favoritesTobaccoIds = newFavorites)
            favorites.updateOneById(favoritesTable.id, tobaccosToBeUpdate).wasAcknowledged()
        } else {
            val newTable = FavoriteTobaccoTable(userId = userId, favoritesTobaccoIds = listOf(tobaccoId))
            favorites.insertOne(newTable).wasAcknowledged()
        }*/
        return false
    }

    override suspend fun insertTobaccoProperties() {
        TODO("Not yet implemented")
    }

    override suspend fun insertTobaccoRating() {
        TODO("Not yet implemented")
    }
}

interface TobaccosDatabase {
    suspend fun getTobaccos(): List<TobaccoTable>
    suspend fun getTobaccoById(tobaccoId: String): TobaccoTable?
    suspend fun getTobaccosVotedByUser(): List<TobaccoRatingUserTable>
    suspend fun getTobaccoVotedByUserWithId(userId: String): TobaccoRatingUserTable?
    suspend fun getTobaccosVotedByUsers(): List<TobaccoRatingGeneralTable>
    suspend fun getTobaccoVotedByUsersWithId(tobaccoId: String): TobaccoRatingGeneralTable?

    suspend fun insertTobaccoRatingForUsers(tobaccoId: String): Boolean
    suspend fun insertTobaccoRatingForUsers(request: TobaccoVoteRequest): Boolean
    suspend fun insertTobaccoProperties(request: TobaccoVoteRequest): Boolean

    suspend fun insertTobaccoFavorite(userId: String, tobaccoId: String): Boolean
    suspend fun insertTobaccoProperties()
    suspend fun insertTobaccoRating()
}
