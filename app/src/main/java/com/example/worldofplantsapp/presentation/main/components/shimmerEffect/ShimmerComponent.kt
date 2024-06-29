package com.example.worldofplantsapp.presentation.main.components.shimmerEffect

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.example.worldofplantsapp.presentation.main.screens.main.screen.main.shimmer.MainScreenShimmer
import com.example.worldofplantsapp.utils.view.model.ThemeViewModel

@Composable
fun ShimmerComponent(
    modifier: Modifier = Modifier,
    themeViewModel: ThemeViewModel,
) {
    Column(
        modifier = modifier
    ) {
        AnimatedShimmer(
            themeViewModel = themeViewModel
        )
    }
}

@Stable
@Composable
fun AnimatedShimmer(themeViewModel: ThemeViewModel) {
    val shimmerColors = listOf(
        Color.Gray.copy(alpha = 0.6f),
        Color.Gray.copy(alpha = 0.2f),
        Color.Gray.copy(alpha = 0.6f),
    )

    val transition = rememberInfiniteTransition(label = "")
    val translateAnim by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 900,
                easing = FastOutLinearInEasing
            ), repeatMode = RepeatMode.Reverse
        ), label = "Shimmer"
    )

    val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset.Zero,
        end = Offset(x = translateAnim, y = translateAnim)
    )

    MainScreenShimmer(brush = brush, themeViewModel = themeViewModel)
}