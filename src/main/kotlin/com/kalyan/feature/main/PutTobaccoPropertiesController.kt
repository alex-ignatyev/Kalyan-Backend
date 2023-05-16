package com.kalyan.feature.main

import com.kalyan.model.dto.tobacco.TobaccoVoteRequest
import com.kalyan.repository.TobaccoRepository
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receiveNullable
import io.ktor.server.response.respond

class PutTobaccoPropertiesController(
    private val call: ApplicationCall,
    private val repo: TobaccoRepository
) {

    suspend fun invoke() {
        val data = call.receiveNullable<TobaccoVoteRequest>() ?: kotlin.run {
            call.respond(HttpStatusCode.BadRequest)
            return
        }

        call.respond(HttpStatusCode.OK, repo.insertTobaccoProperties(data))
    }
}
