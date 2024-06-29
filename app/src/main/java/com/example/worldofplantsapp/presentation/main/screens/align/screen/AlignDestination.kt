package com.example.worldofplantsapp.presentation.main.screens.align.screen

import com.example.worldofplantsapp.presentation.graphMain.manGraph.Destinations

object AlignDestination: Destinations {
    override val route: String
        get() = "align_screen"
    override val routeWithArgs: String
        get() = "align_screen_with_args"
}