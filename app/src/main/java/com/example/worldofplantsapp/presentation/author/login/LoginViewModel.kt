package com.example.worldofplantsapp.presentation.author.login

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.UserSharedPref
import com.example.domain.ResponseStatus
import com.example.domain.models.profile.UserProfileDomainModel
import com.example.domain.usecases.author.login.LoginUseCase
import com.example.worldofplantsapp.presentation.author.login.LoginInteraction.OnEmailChanget
import com.example.worldofplantsapp.presentation.author.login.LoginInteraction.OnLoginButtonClick
import com.example.worldofplantsapp.presentation.author.login.LoginInteraction.OnNavigatyMainScreen
import com.example.worldofplantsapp.presentation.author.login.LoginInteraction.OnUserPasswordChanget
import com.example.worldofplantsapp.presentation.author.nav.graph.MAIN_GRAPH_TAG
import com.example.worldofplantsapp.presentation.graphMain.manager.NavGraphManager
import com.example.worldofplantsapp.utils.util.makeToast
import com.example.worldofplantsapp.utils.util.validateEmail
import com.example.worldofplantsapp.utils.util.validatePassword
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userLoginUseCase: LoginUseCase,
    private val navigationManager: NavGraphManager,
    private val sharedPref: UserSharedPref,
    @ApplicationContext val context: Context
) : ViewModel() {

    private val _userInfoState: MutableStateFlow<LoginInfoState> =
        MutableStateFlow(LoginInfoState())
    val userInfoState: StateFlow<LoginInfoState> = _userInfoState.asStateFlow()

    private val _uiState: MutableStateFlow<LoginUiState> = MutableStateFlow(LoginUiState.Initial)
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun onInteraction(interaction: LoginInteraction) {
        when (interaction) {
            is OnLoginButtonClick -> doUserButtonClick()
            is OnEmailChanget -> doUserNameChange(interaction)
            is OnUserPasswordChanget -> doUserPasswordChange(interaction)
            is OnNavigatyMainScreen -> doNavigatyToMainAppGraph(interaction)
        }
    }

    private fun doNavigatyToMainAppGraph(onNavigatyMainAppNavGraph: OnNavigatyMainScreen) {
        viewModelScope.launch {
            navigationManager.navigateTo(MAIN_GRAPH_TAG, isBackStackClear = true)
            sharedPref.saveCurrentUser(onNavigatyMainAppNavGraph.objectId)
            sharedPref.saveIsUserSigned()
        }
    }

    private fun doUserButtonClick(): Unit = with(_userInfoState.value) {
        val (validateEmail) = validateEmail(email)
        val (validatePassword) = validatePassword(userPassword)
        if (validateEmail && validatePassword) {
            _uiState.tryEmit(LoginUiState.Loading)
            viewModelScope.launch {
                val request = userLoginUseCase(
                    userPassword = _userInfoState.value.userPassword,
                    userName = _userInfoState.value.email
                )
                if (request.status == ResponseStatus.SUCCESS) {
                    makeToast("Вы успешно Вошли в аккаунт", context)
                    doNavigatyToMainAppGraph(
                        OnNavigatyMainScreen(
                            request.data ?: UserProfileDomainModel.unknown
                        )
                    )
                } else {
                    _uiState.tryEmit(LoginUiState.Initial)
                    makeToast("Email или Пароль введены неверно", context)
                }
            }
        } else {
            makeToast("Email или Пароль введены неверно", context)
        }
    }

    private fun doUserNameChange(interaction: OnEmailChanget) {
        _userInfoState.update { newState ->
            newState.copy(
                email = interaction.username
            )
        }
    }

    private fun doUserPasswordChange(interaction: OnUserPasswordChanget) {
        _userInfoState.update { newState ->
            newState.copy(
                userPassword = interaction.password
            )
        }
    }
}