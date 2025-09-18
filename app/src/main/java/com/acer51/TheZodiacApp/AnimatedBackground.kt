package com.acer51.TheZodiacApp

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import kotlin.random.Random

@Composable
fun AnimatedBackground() {
    val infiniteTransition = rememberInfiniteTransition(label = "stars")

    // Animate star opacity
    val starAlpha by infiniteTransition.animateFloat(
        initialValue = 0.3f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(3000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "starAlpha"
    )

    // Choose star colors based on theme
    val starColors = if (MaterialTheme.colorScheme.isLight) {
        listOf(Color.Black, Color.DarkGray, Color.Gray)
    } else {
        listOf(Color.White, Color.LightGray, Color.Cyan)
    }

    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        for (i in 1..50) { // Draw 50 stars
            drawCircle(
                color = starColors.random().copy(alpha = starAlpha),
                radius = Random.nextInt(1, 4).toFloat(), // Random star size
                center = Offset(
                    Random.nextFloat() * size.width,
                    Random.nextFloat() * size.height
                )
            )
        }
    }
}
