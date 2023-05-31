package com.kalyan.model.dto.tobacco

import kotlinx.serialization.Serializable

@Serializable
data class TobaccoInfoResponse(
    val id: String,
    val taste: String,
    val company: String,
    val line: String = "",
    val strength: Long,

    var image: String = "",

    val strengthByUsers: Float = 0f,
    val smokinessByUsers: Float = 0f,
    val aromaByUsers: Float = 0f,
    val ratingByUsers: Float = 0f,
    val tastePowerByUsers: Float = 0f,

    val ratingByUser: Long,
    val strengthByUser: Long,
    val smokinessByUser: Long,
    val aromaByUser: Long,
    val tasteByUser: Long,

    val votes: Long
)
