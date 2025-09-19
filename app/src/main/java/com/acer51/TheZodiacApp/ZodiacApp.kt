package com.acer51.TheZodiacApp

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
        var date by remember { mutableStateOf(LocalDate.now()) }
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
                    title = { Text("TheZodiacApp") },
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
                        text = "The Zodiac App",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onBackground
                    )

                    Spacer(Modifier.height(32.dp)) // This spacer has been adjusted for more room

                    // Zodiac signs are now above the buttons
                    if (tropicalSign.isNotEmpty()) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                "Your tropical zodiac sign: $tropicalSign",
                                color = MaterialTheme.colorScheme.onBackground,
                                style = MaterialTheme.typography.bodyLarge
                            )
                            Spacer(Modifier.height(8.dp))
                            Text(
                                "Your sidereal zodiac sign: $siderealSign",
                                color = MaterialTheme.colorScheme.onBackground,
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                        Spacer(Modifier.height(16.dp))
                    }

                    Text(
                        text = "The current selected birthdate is: ${date.toString()}",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onBackground
                    )

                    Button(
                        onClick = {
                            showDatePicker = true
                            tropicalSign = getTropicalZodiac(date)
                            siderealSign = getSiderealZodiac(date)
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.secondary,
                            contentColor = MaterialTheme.colorScheme.onSecondary
                        )
                    ) {
                        Text("Select Birthdate")
                    }

                    Spacer(Modifier.height(16.dp))

                    Button(
                        onClick = { showZodiacsPopup = true },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.secondary,
                            contentColor = MaterialTheme.colorScheme.onSecondary
                        )
                    ) {
                        Text("Learn More")
                    }

                    Spacer(Modifier.height(32.dp))

                    Text(
                        text = "NOTE: We do not save your data.",
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
                                tropicalSign = getTropicalZodiac(date)
                                siderealSign = getSiderealZodiac(date)
                            }
                            showDatePicker = false
                        }
                    ) {
                        Text("OK")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showDatePicker = false }) {
                        Text("Cancel")
                    }
                }
            ) {
                DatePicker(state = datePickerState)
            }
        }
    }
}
