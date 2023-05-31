package com.kalyan.util.db

import com.kalyan.model.table.Companies
import com.kalyan.model.table.Company
import java.util.UUID
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Transaction
import org.jetbrains.exposed.sql.insertAndGetId

fun Transaction.findOrCreateCompany(company: String): EntityID<UUID> {
    SchemaUtils.create(Companies)
    return Company.find { Companies.name eq company }.firstOrNull()?.id ?: kotlin.run {
        Companies.insertAndGetId {
            it[name] = company
        }
    }
}
