package com.example.worldofplantsapp.presentation.graphMain.bottom.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.worldofplantsapp.presentation.main.screens.align.screen.AlignDestination
import com.example.worldofplantsapp.presentation.main.screens.align.screen.AlignScreen
import com.example.worldofplantsapp.presentation.main.screens.align.screen.AlignViewModel
import com.example.worldofplantsapp.presentation.main.screens.category.screen.CategoryDestinations
import com.example.worldofplantsapp.presentation.main.screens.category.screen.CategoryScreen
import com.example.worldofplantsapp.presentation.main.screens.category.screen.CategoryViewModel
import com.example.worldofplantsapp.presentation.main.screens.detail.screen.DetailsDestination
import com.example.worldofplantsapp.presentation.main.screens.detail.screen.DetailsScreen
import com.example.worldofplantsapp.presentation.main.screens.detail.screen.DetailsViewModel
import com.example.worldofplantsapp.presentation.main.screens.details.advice.DetailsAdviceDestination
import com.example.worldofplantsapp.presentation.main.screens.details.advice.DetailsAdviceScreen
import com.example.worldofplantsapp.presentation.main.screens.details.advice.DetailsAdviceViewModel
import com.example.worldofplantsapp.presentation.main.screens.main.screen.MainScreen
import com.example.worldofplantsapp.presentation.main.screens.main.screen.MainScreenDestination
import com.example.worldofplantsapp.presentation.main.screens.main.screen.MainScreenViewModel
import com.example.worldofplantsapp.presentation.main.screens.plant.screen.PlantDestination
import com.example.worldofplantsapp.presentation.main.screens.plant.screen.PlantScreen
import com.example.worldofplantsapp.presentation.main.screens.plant.screen.PlantViewModel
import com.example.worldofplantsapp.presentation.main.screens.profile.screen.ProfileDestinations
import com.example.worldofplantsapp.presentation.main.screens.profile.screen.ProfileScreen
import com.example.worldofplantsapp.presentation.main.screens.profile.screen.ProfileViewModel
import com.example.worldofplantsapp.presentation.main.screens.search.screen.SearchDestination
import com.example.worldofplantsapp.presentation.main.screens.search.screen.SearchScreen
import com.example.worldofplantsapp.presentation.main.screens.search.screen.SearchViewModel
import com.example.worldofplantsapp.utils.view.model.ThemeViewModel

@Composable
fun BottomNavGraph(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    themeViewModel: ThemeViewModel,
    onNavigatyYouTube: (String) -> Unit,
    context: Context,
    onNavigatyMap: (String) -> Unit,
    onNavigatyTelegram : () -> Unit
) {

    NavHost(
        navController = navHostController,
        startDestination = MainScreenDestination.route,
    ) {
        composable(MainScreenDestination.route) {
            val viewModel: MainScreenViewModel = hiltViewModel()
            MainScreen(uiStateFlow = viewModel.uiState, onNavigatyDetails = { objectId ->
                navHostController.navigate("${DetailsDestination.route}/$objectId")
            }, themeViewModel = themeViewModel, onNavigatyCategoryDetails = { categoryId ->
                navHostController.navigate("${CategoryDestinations.route}/$categoryId")
            })
        }
        composable(SearchDestination.route) {
            val viewModel: SearchViewModel = hiltViewModel()
            SearchScreen(
                uiState = viewModel.uiState.collectAsState().value,
                onValueChange = viewModel::onValueChange,
                onNavigatyDetails = { objectId ->
                    navHostController.navigate("${DetailsDestination.route}/$objectId")
                },
                themeViewModel = themeViewModel,
                popBackStack = {
                    navHostController.popBackStack()
                }
            )
        }
        composable(PlantDestination.route) {
            val viewModel: PlantViewModel = hiltViewModel()
            PlantScreen(
                uiStateFlow = viewModel.uiState,
                onNavigatyDetails = { objectId ->
                    navHostController.navigate("${DetailsDestination.route}/$objectId")
                },
                themeViewModel = themeViewModel,
                deletePlants = viewModel::deletePlants,
                deleteAllPlant = viewModel::deleteAllPlants,
                context = context
            )
        }
        composable(AlignDestination.route) {
            val viewModel: AlignViewModel = hiltViewModel()
            AlignScreen(
                uiStateFlow = viewModel.uiState,
                themeViewModel = themeViewModel,
                onNavigatyAdviceDetails = { objectId ->
                    navHostController.navigate("${DetailsAdviceDestination.route}/$objectId")
                },
            )
        }

        composable(DetailsAdviceDestination.routeWithArgs) {
            val objectId = it.arguments?.getString(DetailsAdviceDestination.plantId) ?: 0
            val viewModel: DetailsAdviceViewModel = hiltViewModel()
            DetailsAdviceScreen(
                uiStateFlow = viewModel.uiState,
                onRetry = { viewModel.getDetailsPlant(objectId.toString()) },
                themeViewModel = themeViewModel,
            )
        }
        composable(ProfileDestinations.route) {
            val viewModel: ProfileViewModel = hiltViewModel()
            ProfileScreen(
                uiStateFlow = viewModel.uiState,
                onInteraction = viewModel::onEvent,
                themeViewModel = themeViewModel,
                onNavigatyMap = onNavigatyMap,
                onNavigatyTelegram = onNavigatyTelegram
            )
        }
        composable(DetailsDestination.routeWithArgs) {
            val objectId = it.arguments?.getString(DetailsDestination.objectId) ?: 0
            val viewModel: DetailsViewModel = hiltViewModel()
            DetailsScreen(
                uiStateFlow = viewModel.uiState,
                onRetry = { viewModel.getDetailsPlant(objectId.toString()) },
                themeViewModel = themeViewModel,
                onNavigatyYouTube = onNavigatyYouTube,
                onSavedPlantScreen = viewModel::savePlantsToCache,
                popBackStack = { navHostController.popBackStack() }
            )
        }

        composable(CategoryDestinations.routeWithArgs) {
            val categoryId = it.arguments?.getString(CategoryDestinations.categoryId) ?: 0
            val viewModel: CategoryViewModel = hiltViewModel()
            CategoryScreen(uiStateFlow = viewModel.uiState,
                themeViewModel = themeViewModel,
                onRetry = { viewModel.fetchCategory(categoryId.toString()) },
                onNavigatyDetails = { objectId ->
                    navHostController.navigate("${DetailsDestination.route}/$objectId")
                },
                popBackStack = { navHostController.popBackStack() }
            )
        }
    }
}