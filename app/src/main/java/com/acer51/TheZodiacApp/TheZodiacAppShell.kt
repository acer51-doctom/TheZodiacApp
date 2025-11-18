package com.acer51.TheZodiacApp

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TheZodiacAppShell() {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: "zodiac"

    // --- UPDATER INTEGRATION START ---
    val context = LocalContext.current
    val updater = remember { AppUpdater(context) }
    val release by updater.updateState.collectAsState()

    // This effect runs once when the app shell is first displayed
    LaunchedEffect(Unit) {
        updater.checkForUpdates()
    }

    // If an update is found, show the dialog
    release?.let {
        UpdateDialog(
            release = it,
            onConfirm = {
                updater.downloadAndInstall(it)
                updater.dismissUpdate() // Hide dialog after starting download
            },
            onDismiss = {
                updater.dismissUpdate() // Hide dialog
            }
        )
    }
    // --- UPDATER INTEGRATION END ---

    val currentTitle = when (currentRoute) {
        "settings" -> stringResource(R.string.settings_title)
        else -> stringResource(id = R.string.app_name)
    }

    ModalNavigationDrawer(
        // ... (rest of your ModalNavigationDrawer is unchanged)
    ) {
        Scaffold(
            // ... (rest of your Scaffold is unchanged)
        ) { paddingValues ->
            androidx.navigation.NavHost(
                // ... (rest of your NavHost is unchanged)
            ) {
                // ...
            }
        }
    }
}