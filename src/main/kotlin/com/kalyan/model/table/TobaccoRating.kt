package com.kalyan.model.table

import java.util.UUID
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable

object TobaccoRatings : UUIDTable("tobacco_ratings") {
    val tobacco = reference("tobacco", Tobaccos)
    val user = reference("user", Users)
    val rate = long("rate").default(0L)
    val strength = long("strength").default(0L)
    val smokiness = long("smokiness").default(0L)
    val aroma = long("aroma").default(0L)
    val taste = long("taste").default(0L)
}

class TobaccoRating(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<TobaccoRating>(TobaccoRatings)

    var tobacco by Tobacco referencedOn TobaccoRatings.tobacco
    var user by User referencedOn TobaccoRatings.user
    var rate by TobaccoRatings.rate
    var strength by TobaccoRatings.strength
    var smokiness by TobaccoRatings.smokiness
    var aroma by TobaccoRatings.aroma
    var taste by TobaccoRatings.taste
}
