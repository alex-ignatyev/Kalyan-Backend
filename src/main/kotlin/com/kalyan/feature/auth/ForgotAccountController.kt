package com.kalyan.feature.auth

import com.kalyan.db.UsersDatabase
import com.kalyan.model.dto.auth.AccountForgotRequest
import com.kalyan.model.entity.UserForgotEntity
import com.kalyan.security.hashing.HashingService
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receiveNullable
import io.ktor.server.response.respond

class ForgotAccountController(
    private val call: ApplicationCall,
    private val userDb: UsersDatabase,
    private val hashingService: HashingService
) {

    suspend fun invoke() {
        val data = call.receiveNullable<AccountForgotRequest>() ?: kotlin.run {
            call.respond(HttpStatusCode.BadRequest)
            return
        }

        if (data.login.isBlank() || data.newPassword.isBlank()) {
            call.respond(HttpStatusCode.BadRequest, "Login or password can't be empty")
            return
        }

        if (data.login.length < 4) {
            call.respond(HttpStatusCode.BadRequest, "Login length must be more than 3 symbols")
            return
        }

        if (data.newPassword.length < 4) {
            call.respond(HttpStatusCode.BadRequest, "Password length must be more than 3 symbols")
            return
        }

        if (data.newPassword != data.repeatNewPassword) {
            call.respond(HttpStatusCode.BadRequest, "Passwords must match")
            return
        }

        val user = try {
            userDb.getUserByLogin(data.login)
        } catch (e: Exception) {
            call.respond(HttpStatusCode.Conflict, e.message ?: "Error")
            return
        } ?: kotlin.run {
            call.respond(HttpStatusCode.Conflict, "Incorrect login")
            return
        }

        val saltedHash = hashingService.generateSaltedHash(data.newPassword)
        val newUser = UserForgotEntity(user.login, saltedHash.hash, saltedHash.salt)

        try {
            userDb.changeUserPassword(newUser)
        } catch (e: Exception) {
            call.respond(HttpStatusCode.Forbidden, e.message ?: "You can't change the password, try later")
            return
        }

        call.respond(HttpStatusCode.OK, "Password was successfully changed")
    }
}
