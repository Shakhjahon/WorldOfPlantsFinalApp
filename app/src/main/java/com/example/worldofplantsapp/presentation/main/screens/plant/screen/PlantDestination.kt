package com.example.worldofplantsapp.presentation.main.screens.plant.screen

import com.example.worldofplantsapp.presentation.graphMain.manGraph.Destinations

object PlantDestination: Destinations {
    override val route: String
        get() = "plant_screen"
    override val routeWithArgs: String
        get() = "plant_screen_with_args"
}