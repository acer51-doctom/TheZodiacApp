package com.acer51.TheZodiacApp

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.acer51.TheZodiacApp.LocaleHelper
import com.acer51.TheZodiacApp.SettingsRepository

// The LanguageOption data class is now in LanguageRepository.kt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen() {
    val context = LocalContext.current
    val settingsRepository = remember { SettingsRepository(context) }

    // --- DYNAMICALLY LOAD LANGUAGES ---
    // Get the list from our new repository and add the "System Default" option at the top.
    val systemDefaultText = stringResource(R.string.settings_language_system_default)
    val languageOptions = remember {
        listOf(LanguageOption(SettingsRepository.SYSTEM_DEFAULT, systemDefaultText)) +
                LanguageRepository.getAvailableLanguages(context)
    }
    // --- END DYNAMIC LOADING ---

    // State for the dropdown menu
    var expanded by remember { mutableStateOf(false) }
    // Get the currently saved language to display it correctly
    val currentLanguageCode = settingsRepository.getLanguage()
    var selectedOption by remember {
        mutableStateOf(
            languageOptions.find { it.code == currentLanguageCode } ?: languageOptions.first()
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.settings_title),
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(Modifier.height(32.dp))

        // Language Dropdown Section (This UI code remains the same)
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = stringResource(R.string.settings_language_label),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded },
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = selectedOption.name,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                    },
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()
                )

                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    languageOptions.forEach { option ->
                        DropdownMenuItem(
                            text = { Text(option.name) },
                            onClick = {
                                selectedOption = option
                                expanded = false
                                // Save and apply the new language
                                settingsRepository.saveLanguage(option.code)
                                LocaleHelper.updateLocale(context, option.code)
                            }
                        )
                    }
                }
            }
        }
    }
}