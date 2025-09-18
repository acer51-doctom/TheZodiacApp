package com.acer51.TheZodiacApp

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.drawCircle
import androidx.compose.material3.MaterialTheme

@Composable
fun AnimatedBackground() {
    val infiniteTransition = rememberInfiniteTransition(label = "stars")

    val starAlpha by infiniteTransition.animateFloat(
        initialValue = 0.3f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(3000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "starAlpha"
    )

    Canvas(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)) {

        val colors = listOf(Color.White, Color.LightGray, Color.Cyan)
        for (i in 1..40) {
            drawCircle(
                color = colors.random().copy(alpha = starAlpha),
                radius = (2..5).random().toFloat(),
                center = androidx.compose.ui.geometry.Offset(
                    (0..size.width.toInt()).random().toFloat(),
                    (0..size.height.toInt()).random().toFloat()
                )
            )
        }
    }
}
