package com.kalyan.plugins

import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.callloging.CallLogging
import io.ktor.server.request.path
import org.slf4j.event.Level.DEBUG

fun Application.configureMonitoring() {
    install(CallLogging) {
        level = DEBUG
        filter { call -> call.request.path().startsWith("/") }
    }
}
