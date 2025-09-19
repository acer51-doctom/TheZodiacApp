package com.acer51.TheZodiacApp

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun ZodiacInfoPopup(onDismiss: () -> Unit) {
    var showZodiacListPopup by remember { mutableStateOf(false) }

    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            )
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Understanding the Zodiacs",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Center
                )

                Text(
                    text = "The normal zodiac, which most people are familiar with, is known as the tropical zodiac. It is based on the Earth's position in relation to the sun and the seasons. The first day of spring (the vernal equinox) is always the starting point for Aries, and the rest of the signs follow in order.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = "The tropical zodiac is the system used in Western astrology. It divides the year into 12 equal segments, or 'houses,' of 30 degrees each, beginning from the moment the sun enters the sign of Aries at the spring equinox. It focuses on the sun's cyclical path through the sky as a marker of the seasons.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = "The difference between the two lies in their starting point. The tropical zodiac is based on the seasons, while the sidereal zodiac is based on the actual constellations in the night sky. Because of a phenomenon called the 'precession of the equinoxes,' the constellations have shifted over time, causing the sidereal signs to be different from the tropical signs.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Button(onClick = { showZodiacListPopup = true }) {
                    Text("Show All Zodiacs")
                }

                TextButton(onClick = onDismiss) {
                    Text("Close")
                }
            }
        }
    }

    if (showZodiacListPopup) {
        ZodiacListPopup(onDismiss = { showZodiacListPopup = false })
    }
}