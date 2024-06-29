package com.example.worldofplantsapp.presentation.main.screens.splash.screen

import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.TwoWayConverter
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateValue
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import com.example.mediconsultfinalapp.ui.theme.sp30
import com.example.worldofplantsapp.presentation.main.components.main.component.ScreenFons

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
) {

    var startAnimation by remember { mutableStateOf(false) }
    val alphaAnim = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f, animationSpec = tween(
            durationMillis = 3000
        ), label = ""
    )

    Box(modifier = modifier) {
        ScreenFons()
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {
            AnimatedTextComponent(
                text = "World Of Plants",
            )
        }
    }
}

@Composable
fun AnimatedTextComponent(
    modifier: Modifier = Modifier,
    text: String,
    durationMillis: Int = 2800,
    easing: Easing = LinearEasing,
) {
    val transition = rememberInfiniteTransition(label = "")
    val textAnim by transition.animateValue(
        initialValue = "", targetValue = text, animationSpec = infiniteRepeatable(
            animation = tween(durationMillis, easing = easing), repeatMode = RepeatMode.Restart
        ), typeConverter = TwoWayConverter(
            convertToVector = { AnimationVector1D(it.length.toFloat()) },
            convertFromVector = { vector -> text.take(vector.value.toInt()) },
        ), label = ""
    )
    Text(
        text = textAnim,
        fontSize = sp30,
        fontFamily = FontFamily.Cursive,
        color = Color.White,
    )
}

@Preview
@Composable
fun SplashScreenPreview() {
    MaterialTheme {
        SplashScreen()
    }
}