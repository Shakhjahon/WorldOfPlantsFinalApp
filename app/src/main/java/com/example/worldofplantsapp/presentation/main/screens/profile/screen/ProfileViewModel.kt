package com.example.worldofplantsapp.presentation.main.screens.profile.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.UserSharedPref
import com.example.domain.usecases.local.delete.ClearTableUseCase
import com.example.worldofplantsapp.presentation.author.nav.graph.AUTHOR_GRAPH_TAG
import com.example.worldofplantsapp.presentation.graphMain.manager.NavGraphManager
import com.example.worldofplantsapp.presentation.mappers.toUio
import com.example.worldofplantsapp.presentation.model.profile.UserProfileUioModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userSharedPref: UserSharedPref,
    private val navGraphManager: NavGraphManager,
    private val clearTableUseCase: ClearTableUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<ProfileUiState> =
        MutableStateFlow(ProfileUiState.Loading)
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    fun onEvent(interaction: ProfileInteraction) {
        when (interaction) {
            ProfileInteraction.OnLogoutFromAccount -> doLogoutFromAccount()
        }
    }

    private fun doLogoutFromAccount() {
        viewModelScope.launch {
            userSharedPref.clear()
            clearTableUseCase.clearTable()
            navGraphManager.navigateTo(AUTHOR_GRAPH_TAG, isBackStackClear = true)
        }
    }

    init {
        viewModelScope.launch {
            val currentUser = userSharedPref.getCurrentUser()
            _uiState.tryEmit(
                ProfileUiState.Loaded(
                    currentUser?.toUio() ?: UserProfileUioModel.unknown
                )
            )
        }
    }
}
