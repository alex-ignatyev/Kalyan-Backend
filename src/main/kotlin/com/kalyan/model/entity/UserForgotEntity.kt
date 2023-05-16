package com.kalyan.model.entity

data class UserForgotEntity(
    val login: String,
    val saltedPassword: String,
    val salt: String
)
