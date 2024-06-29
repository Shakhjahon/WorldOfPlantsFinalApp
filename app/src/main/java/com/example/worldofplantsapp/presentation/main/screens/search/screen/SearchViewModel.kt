package com.example.worldofplantsapp.presentation.main.screens.search.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecases.plant.FetchPlantUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val fetchPlantUseCase: FetchPlantUseCase,
) : ViewModel() {
    private val searQueryFlow = MutableStateFlow("")

    private val _uiStateFlow = MutableStateFlow(SearchUiState(fauna = emptyList()))
    val uiState: StateFlow<SearchUiState> = _uiStateFlow.asStateFlow()

    init {
        searQueryFlow.onEach { query ->
            _uiStateFlow.tryEmit(
                _uiStateFlow.value.copy(
                    query = query, isLoading = true
                )
            )
        }
            .debounce(300).onEach(
                ::startSearch
            ).launchIn(viewModelScope)
    }

    private fun startSearch(query: String) {
        viewModelScope.launch {
            val content = fetchPlantUseCase.invoke()
            val result = content.data?.filter { it.name.contains(query, ignoreCase = true) }
            _uiStateFlow.tryEmit(
                _uiStateFlow.value.copy(
                    fauna = result,
                    isLoading = false
                )
            )
        }
    }

    fun onValueChange(value: String) {
        searQueryFlow.tryEmit(value)
    }
}