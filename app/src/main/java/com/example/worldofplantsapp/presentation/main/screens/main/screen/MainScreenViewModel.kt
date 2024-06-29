package com.example.worldofplantsapp.presentation.main.screens.main.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.ResponseStatus
import com.example.domain.usecases.plant.FetchPlantItemsUseCase
import com.example.domain.usecases.plant.FetchPlantUseCase
import com.example.worldofplantsapp.presentation.mappers.toUio
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private typealias MutableDestination = MainScreenUiState

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val fetchPlantUseCase: FetchPlantUseCase,
    private val fetchPlantItemsUseCase: FetchPlantItemsUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<MutableDestination>(MainScreenUiState.Loading)
    val uiState: StateFlow<MutableDestination> = _uiState.asStateFlow()

    init {
        fetchPlants()
    }

    fun fetchPlants() {
        viewModelScope.launch {
            val responce = fetchPlantUseCase.invoke()
            val request = fetchPlantItemsUseCase.invoke()
            if (responce.status == ResponseStatus.SUCCESS && request.status == ResponseStatus.SUCCESS) {
                responce.data?.let { data ->
                    request.data.let { model ->

                        val plaintBerries =
                            data.filter { it.category == "berries" }.map { it.toUio() }
                                .toImmutableList()

                        val plaintFlowers =
                            data.filter { it.category == "flowers" }.map { it.toUio() }
                                .toImmutableList()

                        val plaintVegetables =
                            data.filter { it.category == "vegetables" }.map { it.toUio() }
                                .toImmutableList()

                        val homeFlowers =
                            data.filter { it.category == "home_flowers" }.map { it.toUio() }
                                .toImmutableList()

                        val plantItem = model?.map { it.toUio() }.orEmpty().toImmutableList()

                        _uiState.tryEmit(
                            MainScreenUiState.Success(
                                berries = plaintBerries.toImmutableList(),
                                vegetables = plaintVegetables.toImmutableList(),
                                flowers = plaintFlowers.toImmutableList(),
                                homeFlowers = homeFlowers.toImmutableList(),
                                plantItem = plantItem.toImmutableList()
                            )
                        )
                    }
                }
            } else {
                _uiState.tryEmit(MainScreenUiState.Error("Error"))
            }
        }
    }
}
