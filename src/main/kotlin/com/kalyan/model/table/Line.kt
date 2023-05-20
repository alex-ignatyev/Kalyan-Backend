package com.kalyan.model.table

import java.util.UUID
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable

object Lines : UUIDTable("lines") {
    val line = text("line")
    val company = reference("company", Companies)
}

class Line(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<Line>(Lines)

    var line by Lines.line
    var company by Company referencedOn Lines.company
}
