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
// MODIFIED: Function signature now accepts PaddingValues from the parent Scaffold.
fun ZodiacApp(paddingValues: PaddingValues) {
    TheZodiacAppTheme {
        var date by remember { mutableStateOf<LocalDate?>(null) }
        var time by remember { mutableStateOf<LocalTime?>(null) }
        var tropicalSignName by remember { mutableStateOf("") }
        var siderealSignName by remember { mutableStateOf("") }
        var risingSignName by remember { mutableStateOf("") }

        // Corrected line 37
        var showZodiacInfoPopup by remember { mutableStateOf(false) }
        var showZodiacListPopup by remember { mutableStateOf(false) }

        var showDatePicker by remember { mutableStateOf(false) }
        var showTimePicker by remember { mutableStateOf(false) }
        val datePickerState = rememberDatePickerState()

        // REMOVED: The Scaffold and TopAppBar that were previously here are now managed by TheZodiacAppShell.

        // The Column is now the top-level layout element in this composable.
        Column(
            modifier = Modifier
                .fillMaxSize()
                // MODIFIED: Apply the padding passed from the shell to avoid content being drawn under the top bar.
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

            // Section to display results (no changes here)
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

            // Section to display selected date and time (no changes here)
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

            // Buttons to select Date and Time (no changes here)
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = { showDatePicker = true },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(stringResource(R.string.button_select_birthdate))
                }
                if (date != null) {
                    Button(
                        onClick = { showTimePicker = true },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(stringResource(R.string.button_select_birthtime))
                    }
                }
            }

            Spacer(Modifier.height(16.dp))

            // Buttons for "Learn More" and "Zodiac List" (no changes here)
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                if (tropicalSignName.isNotEmpty()) {
                    Button(
                        onClick = { showZodiacInfoPopup = true },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(stringResource(R.string.button_learn_more))
                    }
                }
                Button(
                    onClick = { showZodiacListPopup = true },
                    modifier = Modifier.fillMaxWidth()
                ) {
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

        // --- Dialog and Popup logic remains unchanged ---
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
                                tropicalSignName = getTropicalZodiacNonComposable(selectedDate)
                                siderealSignName = getSiderealZodiacNonComposable(selectedDate)
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

        if (showTimePicker) {
            TimePickerDialog(
                onDismissRequest = { showTimePicker = false },
                onTimeSelected = { selectedTime ->
                    time = selectedTime
                    risingSignName = getRisingSign(selectedTime, tropicalSignName)
                    showTimePicker = false
                }
            )
        }
    }
}