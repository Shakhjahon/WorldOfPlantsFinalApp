package com.example.worldofplantsapp.presentation.author.elections

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.mediconsultfinalapp.ui.theme.dp10
import com.example.mediconsultfinalapp.ui.theme.dp17
import com.example.mediconsultfinalapp.ui.theme.dp2
import com.example.mediconsultfinalapp.ui.theme.dp20
import com.example.mediconsultfinalapp.ui.theme.dp30
import com.example.mediconsultfinalapp.ui.theme.dp50
import com.example.mediconsultfinalapp.ui.theme.dp60
import com.example.mediconsultfinalapp.ui.theme.sp17
import com.example.mediconsultfinalapp.ui.theme.sp21
import com.example.worldofplantsapp.R
import com.example.worldofplantsapp.presentation.main.components.main.component.ScreenFons
import com.example.worldofplantsapp.utils.theme.Green
import com.example.worldofplantsapp.utils.theme.LedgerFont

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ElectionsScreen(
    modifier: Modifier = Modifier,
    onNavigatyLogin: () -> Unit,
    onNavigatyRegister: () -> Unit,
) {
    ScreenFons()
    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(0.5f),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.image),
            contentDescription = null,
        )

        Text(
            text = stringResource(R.string.descriptons),
            fontFamily = LedgerFont,
            textAlign = TextAlign.Center,
            color = Color.White,
            fontSize = sp17,
            modifier = modifier
                .padding(horizontal = dp30)
                .padding(top = dp20),
        )

    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = dp60),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedIconButton(
            onClick = { onNavigatyLogin() },
            border = BorderStroke(dp2, Green),
            shape = RoundedCornerShape(dp17),
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = dp50)
        ) {
            Text(
                text = stringResource(id = R.string.login),
                fontSize = sp21,
                color = Color.White,
                fontFamily = LedgerFont,
            )
        }
        Button(
            onClick = { onNavigatyRegister() },
            shape = RoundedCornerShape(dp17),
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = dp50)
                .padding(top = dp10),
            colors = ButtonDefaults.buttonColors(Green)

        ) {
            Text(
                text = stringResource(id = R.string.register),
                fontSize = sp21,
                color = Color.White,
                fontFamily = LedgerFont
            )
        }
    }
}


@Preview
@Composable
fun ElectionsScreenPreview() {
    MaterialTheme {
        ElectionsScreen(
            onNavigatyLogin = {},
            onNavigatyRegister = {},
        )
    }
}