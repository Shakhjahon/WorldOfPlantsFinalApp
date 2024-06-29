package com.example.worldofplantsapp.presentation.main.screens.category.screen

import androidx.compose.runtime.Immutable
import com.example.worldofplantsapp.presentation.model.plant.GetPlantUioModel
import kotlinx.collections.immutable.ImmutableList

sealed interface CategoryUiState {

    @Immutable
    data class Success(
        val plant: ImmutableList<GetPlantUioModel>,
    ) : CategoryUiState

    data object Loading : CategoryUiState

    data class Error(
        val throwable: String
    ) : CategoryUiState
}