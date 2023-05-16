package com.kalyan.feature.auth

import com.kalyan.db.UsersDatabase
import com.kalyan.model.dto.auth.AccountLoginRequest
import com.kalyan.model.dto.token.TokenClaim
import com.kalyan.model.dto.token.TokenConfig
import com.kalyan.model.dto.token.TokenResponse
import com.kalyan.model.table.UserTable.Role
import com.kalyan.security.hashing.HashingService
import com.kalyan.security.hashing.SaltedHash
import com.kalyan.security.token.TokenService
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receiveNullable
import io.ktor.server.response.respond

class LoginAccountController(
    private val call: ApplicationCall,
    private val userDb: UsersDatabase,
    private val hashingService: HashingService,
    private val tokenService: TokenService,
    private val tokenConfig: TokenConfig
) {

    suspend fun invoke() {
        val data = call.receiveNullable<AccountLoginRequest>() ?: kotlin.run {
            call.respond(HttpStatusCode.BadRequest)
            return
        }

        if (data.login.isBlank() || data.password.isBlank()) {
            call.respond(HttpStatusCode.BadRequest, "Login or password can't be empty")
            return
        }

        if (data.login.length < 4) {
            call.respond(HttpStatusCode.BadRequest, "Login length must be more than 3 symbols")
            return
        }

        if (data.password.length < 4) {
            call.respond(HttpStatusCode.BadRequest, "Password length must be more than 3 symbols")
            return
        }

        val user = try {
            userDb.getUserByLogin(data.login)
        } catch (e: Exception) {
            call.respond(HttpStatusCode.BadRequest, e.message ?: "Error")
            return
        } ?: kotlin.run {
            call.respond(HttpStatusCode.Conflict, "Incorrect username or password")
            return
        }

        val isValidPassword = hashingService.verify(
            value = data.password,
            saltedHash = SaltedHash(
                hash = user.saltedPassword,
                salt = user.salt
            )
        )

        if (!isValidPassword) {
            call.respond(HttpStatusCode.Conflict, "Incorrect username or password")
            return
        }

        val token = tokenService.generate(
            config = tokenConfig,
            TokenClaim(
                name = "userId",
                value = user.id.value.toString()
            )
        )

        call.respond(
            HttpStatusCode.OK,
            TokenResponse(token = token, userId = user.id.toString(), isAdmin = user.role == Role.Admin.name)
        )
    }
}
