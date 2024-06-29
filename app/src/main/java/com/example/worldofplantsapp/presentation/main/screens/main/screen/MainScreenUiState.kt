package com.example.worldofplantsapp.presentation.main.screens.main.screen

import androidx.compose.runtime.Immutable
import com.example.worldofplantsapp.presentation.model.plant.GetItemsUioModel
import com.example.worldofplantsapp.presentation.model.plant.GetPlantUioModel
import kotlinx.collections.immutable.ImmutableList

@Immutable
sealed interface MainScreenUiState {

    @Immutable
    data class Success(
        val berries: ImmutableList<GetPlantUioModel>,
        val flowers: ImmutableList<GetPlantUioModel>,
        val vegetables: ImmutableList<GetPlantUioModel>,
        val homeFlowers: ImmutableList<GetPlantUioModel>,
        val plantItem: ImmutableList<GetItemsUioModel>,
    ) : MainScreenUiState

    data object Loading : MainScreenUiState

    data class Error(
        val throwable: String = String()
    ) : MainScreenUiState
}