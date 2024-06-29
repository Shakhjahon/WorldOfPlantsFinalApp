package com.example.worldofplantsapp.di

import com.example.worldofplantsapp.presentation.graphMain.manager.NavGraphManager
import com.example.worldofplantsapp.presentation.graphMain.manager.NavGraphManagerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface ManagerModule {

    @Binds
    fun bintNavigationManager(
        implementation: NavGraphManagerImpl
    ): NavGraphManager
}