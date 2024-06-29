package com.example.worldofplantsapp.presentation.author.register

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
import com.example.mediconsultfinalapp.ui.theme.sp25
import com.example.worldofplantsapp.R
import com.example.worldofplantsapp.presentation.main.components.main.component.AuthorBtnComponents
import com.example.worldofplantsapp.presentation.main.components.main.component.OutlinePasswordTextComponent
import com.example.worldofplantsapp.presentation.main.components.main.component.OutlineTextFiledComponents
import com.example.worldofplantsapp.presentation.main.components.main.component.OutlineTextFiledEmailComponents
import com.example.worldofplantsapp.presentation.main.components.main.component.ScreenFons
import com.example.worldofplantsapp.utils.theme.Green
import com.example.worldofplantsapp.utils.theme.LedgerFont
import com.example.worldofplantsapp.utils.util.isValidEmail
import com.example.worldofplantsapp.utils.util.isValidateName
import com.example.worldofplantsapp.utils.util.isValidatePassword
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun RegisterMainScreen(
    modifier: Modifier = Modifier,
    uiStateFlow: StateFlow<RegisterUiState>,
    registerUserInfoState: StateFlow<RegisterInfoState>,
    onInteraction: (RegisterInteraction) -> Unit,
    onNavigatyLogin: () -> Unit,
) {
    when (val uiState = uiStateFlow.collectAsState().value) {
        is RegisterUiState.Error -> Unit
        RegisterUiState.Initial -> {
            RegisterScreen(
                onNavigatyLogin = onNavigatyLogin,
                onInteraction = onInteraction,
                uiStateFlow = registerUserInfoState,
                isProgressShowLoading = false
            )
        }

        RegisterUiState.Loading -> {
            RegisterScreen(
                onNavigatyLogin = onNavigatyLogin,
                onInteraction = onInteraction,
                uiStateFlow = registerUserInfoState,
                isProgressShowLoading = true
            )
        }

        is RegisterUiState.Success -> {
            onInteraction(RegisterInteraction.OnNavigatyMainScreen(uiState.objectId))
        }

    }

}

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    onNavigatyLogin: () -> Unit,
    uiStateFlow: StateFlow<RegisterInfoState>,
    onInteraction: (RegisterInteraction) -> Unit,
    isProgressShowLoading: Boolean

) {
    val uiState: RegisterInfoState = uiStateFlow.collectAsState().value

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
                text = stringResource(R.string.accaunt),
                fontFamily = LedgerFont,
                color = Color.White,
                fontSize = sp25
            )
        }
        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            OutlineTextFiledComponents(
                text = stringResource(R.string.first_name),
                value = uiState.userName,
                onValueChange = { onInteraction(RegisterInteraction.OnUserNameChanget(it)) },
                label = stringResource(R.string.first_name),
                error = stringResource(R.string.invalid_first_name),
                isError = uiState.userName.isNotEmpty() && !isValidateName(uiState.userName)
            )
            OutlineTextFiledComponents(
                text = stringResource(R.string.lastname),
                value = uiState.lastName,
                modifier = modifier.padding(top = dp20),
                onValueChange = {
                    onInteraction(RegisterInteraction.OnUserLastNameChanget(it))
                },
                label = stringResource(R.string.lastname),
                error = stringResource(R.string.inavlid_last_name),
                isError = uiState.lastName.isNotEmpty() && !isValidateName(uiState.lastName)
            )
            OutlineTextFiledEmailComponents(
                text = "Email",
                value = uiState.email,
                modifier = modifier.padding(top = dp20),
                onValueChange = { onInteraction(RegisterInteraction.OnUserEmailChanget(it)) },
                label = stringResource(R.string.email),
                error = stringResource(id = R.string.invalid_email),
                isError = uiState.email.isNotEmpty() && !isValidEmail(uiState.email),
            )
            OutlinePasswordTextComponent(
                text = stringResource(R.string.password),
                value = uiState.userPassword,
                modifier = modifier.padding(top = dp20),
                onValueChange = { onInteraction(RegisterInteraction.OnUserPasswordChanget(it)) },
                label = stringResource(id = R.string.password),
                error = stringResource(id = R.string.invalid_password),
                isError = uiState.userPassword.isNotEmpty() && !isValidatePassword(uiState.userPassword)
            )

            Spacer(modifier = modifier.height(dp20))
            AuthorBtnComponents(textBtn = stringResource(id = R.string.register),
                isProgressShowLoading = isProgressShowLoading,
                onClick = { onInteraction(RegisterInteraction.OnRegisterButtonClick) })
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = dp25),
                horizontalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = stringResource(R.string.your_yes_accaunt),
                    fontSize = 17.sp,
                    color = Color.White,
                    modifier = modifier.padding(top = 6.dp)
                )
                Button(
                    onClick = { onNavigatyLogin() },
                    modifier = modifier
                        .height(dp36)
                        .padding(start = dp10),
                    shape = RoundedCornerShape(dp15),
                    colors = ButtonDefaults.buttonColors(Green)
                ) {
                    Text(
                        text = stringResource(id = R.string.login),
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
fun RegisterScreenPreview() {
    MaterialTheme {
        RegisterScreen(onNavigatyLogin = {},
            uiStateFlow = MutableStateFlow(RegisterInfoState()),
            onInteraction = {},
            isProgressShowLoading = false
        )
    }
}