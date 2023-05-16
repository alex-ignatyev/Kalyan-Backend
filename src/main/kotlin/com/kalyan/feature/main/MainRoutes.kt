package com.kalyan.feature.main

import com.kalyan.db.TobaccosDatabase
import com.kalyan.repository.TobaccoRepository
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.auth.authenticate
import io.ktor.server.response.respond
import io.ktor.server.routing.Routing
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import org.koin.ktor.ext.inject

fun Routing.configureRatingRouting() {

    val db by inject<TobaccosDatabase>()
    val tobaccoRepo by inject<TobaccoRepository>()

    get("/") {
        call.respond(HttpStatusCode.OK, "Hello world")
    }

    //В хидере Authorization должен быть токен с Bearer
    authenticate {
        get("/tobaccos") {
            GetTobaccosFeedController(call, tobaccoRepo).invoke()
        }

        //TODO Добавить query
        post("/tobacco_info") {
            GetTobaccoInfoController(call, tobaccoRepo).invoke()
        }

        post("/vote_tobacco") {
            PutTobaccoPropertiesController(call, tobaccoRepo).invoke()
        }
    }
}
