package com.kalyan.model.dto.tobacco

import kotlinx.serialization.Serializable

@Serializable
data class TobaccoInfoRequest(
    val userId: String,
    val tobaccoId: String
)
