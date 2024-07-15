package com.example.worldofplantsapp.presentation.main.screens.main.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.worldofplantsapp.presentation.main.components.shimmerEffect.ShimmerComponent
import com.example.worldofplantsapp.presentation.main.screens.erroe.screen.ErrorScreen
import com.example.worldofplantsapp.utils.view.model.ThemeViewModel
import kotlinx.coroutines.flow.StateFlow


@Composable
fun MainScreen(
    uiStateFlow: StateFlow<MainScreenUiState>,
    onNavigatyDetails: (String) -> Unit,
    themeViewModel: ThemeViewModel,
    onNavigatyCategoryDetails: (String) -> Unit
) {
    val viewModel: MainScreenViewModel = hiltViewModel()
    when (val uiState = uiStateFlow.collectAsState().value) {
        is MainScreenUiState.Error -> ErrorScreen(callback = viewModel::fetchPlants)
        is MainScreenUiState.Loading -> ShimmerComponent(themeViewModel = themeViewModel)
        is MainScreenUiState.Success -> LoadedMainScreen(
            uiState = uiState,
            onNavigatyDetails = onNavigatyDetails,
            themeViewModel = themeViewModel,
            onNavigatyCategoryDetails = onNavigatyCategoryDetails
        )
    }
}


@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun LoadedMainScreen(
    modifier: Modifier = Modifier,
    uiState: MainScreenUiState.Success,
    onNavigatyDetails: (String) -> Unit,
    themeViewModel: ThemeViewModel,
    onNavigatyCategoryDetails: (String) -> Unit
) {
    val verticalScrollState = rememberScrollState()
    BoxWithConstraints(
        modifier = modifier
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(state = verticalScrollState)
                .height(maxHeight)
        ) {
            MainBlock(
                uiState = uiState,
                onNavigatyDetails = onNavigatyDetails,
                themeViewModel = themeViewModel,
                onNavigatyCategoryDetails = onNavigatyCategoryDetails,
            )
        }
    }
}
