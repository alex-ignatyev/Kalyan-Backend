package com.kalyan.model.table

import com.kalyan.model.table.TobaccoRatings.default
import java.util.UUID
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable

object Tobaccos : UUIDTable("tobaccos") {
    val taste = text("taste")
    val company = reference("company", Companies)
    val line = reference("line", Lines)
    val strength = long("strength").default(0L)
}

class Tobacco(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<Tobacco>(Tobaccos)

    var taste by Tobaccos.taste
    var company by Company referencedOn Tobaccos.company
    var line by Line referencedOn Tobaccos.line
    var strength by Tobaccos.strength
    val ratings by TobaccoRating referrersOn TobaccoRatings.tobacco
}
