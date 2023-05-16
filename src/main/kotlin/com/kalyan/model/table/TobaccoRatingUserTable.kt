package com.kalyan.model.table

import org.jetbrains.exposed.sql.Table

object TobaccoRatingUserTable : Table("tobacco_rating_user") {
    val id = integer("id")
    val userId = integer("user_id")
    val userName = text("user_name")

/*

//val votedTobaccos: List<TobaccoByUser>

    {
        data class TobaccoByUser(
            val tobaccoId: ObjectId,
            val strengthByUser: Int,
            val smokinessByUser: Int,
            val aromaByUser: Int,
            val tastePowerByUser: Int,
            val ratingByUser: Int
        )
    }*/
}