package com.example.worldofplantsapp.presentation.main.screens.splash.screen

import com.example.worldofplantsapp.presentation.graphMain.manGraph.Destinations

object SplashDestination: Destinations {
    override val route: String
        get() = "splash_screen"
    override val routeWithArgs: String
        get() = "splash_with_args"

}