package com.kalyan.plugins

import com.kalyan.di.databaseModule
import com.kalyan.di.repositoryModule
import com.kalyan.di.securityModule
import io.ktor.server.application.Application
import io.ktor.server.application.install
import org.koin.ktor.plugin.Koin

fun Application.configureDI() {
    install(Koin) {
        modules(
            databaseModule,
            repositoryModule,
            securityModule
        )
    }
}
