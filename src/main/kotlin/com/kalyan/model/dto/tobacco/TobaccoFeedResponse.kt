package com.kalyan.model.dto.tobacco

import kotlinx.serialization.Serializable

@Serializable
data class TobaccoFeedResponse(
    val id: String,
    val taste: String,
    val company: String,
    val line: String = "",
    var image: String = "",
    val rating: Float,
    val votes: Int
)
/*

fun mapToTobaccoFeedResponse(from1: TobaccoTable, from2: TobaccoRatingGeneralTable): TobaccoFeedResponse {
    return TobaccoFeedResponse(
        id = from1.id.toString(),
        taste = from1.taste,
        company = from1.company,
        line = from1.line,
        image = getCompanyLogo(from1.company),
        rating = from2.ratingByUsers,
        votes = from2.votes
    )
}
*/
