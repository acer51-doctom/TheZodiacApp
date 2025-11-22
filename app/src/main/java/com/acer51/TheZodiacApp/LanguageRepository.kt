package com.acer51.TheZodiacApp

import android.content.Context
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

// Data class to match the structure of our JSON objects
@Serializable
data class LanguageOption(val code: String, val name: String)

object LanguageRepository {

    private val json = Json { ignoreUnknownKeys = true }

    // Function to read the JSON from assets and return a list of LanguageOption objects
    fun getAvailableLanguages(context: Context): List<LanguageOption> {
        return try {
            // Open, read, and parse the JSON file
            val jsonString = context.assets.open("languages.json").bufferedReader().use { it.readText() }
            json.decodeFromString<List<LanguageOption>>(jsonString)
        } catch (e: Exception) {
            // If the file is missing or corrupt, fall back to a default list
            e.printStackTrace()
            listOf(LanguageOption("en", "English"))
        }
    }
}