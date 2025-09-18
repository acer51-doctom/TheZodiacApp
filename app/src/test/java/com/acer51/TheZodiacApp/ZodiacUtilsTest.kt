package com.acer51.TheZodiacApp

import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.LocalDate

class ZodiacUtilsTest {

    @Test
    fun testTropicalZodiac() {
        val ariesDate = LocalDate.of(2025, 3, 25)
        val taurusDate = LocalDate.of(2025, 5, 15)
        val capricornDate = LocalDate.of(2025, 1, 10)

        assertEquals("Aries", getTropicalZodiac(ariesDate))
        assertEquals("Taurus", getTropicalZodiac(taurusDate))
        assertEquals("Capricorn", getTropicalZodiac(capricornDate))
    }

    @Test
    fun testSiderealZodiac() {
        // Sidereal is approx 24 days earlier than tropical
        val ariesDate = LocalDate.of(2025, 4, 10) // Tropical Aries -> Sidereal Pisces
        assertEquals("Pisces", getSiderealZodiac(ariesDate))
    }

    @Test
    fun testDescriptionsExist() {
        val tropical = "Leo"
        val sidereal = "Cancer"
        val desc = getZodiacDescriptions(tropical, sidereal)

        // Should contain both zodiac names
        assert(desc.contains("Leo"))
        assert(desc.contains("Cancer"))
        // Should contain some descriptive text
        assert(desc.contains("confident"))
        assert(desc.contains("nurturing"))
    }
}