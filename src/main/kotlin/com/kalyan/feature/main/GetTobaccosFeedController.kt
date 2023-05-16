package com.kalyan.feature.main

import com.kalyan.repository.TobaccoRepository
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond

class GetTobaccosFeedController(
    private val call: ApplicationCall,
    private val repo: TobaccoRepository
) {

    suspend fun invoke() {
        call.respond(HttpStatusCode.OK, repo.getFeed())
    }
}
