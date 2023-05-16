package com.kalyan.model.table

import org.jetbrains.exposed.sql.Table


object CompanyTable : Table("companies") {
    val id = integer("id").autoIncrement()
    val company = text("company")
    //val lines = list
}
