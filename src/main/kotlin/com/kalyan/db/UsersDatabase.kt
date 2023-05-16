package com.kalyan.db

import com.kalyan.model.entity.UserCreateEntity
import com.kalyan.model.entity.UserForgotEntity
import com.kalyan.model.table.TobaccoFavoriteTable
import com.kalyan.model.table.TobaccoRatingUserTable
import com.kalyan.model.table.User
import com.kalyan.model.table.UserTable
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update

class UsersDatabaseImpl : UsersDatabase {

    override suspend fun createUser(user: UserCreateEntity) {
        transaction {
            SchemaUtils.create(UserTable, TobaccoFavoriteTable, TobaccoRatingUserTable)

            val newUser = UserTable.insert {
                it[login] = user.login
                it[name] = user.name
                it[saltedPassword] = user.saltedPassword
                it[salt] = user.salt
                it[role] = "admin"
            }

            TobaccoFavoriteTable.insert {
                it[id] = newUser[UserTable.tobaccoFavoriteTableId]
                it[userId] = newUser[UserTable.id].value
                it[userName] = newUser[UserTable.name]
            }

            TobaccoRatingUserTable.insert {
                it[id] = newUser[UserTable.tobaccoRatingTableId]
                it[userId] = newUser[UserTable.id].value
                it[userName] = newUser[UserTable.name]
            }
        }
    }

    override suspend fun changeUserPassword(user: UserForgotEntity) {
        transaction {
            SchemaUtils.create(UserTable, TobaccoFavoriteTable, TobaccoRatingUserTable)
            UserTable.update({ UserTable.login eq user.login }) {
                it[saltedPassword] = user.saltedPassword
                it[salt] = user.salt
            }
        }
    }

    override suspend fun getUserByLogin(login: String): User? {
        return transaction {
            SchemaUtils.create(UserTable, TobaccoFavoriteTable, TobaccoRatingUserTable)
            User.find { UserTable.login eq login }.firstOrNull()
        }
    }
}

interface UsersDatabase {
    suspend fun createUser(user: UserCreateEntity)
    suspend fun changeUserPassword(user: UserForgotEntity)
    suspend fun getUserByLogin(login: String): User?
}
