package com.kalyan.feature.auth

import com.kalyan.db.UsersDatabase
import com.kalyan.model.dto.auth.AccountCreateRequest
import com.kalyan.model.entity.UserCreateEntity
import com.kalyan.security.hashing.HashingService
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receiveNullable
import io.ktor.server.response.respond

class CreateAccountController(
    private val call: ApplicationCall,
    private val userDb: UsersDatabase,
    private val hashingService: HashingService
) {

    suspend fun invoke() {
        val data = call.receiveNullable<AccountCreateRequest>() ?: kotlin.run {
            call.respond(HttpStatusCode.BadRequest)
            return
        }

        if (data.login.isBlank() || data.name.isBlank() || data.password.isBlank() || data.repeatPassword.isBlank()) {
            call.respond(HttpStatusCode.BadRequest, "All user info should be filled")
            return
        }

        if (data.login.length < 4) {
            call.respond(HttpStatusCode.BadRequest, "Login length must be more than 3 symbols")
            return
        }

        if (data.password.length < 4 || data.repeatPassword.length < 4) {
            call.respond(HttpStatusCode.BadRequest, "Password length must be more than 3 symbols")
            return
        }

        if (data.login == data.name) {
            call.respond(HttpStatusCode.BadRequest, "Login and Name should be different")
            return
        }

        if (data.password != data.repeatPassword) {
            call.respond(HttpStatusCode.BadRequest, "Passwords must match")
            return
        }

        // It's not safety to create user, but we don't have the way to use free sms services
        // When you have sms service, you can send user to restore password
        val user = try {
            userDb.getUserByLogin(data.login)
        } catch (e: Exception) {
            call.respond(HttpStatusCode.BadRequest, e.message ?: "Error")
            return
        }

        if (user != null) {
            if (user.login == data.login) {
                call.respond(HttpStatusCode.Conflict, "You can't use this login")
                return
            } else if (user.name == data.name) {
                call.respond(HttpStatusCode.Conflict, "You should create uniq user name")
                return
            }
        }

        val saltedHash = hashingService.generateSaltedHash(data.password)
        val newUser = UserCreateEntity(
            login = data.login,
            name = data.name,
            saltedPassword = saltedHash.hash,
            salt = saltedHash.salt
        )

        try {
            userDb.createUser(newUser)
        } catch (e: Exception) {
            call.respond(HttpStatusCode.Conflict, e.message ?: "User wasn't created try later")
            return
        }

        call.respond(HttpStatusCode.Created, "User successfully created")
    }
}
