package com.example.worldofplantsapp.presentation.main.screens.category.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.ResponseStatus
import com.example.domain.usecases.category.CategoryUseCase
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

private typealias MutableDestination = CategoryUiState

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val categoryUseCase: CategoryUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<MutableDestination>(CategoryUiState.Loading)
    val uiState: StateFlow<MutableDestination> = _uiState.asStateFlow()

    private val handle = CoroutineExceptionHandler { _, throwable ->
        _uiState.tryEmit(CategoryUiState.Error(throwable.localizedMessage ?: ""))
    }

    fun fetchCategory(
        categoryId: String
    ) {
        viewModelScope.launch(handle + Dispatchers.IO) {
            val response = categoryUseCase.invoke(categoryId)
            if (response.status == ResponseStatus.SUCCESS) {
                _uiState.tryEmit(
                    CategoryUiState.Success(
                        plant = response.data?.getPlants.orEmpty().map { it.toUio() }.toImmutableList(),
                    ),
                )
            }
        }
    }
}