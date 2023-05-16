package com.kalyan.plugins

import io.ktor.server.application.Application
import org.jetbrains.exposed.sql.Database

fun Application.configureDB() {
    // Пароль для закрытой БД который подставляется в УРЛ и хранится в Edit Configuration -> Environment Variables
    val mongoPw = System.getenv("MONGO_PW")
    Database.connect(
        "jdbc:postgresql://localhost:5432/root",
        driver = "org.postgresql.Driver",
        user = "root",
        password = "root"
    )
}