package com.kalyan.model.dto.tobacco

import kotlinx.serialization.Serializable

@Serializable
data class TobaccoVoteRequest(
    val userId: String,
    val tobaccoId: String,

    val strength: Int,
    val smokiness: Int,
    val aroma: Int,
    val tastePower: Int,

    val rating: Int
)
