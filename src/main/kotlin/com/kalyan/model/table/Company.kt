package com.kalyan.model.table

import java.util.UUID
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable

object Companies : UUIDTable("companies") {
    val name = text("name")
}

class Company(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<Company>(Companies)

    var name by Companies.name
}
