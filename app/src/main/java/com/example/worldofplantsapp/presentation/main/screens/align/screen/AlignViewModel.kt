package com.example.worldofplantsapp.presentation.main.screens.align.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.ResponseStatus
import com.example.domain.usecases.plant.FetchPlantUseCase
import com.example.worldofplantsapp.presentation.mappers.toUio
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private typealias MutableDestination = AlignUiState

@HiltViewModel
class AlignViewModel @Inject constructor(
    private val fetchPlantUseCase: FetchPlantUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<MutableDestination>(AlignUiState.Loading)
    val uiState: StateFlow<MutableDestination> = _uiState.asStateFlow()

    init {
        advicePlants()
    }

    fun advicePlants() {
        viewModelScope.launch {
            val responce = fetchPlantUseCase.invoke()
            if (responce.status == ResponseStatus.SUCCESS) {
                responce.data?.let { data ->

                    val plaintBerries = data.filter { it.category == "berries" }.map { it.toUio() }
                        .toImmutableList()

                    val plaintFlowers = data.filter { it.category == "flowers" }.map { it.toUio() }
                        .toImmutableList()

                    val plaintVegetables =
                        data.filter { it.category == "vegetables" }.map { it.toUio() }
                            .toImmutableList()

                    val trees =
                        data.filter { it.category == "trees" }.map { it.toUio() }.toImmutableList()
                    val fruit =
                        data.filter { it.category == "fruit" }.map { it.toUio() }.toImmutableList()
                    val weeds =
                        data.filter { it.category == "fruit" }.map { it.toUio() }.toImmutableList()
                    val homeFlowers =
                        data.filter { it.category == "home_flowers" }.map { it.toUio() }
                            .toImmutableList()

                    _uiState.tryEmit(
                        AlignUiState.Success(
                            berries = plaintBerries.toImmutableList(),
                            vegetables = plaintVegetables.toImmutableList(),
                            flowers = plaintFlowers.toImmutableList(),
                            homeFlowers = homeFlowers.toImmutableList(),
                            trees = trees.toImmutableList(),
                            fruit = fruit.toImmutableList(),
                            weeds = weeds.toImmutableList(),
                        )
                    )
                }
            } else {
                _uiState.tryEmit(AlignUiState.Error(throwable = "Error"))
            }
        }
    }
}