package com.example.worldofplantsapp.presentation.author.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mediconsultfinalapp.ui.theme.dp10
import com.example.mediconsultfinalapp.ui.theme.dp15
import com.example.mediconsultfinalapp.ui.theme.dp20
import com.example.mediconsultfinalapp.ui.theme.dp25
import com.example.mediconsultfinalapp.ui.theme.dp36
import com.example.mediconsultfinalapp.ui.theme.sp23
import com.example.worldofplantsapp.R
import com.example.worldofplantsapp.presentation.main.components.main.component.AuthorBtnComponents
import com.example.worldofplantsapp.presentation.main.components.main.component.OutlinePasswordTextComponent
import com.example.worldofplantsapp.presentation.main.components.main.component.OutlineTextFiledEmailComponents
import com.example.worldofplantsapp.presentation.main.components.main.component.ScreenFons
import com.example.worldofplantsapp.utils.theme.Green
import com.example.worldofplantsapp.utils.theme.LedgerFont
import com.example.worldofplantsapp.utils.util.isValidEmail
import com.example.worldofplantsapp.utils.util.isValidatePassword
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun LoginMainScreen(
    modifier: Modifier = Modifier,
    uiStateFlow: StateFlow<LoginUiState>,
    userInfoState: StateFlow<LoginInfoState>,
    onInteraction: (LoginInteraction) -> Unit,
    onNavigatyRegister: () -> Unit,
) {
    when (val uiState = uiStateFlow.collectAsState().value) {
        is LoginUiState.Initial -> {
            LoginScreen(
                onNavigatyRegister = onNavigatyRegister,
                uiStateFlow = userInfoState,
                onInteraction = onInteraction,
                isProgressShowLoading = false
            )
        }

        is LoginUiState.Loading -> {
            LoginScreen(
                onNavigatyRegister = onNavigatyRegister,
                uiStateFlow = userInfoState,
                onInteraction = onInteraction,
                isProgressShowLoading = true
            )
        }

        is LoginUiState.Success -> {
            onInteraction(LoginInteraction.OnNavigatyMainScreen(uiState.objectId))
        }
    }
}

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    onNavigatyRegister: () -> Unit,
    isProgressShowLoading: Boolean,
    uiStateFlow: StateFlow<LoginInfoState>,
    onInteraction: (LoginInteraction) -> Unit,
) {
    val uiState: LoginInfoState = uiStateFlow.collectAsState().value
    ScreenFons()
    Column {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight(0.3f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.image),
                contentDescription = null,
            )
            Text(
                modifier = modifier.padding(top = dp20),
                text = stringResource(R.string.welcome_to_back),
                fontFamily = LedgerFont,
                color = Color.White,
                fontSize = sp23
            )
        }
        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            OutlineTextFiledEmailComponents(
                text = stringResource(R.string.email),
                value = uiState.email,
                onValueChange = { onInteraction(LoginInteraction.OnEmailChanget(it)) },
                label = stringResource(R.string.email),
                error = stringResource(R.string.invalid_email),
                isError = uiState.email.isNotEmpty() && !isValidEmail(uiState.email)
            )
            OutlinePasswordTextComponent(
                value = uiState.userPassword,
                onValueChange = { onInteraction(LoginInteraction.OnUserPasswordChanget(it)) },
                text = stringResource(R.string.password),
                modifier = modifier.padding(top = dp20),
                label = stringResource(R.string.password),
                error = stringResource(R.string.invalid_password),
                isError = uiState.userPassword.isNotEmpty() && !isValidatePassword(uiState.userPassword)
            )
            Spacer(modifier = modifier.height(dp20))
            AuthorBtnComponents(
                textBtn = stringResource(R.string.login),
                onClick = { onInteraction(LoginInteraction.OnLoginButtonClick) },
                isProgressShowLoading = isProgressShowLoading
            )
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = dp25),
                horizontalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = stringResource(R.string.not_accaunt),
                    fontSize = 17.sp,
                    color = Color.White,
                    modifier = modifier.padding(top = 6.dp)
                )
                Button(
                    onClick = { onNavigatyRegister() },
                    modifier = modifier
                        .height(dp36)
                        .padding(start = dp10),
                    shape = RoundedCornerShape(dp15),
                    colors = ButtonDefaults.buttonColors(Green)
                ) {
                    Text(
                        text = stringResource(R.string.register),
                        fontSize = 17.sp,
                        color = Color.White,
                        fontFamily = LedgerFont,
                        fontWeight = FontWeight.Normal

                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun LoginScreenPreview() {
    MaterialTheme {
        LoginScreen(
            onNavigatyRegister = {},
            onInteraction = {},
            uiStateFlow = MutableStateFlow(LoginInfoState()),
            isProgressShowLoading = false
        )
    }
}