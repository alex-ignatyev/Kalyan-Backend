package com.kalyan.model.dto.admin

import com.kalyan.model.table.CompanyTable
import kotlinx.serialization.Serializable

@Serializable
data class AdminCompaniesResponse(
    val id: String,
    val companyName: String,
    val lines: List<String>
)

/*
fun CompanyTable.toDto(): AdminCompaniesResponse {
    return AdminCompaniesResponse(
        id = id.toString(),
        companyName = company,
        lines = lines
    )
}

fun List<CompanyTable>.toDto(): List<AdminCompaniesResponse> {
    return this.map { it.toDto() }
}
*/
