package com.kalyan.model.table

import org.jetbrains.exposed.sql.Table

object TobaccoTable : Table("tobacco") {
    val id = integer("id")
    val taste = text("taste")
    val company = text("company")
    val line = text("line")
    val strengthByCompany = integer("strength_by_company")
}
