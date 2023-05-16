package com.kalyan.model.table

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object UserTable : IntIdTable() {
    val login = text("login")
    val name = text("name").uniqueIndex()
    val saltedPassword = text("salted_password")
    val salt = text("salt")
    val role = text("role")

    val tobaccoRatingTableId = integer("tobacco_voted_table_id").autoIncrement()
    val tobaccoFavoriteTableId = integer("tobacco_favorite_table_id").autoIncrement()

    enum class Role {
        User,
        Admin;
    }
}

class User(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<User>(UserTable)

    var login by UserTable.login
    var name by UserTable.name
    var saltedPassword by UserTable.saltedPassword
    var salt by UserTable.salt
    var role by UserTable.role
    var tobaccoRatingTableId by UserTable.tobaccoRatingTableId
    var tobaccoFavoriteTableId by UserTable.tobaccoFavoriteTableId
}
