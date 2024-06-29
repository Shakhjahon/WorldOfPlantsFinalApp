package com.example.worldofplantsapp.presentation.main.screens.profile.screen

import com.example.worldofplantsapp.presentation.graphMain.manGraph.Destinations

object ProfileDestinations: Destinations {
    override val route: String
        get() = "profile_screen"
    override val routeWithArgs: String
        get() = "profile_screen_with_args"
}