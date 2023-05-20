package com.kalyan.model.dto.admin

import kotlinx.serialization.Serializable

@Serializable
data class AdminLinesResponse(
    val id: String,
    val line: String
)
