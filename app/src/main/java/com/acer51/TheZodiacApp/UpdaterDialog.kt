package com.acer51.TheZodiacApp

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun UpdateDialog(
    release: GitHubRelease,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Update Available!") },
        text = { Text(text = "A new version (${release.name}) is available. Do you want to download it?\n\nRelease Notes:\n${release.body}") },
        confirmButton = {
            Button(onClick = onConfirm) {
                Text("Download")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Later")
            }
        }
    )
}