package com.kalyan.model.dto.token

import kotlinx.serialization.Serializable

@Serializable
data class TokenResponse(
    val token: String,
    val userId: String,
    val isAdmin: Boolean
)
