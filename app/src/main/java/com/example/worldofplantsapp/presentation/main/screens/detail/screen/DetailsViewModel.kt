package com.example.worldofplantsapp.presentation.main.screens.detail.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.ResponseStatus
import com.example.domain.models.plant.GetPlantDomainModel
import com.example.domain.usecases.detail.DetailsUseCase
import com.example.domain.usecases.local.delete.DeletePlantByIdUseCase
import com.example.domain.usecases.local.observe.ObserveIsPlantSavedById
import com.example.domain.usecases.local.save.SavePlantsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

private typealias MutableStateFlowDetails = MutableStateFlow<DetailsUiState>

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val plantUseCase: DetailsUseCase,
    private val savedUseCase: SavePlantsUseCase,
    private val deleteUseCase: DeletePlantByIdUseCase,
    private val observeUseCase: ObserveIsPlantSavedById,
) : ViewModel() {

    private val _uiState: MutableStateFlowDetails = MutableStateFlow(DetailsUiState.Loading)
    val uiState: StateFlow<DetailsUiState> = _uiState.asStateFlow()

    private val handle = CoroutineExceptionHandler { _, _ -> }

    fun savePlantsToCache(movieDomain: ImmutableList<GetPlantDomainModel>) {
        saveOrDeletePlants(movieDomain)
    }

    private fun saveOrDeletePlants(
        plantDomainModel: ImmutableList<GetPlantDomainModel>,
    ) = viewModelScope.launch {
        val uiStateValue = _uiState.value as DetailsUiState.Success
        if (uiStateValue.isSaved) {
            deleteUseCase(plantDomainModel.getOrNull(0)?.objectId.toString())
        } else {
            savedUseCase(plantDomainModel)
        }
    }

    private fun checkIsPlantSaved(plantId: String) {
        observeUseCase(plantId).onEach { isSaved ->
            val uiStateValue = _uiState.value as DetailsUiState.Success
            _uiState.update {
                uiStateValue.copy(isSaved = isSaved)
            }
        }.launchIn(viewModelScope)
    }


    fun getDetailsPlant(plantId: String) {
        viewModelScope.launch(handle + Dispatchers.IO) {
            val response = plantUseCase.details(plantId = plantId)
            if (response.status == ResponseStatus.SUCCESS) {
                val plants = response.data?.getPlants.orEmpty().toImmutableList()
                _uiState.tryEmit(
                    DetailsUiState.Success(
                        model = plants,
                        isSaved = false,
                    )
                )
                if (plants.isNotEmpty()) {
                    checkIsPlantSaved(plants.first().objectId)
                }
            } else {
                _uiState.tryEmit(
                    DetailsUiState.Error(throwable = "writhing")
                )
            }
        }
    }
}