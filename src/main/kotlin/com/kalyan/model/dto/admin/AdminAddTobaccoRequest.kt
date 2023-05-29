package com.kalyan.model.dto.admin

import kotlinx.serialization.Serializable

@Serializable
data class AdminAddTobaccoRequest(
    val manual: Boolean,
    val taste: String,
    val company: String,
    val line: String,
    val strengthByCompany: Int
)
