package com.kalyan.model.table

import java.util.UUID
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable

object TobaccoFavorites : UUIDTable("tobacco_favorites") {
    val user = reference("user", Users)
    val tobacco = reference("tobacco", Tobaccos)
}

class TobaccoFavorite(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<TobaccoFavorite>(TobaccoFavorites)

    var user by TobaccoFavorites.user
    var tobacco by TobaccoFavorites.tobacco
}
