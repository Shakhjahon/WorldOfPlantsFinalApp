package com.example.worldofplantsapp.presentation.main.screens.on.boarding

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.mediconsultfinalapp.ui.theme.dp100
import com.example.mediconsultfinalapp.ui.theme.dp16
import com.example.mediconsultfinalapp.ui.theme.dp80
import com.example.mediconsultfinalapp.ui.theme.sp18
import com.example.mediconsultfinalapp.ui.theme.sp30
import com.example.worldofplantsapp.presentation.main.components.main.component.ScreenFons
import com.example.worldofplantsapp.utils.theme.Green
import com.example.worldofplantsapp.utils.theme.LedgerFont

@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
fun OnBoardingScreen(
    modifier: Modifier = Modifier,
    onNavigatyAuthor: () -> Unit,
) {
    Scaffold(
        topBar = {
            ScreenFons()
            Column(
                modifier = modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "World Of Plants",
                    fontSize = sp30,
                    fontFamily = FontFamily.Cursive,
                    color = Color.White
                )
            }
        },
        bottomBar = {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(bottom = dp100),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = dp80),
                    onClick = { onNavigatyAuthor() },
                    shape = RoundedCornerShape(dp16),
                    colors = ButtonDefaults.buttonColors(Green)
                ) {
                    Text(
                        text = "Начать",
                        fontSize = sp18,
                        fontFamily = LedgerFont,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,

                        )
                }
            }
        },
    ) {}
}

@Preview
@Composable
fun OnBoardingScreenPreview() {
    MaterialTheme {
        OnBoardingScreen(onNavigatyAuthor = {})
    }
}