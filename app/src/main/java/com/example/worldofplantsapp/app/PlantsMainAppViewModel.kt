package com.example.worldofplantsapp.app

import androidx.lifecycle.ViewModel
import com.example.worldofplantsapp.presentation.graphMain.manager.NavGraphManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PlantsMainAppViewModel @Inject constructor(
    private val navigationManager: NavGraphManager
) : ViewModel() {
    val destinationFlow = navigationManager.observeDestinationFlow()
}