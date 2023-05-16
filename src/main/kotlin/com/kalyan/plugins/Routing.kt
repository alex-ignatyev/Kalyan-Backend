package com.kalyan.plugins

import com.kalyan.feature.admin.configureAdminRouting
import com.kalyan.feature.auth.configureAuthRouting
import com.kalyan.feature.main.configureRatingRouting
import io.ktor.server.application.Application
import io.ktor.server.http.content.resources
import io.ktor.server.http.content.static
import io.ktor.server.routing.routing

fun Application.configureRouting() {
    routing {
        configureAuthRouting()
        configureRatingRouting()

        configureAdminRouting()

        static {
            resources("static")
        }
    }
}
