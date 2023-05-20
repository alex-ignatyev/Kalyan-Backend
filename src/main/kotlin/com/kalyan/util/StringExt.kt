package com.kalyan.util

import java.util.UUID

fun String.toUUID(): UUID {
    return UUID.fromString(this)
}