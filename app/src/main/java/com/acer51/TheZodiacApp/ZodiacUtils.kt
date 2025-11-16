package com.acer51.TheZodiacApp

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import java.time.LocalDate
import java.time.LocalTime
import java.time.MonthDay

// (Keep your existing getTropicalZodiacNonComposable, getSiderealZodiacNonComposable, and getLocalizedZodiacName functions here...)
// I'll re-add them here for completeness.

fun getTropicalZodiacNonComposable(date: LocalDate): String {
    val monthDay = MonthDay.from(date)
    return when {
        monthDay.isAfter(MonthDay.of(3, 20)) && monthDay.isBefore(MonthDay.of(4, 20)) -> "Aries"
        monthDay.isAfter(MonthDay.of(4, 19)) && monthDay.isBefore(MonthDay.of(5, 21)) -> "Taurus"
        monthDay.isAfter(MonthDay.of(5, 20)) && monthDay.isBefore(MonthDay.of(6, 21)) -> "Gemini"
        monthDay.isAfter(MonthDay.of(6, 20)) && monthDay.isBefore(MonthDay.of(7, 23)) -> "Cancer"
        monthDay.isAfter(MonthDay.of(7, 22)) && monthDay.isBefore(MonthDay.of(8, 23)) -> "Leo"
        monthDay.isAfter(MonthDay.of(8, 22)) && monthDay.isBefore(MonthDay.of(9, 23)) -> "Virgo"
        monthDay.isAfter(MonthDay.of(9, 22)) && monthDay.isBefore(MonthDay.of(10, 23)) -> "Libra"
        monthDay.isAfter(MonthDay.of(10, 22)) && monthDay.isBefore(MonthDay.of(11, 22)) -> "Scorpio"
        monthDay.isAfter(MonthDay.of(11, 21)) && monthDay.isBefore(MonthDay.of(12, 22)) -> "Sagittarius"
        monthDay.isAfter(MonthDay.of(12, 21)) || monthDay.isBefore(MonthDay.of(1, 20)) -> "Capricorn"
        monthDay.isAfter(MonthDay.of(1, 19)) && monthDay.isBefore(MonthDay.of(2, 19)) -> "Aquarius"
        monthDay.isAfter(MonthDay.of(2, 18)) && monthDay.isBefore(MonthDay.of(3, 21)) -> "Pisces"
        else -> "Unknown"
    }
}

fun getSiderealZodiacNonComposable(date: LocalDate): String {
    // Sidereal is roughly 24 days behind Tropical. We subtract 24 days.
    val siderealDate = date.minusDays(24)
    return getTropicalZodiacNonComposable(siderealDate)
}

/**
 * NEW FUNCTION
 * Calculates the Rising Sign (Ascendant) based on birth time and sun sign.
 * This is a common simplification that assumes sunrise is at 6 AM.
 * The Rising Sign changes approximately every 2 hours.
 * At sunrise (6 AM), the Rising Sign is the same as the Sun Sign.
 */
fun getRisingSign(birthTime: LocalTime, sunSign: String): String {
    val zodiacOrder = listOf(
        "Aries", "Taurus", "Gemini", "Cancer", "Leo", "Virgo",
        "Libra", "Scorpio", "Sagittarius", "Capricorn", "Aquarius", "Pisces"
    )

    val sunSignIndex = zodiacOrder.indexOf(sunSign)
    if (sunSignIndex == -1) return "Unknown"

    // Calculate how many 2-hour blocks have passed since 6 AM.
    // We normalize the hour to handle times past midnight (e.g., 2 AM is 20 hours after 6 AM yesterday).
    val hoursSince6AM = (birthTime.hour - 6 + 24) % 24
    val signShifts = hoursSince6AM / 2

    // The rising sign index is the sun sign index plus the number of shifts, wrapped around 12.
    val risingSignIndex = (sunSignIndex + signShifts) % 12

    return zodiacOrder[risingSignIndex]
}


@Composable
fun getLocalizedZodiacName(signKey: String): String {
    val resourceId = when (signKey) {
        "Aries" -> R.string.aries_name
        "Taurus" -> R.string.taurus_name
        "Gemini" -> R.string.gemini_name
        "Cancer" -> R.string.cancer_name
        "Leo" -> R.string.leo_name
        "Virgo" -> R.string.virgo_name
        "Libra" -> R.string.libra_name
        "Scorpio" -> R.string.scorpio_name
        "Sagittarius" -> R.string.sagittarius_name
        "Capricorn" -> R.string.capricorn_name
        "Aquarius" -> R.string.aquarius_name
        "Pisces" -> R.string.pisces_name
        else -> R.string.unknown_sign_name
    }
    return stringResource(id = resourceId)
}

fun getZodiacDescription(signKey: String): Int {
    return when (signKey) {
        "Aries" -> R.string.aries_description
        "Taurus" -> R.string.taurus_description
        "Gemini" -> R.string.gemini_description
        "Cancer" -> R.string.cancer_description
        "Leo" -> R.string.leo_description
        "Virgo" -> R.string.virgo_description
        "Libra" -> R.string.libra_description
        "Scorpio" -> R.string.scorpio_description
        "Sagittarius" -> R.string.sagittarius_description
        "Capricorn" -> R.string.capricorn_description
        "Aquarius" -> R.string.aquarius_description
        "Pisces" -> R.string.pisces_description
        else -> R.string.no_description_available
    }
}

/**
 * UPDATED FUNCTION
 * Generates the detailed description text for the popup, now including the Rising Sign.
 */
@Composable
fun getZodiacDescriptions(tropicalSign: String, siderealSign: String, risingSign: String): String {
    val tropicalDesc = stringResource(getZodiacDescription(tropicalSign))
    val siderealDesc = stringResource(getZodiacDescription(siderealSign))
    val risingDesc = if (risingSign.isNotEmpty()) stringResource(getZodiacDescription(risingSign)) else ""

    val sb = StringBuilder()

    // Tropical vs Sidereal explanation
    sb.append("**${stringResource(R.string.tropical_vs_sidereal_header)}**\n")
    sb.append(stringResource(R.string.tropical_explanation)).append("\n\n")
    sb.append(stringResource(R.string.sidereal_explanation)).append("\n\n")

    // Your calculated signs
    sb.append("**${stringResource(R.string.your_tropical_sign_label)} (${getLocalizedZodiacName(tropicalSign)})**\n")
    sb.append(tropicalDesc).append("\n\n")

    sb.append("**${stringResource(R.string.your_sidereal_sign_label)} (${getLocalizedZodiacName(siderealSign)})**\n")
    sb.append(siderealDesc).append("\n\n")

    // Add Rising Sign section if it has been calculated
    if (risingSign.isNotEmpty()) {
        sb.append("**${stringResource(R.string.what_is_a_rising_sign_header)}**\n")
        sb.append(stringResource(R.string.rising_sign_explanation)).append("\n\n")

        sb.append("**${stringResource(R.string.your_rising_sign_label)} (${getLocalizedZodiacName(risingSign)})**\n")
        sb.append(risingDesc).append("\n")
    }

    return sb.toString()
}
