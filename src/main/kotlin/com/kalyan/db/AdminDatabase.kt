package com.kalyan.db

import com.kalyan.model.dto.admin.AdminAddTobaccoRequest
import com.kalyan.model.dto.admin.AdminCompaniesResponse
import com.kalyan.model.table.Companies
import com.kalyan.model.table.Company
import com.kalyan.model.table.Line
import com.kalyan.model.table.Lines
import com.kalyan.model.table.Tobaccos
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.transactions.transaction

class AdminDatabaseImpl : AdminDatabase {

    override suspend fun insertTobaccoToBase(request: AdminAddTobaccoRequest) {
        return transaction {
            SchemaUtils.create(Tobaccos)
            val company = Company.find { Companies.company eq request.company }.first()
            val line = Line.find { Lines.line eq request.line }.first()
            Tobaccos.insertAndGetId {
                it[taste] = request.taste
                it[this.company] = company.id
                it[this.line] = line.id
                it[strengthByCompany] = request.strengthByCompany
            }
        }
    }

    override suspend fun getCompanies(): List<AdminCompaniesResponse> {
        return transaction {
            SchemaUtils.create(Companies, Lines)
            Line.all().groupBy { it.company }.map {
                AdminCompaniesResponse(
                    id = it.key.id.value.toString(),
                    companyName = it.key.company,
                    lines = it.value.map { it.line }.sorted()
                )
            }.sortedBy { it.companyName }
        }
    }
}

interface AdminDatabase {
    suspend fun insertTobaccoToBase(request: AdminAddTobaccoRequest)
    suspend fun getCompanies(): List<AdminCompaniesResponse>
}
