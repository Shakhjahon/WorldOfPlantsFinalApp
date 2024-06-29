package com.example.worldofplantsapp.presentation.main.screens.search.screen

import com.example.domain.models.plant.GetPlantDomainModel


data class SearchUiState(
    val query: String = "",
    val fauna: List<GetPlantDomainModel>?,
    val isLoading: Boolean = true
)

