package com.acer51.TheZodiacApp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Colors
val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)
val White = Color(0xFFFFFFFF)
val Black = Color(0xFF000000)
val DarkGray = Color(0xFF121212) // Added a new color for the lighter dark background

// Light and Dark Color Schemes
private val LightColors = lightColorScheme(
    primary = Purple500,
    secondary = Teal200,
    background = White,
    surface = White,
    onPrimary = White,
    onSecondary = Black,
    onBackground = Black,
    onSurface = Black
)

private val DarkColors = darkColorScheme(
    primary = Purple200,
    secondary = Teal200,
    background = DarkGray, // Changed the background to a lighter dark gray
    surface = DarkGray, // Also changed the surface to match
    onPrimary = Black,
    onSecondary = White,
    onBackground = White,
    onSurface = White
)

@Composable
fun TheZodiacAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColors else LightColors

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
