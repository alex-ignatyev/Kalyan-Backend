package com.kalyan.feature.admin

import com.kalyan.db.AdminDatabase
import com.kalyan.model.dto.admin.AdminAddTobaccoRequest
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receiveNullable
import io.ktor.server.response.respond

class AdminAddTobaccoController(
    private val call: ApplicationCall,
    private val db: AdminDatabase
) {

    suspend fun invoke() {
        val data = call.receiveNullable<AdminAddTobaccoRequest>() ?: kotlin.run {
            call.respond(HttpStatusCode.BadRequest)
            return
        }

        if (data.taste.isBlank() || data.company.isBlank() || data.strengthByCompany == 0) {
            call.respond(HttpStatusCode.BadRequest, "All tobacco info should be filled")
            return
        }

        val isTobaccoWasInserted = db.insertTobaccoToBase(data)

        if (isTobaccoWasInserted) {
            call.respond(HttpStatusCode.Created, "Tobacco successfully added")
        } else {
            call.respond(HttpStatusCode.BadRequest, "Tobacco was not added")
        }
    }
}
