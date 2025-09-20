package com.acer51.TheZodiacApp

import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.LocalDate

// No explicit import needed for getTropicalZodiacNonComposable and getSiderealZodiacNonComposable
// as they are top-level functions in the same package (com.acer51.TheZodiacApp).
// If they were in a sub-package like com.acer51.TheZodiacApp.utils, you'd need:
// import com.acer51.TheZodiacApp.utils.getTropicalZodiacNonComposable
// import com.acer51.TheZodiacApp.utils.getSiderealZodiacNonComposable

/**
 * A class containing unit tests for the functions in ZodiacUtils.kt.
 * These tests ensure the tropical and sidereal zodiac calculations are correct.
 */
class ZodiacUtilsTest {

    // --- Tropical Zodiac Tests ---

    @Test
    fun getTropicalZodiac_forAries_returnsAries() {
        val date = LocalDate.of(2025, 3, 25)
        val result = getTropicalZodiacNonComposable(date) // Use the new function name
        assertEquals("Aries", result)
    }

    @Test
    fun getTropicalZodiac_forTaurus_returnsTaurus() {
        val date = LocalDate.of(2025, 4, 25)
        val result = getTropicalZodiacNonComposable(date) // Use the new function name
        assertEquals("Taurus", result)
    }

    @Test
    fun getTropicalZodiac_forGemini_returnsGemini() {
        val date = LocalDate.of(2025, 5, 25)
        val result = getTropicalZodiacNonComposable(date) // Use the new function name
        assertEquals("Gemini", result)
    }

    @Test
    fun getTropicalZodiac_forCancer_returnsCancer() {
        val date = LocalDate.of(2025, 6, 25)
        val result = getTropicalZodiacNonComposable(date) // Use the new function name
        assertEquals("Cancer", result)
    }

    @Test
    fun getTropicalZodiac_forLeo_returnsLeo() {
        val date = LocalDate.of(2025, 7, 25)
        val result = getTropicalZodiacNonComposable(date) // Use the new function name
        assertEquals("Leo", result)
    }

    @Test
    fun getTropicalZodiac_forVirgo_returnsVirgo() {
        val date = LocalDate.of(2025, 8, 25)
        val result = getTropicalZodiacNonComposable(date) // Use the new function name
        assertEquals("Virgo", result)
    }

    @Test
    fun getTropicalZodiac_forLibra_returnsLibra() {
        val date = LocalDate.of(2025, 9, 25)
        val result = getTropicalZodiacNonComposable(date) // Use the new function name
        assertEquals("Libra", result)
    }

    @Test
    fun getTropicalZodiac_forScorpio_returnsScorpio() {
        val date = LocalDate.of(2025, 10, 25)
        val result = getTropicalZodiacNonComposable(date) // Use the new function name
        assertEquals("Scorpio", result)
    }

    @Test
    fun getTropicalZodiac_forSagittarius_returnsSagittarius() {
        val date = LocalDate.of(2025, 11, 25)
        val result = getTropicalZodiacNonComposable(date) // Use the new function name
        assertEquals("Sagittarius", result)
    }

    @Test
    fun getTropicalZodiac_forCapricorn_returnsCapricorn() {
        val date = LocalDate.of(2025, 12, 25)
        val result = getTropicalZodiacNonComposable(date) // Use the new function name
        assertEquals("Capricorn", result)
    }

    @Test
    fun getTropicalZodiac_forAquarius_returnsAquarius() {
        val date = LocalDate.of(2025, 1, 25)
        val result = getTropicalZodiacNonComposable(date) // Use the new function name
        assertEquals("Aquarius", result)
    }

    @Test
    fun getTropicalZodiac_forPisces_returnsPisces() {
        val date = LocalDate.of(2025, 2, 25)
        val result = getTropicalZodiacNonComposable(date) // Use the new function name
        assertEquals("Pisces", result)
    }

    // --- Sidereal Zodiac Tests (with a fixed 24-day shift) ---

    @Test
    fun getSiderealZodiac_forAries_returnsPisces() {
        // A tropical Aries becomes a sidereal Pisces due to the ~24-day shift
        val date = LocalDate.of(2025, 3, 25)
        val result = getSiderealZodiacNonComposable(date) // Use the new function name
        assertEquals("Pisces", result)
    }

    @Test
    fun getSiderealZodiac_forTaurus_returnsAries() {
        val date = LocalDate.of(2025, 4, 25)
        val result = getSiderealZodiacNonComposable(date) // Use the new function name
        assertEquals("Aries", result)
    }

    @Test
    fun getSiderealZodiac_forGemini_returnsTaurus() {
        val date = LocalDate.of(2025, 5, 25)
        val result = getSiderealZodiacNonComposable(date) // Use the new function name
        assertEquals("Taurus", result)
    }

    @Test
    fun getSiderealZodiac_forCancer_returnsGemini() {
        val date = LocalDate.of(2025, 6, 25)
        val result = getSiderealZodiacNonComposable(date) // Use the new function name
        assertEquals("Gemini", result)
    }

    @Test
    fun getSiderealZodiac_forLeo_returnsCancer() {
        val date = LocalDate.of(2025, 7, 25)
        val result = getSiderealZodiacNonComposable(date) // Use the new function name
        assertEquals("Cancer", result)
    }

    @Test
    fun getSiderealZodiac_forVirgo_returnsLeo() {
        val date = LocalDate.of(2025, 8, 25)
        val result = getSiderealZodiacNonComposable(date) // Use the new function name
        assertEquals("Leo", result)
    }

    @Test
    fun getSiderealZodiac_forLibra_returnsVirgo() {
        val date = LocalDate.of(2025, 9, 25)
        val result = getSiderealZodiacNonComposable(date) // Use the new function name
        assertEquals("Virgo", result)
    }

    @Test
    fun getSiderealZodiac_forScorpio_returnsLibra() {
        val date = LocalDate.of(2025, 10, 25)
        val result = getSiderealZodiacNonComposable(date) // Use the new function name
        assertEquals("Libra", result)
    }

    @Test
    fun getSiderealZodiac_forSagittarius_returnsScorpio() {
        val date = LocalDate.of(2025, 11, 25)
        val result = getSiderealZodiacNonComposable(date) // Use the new function name
        assertEquals("Scorpio", result)
    }

    @Test
    fun getSiderealZodiac_forCapricorn_returnsSagittarius() {
        val date = LocalDate.of(2025, 12, 25)
        val result = getSiderealZodiacNonComposable(date) // Use the new function name
        assertEquals("Sagittarius", result)
    }

    @Test
    fun getSiderealZodiac_forAquarius_returnsCapricorn() {
        val date = LocalDate.of(2025, 1, 25)
        val result = getSiderealZodiacNonComposable(date) // Use the new function name
        assertEquals("Capricorn", result)
    }

    @Test
    fun getSiderealZodiac_forPisces_returnsAquarius() {
        val date = LocalDate.of(2025, 2, 25)
        val result = getSiderealZodiacNonComposable(date) // Use the new function name
        assertEquals("Aquarius", result)
    }
}
