package com.example.worldofplantsapp.presentation.main.screens.category.screen

import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.worldofplantsapp.presentation.graphMain.manGraph.Destinations

object CategoryDestinations : Destinations {
    override val route: String
        get() = "category_screen"
    const val categoryId = "category"
    override val routeWithArgs = "$route/{$categoryId}"
    val arguments = listOf(navArgument(categoryId) { type = NavType.StringType })
}