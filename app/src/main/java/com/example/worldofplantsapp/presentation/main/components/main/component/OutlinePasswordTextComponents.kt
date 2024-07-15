package com.example.worldofplantsapp.presentation.main.components.main.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.example.mediconsultfinalapp.ui.theme.dp20
import com.example.mediconsultfinalapp.ui.theme.dp25
import com.example.mediconsultfinalapp.ui.theme.sp17
import com.example.worldofplantsapp.R
import com.example.worldofplantsapp.utils.theme.Green
import com.example.worldofplantsapp.utils.theme.Red

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutlinePasswordTextComponent(
    modifier: Modifier = Modifier,
    text: String,
    value: String,
    onValueChange: (String) -> Unit,
    isError: Boolean,
    label: String,
    error: String
) {

    var passwordVisiblety by remember { mutableStateOf(false) }

    val icon = if (passwordVisiblety) painterResource(id = R.drawable.eye_icon)
    else painterResource(id = R.drawable.eye_icon_visiblety)

    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = dp20),
        value = value,
        onValueChange = onValueChange,
        shape = RoundedCornerShape(dp25),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Green,
            unfocusedBorderColor = Green,
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            errorTextColor = Red,
            errorBorderColor = Red,
        ),
        maxLines = 1,
        textStyle = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = sp17,
        ),
        singleLine = true,
        label = {
            if (!isError) {
                Text(
                    text = label,
                    color = Color.White,
                )
            } else {
                Text(
                    text = error,
                    color = Red,
                )
            }
        },
        isError = isError,
        trailingIcon = {
            IconButton(onClick = { passwordVisiblety = !passwordVisiblety }) {
                Icon(
                    painter = icon,
                    contentDescription = null,
                    tint = Color.White,
                )
            }
        },
        visualTransformation = if (passwordVisiblety) VisualTransformation.None
        else PasswordVisualTransformation()
    )
}

@Preview
@Composable
fun OutlinePasswordTextComponentPreview() {
    MaterialTheme {
        OutlinePasswordTextComponent(
            text = "", value = "", onValueChange = {}, isError = false, label = "", error = ""
        )
    }
}