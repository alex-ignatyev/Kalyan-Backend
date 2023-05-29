package com.kalyan.model.table

import java.util.UUID
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable

object TobaccoRatings : UUIDTable("tobacco_ratings") {
    val rate = long("rate").default(0)
    val strength = long("strength").default(0)
    val smokiness = long("smokiness").default(0)
    val aroma = long("aroma").default(0)
    val taste = long("taste").default(0)
    val tobacco = reference("tobacco", Tobaccos)
    val user = reference("user", Users)
}

class TobaccoRating(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<TobaccoRating>(TobaccoRatings)

    var rate by TobaccoRatings.rate
    var strength by TobaccoRatings.strength
    var smokiness by TobaccoRatings.smokiness
    var aroma by TobaccoRatings.aroma
    var taste by TobaccoRatings.taste
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