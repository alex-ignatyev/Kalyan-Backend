package com.kalyan.model.table

import java.util.UUID
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable

object TobaccoRatings : UUIDTable("tobacco_ratings") {
    val value = long("value").default(0)
    val tobacco = reference("tobacco", Tobaccos)
    val user = reference("user", Users)
}

class TobaccoRating(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<TobaccoRating>(TobaccoRatings)

    var value by TobaccoRatings.value
    var tobacco by Tobacco referencedOn TobaccoRatings.tobacco
    var user by User referencedOn TobaccoRatings.user
}

/*
val votedTobaccos: List<TobaccoByUser>

    {
        data class TobaccoByUser(
            val tobaccoId: ObjectId,
            val strengthByUser: Int,
            val smokinessByUser: Int,
            val aromaByUser: Int,
            val tastePowerByUser: Int,
            val ratingByUser: Int
        )
    }
*/