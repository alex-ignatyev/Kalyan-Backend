package com.kalyan.feature.main

import com.kalyan.db.TobaccosDatabase
import com.kalyan.model.dto.tobacco.TobaccoVoteRequest
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receiveNullable
import io.ktor.server.response.respond

class PutTobaccoVoteController(
    private val call: ApplicationCall,
    private val db: TobaccosDatabase
) {

    suspend fun invoke() {
        val data = call.receiveNullable<TobaccoVoteRequest>() ?: kotlin.run {
            call.respond(HttpStatusCode.BadRequest)
            return
        }

        call.respond(HttpStatusCode.OK, db.voteTobacco(data))
    }
}
