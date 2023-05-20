package com.kalyan.model.table

import java.util.UUID
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable

object Users : UUIDTable() {
    val login = text("login")
    val name = text("name").uniqueIndex()
    val saltedPassword = text("salted_password")
    val salt = text("salt")
    val role = text("role")

    enum class Role {
        User,
        Admin;
    }
}

class User(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<User>(Users)

    var login by Users.login
    var name by Users.name
    var saltedPassword by Users.saltedPassword
    var salt by Users.salt
    var role by Users.role
}
