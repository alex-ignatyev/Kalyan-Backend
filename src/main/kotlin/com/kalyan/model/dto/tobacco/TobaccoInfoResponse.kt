package com.kalyan.model.dto.tobacco

import kotlinx.serialization.Serializable

@Serializable
data class TobaccoInfoResponse(
    val id: String,
    val taste: String,
    val company: String,
    val line: String = "",

    var image: String = "",

    val strengthByCompany: Int,

    val strengthByUsers: Float = 0f,
    val smokinessByUsers: Float = 0f,
    val aromaByUsers: Float = 0f,
    val ratingByUsers: Float = 0f,
    val tastePowerByUsers: Float = 0f,

    val strengthByUser: Int = 0,
    val smokinessByUser: Int = 0,
    val aromaByUser: Int = 0,
    val tastePowerByUser: Int = 0,
    val ratingByUser: Int = 0,

    val votes: Int
)
/*

fun mapToTobaccoInfoResponse(
    tobaccoTable: TobaccoTable,
    tobaccoVotedByUsersTable: TobaccoRatingGeneralTable?,
    tobaccoByUser: TobaccoByUser?
): TobaccoInfoResponse {
    return TobaccoInfoResponse(
        id = tobaccoTable.id.toString(),
        taste = tobaccoTable.taste,
        company = tobaccoTable.company,
        line = tobaccoTable.line,
        image = getCompanyLogo(tobaccoTable.company),
        strengthByCompany = tobaccoTable.strengthByCompany,

        strengthByUsers = tobaccoVotedByUsersTable?.strengthByUsers ?: 0f,
        smokinessByUsers = tobaccoVotedByUsersTable?.smokinessByUsers ?: 0f,
        aromaByUsers = tobaccoVotedByUsersTable?.aromaByUsers ?: 0f,
        ratingByUsers = tobaccoVotedByUsersTable?.ratingByUsers ?: 0f,
        tastePowerByUsers = tobaccoVotedByUsersTable?.tastePowerByUsers ?: 0f,
        votes = tobaccoVotedByUsersTable?.votes ?: 0,

        strengthByUser = tobaccoByUser?.strengthByUser ?: 0,
        smokinessByUser = tobaccoByUser?.smokinessByUser ?: 0,
        aromaByUser = tobaccoByUser?.aromaByUser ?: 0,
        tastePowerByUser = tobaccoByUser?.tastePowerByUser ?: 0,
        ratingByUser = tobaccoByUser?.ratingByUser ?: 0,
    )
}
*/
