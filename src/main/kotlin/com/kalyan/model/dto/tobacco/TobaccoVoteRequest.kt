package com.kalyan.model.dto.tobacco

import kotlinx.serialization.Serializable

@Serializable
data class TobaccoVoteRequest(
    val tobaccoId: String,
    val type: VoteType,
    val value: Long
) {

    enum class VoteType {
        Rating, Strength, Smokiness, Aroma, Taste
    }
}
