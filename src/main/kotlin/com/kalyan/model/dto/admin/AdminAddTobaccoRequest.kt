package com.kalyan.model.dto.admin

import kotlinx.serialization.Serializable

@Serializable
data class AdminAddTobaccoRequest(
    val taste: String,
    val company: String,
    val line: String,
    val strengthByCompany: Int
)

/*fun AdminAddTobaccoRequest.toTable(): TobaccoTable {
    return TobaccoTable(
        taste = taste,
        company = company,
        line = line,
        strengthByCompany = strengthByCompany
    )
}*/
