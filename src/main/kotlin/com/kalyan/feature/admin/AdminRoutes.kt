package com.kalyan.feature.admin

import com.kalyan.db.AdminDatabase
import com.kalyan.db.TobaccosDatabase
import io.ktor.server.application.call
import io.ktor.server.auth.authenticate
import io.ktor.server.routing.Routing
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import org.koin.ktor.ext.inject

fun Routing.configureAdminRouting() {

    val db by inject<AdminDatabase>()

    //В хидере Authorization должен быть токен с Bearer
    authenticate {
        post("/admin_add_tobacco") {
            AdminAddTobaccoController(call, db).invoke()
        }

        get("/admin_tobacco_companies") {
            AdminGetCompaniesController(call, db).invoke()
        }
    }
}
