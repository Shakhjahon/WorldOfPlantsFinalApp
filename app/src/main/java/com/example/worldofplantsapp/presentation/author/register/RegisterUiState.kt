package com.example.worldofplantsapp.presentation.author.register

import com.example.domain.models.profile.UserProfileDomainModel

sealed interface RegisterUiState {

    data object Loading : RegisterUiState

    data object Initial : RegisterUiState

    data class Success(val objectId: UserProfileDomainModel) : RegisterUiState

    data class Error(val throwable: Throwable) : RegisterUiState

}