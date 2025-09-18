package com.acer51.TheZodiacApp

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.time.LocalDate
import androidx.compose.ui.platform.LocalContext
import com.acer51.thezodiacapp.components.showDatePicker

@Composable
fun ZodiacApp() {
    var date by remember { mutableStateOf(LocalDate.now()) }
    var tropicalSign by remember { mutableStateOf("") }
    var siderealSign by remember { mutableStateOf("") }
    var showDetails by remember { mutableStateOf(false) }

    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("TheZodiacApp", color = MaterialTheme.colorScheme.onPrimary) },
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
                        showDatePicker(context) { pickedDate ->
                            date = pickedDate
                            tropicalSign = getTropicalZodiac(date)
                            siderealSign = getSiderealZodiac(date)
                        }
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
}
