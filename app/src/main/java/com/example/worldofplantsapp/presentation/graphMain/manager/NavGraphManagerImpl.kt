package com.example.worldofplantsapp.presentation.graphMain.manager

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NavGraphManagerImpl @Inject constructor(): NavGraphManager {

    private val navigationDestinationFlow = MutableSharedFlow<Pair<String, Boolean>>(
        replay = 0,
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST,
    )

    override fun navigateTo(route: String, isBackStackClear: Boolean) {
        navigationDestinationFlow.tryEmit(route to isBackStackClear)
    }

    override fun observeDestinationFlow(): SharedFlow<Pair<String, Boolean>> {
       return navigationDestinationFlow.asSharedFlow()
    }
}