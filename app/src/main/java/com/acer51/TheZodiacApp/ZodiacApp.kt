package com.acer51.TheZodiacApp

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.time.LocalDate
import java.time.Instant
import java.time.ZoneId
import com.acer51.TheZodiacApp.ui.theme.TheZodiacAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ZodiacApp() {
    TheZodiacAppTheme {
        var date by remember { mutableStateOf(LocalDate.now()) }
        var tropicalSign by remember { mutableStateOf("") }
        var siderealSign by remember { mutableStateOf("") }
        var showDetails by remember { mutableStateOf(false) }

        // State to control the visibility of the DatePickerDialog
        var showDatePicker by remember { mutableStateOf(false) }
        val datePickerState = rememberDatePickerState()

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
                AnimatedBackground()

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Button(
                        onClick = {
                            // Show the date picker dialog when the button is clicked
                            showDatePicker = true
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = MaterialTheme.colorScheme.onPrimary
                        )
                    ) {
                        Text("Pick Birth Date")
                    }

                    Spacer(Modifier.height(16.dp))

                    if (tropicalSign.isNotEmpty()) {
                        Text(
                            "🌞 Tropical Zodiac: $tropicalSign",
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        Text(
                            "🌌 Sidereal Zodiac: $siderealSign",
                            color = MaterialTheme.colorScheme.onBackground
                        )

                        Spacer(Modifier.height(16.dp))

                        Button(
                            onClick = { showDetails = !showDetails },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.secondary,
                                contentColor = MaterialTheme.colorScheme.onSecondary
                            )
                        ) {
                            Text(if (showDetails) "Hide Details" else "Learn More")
                        }

                        if (showDetails) {
                            Text(
                                text = getZodiacDescriptions(tropicalSign, siderealSign),
                                color = MaterialTheme.colorScheme.onBackground,
                                modifier = Modifier.padding(top = 12.dp)
                            )
                        }
                    }
                }
            }
        }

        // Display the DatePickerDialog only when showDatePicker is true
        if (showDatePicker) {
            DatePickerDialog(
                onDismissRequest = {
                    // Dismiss the dialog without confirming the selection
                    showDatePicker = false
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            // Convert the selected date from milliseconds to LocalDate
                            datePickerState.selectedDateMillis?.let { millis ->
                                date = Instant.ofEpochMilli(millis).atZone(ZoneId.systemDefault()).toLocalDate()
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