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

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("TheZodiacApp") })
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
                Button(onClick = {
                    showDatePicker(context = LocalContext.current) { pickedDate ->
                        date = pickedDate
                        tropicalSign = getTropicalZodiac(date)
                        siderealSign = getSiderealZodiac(date)
                    }
                }) {
                    Text("Pick Birth Date")
                }


                Spacer(Modifier.height(16.dp))

                if (tropicalSign.isNotEmpty()) {
                    Text("🌞 Tropical Zodiac: $tropicalSign")
                    Text("🌌 Sidereal Zodiac: $siderealSign")

                    Spacer(Modifier.height(16.dp))

                    Button(onClick = { showDetails = !showDetails }) {
                        Text(if (showDetails) "Hide Details" else "Learn More")
                    }

                    if (showDetails) {
                        Text(
                            text = getZodiacDescriptions(tropicalSign, siderealSign),
                            modifier = Modifier.padding(top = 12.dp)
                        )
                    }
                }
            }
        }
    }
}
