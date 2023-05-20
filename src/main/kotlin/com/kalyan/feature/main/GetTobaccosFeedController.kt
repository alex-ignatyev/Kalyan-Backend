package com.kalyan.feature.main

import com.kalyan.db.TobaccosDatabase
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond

class GetTobaccosFeedController(
    private val call: ApplicationCall,
    private val db: TobaccosDatabase
) {

    suspend fun invoke() {
        call.respond(HttpStatusCode.OK, db.getTobaccos())
    }
}
