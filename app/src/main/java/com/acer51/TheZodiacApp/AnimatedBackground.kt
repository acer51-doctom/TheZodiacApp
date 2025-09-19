package com.acer51.TheZodiacApp

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
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
    val starAlpha by infiniteTransition.animateFloat(
        initialValue = 0.3f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(8000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "starAlpha"
    )

    val starColors = if (isSystemInDarkTheme()) {
        listOf(Color.White, Color.LightGray, Color.Cyan)
    } else {
        listOf(Color.Black, Color.DarkGray, Color.Gray)
    }

    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        repeat(50) {
            drawCircle(
                color = starColors.random().copy(alpha = starAlpha),
                radius = Random.nextInt(1, 4).toFloat(),
                center = Offset(
                    Random.nextFloat() * size.width,
                    Random.nextFloat() * size.height
                )
            )
        }
    }
}