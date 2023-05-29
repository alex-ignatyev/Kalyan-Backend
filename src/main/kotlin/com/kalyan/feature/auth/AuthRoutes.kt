package com.kalyan.feature.auth

import com.kalyan.db.UsersDatabase
import com.kalyan.model.dto.token.TokenConfig
import com.kalyan.security.hashing.HashingService
import com.kalyan.security.token.TokenService
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.auth.authenticate
import io.ktor.server.response.respond
import io.ktor.server.routing.Routing
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import org.koin.ktor.ext.inject

fun Routing.configureAuthRouting() {

    val userDb by inject<UsersDatabase>()
    val hashingService by inject<HashingService>()
    val tokenService by inject<TokenService>()
    val tokenConfig by inject<TokenConfig>()


    authenticate {
        get("/authorize") {
            call.respond(HttpStatusCode.OK)
        }
    }

    post("/account_create") {
        CreateAccountController(call, userDb, hashingService).invoke()
    }

    post("/account_login") {
        LoginAccountController(call, userDb, hashingService, tokenService, tokenConfig).invoke()
    }

    post("/account_forgot") {
        ForgotAccountController(call, userDb, hashingService).invoke()
    }
}
