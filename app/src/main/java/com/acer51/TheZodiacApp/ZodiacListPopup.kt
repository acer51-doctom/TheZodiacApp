package com.acer51.TheZodiacApp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun ZodiacListPopup(onDismiss: () -> Unit) {
    val zodiacs = listOf(
        stringResource(R.string.aries_name),
        stringResource(R.string.taurus_name),
        stringResource(R.string.gemini_name),
        stringResource(R.string.cancer_name),
        stringResource(R.string.leo_name),
        stringResource(R.string.virgo_name),
        stringResource(R.string.libra_name),
        stringResource(R.string.scorpio_name),
        stringResource(R.string.sagittarius_name),
        stringResource(R.string.capricorn_name),
        stringResource(R.string.aquarius_name),
        stringResource(R.string.pisces_name)
    )

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
                    text = stringResource(R.string.popup_list_title),
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Center
                )

                LazyColumn(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(zodiacs) { zodiac ->
                        Text(
                            text = zodiac,
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.padding(vertical = 4.dp)
                        )
                        Divider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f))
                    }
                }

                Spacer(Modifier.height(16.dp))

                TextButton(onClick = onDismiss) {
                    Text(stringResource(R.string.popup_close))
                }
            }
        }
    }
}
