package com.kalyan.util

fun getCompaniesAndLines() = mapOf(
    "Satyr" to listOf("No Flavors", "Dokha", "Brilliant Collection", "Aroma", "Platinum Collection"),
    "Al Fakher" to listOf("Основная", "Base"),
    "Tangiers" to listOf("Noir", "F-Line", "Burley", "Birquq", "Special Edition"),
    "Северный" to listOf("Основная", "Professional"),
    "Naш (Наш)" to listOf("Основная", "Крепкая"),
    "Хулиган" to listOf("Стандартная", "Крепкая"),
    "Сарма" to listOf("Основная", "Сарма 360"),
    "Bonche" to listOf("Основная", "Линейка 5%"),
    "Must Have" to listOf("Основная"),
    "Duft" to listOf("Solo", "Checkmate", "Pheromone", "Morgenshtern", "Cobra", "Intro", "The Hatters", "Strong", "All-in"),
    "Jam" to listOf("Основная"),
    "Smoke Angels" to listOf("Основная"),
    "Mattpear" to listOf("Основная", "Old School", "Crazy", "Pop"),
    "Black Burn" to listOf("Основная"),
    "Sebero" to listOf("Основная", "Limited Edition", "Arctic Mix", "Black"),
    "Cobra" to listOf("La Muerte", "Virgin", "Origins", "Select"),
    "Spectrum" to listOf("Classic", "Hard", "Mix", "Kitchen"),
    "Chabacco" to listOf("Medium", "Strong", "Chabacco MIX", "JohnCalliano Fest", "Hookahplace", "Gastro", "Emotions"),
    "Element" to listOf("Вода", "Земля", "Воздух", "Огонь", "V Element"),
    "Daily Hookah" to listOf("Основная"),
    "Starline" to listOf("Ягоды", "Фрукты", "Десерты", "Напитки", "Другое"),
    "Darkside" to listOf("Base", "Core", "Rare", "Shot"),
    "Brusko" to listOf("Medium (чайная смесь)", "BIT", "Strong (чайная смесь)", "Hookah (табак)"),
    "Adalya" to listOf("Основная", "Black"),
    "Banger" to listOf("Основная"),
    "Afzal" to listOf("Основная", "Strong")
)

fun getCompanyLogo(company: String): String {
    return when (company) {
        "Adalya" -> "/icons/ic_adalya.webp"
        "Afzal" -> "/icons/ic_afzal.webp"
        "Alfakher" -> "/icons/ic_alfakher.webp"
        "Banger" -> "/icons/ic_banger.webp"
        "Blackburn" -> "/icons/ic_blackburn.webp"
        "Bonche" -> "/icons/ic_bonche.webp"
        "Brusko" -> "/icons/ic_brusko.webp"
        "Chabacco" -> "/icons/ic_chabacco.webp"
        "Cobra" -> "/icons/ic_cobra.webp"
        "Dailyhookah" -> "/icons/ic_dailyhookah.webp"
        "Darkside" -> "/icons/ic_darkside.webp"
        "Duft" -> "/icons/ic_duft.webp"
        "Element" -> "/icons/ic_element.webp"
        "Huligan" -> "/icons/ic_huligan.webp"
        "Jam" -> "/icons/ic_jam.webp"
        "Mattpear" -> "/icons/ic_mattpear.webp"
        "Musthave" -> "/icons/ic_musthave.webp"
        "Nash" -> "/icons/ic_nash.webp"
        "Sarma" -> "/icons/ic_sarma.webp"
        "Satyr" -> "/icons/ic_satyr.webp"
        "Sebero" -> "/icons/ic_sebero.webp"
        "Sever" -> "/icons/ic_sever.webp"
        "Smokeangels" -> "/icons/ic_smokeangels.webp"
        "Spectrum" -> "/icons/ic_spectrum.svg"
        "Starline" -> "/icons/ic_starline.webp"
        "" -> "/icons/ic_tangiers.webp"
        else -> "" //TODO Добавить картинку на неизвестные табаки
    }
}
