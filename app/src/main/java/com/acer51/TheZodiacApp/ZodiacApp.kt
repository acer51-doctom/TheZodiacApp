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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import java.time.LocalDate
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter // For formatting the date
import com.acer51.TheZodiacApp.ui.theme.TheZodiacAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ZodiacApp() {
    TheZodiacAppTheme {
        var date by remember { mutableStateOf<LocalDate?>(null) }
        var tropicalSignName by remember { mutableStateOf("") }
        var siderealSignName by remember { mutableStateOf("") }
        var showZodiacInfoPopup by remember { mutableStateOf(false) } // Renamed from showZodiacsPopup for clarity
        var showZodiacListPopup by remember { mutableStateOf(false) } // Added state for the list popup

        var showDatePicker by remember { mutableStateOf(false) }
        val datePickerState = rememberDatePickerState()

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(stringResource(R.string.app_name)) },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                )
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
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
                    // Format the date for display
                    val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
                    Text(
                        text = stringResource(R.string.current_birthdate, it.format(formatter)),
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

                Row( // Use a Row for multiple buttons side-by-side
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (tropicalSignName.isNotEmpty()) { // Only show if signs are calculated
                        Button(
                            onClick = { showZodiacInfoPopup = true },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.secondary,
                                contentColor = MaterialTheme.colorScheme.onSecondary
                            )
                        ) {
                            Text(stringResource(R.string.button_learn_more))
                        }
                    }

                    // Button to always show the list of all zodiacs
                    Button(
                        onClick = { showZodiacListPopup = true },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.secondary,
                            contentColor = MaterialTheme.colorScheme.onSecondary
                        )
                    ) {
                        Text(stringResource(R.string.button_zodiac_list)) // Ensure R.string.button_zodiac_list exists
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

        if (showZodiacInfoPopup) { // tropicalSignName check is done before setting this true
            val detailedDescription = getZodiacDescriptions(tropicalSignName, siderealSignName)
            ZodiacInfoPopup(
                descriptionText = detailedDescription,
                onDismiss = { showZodiacInfoPopup = false }
            )
        }

        if (showZodiacListPopup) {
            ZodiacListPopup(onDismiss = { showZodiacListPopup = false })
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
