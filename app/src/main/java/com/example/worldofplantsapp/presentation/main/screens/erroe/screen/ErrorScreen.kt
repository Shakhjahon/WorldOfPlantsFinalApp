package com.example.worldofplantsapp.presentation.main.screens.erroe.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.mediconsultfinalapp.ui.theme.dp1
import com.example.mediconsultfinalapp.ui.theme.dp10
import com.example.mediconsultfinalapp.ui.theme.dp100
import com.example.mediconsultfinalapp.ui.theme.dp15
import com.example.mediconsultfinalapp.ui.theme.dp150
import com.example.mediconsultfinalapp.ui.theme.dp170
import com.example.mediconsultfinalapp.ui.theme.dp190
import com.example.mediconsultfinalapp.ui.theme.dp2
import com.example.mediconsultfinalapp.ui.theme.dp200
import com.example.mediconsultfinalapp.ui.theme.dp5
import com.example.mediconsultfinalapp.ui.theme.dp80
import com.example.mediconsultfinalapp.ui.theme.sp14
import com.example.mediconsultfinalapp.ui.theme.sp15
import com.example.mediconsultfinalapp.ui.theme.sp20
import com.example.worldofplantsapp.utils.theme.Green
import com.example.worldofplantsapp.utils.theme.Greens

@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier,
    callback: () -> Unit,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ErrorLottie(
            modifier = modifier.size(dp200)
        )
        Text(
            text = "Невозможно подключиться",
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = dp5)
                .padding(horizontal = dp5),
            text = "Пожалуйста проверьте подключение и повторите \n попытку позже",
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold
        )
        OutlinedButton(
            modifier = modifier
                .width(dp190)
                .padding(top = dp10),
            shape = RoundedCornerShape(dp15),
            onClick = callback,
            border = BorderStroke(dp2, Greens)
        ) {
            Text(text = "Подключено снова")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ErrorPreview() {
    MaterialTheme {
        ErrorScreen {

        }
    }
}