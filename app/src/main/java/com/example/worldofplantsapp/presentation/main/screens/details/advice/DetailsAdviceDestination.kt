package com.example.worldofplantsapp.presentation.main.screens.details.advice

import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.worldofplantsapp.presentation.graphMain.manGraph.Destinations

object DetailsAdviceDestination: Destinations {
    override val route: String
        get() = "details_advice"
    const val plantId = "objectId"
    override val routeWithArgs = "$route/{$plantId}"
    val arguments = listOf(navArgument(plantId) { type = NavType.StringType })
}