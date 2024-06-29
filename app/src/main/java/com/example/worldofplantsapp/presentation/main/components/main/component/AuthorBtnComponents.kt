package com.example.worldofplantsapp.presentation.main.components.main.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mediconsultfinalapp.ui.theme.dp20
import com.example.mediconsultfinalapp.ui.theme.dp30
import com.example.mediconsultfinalapp.ui.theme.sp21
import com.example.worldofplantsapp.utils.theme.Green
import com.example.worldofplantsapp.utils.theme.LedgerFont

@Composable
fun AuthorBtnComponents(
    modifier: Modifier = Modifier,
    textBtn: String,
    onClick: () -> Unit,
    isProgressShowLoading: Boolean
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(horizontal = dp20),
        shape = RoundedCornerShape(dp20),
        colors = ButtonDefaults.buttonColors(Green)
    ) {
        if (isProgressShowLoading) {
            CircularProgressIndicator(
                modifier = modifier.size(dp30),
                color = Color.White,
                trackColor = Green,
                strokeWidth = 2.dp,
            )
        } else {
            Text(
                text = textBtn,
                fontSize = sp21,
                fontFamily = LedgerFont,
                color = Color.White,
            )
        }
    }
}


@Preview
@Composable
fun AuthorBtnComponentsPreview() {
    MaterialTheme {
        AuthorBtnComponents(
            textBtn = "",
            onClick = {},
            isProgressShowLoading = false,
        )
    }
}