package com.acer51.TheZodiacApp

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import java.util.Locale

/**
 * A utility object for managing the application's locale (language).
 */
object LocaleHelper {

    /**
     * Updates the application's locale and persists it across app restarts.
     *
     * @param context The application context.
     * @param languageCode The ISO 639-1 language code (e.g., "en", "es") or
     *                     SettingsRepository.SYSTEM_DEFAULT to follow the system language.
     */
    fun updateLocale(context: Context, languageCode: String) {
        val localeList = if (languageCode == SettingsRepository.SYSTEM_DEFAULT) {
            // Use an empty list to reset to the system default
            LocaleListCompat.getEmptyLocaleList()
        } else {
            // Create a list with the specified language
            val locale = Locale(languageCode)
            LocaleListCompat.create(locale)
        }

        // This is the modern, recommended way to set the app's locale.
        // It persists the locale configuration and automatically recreates Activities.
        AppCompatDelegate.setApplicationLocales(localeList)
    }
}