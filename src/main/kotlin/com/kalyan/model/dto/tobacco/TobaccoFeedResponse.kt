package com.kalyan.model.dto.tobacco

import kotlinx.serialization.Serializable

@Serializable
data class TobaccoFeedResponse(
    val id: String,
    val taste: String,
    val company: String,
    val line: String = "",
    var image: String = "",
    val rating: Float,
    val votes: Long
)
