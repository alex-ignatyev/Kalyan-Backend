package com.kalyan.db

import com.kalyan.model.dto.admin.AdminAddTobaccoRequest
import com.kalyan.model.dto.admin.AdminCompaniesResponse
import com.kalyan.model.table.Companies
import com.kalyan.model.table.Line
import com.kalyan.model.table.Lines
import com.kalyan.model.table.Tobacco
import com.kalyan.model.table.Tobaccos
import com.kalyan.util.Answer
import com.kalyan.util.db.findOrCreateCompany
import com.kalyan.util.db.findOrCreateLine
import io.ktor.http.HttpStatusCode
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.transactions.transaction

class AdminDatabaseImpl : AdminDatabase {

    override suspend fun insertTobaccoToBase(request: AdminAddTobaccoRequest): Answer {
        return transaction {
            SchemaUtils.create(Tobaccos)
            val companyId = findOrCreateCompany(request.company)
            val lineId = findOrCreateLine(companyId, request.line)

            Tobacco.find { Tobaccos.taste eq request.taste }.find { it.company.id == companyId } ?: kotlin.run {
                val newTobaccoId = Tobaccos.insertAndGetId {
                    it[taste] = request.taste
                    it[company] = companyId
                    it[line] = lineId
                    it[strength] = request.strength
                }

                return@transaction Answer(HttpStatusCode.OK, "New tobacco was added: id = $newTobaccoId")
            }

            return@transaction Answer(HttpStatusCode.BadRequest, "Tobacco already exist")
        }
    }

    override suspend fun getCompanies(): List<AdminCompaniesResponse> {
        return transaction {
            SchemaUtils.create(Companies, Lines)
            Line.all().groupBy { it.company }.map { (company, lines) ->
                AdminCompaniesResponse(
                    id = company.id.toString(),
                    company = company.name,
                    lines = lines.map { line -> line.name }.sorted()
                )
            }.sortedBy { it.company }
        }
    }
}

interface AdminDatabase {
    suspend fun insertTobaccoToBase(request: AdminAddTobaccoRequest): Answer
    suspend fun getCompanies(): List<AdminCompaniesResponse>
}
