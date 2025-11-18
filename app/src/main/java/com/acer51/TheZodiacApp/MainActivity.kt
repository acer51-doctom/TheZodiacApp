package com.acer51.TheZodiacApp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.acer51.TheZodiacApp.ui.theme.TheZodiacAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TheZodiacAppTheme {
                // MODIFIED: Call the new shell composable which contains all navigation logic.
                // This replaces the direct call to ZodiacApp().
                TheZodiacAppShell()
            }
        }
    }
}