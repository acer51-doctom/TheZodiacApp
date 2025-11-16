package com.acer51.TheZodiacApp

import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.LocalDate
import java.time.LocalTime // <-- IMPORT ADDED

// No explicit import needed for getTropicalZodiacNonComposable, getSiderealZodiacNonComposable, and getRisingSign
// as they are top-level functions in the same package (com.acer51.TheZodiacApp).

/**
 * A class containing unit tests for the functions in ZodiacUtils.kt.
 * These tests ensure the tropical, sidereal, and rising sign calculations are correct.
 */
class ZodiacUtilsTest {

    // --- Tropical Zodiac Tests ---

    @Test
    fun getTropicalZodiac_forAries_returnsAries() {
        val date = LocalDate.of(2025, 3, 25)
        val result = getTropicalZodiacNonComposable(date)
        assertEquals("Aries", result)
    }

    @Test
    fun getTropicalZodiac_forTaurus_returnsTaurus() {
        val date = LocalDate.of(2025, 4, 25)
        val result = getTropicalZodiacNonComposable(date)
        assertEquals("Taurus", result)
    }

    // ... (other tropical tests remain the same) ...

    @Test
    fun getTropicalZodiac_forPisces_returnsPisces() {
        val date = LocalDate.of(2025, 2, 25)
        val result = getTropicalZodiacNonComposable(date)
        assertEquals("Pisces", result)
    }

    // --- Sidereal Zodiac Tests (with a fixed 24-day shift) ---

    @Test
    fun getSiderealZodiac_forAries_returnsPisces() {
        // A tropical Aries becomes a sidereal Pisces due to the ~24-day shift
        val date = LocalDate.of(2025, 3, 25)
        val result = getSiderealZodiacNonComposable(date)
        assertEquals("Pisces", result)
    }

    @Test
    fun getSiderealZodiac_forTaurus_returnsAries() {
        val date = LocalDate.of(2025, 4, 25)
        val result = getSiderealZodiacNonComposable(date)
        assertEquals("Aries", result)
    }

    // ... (other sidereal tests remain the same) ...

    @Test
    fun getSiderealZodiac_forPisces_returnsAquarius() {
        val date = LocalDate.of(2025, 2, 25)
        val result = getSiderealZodiacNonComposable(date)
        assertEquals("Aquarius", result)
    }

    // --- Rising Sign Tests ---

    @Test
    fun getRisingSign_atSunrise_matchesSunSign() {
        val time = LocalTime.of(6, 30) // Approx sunrise
        val result = getRisingSign(time, "Aries")
        assertEquals("Aries", result)
    }

    @Test
    fun getRisingSign_midMorning_shiftsOneSign() {
        val time = LocalTime.of(8, 30) // One 2-hour block after sunrise
        val result = getRisingSign(time, "Aries")
        assertEquals("Taurus", result)
    }

    @Test
    fun getRisingSign_afternoon_shiftsMultipleSigns() {
        val time = LocalTime.of(15, 0) // 15:00 is 9 hours after 6:00 -> 4 shifts
        val result = getRisingSign(time, "Leo") // Leo is index 4. 4+4=8. Sagittarius.
        assertEquals("Sagittarius", result)
    }

    @Test
    fun getRisingSign_lateEvening_shiftsManySigns() {
        val time = LocalTime.of(23, 0) // 23:00 is 17 hours after 6:00 -> 8 shifts
        val result = getRisingSign(time, "Cancer") // Cancer is index 3. 3+8=11. Pisces.
        assertEquals("Pisces", result)
    }

    @Test
    fun getRisingSign_afterMidnight_wrapsAroundCorrectly() {
        val time = LocalTime.of(3, 15) // 3:00 is 21 hours after 6:00 -> 10 shifts
        val result = getRisingSign(time, "Virgo") // Virgo is index 5. (5+10)%12 = 3. Cancer.
        assertEquals("Cancer", result)
    }

    @Test
    fun getRisingSign_atTimeBoundary_usesCorrectBlock() {
        val timeJustBeforeShift = LocalTime.of(7, 59)
        val resultBefore = getRisingSign(timeJustBeforeShift, "Aries")
        assertEquals("Aries", resultBefore) // Still in the 6:00-7:59 block

        val timeOnShift = LocalTime.of(8, 0)
        val resultOn = getRisingSign(timeOnShift, "Aries")
        assertEquals("Taurus", resultOn) // Now in the 8:00-9:59 block
    }

    @Test
    fun getRisingSign_zodiacWrapAround_handlesPiscesToAries() {
        val time = LocalTime.of(9, 0) // 9:00 is 3 hours after 6:00 -> 1 shift
        val result = getRisingSign(time, "Pisces") // Pisces is index 11. (11+1)%12=0. Aries.
        assertEquals("Aries", result)
    }

    @Test
    fun getRisingSign_forInvalidSunSign_returnsUnknown() {
        val time = LocalTime.of(12, 0)
        val result = getRisingSign(time, "InvalidSign")
        assertEquals("Unknown", result)
    }
}