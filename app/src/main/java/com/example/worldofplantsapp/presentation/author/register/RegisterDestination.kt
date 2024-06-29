package com.example.worldofplantsapp.presentation.author.register

import com.example.worldofplantsapp.presentation.graphMain.manGraph.Destinations

object RegisterDestination: Destinations {
    override val route: String
        get() = "register_screen"
    override val routeWithArgs: String
        get() = "register_with_args"
}