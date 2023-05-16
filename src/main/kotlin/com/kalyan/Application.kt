package com.kalyan

import com.kalyan.plugins.configureDB
import com.kalyan.plugins.configureDI
import com.kalyan.plugins.configureHTTP
import com.kalyan.plugins.configureMonitoring
import com.kalyan.plugins.configureRouting
import com.kalyan.plugins.configureSecurity
import com.kalyan.plugins.configureSerialization
import io.ktor.server.application.Application
import org.jetbrains.exposed.sql.Database

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {
    configureDI()
    configureSecurity()
    configureRouting()
    configureSerialization()
    configureHTTP() // Почитать об этом
    configureMonitoring()
    configureDB()
}
