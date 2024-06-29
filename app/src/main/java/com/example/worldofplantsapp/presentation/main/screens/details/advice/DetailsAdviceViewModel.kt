package com.example.worldofplantsapp.presentation.main.screens.details.advice

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.ResponseStatus
import com.example.domain.usecases.detail.DetailsUseCase
import com.example.worldofplantsapp.presentation.mappers.toUio
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private typealias MutableStateFlowDetails = MutableStateFlow<DetailsAdviceUiState>

@HiltViewModel
class DetailsAdviceViewModel @Inject constructor(
    private val plantUseCase: DetailsUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlowDetails = MutableStateFlow(DetailsAdviceUiState.Loading)
    val uiState: StateFlow<DetailsAdviceUiState> = _uiState.asStateFlow()

    private val handle = CoroutineExceptionHandler { _, _ -> }

    fun getDetailsPlant(plantId: String) {
        viewModelScope.launch(handle + Dispatchers.IO) {
            val response = plantUseCase.details(plantId = plantId)
            if (response.status == ResponseStatus.SUCCESS) {
                _uiState.tryEmit(
                    DetailsAdviceUiState.Success(
                        model = response.data?.getPlants.orEmpty().map { it.toUio() }
                            .toImmutableList(),
                    ),
                )
            } else {
                _uiState.tryEmit(DetailsAdviceUiState.Error(throwable = "Error"))
            }
        }
    }
}