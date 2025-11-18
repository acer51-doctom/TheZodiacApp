package com.acer51.TheZodiacApp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun AppDrawer(
    currentRoute: String,
    navController: NavHostController,
    closeDrawer: () -> Unit // Function to close the drawer
) {
    ModalDrawerSheet {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            HorizontalDivider()

            // Zodiac Calculator item
            NavigationDrawerItem(
                label = { Text(stringResource(R.string.drawer_zodiac_calculator)) }, // MODIFIED
                selected = currentRoute == "zodiac", // Highlight if this is the current screen
                onClick = {
                    navController.navigate("zodiac") { launchSingleTop = true }
                    closeDrawer() // Close the drawer after navigation
                }
            )

            // Settings item
            NavigationDrawerItem(
                label = { Text(stringResource(R.string.drawer_settings)) }, // MODIFIED
                selected = currentRoute == "settings",
                onClick = {
                    navController.navigate("settings") { launchSingleTop = true }
                    closeDrawer()
                }
            )
        }
    }
}