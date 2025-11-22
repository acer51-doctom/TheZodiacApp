package com.acer51.TheZodiacApp

import android.content.Context
import android.content.SharedPreferences

class SettingsRepository(context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun saveLanguage(languageCode: String) {
        prefs.edit().putString(KEY_LANGUAGE, languageCode).apply()
    }

    fun getLanguage(): String {
        // Return the saved language, or SYSTEM_DEFAULT if nothing is saved yet.
        return prefs.getString(KEY_LANGUAGE, SYSTEM_DEFAULT) ?: SYSTEM_DEFAULT
    }

    companion object {
        private const val PREFS_NAME = "ZodiacAppPrefs"
        private const val KEY_LANGUAGE = "selected_language"
        const val SYSTEM_DEFAULT = "system_default" // The value used in SettingsScreen
    }
}
    