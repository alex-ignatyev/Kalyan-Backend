package com.kalyan.model.dto.auth

import kotlinx.serialization.Serializable

@Serializable
data class AccountForgotRequest(
    val login: String,
    val newPassword: String,
    val repeatNewPassword: String
)
