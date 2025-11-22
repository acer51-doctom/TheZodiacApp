package com.acer51.TheZodiacApp

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
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

    // CORRECTED: Capture the state value in a local variable to enable a safe smart cast.
    val currentRelease = release
    if (currentRelease != null) {
        UpdateDialog(
            release = currentRelease,
            onConfirm = {
                updater.downloadAndInstall(currentRelease)
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
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Spacer(modifier = Modifier.height(12.dp))

                NavigationDrawerItem(
                    label = { Text(text = "Zodiac Signs") },
                    selected = currentRoute == "zodiac",
                    onClick = {
                        scope.launch { drawerState.close() }
                        navController.navigate("zodiac")
                    }
                )

                NavigationDrawerItem(
                    label = { Text(text = "Settings") },
                    selected = currentRoute == "settings",
                    onClick = {
                        scope.launch { drawerState.close() }
                        navController.navigate("settings")
                    }
                )
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(currentTitle) },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                drawerState.apply {
                                    if (isClosed) open() else close()
                                }
                            }
                        }) {
                            Icon(Icons.Filled.Menu, contentDescription = "Menu")
                        }
                    }
                )
            }
        ) { paddingValues ->
            NavHost(
                navController = navController,
                startDestination = "zodiac",
                modifier = Modifier.padding(paddingValues)
            ) {
                composable(route = "zodiac") {
                    ZodiacApp(paddingValues = paddingValues)
                }
                composable(route = "settings") {
                    SettingsScreen()
                }
                // CORRECTED: The original file was likely missing this closing brace
            }
        }
    }
}