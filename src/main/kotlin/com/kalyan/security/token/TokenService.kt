package com.kalyan.security.token

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.kalyan.model.dto.token.TokenClaim
import com.kalyan.model.dto.token.TokenConfig
import java.util.Date

class TokenServiceImpl : TokenService {

    override fun generate(config: TokenConfig, vararg claims: TokenClaim): String {
        var token = JWT.create()
            .withAudience(config.audience)
            .withIssuer(config.issuer)
            .withExpiresAt(Date(System.currentTimeMillis() + config.expiresIn))
        claims.forEach { claim ->
            token = token.withClaim(claim.name, claim.value)
        }
        return token.sign(Algorithm.HMAC256(config.secret))
    }
}

interface TokenService {
    fun generate(config: TokenConfig, vararg claims: TokenClaim): String
}
