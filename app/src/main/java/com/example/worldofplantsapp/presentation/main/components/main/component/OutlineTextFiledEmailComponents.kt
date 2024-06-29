package com.example.worldofplantsapp.presentation.main.components.main.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import com.example.mediconsultfinalapp.ui.theme.dp20
import com.example.mediconsultfinalapp.ui.theme.dp25
import com.example.mediconsultfinalapp.ui.theme.sp17
import com.example.worldofplantsapp.utils.theme.Green
import com.example.worldofplantsapp.utils.theme.Red
import com.example.worldofplantsapp.utils.theme.Reds

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutlineTextFiledEmailComponents(
    modifier: Modifier = Modifier,
    text: String,
    value: String,
    onValueChange: (String) -> Unit,
    isError: Boolean,
    label: String,
    error: String,
) {
    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = dp20),
        value = value,
        onValueChange = onValueChange,
        shape = RoundedCornerShape(dp25),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        maxLines = 1,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Green,
            unfocusedBorderColor = Green,
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            errorTextColor = Red,
            errorBorderColor = Red
        ),
        textStyle = TextStyle(
            fontWeight = FontWeight.Medium, fontSize = sp17
        ),
        singleLine = true,
        label = {
            if (!isError) {
                Text(
                    text = label,
                    fontWeight = FontWeight.Normal,
                    color = Color.White,
                )
            } else {
                Text(
                    text = error,
                    fontWeight = FontWeight.Normal,
                    color = Red,
                )
            }
        },
        isError = isError
    )
}