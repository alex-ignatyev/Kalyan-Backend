package com.kalyan.model.dto.auth

import kotlinx.serialization.Serializable

@Serializable
data class AccountLoginRequest(
    val login: String,
    val password: String
)
