package com.example.worldofplantsapp.presentation.author.login

import com.example.worldofplantsapp.presentation.graphMain.manGraph.Destinations

object LoginDestination: Destinations {
    override val route: String
        get() = "login_screen"
    override val routeWithArgs: String
        get() = "login_with_args"
}