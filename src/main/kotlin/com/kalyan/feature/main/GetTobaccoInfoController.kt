package com.kalyan.feature.main

import com.kalyan.model.dto.tobacco.TobaccoInfoRequest
import com.kalyan.repository.TobaccoRepository
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receiveNullable
import io.ktor.server.response.respond

class GetTobaccoInfoController(
    private val call: ApplicationCall,
    private val repo: TobaccoRepository
) {

    suspend fun invoke() {
        val data = call.receiveNullable<TobaccoInfoRequest>() ?: kotlin.run {
            call.respond(HttpStatusCode.BadRequest)
            return
        }

        val response = repo.getTobaccoInfo(userId = data.userId, tobaccoId = data.tobaccoId)
        if (response == null) {
            call.respond(HttpStatusCode.BadRequest)
        } else {
            call.respond(HttpStatusCode.OK, response)
        }
    }
}
