package com.kalyan.feature.main

import com.kalyan.db.TobaccosDatabase
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
    val tobaccoDb by inject<TobaccosDatabase>()

    get("/") {
        call.respond(HttpStatusCode.OK, "Hello world")
    }

    //В хидере Authorization должен быть токен с Bearer
    authenticate {
        get("/tobaccos") {
            GetTobaccosFeedController(call, tobaccoDb).invoke()
        }

        //TODO Добавить query
        post("/tobacco_info") {
            GetTobaccoInfoController(call, tobaccoDb).invoke()
        }

        post("/vote_tobacco") {
            PutTobaccoVoteController(call, tobaccoDb).invoke()
        }
    }
}
