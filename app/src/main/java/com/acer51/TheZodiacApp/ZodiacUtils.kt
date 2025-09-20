package com.acer51.TheZodiacApp

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import java.time.LocalDate

// This function is NO LONGER @Composable and returns a plain String
fun getTropicalZodiacNonComposable(date: LocalDate): String {
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
        else -> "Unknown" // Default case
    }
}

// This function is NO LONGER @Composable and returns a plain String
fun getSiderealZodiacNonComposable(date: LocalDate): String {
    return getTropicalZodiacNonComposable(date.minusDays(24)) // Approximate sidereal shift
}

// This function REMAINS @Composable because it uses stringResource directly
// and is intended to be called from a Composable context to build a display string.
@Composable
fun getZodiacDescriptions(tropicalSignName: String, siderealSignName: String): String {
    val tropicalNameLocalized = getLocalizedZodiacName(tropicalSignName)
    val siderealNameLocalized = getLocalizedZodiacName(siderealSignName)

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

    val tropicalDescription = descriptions[tropicalNameLocalized] ?: stringResource(R.string.no_description_available) // Using string resource for fallback
    val siderealDescription = descriptions[siderealNameLocalized] ?: stringResource(R.string.no_description_available) // Using string resource for fallback

    val explanationHeader = stringResource(R.string.tropical_vs_sidereal_header)
    val tropicalExplanation = stringResource(R.string.tropical_explanation)
    val siderealExplanation = stringResource(R.string.sidereal_explanation)
    val yourTropicalSignLabel = stringResource(R.string.your_tropical_sign_label)
    val yourSiderealSignLabel = stringResource(R.string.your_sidereal_sign_label)

    return "$explanationHeader\n\n" +
            "$tropicalExplanation\n\n" +
            "$siderealExplanation\n\n" +
            "---\n\n" +
            "**$yourTropicalSignLabel ($tropicalNameLocalized):**\n$tropicalDescription\n\n" +
            "**$yourSiderealSignLabel ($siderealNameLocalized):**\n$siderealDescription"
}

// This helper is for localizing the plain string names (Aries, Taurus, etc.)
// that getTropicalZodiacNonComposable returns.
@Composable
fun getLocalizedZodiacName(signName: String): String {
    return when (signName) {
        "Aries" -> stringResource(R.string.aries_name)
        "Taurus" -> stringResource(R.string.taurus_name)
        "Gemini" -> stringResource(R.string.gemini_name)
        "Cancer" -> stringResource(R.string.cancer_name)
        "Leo" -> stringResource(R.string.leo_name)
        "Virgo" -> stringResource(R.string.virgo_name)
        "Libra" -> stringResource(R.string.libra_name)
        "Scorpio" -> stringResource(R.string.scorpio_name)
        "Sagittarius" -> stringResource(R.string.sagittarius_name)
        "Capricorn" -> stringResource(R.string.capricorn_name)
        "Aquarius" -> stringResource(R.string.aquarius_name)
        "Pisces" -> stringResource(R.string.pisces_name)
        else -> signName // Fallback for "Unknown" or other cases
    }
}
