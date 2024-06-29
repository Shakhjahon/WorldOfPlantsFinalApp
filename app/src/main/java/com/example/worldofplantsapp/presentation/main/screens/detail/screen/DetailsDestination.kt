package com.example.worldofplantsapp.presentation.main.screens.detail.screen

import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.worldofplantsapp.presentation.graphMain.manGraph.Destinations

object DetailsDestination: Destinations {
    override val route: String
        get() = "details_screen"
    const val objectId = "objectId"
    override val routeWithArgs = "$route/{$objectId}"
    val arguments = listOf(navArgument(objectId) { type = NavType.StringType })
}