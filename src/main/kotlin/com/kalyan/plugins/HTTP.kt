package com.kalyan.plugins

import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.hsts.HSTS

fun Application.configureHTTP() {
    install(HSTS) {
        includeSubDomains = true
    }
}
