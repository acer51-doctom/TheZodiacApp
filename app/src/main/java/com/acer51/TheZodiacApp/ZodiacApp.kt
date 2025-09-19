package com.acer51.TheZodiacApp

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import java.time.LocalDate
import java.time.Instant
import java.time.ZoneId
import com.acer51.TheZodiacApp.ui.theme.TheZodiacAppTheme
import androidx.compose.ui.platform.LocalContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ZodiacApp() {
    TheZodiacAppTheme {
        // Initialize date to null so it is not displayed on launch
        var date by remember { mutableStateOf<LocalDate?>(null) }
        var tropicalSign by remember { mutableStateOf("") }
        var siderealSign by remember { mutableStateOf("") }
        var showZodiacsPopup by remember { mutableStateOf(false) }

        // State to control the visibility of the DatePickerDialog
        var showDatePicker by remember { mutableStateOf(false) }
        val datePickerState = rememberDatePickerState()

        val context = LocalContext.current

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(stringResource(R.string.app_name)) },
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                )
            }
        ) { padding ->
            Box(modifier = Modifier.fillMaxSize()) {
                // The AnimatedBackground composable has been removed.

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(R.string.title_app),
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onBackground
                    )

                    Spacer(Modifier.height(32.dp)) // This spacer has been adjusted for more room

                    // Conditionally show the zodiac results only after a date has been selected.
                    if (tropicalSign.isNotEmpty()) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                stringResource(R.string.tropical_sign_result, tropicalSign),
                                color = MaterialTheme.colorScheme.onBackground,
                                style = MaterialTheme.typography.bodyLarge
                            )
                            Spacer(Modifier.height(8.dp))
                            Text(
                                stringResource(R.string.sidereal_sign_result, siderealSign),
                                color = MaterialTheme.colorScheme.onBackground,
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                        Spacer(Modifier.height(16.dp))
                    }

                    // Conditionally show the birthdate display only after a date has been selected.
                    if (date != null) {
                        Text(
                            text = stringResource(R.string.current_birthdate, date.toString()),
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        // This spacer adds more space between the birthdate text and the button.
                        Spacer(Modifier.height(16.dp))
                    }

                    Button(
                        onClick = {
                            showDatePicker = true
                            // The logic for calculating zodiac signs has been moved to the confirm button in DatePickerDialog
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.secondary,
                            contentColor = MaterialTheme.colorScheme.onSecondary
                        )
                    ) {
                        Text(stringResource(R.string.button_select_birthdate))
                    }

                    Spacer(Modifier.height(16.dp))

                    Button(
                        onClick = { showZodiacsPopup = true },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.secondary,
                            contentColor = MaterialTheme.colorScheme.onSecondary
                        )
                    ) {
                        Text(stringResource(R.string.button_learn_more))
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
        }

        if (showZodiacsPopup) {
            ZodiacInfoPopup(onDismiss = { showZodiacsPopup = false })
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
                                date = Instant.ofEpochMilli(millis).atZone(ZoneId.systemDefault()).toLocalDate()
                                // The zodiac signs are now calculated and shown after confirming the date
                                tropicalSign = getTropicalZodiac(date!!)
                                siderealSign = getSiderealZodiac(date!!)
                            }
                            showDatePicker = false
                        }
                    ) {
                        Text(stringResource(R.string.dialog_ok))
                    }
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
    }
}
