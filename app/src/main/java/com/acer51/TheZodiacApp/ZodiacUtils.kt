package com.acer51.TheZodiacApp

import java.time.LocalDate
import androidx.compose.ui.res.stringResource
import androidx.compose.runtime.Composable

@Composable
fun getTropicalZodiac(date: LocalDate): String {
    val day = date.dayOfMonth
    val month = date.monthValue
    return when (month) {
        1 -> if (day < 20) stringResource(R.string.capricorn_name) else stringResource(R.string.aquarius_name)
        2 -> if (day < 19) stringResource(R.string.aquarius_name) else stringResource(R.string.pisces_name)
        3 -> if (day < 21) stringResource(R.string.pisces_name) else stringResource(R.string.aries_name)
        4 -> if (day < 20) stringResource(R.string.aries_name) else stringResource(R.string.taurus_name)
        5 -> if (day < 21) stringResource(R.string.taurus_name) else stringResource(R.string.gemini_name)
        6 -> if (day < 21) stringResource(R.string.gemini_name) else stringResource(R.string.cancer_name)
        7 -> if (day < 23) stringResource(R.string.cancer_name) else stringResource(R.string.leo_name)
        8 -> if (day < 23) stringResource(R.string.leo_name) else stringResource(R.string.virgo_name)
        9 -> if (day < 23) stringResource(R.string.virgo_name) else stringResource(R.string.libra_name)
        10 -> if (day < 23) stringResource(R.string.libra_name) else stringResource(R.string.scorpio_name)
        11 -> if (day < 22) stringResource(R.string.scorpio_name) else stringResource(R.string.sagittarius_name)
        12 -> if (day < 22) stringResource(R.string.sagittarius_name) else stringResource(R.string.capricorn_name)
        else -> "Unknown"
    }
}

@Composable
fun getSiderealZodiac(date: LocalDate): String {
    // Approximate sidereal shift: ~24 days earlier
    return getTropicalZodiac(date.minusDays(24))
}

@Composable
fun getZodiacDescriptions(tropical: String, sidereal: String): String {
    val descriptions = mapOf(
        stringResource(R.string.aries_name) to stringResource(R.string.aries_description),
        stringResource(R.string.taurus_name) to stringResource(R.string.taurus_description),
        stringResource(R.string.gemini_name) to stringResource(R.string.gemini_description),
        stringResource(R.string.cancer_name) to stringResource(R.string.cancer_description),
        stringResource(R.string.leo_name) to stringResource(R.string.leo_description),
        stringResource(R.string.virgo_name) to stringResource(R.string.virgo_description),
        stringResource(R.string.libra_name) to stringResource(R.string.libra_description),
        stringResource(R.string.scorpio_name) to stringResource(R.string.scorpio_description),
        stringResource(R.string.sagittarius_name) to stringResource(R.string.sagittarius_description),
        stringResource(R.string.capricorn_name) to stringResource(R.string.capricorn_description),
        stringResource(R.string.aquarius_name) to stringResource(R.string.aquarius_description),
        stringResource(R.string.pisces_name) to stringResource(R.string.pisces_description)
    )

    val tropicalDescription = descriptions[tropical] ?: "No description available for Tropical sign."
    val siderealDescription = descriptions[sidereal] ?: "No description available for Sidereal sign."

    return "Tropical vs. Sidereal Astrology:\n\n" +
            "Your tropical sign is based on the position of the sun relative to the seasons on the date of your birth. " +
            "This is the most common form of astrology in the Western world.\n\n" +
            "Your sidereal sign is based on the position of the sun relative to the actual constellations in the sky. " +
            "Because the Earth wobbles on its axis over time, the sidereal zodiac has shifted. " +
            "This is more common in Vedic astrology. Due to this shift, your sidereal sign is often different from your tropical sign." +
            "\n\n---" +
            "\n\n**Your Tropical Sign ($tropical):**\n$tropicalDescription" +
            "\n\n**Your Sidereal Sign ($sidereal):**\n$siderealDescription"
}
