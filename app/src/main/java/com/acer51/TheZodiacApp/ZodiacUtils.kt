package com.acer51.TheZodiacApp

import java.time.LocalDate

fun getTropicalZodiac(date: LocalDate): String {
    val day = date.dayOfMonth
    val month = date.monthValue
    return when (month) {
        1 -> if (day < 20) "Capricorn" else "Aquarius"
        2 -> if (day < 19) "Aquarius" else "Pisces"
        3 -> if (day < 21) "Pisces" else "Aries"
        4 -> if (day < 20) "Aries" else "Taurus"
        5 -> if (day < 21) "Taurus" else "Gemini"
        6 -> if (day < 21) "Gemini" else "Cancer"
        7 -> if (day < 23) "Cancer" else "Leo"
        8 -> if (day < 23) "Leo" else "Virgo"
        9 -> if (day < 23) "Virgo" else "Libra"
        10 -> if (day < 23) "Libra" else "Scorpio"
        11 -> if (day < 22) "Scorpio" else "Sagittarius"
        12 -> if (day < 22) "Sagittarius" else "Capricorn"
        else -> "Unknown"
    }
}

fun getSiderealZodiac(date: LocalDate): String {
    // Approximate sidereal shift: ~24 days earlier
    return getTropicalZodiac(date.minusDays(24))
}

fun getZodiacDescriptions(tropical: String, sidereal: String): String {
    val descriptions = mapOf(
        "Aries" to "Aries: energetic, bold, pioneering.",
        "Taurus" to "Taurus: steady, reliable, grounded.",
        "Gemini" to "Gemini: curious, adaptable, sociable.",
        "Cancer" to "Cancer: nurturing, sensitive, protective.",
        "Leo" to "Leo: confident, creative, expressive.",
        "Virgo" to "Virgo: analytical, practical, detail-oriented.",
        "Libra" to "Libra: diplomatic, charming, balanced.",
        "Scorpio" to "Scorpio: intense, passionate, transformative.",
        "Sagittarius" to "Sagittarius: adventurous, optimistic, independent.",
        "Capricorn" to "Capricorn: disciplined, ambitious, responsible.",
        "Aquarius" to "Aquarius: innovative, humanitarian, free-thinking.",
        "Pisces" to "Pisces: empathetic, imaginative, dreamy."
    )

    return "🌞 Tropical ($tropical): ${descriptions[tropical] ?: ""}\n\n" +
            "🌌 Sidereal ($sidereal): ${descriptions[sidereal] ?: ""}"
}
