package com.example.worldofplantsapp.presentation.author.login

import com.example.domain.models.profile.UserProfileDomainModel

sealed interface LoginUiState {

    data object Loading : LoginUiState

    data object Initial : LoginUiState

    data class Success(val objectId: UserProfileDomainModel) : LoginUiState


}