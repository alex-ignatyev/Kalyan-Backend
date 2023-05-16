package com.kalyan.model.table

import org.jetbrains.exposed.sql.Table

object TobaccoRatingGeneralTable : Table("tobacco_rating_general") {
    val id = integer("id").autoIncrement()
    val tobaccoId = integer("tobacco_id")
    val strength = float("strength")
    val smokiness = float("smokiness")
    val aroma = float("aroma")
    val taste = float("taste")
    val rating = float("rating")
    val votes = integer("votes")
}
