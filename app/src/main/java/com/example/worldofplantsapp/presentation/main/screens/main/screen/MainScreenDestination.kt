package com.example.worldofplantsapp.presentation.main.screens.main.screen

import com.example.worldofplantsapp.presentation.graphMain.manGraph.Destinations

object MainScreenDestination: Destinations {
    override val route: String
        get() = "main_screen"
    override val routeWithArgs: String
        get() = "main_screen_with_args"
}