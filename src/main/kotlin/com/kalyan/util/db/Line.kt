package com.kalyan.util.db

import com.kalyan.model.table.Companies
import com.kalyan.model.table.Line
import com.kalyan.model.table.Lines
import java.util.UUID
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Transaction
import org.jetbrains.exposed.sql.insertAndGetId

fun Transaction.findOrCreateLine(companyId: EntityID<UUID>, line: String): EntityID<UUID> {
    SchemaUtils.create(Companies, Lines)
    return Line.find { Lines.name eq line }.findLast { it.company.id == companyId }?.id ?: kotlin.run {
        Lines.insertAndGetId {
            it[company] = companyId
            it[name] = line
        }
    }
}
