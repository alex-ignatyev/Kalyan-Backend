package com.kalyan.util

import io.ktor.http.HttpStatusCode

data class Answer(val statusCode: HttpStatusCode, val message: String)
