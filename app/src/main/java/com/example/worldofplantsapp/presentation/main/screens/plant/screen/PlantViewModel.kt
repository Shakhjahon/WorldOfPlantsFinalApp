package com.example.worldofplantsapp.presentation.main.screens.plant.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.plant.GetPlantDomainModel
import com.example.domain.usecases.local.delete.ClearTableUseCase
import com.example.domain.usecases.local.delete.DeletePlantByIdUseCase
import com.example.domain.usecases.local.observe.FetchAllSaveUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlantViewModel @Inject constructor(
    private val fetchAllSaveUseCase: FetchAllSaveUseCase,
    private val deletePlantByIdUseCase: DeletePlantByIdUseCase,
    private val clearTableUseCase: ClearTableUseCase
) : ViewModel() {
    private val _uiState: MutableStateFlow<PlantUiState> = MutableStateFlow(PlantUiState.Loading)
    val uiState: StateFlow<PlantUiState> = _uiState.asStateFlow()


    private val cacheMoviesFlow =
        fetchAllSaveUseCase.invoke().stateIn(viewModelScope, SharingStarted.Lazily, null)

    init {
        cacheMoviesFlow.filterNotNull().distinctUntilChanged().onEach(::updateUiState)
            .launchIn(viewModelScope)
    }

    private fun updateUiState(cacheMovies: List<GetPlantDomainModel>) {
        _uiState.update {
            PlantUiState.Success(model = cacheMovies)
        }
    }

    fun deletePlants(plantId: String) {
        viewModelScope.launch {
            deletePlantByIdUseCase(plantId)
        }
    }

    fun deleteAllPlants() {
        viewModelScope.launch {
            clearTableUseCase.clearTable()
        }
    }
}