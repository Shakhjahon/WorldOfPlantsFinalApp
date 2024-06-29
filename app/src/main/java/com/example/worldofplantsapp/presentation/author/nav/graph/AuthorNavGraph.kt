package com.example.worldofplantsapp.presentation.author.nav.graph

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.worldofplantsapp.presentation.author.elections.ElectionsDestination
import com.example.worldofplantsapp.presentation.author.elections.ElectionsScreen
import com.example.worldofplantsapp.presentation.author.login.LoginDestination
import com.example.worldofplantsapp.presentation.author.login.LoginMainScreen
import com.example.worldofplantsapp.presentation.author.login.LoginViewModel
import com.example.worldofplantsapp.presentation.author.register.RegisterDestination
import com.example.worldofplantsapp.presentation.author.register.RegisterMainScreen
import com.example.worldofplantsapp.presentation.author.register.RegisterViewModel
import com.example.worldofplantsapp.presentation.main.screens.on.boarding.OnBoardingDestination
import com.example.worldofplantsapp.presentation.main.screens.on.boarding.OnBoardingScreen

const val AUTHOR_GRAPH_TAG = "AUTHOR_GRAPH_TAG"

@Composable
fun AuthorNavGraph() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = OnBoardingDestination.route,
    ) {
        composable(OnBoardingDestination.route) {
            OnBoardingScreen(
                onNavigatyAuthor = {
                    navController.navigate(ElectionsDestination.route)
                }
            )
        }
        composable(ElectionsDestination.route) {
            ElectionsScreen(
                onNavigatyLogin = {
                    navController.navigate(LoginDestination.route)
                },
                onNavigatyRegister = {
                    navController.navigate(RegisterDestination.route)
                },
            )
        }
        composable(LoginDestination.route) {
            val viewModel: LoginViewModel = hiltViewModel()
            LoginMainScreen(
                onNavigatyRegister = {
                    navController.navigate(RegisterDestination.route)
                },
                onInteraction = viewModel::onInteraction,
                uiStateFlow = viewModel.uiState,
                userInfoState = viewModel.userInfoState
            )
        }
        composable(RegisterDestination.route) {
            val viewModel: RegisterViewModel = hiltViewModel()
            RegisterMainScreen(
                onNavigatyLogin = {
                    navController.navigate(LoginDestination.route)
                },
                onInteraction = viewModel::onInteraction,
                uiStateFlow = viewModel.uiState,
                registerUserInfoState = viewModel.infoState
            )
        }
    }
}