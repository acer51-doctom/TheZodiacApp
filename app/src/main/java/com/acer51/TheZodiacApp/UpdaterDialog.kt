package com.acer51.TheZodiacApp

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

@Composable
fun UpdateDialog(
    release: GitHubRelease,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = stringResource(R.string.updater_dialog_title)) },
        text = {
            Text(
                text = stringResource(
                    R.string.updater_dialog_text,
                    release.name,
                    release.body.orEmpty() // Use orEmpty() to handle null release notes
                )
            )
        },
        confirmButton = {
            Button(onClick = onConfirm) {
                Text(stringResource(R.string.updater_dialog_confirm_button))
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(stringResource(R.string.updater_dialog_dismiss_button))
            }
        }
    )
}