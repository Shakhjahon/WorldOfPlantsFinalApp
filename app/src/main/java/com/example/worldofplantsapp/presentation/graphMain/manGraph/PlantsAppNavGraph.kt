package com.example.worldofplantsapp.presentation.graphMain.manGraph

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.worldofplantsapp.presentation.author.nav.graph.AUTHOR_GRAPH_TAG
import com.example.worldofplantsapp.presentation.author.nav.graph.AuthorNavGraph
import com.example.worldofplantsapp.presentation.author.nav.graph.MAIN_GRAPH_TAG
import com.example.worldofplantsapp.presentation.author.nav.graph.MainNavGraph
import com.example.worldofplantsapp.presentation.main.screens.splash.screen.SplashDestination
import com.example.worldofplantsapp.presentation.main.screens.splash.screen.SplashScreen
import com.example.worldofplantsapp.presentation.main.screens.splash.screen.SplashViewModel
import com.example.worldofplantsapp.utils.view.model.ThemeViewModel

@Composable
fun PlantsAppNavGraph(
    navController: NavHostController,
    themeViewModel: ThemeViewModel,
    onNavigaty: (String) -> Unit,
    context: Context,
    onNavigatyMap: (String) -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = SplashDestination.route,
    ) {
        composable(SplashDestination.route) {
            val viewModel: SplashViewModel = hiltViewModel()
            SplashScreen()
        }
        composable(AUTHOR_GRAPH_TAG) {
            AuthorNavGraph()
        }
        composable(MAIN_GRAPH_TAG) {
            MainNavGraph(
                themeViewModel = themeViewModel,
                onNavigaty = onNavigaty,
                context = context,
                onNavigatyMap = onNavigatyMap
            )
        }
    }
}