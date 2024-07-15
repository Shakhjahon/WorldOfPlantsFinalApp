package com.example.worldofplantsapp.presentation.main.screens.plant.screen

import androidx.compose.runtime.Immutable
import com.example.domain.models.plant.GetPlantDomainModel

sealed interface PlantUiState {

    @Immutable
    data class Success(
        val model: List<GetPlantDomainModel>
    ) : PlantUiState

    data object Loading : PlantUiState

    @Immutable
    data class Error(
        val throwable: String
    ) : PlantUiState
}