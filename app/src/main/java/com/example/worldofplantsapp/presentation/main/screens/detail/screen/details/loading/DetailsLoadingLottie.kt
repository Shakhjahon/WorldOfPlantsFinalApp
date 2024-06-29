package com.example.worldofplantsapp.presentation.main.screens.detail.screen.details.loading

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.mediconsultfinalapp.ui.theme.dp250
import com.example.worldofplantsapp.R

@Composable
fun DetailsLoadingLottie(
    modifier: Modifier = Modifier,
) {
    val preloaderLottieComposition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(
            R.raw.lottie_loading
        )
    )

    val preloaderProgress by animateLottieCompositionAsState(
        preloaderLottieComposition, iterations = LottieConstants.IterateForever, isPlaying = true
    )


    LottieAnimation(
        composition = preloaderLottieComposition, progress = preloaderProgress, modifier = modifier
    )
}



@Composable
fun DetailsLoading(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        DetailsLoadingLottie(
            modifier = modifier.size(dp250)
        )
    }
}

