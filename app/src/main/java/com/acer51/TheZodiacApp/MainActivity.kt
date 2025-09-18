package com.acer51.TheZodiacApp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

// FIX: This import statement is what was missing
import com.acer51.TheZodiacApp.ui.theme.TheZodiacAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TheZodiacAppTheme {
                ZodiacApp()
            }
        }
    }
}