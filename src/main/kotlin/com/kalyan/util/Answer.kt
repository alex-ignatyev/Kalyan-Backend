package com.kalyan.util

import io.ktor.http.HttpStatusCode

/*
classsealed class Result {
    data class Failure(val code: Int, val message: String): Result
    data class Success(): Result
}*/

data class Answer(val code: HttpStatusCode = HttpStatusCode.OK, val message: String)
