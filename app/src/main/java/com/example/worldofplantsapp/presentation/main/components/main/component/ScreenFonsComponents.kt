package com.example.worldofplantsapp.presentation.main.components.main.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.worldofplantsapp.R
import com.example.worldofplantsapp.utils.theme.Black

@Composable
fun ScreenFons(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.on_screen_fon),
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(Black)
        )
    }
}