package com.example.worldofplantsapp.presentation.graphMain.manager

import kotlinx.coroutines.flow.SharedFlow

interface NavGraphManager {

    fun navigateTo(route: String, isBackStackClear: Boolean = false)

    fun observeDestinationFlow(): SharedFlow<Pair<String, Boolean>>
}