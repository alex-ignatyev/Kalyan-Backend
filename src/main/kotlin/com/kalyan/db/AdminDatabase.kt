package com.kalyan.db

import com.kalyan.model.dto.admin.AdminAddTobaccoRequest
import com.kalyan.model.dto.admin.AdminCompaniesResponse
import com.kalyan.model.table.Companies
import com.kalyan.model.table.Company
import com.kalyan.model.table.Line
import com.kalyan.model.table.Lines
import com.kalyan.model.table.Tobacco
import com.kalyan.model.table.Tobaccos
import com.kalyan.util.Answer
import io.ktor.http.HttpStatusCode
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.transactions.transaction

class AdminDatabaseImpl : AdminDatabase {

    //TODO доразобраться с логикой создания нового вкуса, компании и линейки
    override suspend fun insertTobaccoToBase(request: AdminAddTobaccoRequest): Answer {
        return transaction {
            SchemaUtils.create(Tobaccos)
            val line = Line.find { Lines.line eq request.line }.findLast { it.company.company == request.company }
            val company = Company.find { Companies.company eq request.company }.firstOrNull()

            if (request.manual) {
                if (line != null) return@transaction Answer(HttpStatusCode.Conflict, "This line already exist")

                if (company == null) {
                    val newCompanyId = Companies.insertAndGetId {
                        it[Companies.company] = request.company
                    }

                    val newLineId = Lines.insertAndGetId {
                        it[Lines.company] = newCompanyId
                        it[Lines.line] = request.line
                    }

                    Tobaccos.insert {
                        it[taste] = request.taste
                        it[this.company] = newCompanyId
                        it[this.line] = newLineId
                        it[strengthByCompany] = request.strengthByCompany
                    }
                } else {
                    val newLineId = Lines.insertAndGetId {
                        it[Lines.company] = company.id
                        it[Lines.line] = request.line
                    }

                    val oldTobacco =
                        Tobacco.find { (Tobaccos.taste eq request.taste) }.find { company.company == request.company }
                    if (oldTobacco != null) {
                        return@transaction Answer(HttpStatusCode.BadRequest, "Taste exist")
                    }

                    Tobaccos.insert {
                        it[taste] = request.taste
                        it[this.company] = company.id
                        it[this.line] = newLineId
                        it[strengthByCompany] = request.strengthByCompany
                    }
                }
            } else {
                if (company == null || line == null) return@transaction Answer(
                    HttpStatusCode.Conflict,
                    "Cant find company or line"
                )
                val tobacco = Tobacco.find { (Tobaccos.taste eq request.taste) }.find { it.company == company }
                if (tobacco != null) return@transaction Answer(HttpStatusCode.BadRequest, "Tobacco already exist")

                Tobaccos.insert {
                    it[taste] = request.taste
                    it[this.company] = company.id
                    it[this.line] = line.id
                    it[strengthByCompany] = request.strengthByCompany
                }
            }
            return@transaction Answer(HttpStatusCode.OK, "New tobacco was added")
        }
    }

    override suspend fun getCompanies(): List<AdminCompaniesResponse> {
        return transaction {
            SchemaUtils.create(Companies, Lines)
            Line.all().groupBy { it.company }.map {
                AdminCompaniesResponse(
                    id = it.key.id.value.toString(),
                    companyName = it.key.company,
                    lines = it.value.map { line -> line.line }.sorted()
                )
            }.sortedBy { it.companyName }
        }
    }
}

interface AdminDatabase {
    suspend fun insertTobaccoToBase(request: AdminAddTobaccoRequest): Answer
    suspend fun getCompanies(): List<AdminCompaniesResponse>
}
