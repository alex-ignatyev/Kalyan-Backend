package com.kalyan.feature.main

import com.kalyan.db.TobaccosDatabase
import com.kalyan.model.dto.tobacco.TobaccoInfoRequest
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receiveNullable
import io.ktor.server.response.respond

class GetTobaccoInfoController(
    private val call: ApplicationCall,
    private val db: TobaccosDatabase
) {

    suspend fun invoke() {
        val data = call.receiveNullable<TobaccoInfoRequest>() ?: kotlin.run {
            call.respond(HttpStatusCode.BadRequest)
            return
        }

        val userId = call.request.queryParameters["userId"] ?: kotlin.run {
            call.respond(HttpStatusCode.BadRequest, "User doesn't exist")
            return
        }

        val response = db.getTobaccoById(data.tobaccoId, userId)

        if (response == null) {
            call.respond(HttpStatusCode.BadRequest)
        } else {
            call.respond(HttpStatusCode.OK, response)
        }
    }
}
