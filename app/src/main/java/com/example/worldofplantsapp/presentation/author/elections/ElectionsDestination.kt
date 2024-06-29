package com.example.worldofplantsapp.presentation.author.elections

import com.example.worldofplantsapp.presentation.graphMain.manGraph.Destinations

object ElectionsDestination: Destinations {
    override val route: String
        get() = "elections_screen"
    override val routeWithArgs: String
        get() = "elections_with_args"
}