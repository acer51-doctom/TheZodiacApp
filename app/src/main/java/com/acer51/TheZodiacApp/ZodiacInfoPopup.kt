package com.acer51.TheZodiacApp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun ZodiacInfoPopup(
    descriptionText: String,
    onDismiss: () -> Unit
) {
    var showZodiacListPopup by remember { mutableStateOf(false) }

    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .widthIn(max = 400.dp) // Constrain width for better dialog appearance
                .padding(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant // Or surface
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth() // Take available width within card constraints
                    .padding(24.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = stringResource(R.string.popup_detailed_info_title),
                    style = MaterialTheme.typography.headlineSmall, // Adjusted for typical dialog title
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center)

                Divider(modifier = Modifier.padding(vertical = 8.dp))

                Text(
                    text = descriptionText.replace("**", ""), // Remove markdown for bold
                    style = MaterialTheme.typography.bodyLarge, // Or bodyMedium
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                    // textAlign might not be needed if text is long and naturally flows
                )

                Divider(modifier = Modifier.padding(vertical = 8.dp))

                Text(
                    text = stringResource(R.string.popup_info_p3),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center
                )

                Button(
                    onClick = { showZodiacListPopup = true },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondary,
                        contentColor = MaterialTheme.colorScheme.onSecondary
                    )
                ) {
                    Text(stringResource(R.string.popup_show_all_zodiacs))
                }

                TextButton(onClick = onDismiss) {
                    Text(stringResource(R.string.popup_close))
                }
            }
        }
    }

    if (showZodiacListPopup) {
        ZodiacListPopup(onDismiss = { showZodiacListPopup = false })
    }
}
