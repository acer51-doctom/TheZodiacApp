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
import java.time.LocalTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import com.acer51.TheZodiacApp.ui.theme.TheZodiacAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ZodiacApp() {
    TheZodiacAppTheme {
        var date by remember { mutableStateOf<LocalDate?>(null) }
        var time by remember { mutableStateOf<LocalTime?>(null) } // State for birth time
        var tropicalSignName by remember { mutableStateOf("") }
        var siderealSignName by remember { mutableStateOf("") }
        var risingSignName by remember { mutableStateOf("") } // State for rising sign

        var showZodiacInfoPopup by remember { mutableStateOf(false) }
        var showZodiacListPopup by remember { mutableStateOf(false) }

        var showDatePicker by remember { mutableStateOf(false) }
        var showTimePicker by remember { mutableStateOf(false) } // State to control time picker
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

                // Section to display results
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
                        // Display Rising Sign only if it's calculated
                        if (risingSignName.isNotEmpty()) {
                            Spacer(Modifier.height(8.dp))
                            Text(
                                text = stringResource(R.string.rising_sign_result, getLocalizedZodiacName(risingSignName)),
                                color = MaterialTheme.colorScheme.onBackground,
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                    }
                    Spacer(Modifier.height(16.dp))
                }

                // Section to display selected date and time
                date?.let {
                    val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
                    Text(
                        text = stringResource(R.string.current_birthdate, it.format(formatter)),
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
                time?.let {
                    val formatter = DateTimeFormatter.ofPattern("HH:mm")
                    Text(
                        text = stringResource(R.string.current_birthtime, it.format(formatter)),
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
                if (date != null || time != null) {
                    Spacer(Modifier.height(16.dp))
                }

                // Buttons to select Date and Time
                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    Button(onClick = { showDatePicker = true }) {
                        Text(stringResource(R.string.button_select_birthdate))
                    }
                    // Only show time picker if a date has been selected
                    if (date != null) {
                        Button(onClick = { showTimePicker = true }) {
                            Text(stringResource(R.string.button_select_birthtime))
                        }
                    }
                }

                Spacer(Modifier.height(16.dp))

                // Buttons for "Learn More" and "Zodiac List"
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (tropicalSignName.isNotEmpty()) {
                        Button(onClick = { showZodiacInfoPopup = true }) {
                            Text(stringResource(R.string.button_learn_more))
                        }
                    }
                    Button(onClick = { showZodiacListPopup = true }) {
                        Text(stringResource(R.string.button_zodiac_list))
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

        if (showZodiacInfoPopup) {
            val detailedDescription = getZodiacDescriptions(tropicalSignName, siderealSignName, risingSignName)
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
                onDismissRequest = { showDatePicker = false },
                confirmButton = {
                    TextButton(
                        onClick = {
                            datePickerState.selectedDateMillis?.let { millis ->
                                val selectedDate = Instant.ofEpochMilli(millis).atZone(ZoneId.systemDefault()).toLocalDate()
                                date = selectedDate
                                // Calculate Sun signs
                                tropicalSignName = getTropicalZodiacNonComposable(selectedDate)
                                siderealSignName = getSiderealZodiacNonComposable(selectedDate)
                                // If time is already set, recalculate rising sign with new sun sign
                                time?.let {
                                    risingSignName = getRisingSign(it, tropicalSignName)
                                }
                            }
                            showDatePicker = false
                        }
                    ) { Text(stringResource(R.string.dialog_ok)) }
                },
                dismissButton = {
                    TextButton(onClick = { showDatePicker = false }) {
                        Text(stringResource(R.string.dialog_cancel))
                    }
                }
            ) {
                DatePicker(state = datePickerState)
            }
        }

        // Call the TimePickerDialog
        if (showTimePicker) {
            TimePickerDialog(
                onDismissRequest = { showTimePicker = false },
                onTimeSelected = { selectedTime ->
                    time = selectedTime
                    // Calculate rising sign as soon as time is selected
                    risingSignName = getRisingSign(selectedTime, tropicalSignName)
                    showTimePicker = false
                }
            )
        }
    }
}