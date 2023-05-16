package com.kalyan.model.dto.auth

import kotlinx.serialization.Serializable

@Serializable
data class AccountCreateRequest(
    val login: String,
    val name: String,
    val password: String,
    val repeatPassword: String
)
