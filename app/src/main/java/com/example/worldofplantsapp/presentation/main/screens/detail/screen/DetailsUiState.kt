package com.example.worldofplantsapp.presentation.main.screens.detail.screen

import androidx.compose.runtime.Immutable
import com.example.domain.models.plant.GetPlantDomainModel
import kotlinx.collections.immutable.ImmutableList

@Immutable
sealed interface DetailsUiState {

    @Immutable
    data class Success(
        val model: ImmutableList<GetPlantDomainModel>,
        val isSaved: Boolean = false
    ) : DetailsUiState

    data object Loading : DetailsUiState

    data class Error(
        val throwable: String
    ) : DetailsUiState
}