package com.example.worldofplantsapp.presentation.main.screens.profile.screen

import androidx.compose.runtime.Immutable
import com.example.worldofplantsapp.presentation.model.profile.UserProfileUioModel

@Immutable
sealed interface ProfileUiState {

    @Immutable
    data class Loaded(
        val profile: UserProfileUioModel,
    ) : ProfileUiState

    data object Loading : ProfileUiState

    data class Error(
        val throwable: Throwable,
    ) : ProfileUiState
}