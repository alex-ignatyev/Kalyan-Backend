package com.kalyan.model.entity

data class UserCreateEntity(
    val login: String,
    val name: String,
    val saltedPassword: String,
    val salt: String
)
