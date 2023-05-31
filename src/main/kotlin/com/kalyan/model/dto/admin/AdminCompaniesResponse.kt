package com.kalyan.model.dto.admin

import kotlinx.serialization.Serializable

@Serializable
data class AdminCompaniesResponse(
    val id: String,
    val company: String,
    val lines: List<String>
)
