package com.kalyan.util

fun getCompanyLogo(company: String): String {
    return when (company) {
        "Spectrum" -> "/icons/ic_spectrum.svg"
        "Duft" -> "/icons/ic_duft.webp"
        "Starline" -> "/icons/ic_starline.webp"
        else -> ""
    }
}
