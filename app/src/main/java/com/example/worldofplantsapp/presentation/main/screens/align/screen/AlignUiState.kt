package com.example.worldofplantsapp.presentation.main.screens.align.screen

import androidx.compose.runtime.Immutable
import com.example.worldofplantsapp.presentation.model.plant.GetPlantUioModel
import kotlinx.collections.immutable.ImmutableList

sealed interface AlignUiState {

    @Immutable
    data class Success(
        val berries: ImmutableList<GetPlantUioModel>,
        val flowers: ImmutableList<GetPlantUioModel>,
        val vegetables: ImmutableList<GetPlantUioModel>,
        val homeFlowers: ImmutableList<GetPlantUioModel>,
        val weeds: ImmutableList<GetPlantUioModel>,
        val fruit: ImmutableList<GetPlantUioModel>,
        val trees: ImmutableList<GetPlantUioModel>,
    ) : AlignUiState

    data object Loading : AlignUiState

    data class Error(
        val throwable: String
    ) : AlignUiState
}