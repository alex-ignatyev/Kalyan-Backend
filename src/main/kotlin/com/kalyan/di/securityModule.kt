package com.kalyan.di

import com.kalyan.model.dto.token.TokenConfig
import com.kalyan.security.hashing.HashingService
import com.kalyan.security.hashing.HashingServiceImpl
import com.kalyan.security.token.TokenService
import com.kalyan.security.token.TokenServiceImpl
import org.koin.dsl.module

val securityModule = module {

    // Token
    single<TokenService> { TokenServiceImpl() }

    single {
        TokenConfig(
            issuer = "http://0.0.0.0:8080", // environment.config.property("jwt.issuer").getString()
            audience = "users", // environment.config.property("jwt.audience").getString()
            expiresIn =  3600000, //1 hour //365L * 1000L * 60L * 60L * 24L -  1 Year in miliseconds
            secret = "JWT_SECRET" // Нужно хранить в в Edit Configuration -> Environment Variables
        )
    }

    // Hashing
    single<HashingService> { HashingServiceImpl() }
}
