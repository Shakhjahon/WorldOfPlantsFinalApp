package com.example.worldofplantsapp.presentation.main.screens.on.boarding

import com.example.worldofplantsapp.presentation.graphMain.manGraph.Destinations

object OnBoardingDestination: Destinations {
    override val route: String
        get() = "on_boarding"
    override val routeWithArgs: String
        get() = "on_boarding_with_args"

}