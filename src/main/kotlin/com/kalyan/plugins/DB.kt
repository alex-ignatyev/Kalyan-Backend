package com.kalyan.plugins

import com.kalyan.model.table.Companies
import com.kalyan.model.table.Company
import com.kalyan.model.table.Lines
import com.kalyan.util.getCompaniesAndLines
import io.ktor.server.application.Application
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.batchInsert
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.configureDB() {
    // Пароль для закрытой БД который подставляется в УРЛ и хранится в Edit Configuration -> Environment Variables
    val mongoPw = System.getenv("MONGO_PW")
    Database.connect(
        "jdbc:postgresql://localhost:5432/root",
        driver = "org.postgresql.Driver",
        user = "root",
        password = "root"
    )

    transaction {
        SchemaUtils.create(Companies, Lines)

        getCompaniesAndLines().forEach { (companyKey, linesValue) ->
            val isCompanyExist = Company.find { Companies.company eq companyKey }.firstOrNull() != null
            if (isCompanyExist) return@forEach

            val companyId = Companies.insertAndGetId {
                it[company] = companyKey
            }

            Lines.batchInsert(linesValue) { line ->
                this[Lines.company] = companyId
                this[Lines.line] = line
            }
        }
    }
}
