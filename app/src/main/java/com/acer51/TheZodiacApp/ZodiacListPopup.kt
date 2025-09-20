package com.acer51.TheZodiacApp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight // Not used if using MaterialTheme typography
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun ZodiacListPopup(
    onDismiss: () -> Unit
) {
    // These are the English names, which getLocalizedZodiacName will convert to localized versions
    val zodiacKeys = listOf(
        "Aries", "Taurus", "Gemini", "Cancer", "Leo", "Virgo",
        "Libra", "Scorpio", "Sagittarius", "Capricorn", "Aquarius", "Pisces"
    )

    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .widthIn(max = 400.dp) // Constrain width
                .padding(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant // Or surface
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth() // Take available width within card constraints
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = stringResource(R.string.popup_list_title),
                    style = MaterialTheme.typography.headlineSmall, // Use MaterialTheme
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = 300.dp), // Set a max height for the list
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(4.dp) // Space between items
                ) {
                    items(zodiacKeys) { zodiacKey ->
                        Text(
                            text = getLocalizedZodiacName(zodiacKey), // Use the utility to get localized name
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                        if (zodiacKeys.last() != zodiacKey) { // Don't add divider after last item
                            HorizontalDivider(modifier = Modifier.padding(
                                vertical = 4.dp
                            ))
                        }
                    }
                }
                TextButton(onClick = onDismiss) {
                    Text(stringResource(R.string.popup_close))
                }
            }
        }
    }
}
