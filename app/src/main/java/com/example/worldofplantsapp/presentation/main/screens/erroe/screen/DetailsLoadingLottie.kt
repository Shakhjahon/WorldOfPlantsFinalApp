package com.example.worldofplantsapp.presentation.main.screens.erroe.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.worldofplantsapp.R

@Composable
fun ErrorLottie(
    modifier: Modifier = Modifier,
) {
    val preloaderLottieComposition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(
            R.raw.error_lottie
        )
    )

    val preloaderProgress by animateLottieCompositionAsState(
        preloaderLottieComposition, iterations = LottieConstants.IterateForever, isPlaying = true
    )


    LottieAnimation(
        composition = preloaderLottieComposition, progress = preloaderProgress, modifier = modifier
    )
}
