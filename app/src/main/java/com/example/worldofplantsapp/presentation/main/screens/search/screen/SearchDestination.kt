package com.example.worldofplantsapp.presentation.main.screens.search.screen

import com.example.worldofplantsapp.presentation.graphMain.manGraph.Destinations

object SearchDestination: Destinations {
    override val route: String
        get() = "search_screen"
    override val routeWithArgs: String
        get() = "search_screen_with_args"
}