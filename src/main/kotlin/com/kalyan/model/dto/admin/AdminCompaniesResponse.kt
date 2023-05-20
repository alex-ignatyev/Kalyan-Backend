package com.kalyan.model.dto.admin

import kotlinx.serialization.Serializable

@Serializable
data class AdminCompaniesResponse(
    val id: String,
    val companyName: String,
    val lines: List<String>
)
