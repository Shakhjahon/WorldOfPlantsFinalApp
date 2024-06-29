package com.example.worldofplantsapp.presentation.main.screens.splash.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.UserSharedPref
import com.example.worldofplantsapp.presentation.graphMain.manager.NavGraphManager
import com.example.worldofplantsapp.presentation.author.nav.graph.AUTHOR_GRAPH_TAG
import com.example.worldofplantsapp.presentation.author.nav.graph.MAIN_GRAPH_TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val SPLASH_DELAY = 2555L

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val navigatorManager: NavGraphManager,
    private val sharedPref: UserSharedPref
) : ViewModel() {
    init {
        viewModelScope.launch {
            delay(SPLASH_DELAY)
            if (sharedPref.observeIsUserSigned()) navigatorManager.navigateTo(MAIN_GRAPH_TAG, isBackStackClear = true)
            else navigatorManager.navigateTo(AUTHOR_GRAPH_TAG, isBackStackClear = true)
        }
    }
}