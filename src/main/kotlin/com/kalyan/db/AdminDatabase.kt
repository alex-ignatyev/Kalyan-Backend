package com.kalyan.db

import com.kalyan.model.dto.admin.AdminAddTobaccoRequest
import com.kalyan.model.table.CompanyTable
import org.jetbrains.exposed.sql.Database

class AdminDatabaseImpl : AdminDatabase {

    override suspend fun insertTobaccoToBase(request: AdminAddTobaccoRequest): Boolean {
        /*  val tobacco = tobaccos.findOne(
              AdminAddTobaccoRequest::company eq request.company,
              AdminAddTobaccoRequest::taste eq request.taste
          )*/

        return true /*if (tobacco == null) {
            createNewTobacco(request)
        } else {
            tobaccoVotedByUsers.findOneById(tobacco.id) ?: kotlin.run {
                val isTobaccoByUsersInserted =
                    tobaccoVotedByUsers.insertOne(TobaccoVotedByUsersTable(tobaccoId = tobacco.id)).wasAcknowledged()
                if (!isTobaccoByUsersInserted) {
                    deleteTobacco(tobacco.id)
                }
            }

            true
        }*/
    }

    private suspend fun createNewTobacco(request: AdminAddTobaccoRequest): Boolean {
        /*val newTobacco = request.toTable()

        val isTobaccoInserted = tobaccos.insertOne(newTobacco).wasAcknowledged()
        val isTobaccoByUsersInserted =
            tobaccoVotedByUsers.insertOne(TobaccoVotedByUsersTable(tobaccoId = newTobacco.id)).wasAcknowledged()

        if (!(isTobaccoInserted && isTobaccoByUsersInserted)) {
            deleteTobacco(newTobacco.id)
        }*/
        return true
    }

    override suspend fun getCompanies(): List<CompanyTable> {
        return emptyList()
    }
}

interface AdminDatabase {
    suspend fun insertTobaccoToBase(request: AdminAddTobaccoRequest): Boolean
    suspend fun getCompanies(): List<CompanyTable>
}
