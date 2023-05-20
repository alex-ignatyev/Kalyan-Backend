package com.kalyan.db

import com.kalyan.model.entity.UserCreateEntity
import com.kalyan.model.entity.UserForgotEntity
import com.kalyan.model.table.User
import com.kalyan.model.table.Users
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update

class UsersDatabaseImpl : UsersDatabase {

    override suspend fun createUser(user: UserCreateEntity) {
        transaction {
            SchemaUtils.create(Users)
            Users.insert {
                it[login] = user.login
                it[name] = user.name
                it[saltedPassword] = user.saltedPassword
                it[salt] = user.salt
                it[role] = "Admin"
            }
        }
    }

    override suspend fun changeUserPassword(user: UserForgotEntity) {
        transaction {
            SchemaUtils.create(Users)
            Users.update({ Users.login eq user.login }) {
                it[saltedPassword] = user.saltedPassword
                it[salt] = user.salt
            }
        }
    }

    override suspend fun getUserByLogin(login: String): User? {
        return transaction {
            SchemaUtils.create(Users)
            User.find { Users.login eq login }.firstOrNull()
        }
    }
}

interface UsersDatabase {
    suspend fun createUser(user: UserCreateEntity)
    suspend fun changeUserPassword(user: UserForgotEntity)
    suspend fun getUserByLogin(login: String): User?
}
