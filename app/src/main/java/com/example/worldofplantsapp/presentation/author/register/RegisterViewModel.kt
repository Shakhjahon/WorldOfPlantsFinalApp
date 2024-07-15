package com.example.worldofplantsapp.presentation.author.register

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.UserSharedPref
import com.example.domain.ResponseStatus
import com.example.domain.models.profile.UserProfileDomainModel
import com.example.domain.models.profile.UserSignDomainModel
import com.example.domain.usecases.author.register.RegisterUseCase
import com.example.worldofplantsapp.presentation.author.nav.graph.MAIN_GRAPH_TAG
import com.example.worldofplantsapp.presentation.graphMain.manager.NavGraphManager
import com.example.worldofplantsapp.utils.util.makeToast
import com.example.worldofplantsapp.utils.util.validateEmail
import com.example.worldofplantsapp.utils.util.validateName
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
class RegisterViewModel @Inject constructor(
    private val userRegisterUseCase: RegisterUseCase,
    private val navigationManager: NavGraphManager,
    private val sharedPref: UserSharedPref,
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val _uiState: MutableStateFlow<RegisterUiState> =
        MutableStateFlow(RegisterUiState.Initial)
    val uiState: StateFlow<RegisterUiState> = _uiState.asStateFlow()

    private val _infoState: MutableStateFlow<RegisterInfoState> =
        MutableStateFlow(RegisterInfoState())
    val infoState: StateFlow<RegisterInfoState> = _infoState.asStateFlow()

    private val _navigateUpFlow: MutableStateFlow<Unit> = MutableStateFlow(Unit)

    fun onInteraction(interaction: RegisterInteraction) {
        when (interaction) {
            is RegisterInteraction.OnNavigateUp -> _navigateUpFlow.tryEmit(Unit)
            is RegisterInteraction.OnUserEmailChanget -> onUpDateEmail(interaction)
            is RegisterInteraction.OnUserNameChanget -> onUpFirstName(interaction)
            is RegisterInteraction.OnUserLastNameChanget -> onUpDateLastName(interaction)
            is RegisterInteraction.OnUserPasswordChanget -> onUpDatePassword(interaction)
            is RegisterInteraction.OnRegisterButtonClick -> doClickOnRegisterButton()
            is RegisterInteraction.OnNavigatyMainScreen -> doNavigatyToMainAppGraph(interaction)
        }
    }

    private fun doNavigatyToMainAppGraph(onNavigatyMainAppNavGraph: RegisterInteraction.OnNavigatyMainScreen) {
        viewModelScope.launch {
            navigationManager.navigateTo(MAIN_GRAPH_TAG, isBackStackClear = true)
            sharedPref.saveCurrentUser(onNavigatyMainAppNavGraph.objectId)
            sharedPref.saveIsUserSigned()
        }
    }

    private fun doClickOnRegisterButton(): Unit = with(infoState.value) {
        val (validateName) = validateName(userName)
        val (validateLastName) = validateName(lastName)
        val (validateEmail) = validateEmail(email)
        val (validatePassword) = validatePassword(userPassword)
        if (validateName && validateLastName && validateEmail && validatePassword) {
            _uiState.tryEmit(RegisterUiState.Loading)
            viewModelScope.launch {
                val request = userRegisterUseCase(
                    with(infoState.value) {
                        UserSignDomainModel(
                            username = userName,
                            lastname = lastName,
                            email = email,
                            password = userPassword,
                        )
                    },
                )
                if (request.status == ResponseStatus.SUCCESS) {
                    makeToast("Вы успешно зарегистрировались", context)
                    doNavigatyToMainAppGraph(
                        RegisterInteraction.OnNavigatyMainScreen(
                            objectId = request.data ?: UserProfileDomainModel.unknown
                        )
                    )
                } else {
                    _uiState.tryEmit(RegisterUiState.Initial)
                    makeToast("Уже занята", context)
                }
            }
        } else {
            makeToast("Не Правильный Формат", context)
        }
    }

    private fun onUpFirstName(interaction: RegisterInteraction.OnUserNameChanget) {
        _infoState.update { newState ->
            newState.copy(
                userName = interaction.userFirstName
            )
        }
    }

    private fun onUpDateLastName(interaction: RegisterInteraction.OnUserLastNameChanget) {
        _infoState.update { newState ->
            newState.copy(
                lastName = interaction.userLastName
            )
        }
    }

    private fun onUpDateEmail(interaction: RegisterInteraction.OnUserEmailChanget) {
        _infoState.update { newState ->
            newState.copy(
                email = interaction.userEmail
            )
        }
    }

    private fun onUpDatePassword(interaction: RegisterInteraction.OnUserPasswordChanget) {
        _infoState.update { newState ->
            newState.copy(
                userPassword = interaction.userPassword
            )
        }
    }
}