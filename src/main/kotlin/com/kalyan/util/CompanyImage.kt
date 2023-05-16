package com.kalyan.util

fun getCompanyLogo(company: String): String {
    return when (company) {
        "Spectrum" -> "/icons/ic_spectrum.svg"
        "Daft" -> "/icons/ic_daft.webp"
        "Starline" -> "/icons/ic_starline.webp"
        else -> ""
    }
}
