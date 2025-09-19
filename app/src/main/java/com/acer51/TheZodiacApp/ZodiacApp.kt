package com.acer51.TheZodiacApp

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import java.time.LocalDate
import java.time.Instant
import java.time.ZoneId
import com.acer51.TheZodiacApp.ui.theme.TheZodiacAppTheme
// LocalContext is not used in this version, so it can be removed if not needed elsewhere

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ZodiacApp() {
    TheZodiacAppTheme {
        var date by remember { mutableStateOf<LocalDate?>(null) }
        // These will now store the plain string names (e.g., "Aries", "Taurus")
        var tropicalSignName by remember { mutableStateOf("") }
        var siderealSignName by remember { mutableStateOf("") }
        var showZodiacsPopup by remember { mutableStateOf(false) }

        var showDatePicker by remember { mutableStateOf(false) }
        val datePickerState = rememberDatePickerState()

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(stringResource(R.string.app_name)) },
                    colors = TopAppBarDefaults.topAppBarColors( // Use topAppBarColors for M3
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                )
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues) // Use the paddingValues from Scaffold
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(R.string.title_app),
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onBackground
                )

                Spacer(Modifier.height(32.dp))

                if (tropicalSignName.isNotEmpty()) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            // Use getLocalizedZodiacName to display the translated sign
                            text = stringResource(R.string.tropical_sign_result, getLocalizedZodiacName(tropicalSignName)),
                            color = MaterialTheme.colorScheme.onBackground,
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Spacer(Modifier.height(8.dp))
                        Text(
                            text = stringResource(R.string.sidereal_sign_result, getLocalizedZodiacName(siderealSignName)),
                            color = MaterialTheme.colorScheme.onBackground,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                    Spacer(Modifier.height(16.dp))
                }

                date?.let {
                    Text(
                        text = stringResource(R.string.current_birthdate, it.toString()),
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Spacer(Modifier.height(16.dp))
                }

                Button(
                    onClick = { showDatePicker = true },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondary,
                        contentColor = MaterialTheme.colorScheme.onSecondary
                    )
                ) {
                    Text(stringResource(R.string.button_select_birthdate))
                }

                Spacer(Modifier.height(16.dp))

                // Only show "Learn More" if signs have been calculated
                if (tropicalSignName.isNotEmpty()) {
                    Button(
                        onClick = { showZodiacsPopup = true },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.secondary,
                            contentColor = MaterialTheme.colorScheme.onSecondary
                        )
                    ) {
                        Text(stringResource(R.string.button_learn_more))
                    }
                }

                Spacer(Modifier.height(32.dp))

                Text(
                    text = stringResource(R.string.disclaimer),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onBackground,
                    textAlign = TextAlign.Center
                )
            }
        }

        if (showZodiacsPopup && tropicalSignName.isNotEmpty()) {
            // Generate the detailed description string
            val detailedDescription = getZodiacDescriptions(tropicalSignName, siderealSignName)

            ZodiacInfoPopup( // Call the updated ZodiacInfoPopup
                descriptionText = detailedDescription,
                onDismiss = { showZodiacsPopup = false }
            )
        }


        if (showDatePicker) {
            DatePickerDialog(
                onDismissRequest = {
                    showDatePicker = false
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            datePickerState.selectedDateMillis?.let { millis ->
                                val selectedDate = Instant.ofEpochMilli(millis).atZone(ZoneId.systemDefault()).toLocalDate()
                                date = selectedDate
                                // Call the NON-COMPOSABLE versions from ZodiacUtils
                                tropicalSignName = getTropicalZodiacNonComposable(selectedDate)
                                siderealSignName = getSiderealZodiacNonComposable(selectedDate)
                            }
                            showDatePicker = false
                        }
                    ) {
                        Text(stringResource(R.string.dialog_ok))
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = { showDatePicker = false }
                    ) {
                        Text(stringResource(R.string.dialog_cancel))
                    }
                }
            ) {
                DatePicker(state = datePickerState)
            }
        }
    }
}

// A simple popup to display a title and text.
// You can replace ZodiacInfoPopup with this or adapt ZodiacInfoPopup.
@Composable
fun SimpleInfoPopup(title: String, text: String, onDismiss: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant // Or surface
            )
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant, // Or onSurface
                    textAlign = TextAlign.Center
                )
                Text(
                    text = text,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant // Or onSurface
                )
                TextButton(onClick = onDismiss) {
                    Text(stringResource(R.string.popup_close))
                }
            }
        }
    }
}
    