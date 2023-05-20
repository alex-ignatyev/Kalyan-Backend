package com.kalyan.feature.admin

import com.kalyan.db.AdminDatabase
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond

class AdminGetCompaniesController(
    private val call: ApplicationCall,
    private val db: AdminDatabase
) {

    suspend fun invoke() {
        call.respond(HttpStatusCode.OK, db.getCompanies())
    }
}
