package com.example.worldofplantsapp.presentation.main.screens.details.advice

import androidx.compose.runtime.Immutable
import com.example.worldofplantsapp.presentation.model.plant.GetPlantUioModel
import kotlinx.collections.immutable.ImmutableList

@Immutable
sealed interface DetailsAdviceUiState {

    @Immutable
    data class Success(
        val model: ImmutableList<GetPlantUioModel>,
    ) : DetailsAdviceUiState

    data object Loading : DetailsAdviceUiState

    data class Error(
        val throwable: String
    ) : DetailsAdviceUiState
}