package com.kalyan.model.table

import org.jetbrains.exposed.sql.Table

object TobaccoFavoriteTable : Table("tobacco_favorite") {
    val id = integer("id")
    val userId = integer("user_id")
    val userName = text("user_name")
    //val favoriteTobaccosIds: List<String>

    override val primaryKey = PrimaryKey(UserTable.id)
}
