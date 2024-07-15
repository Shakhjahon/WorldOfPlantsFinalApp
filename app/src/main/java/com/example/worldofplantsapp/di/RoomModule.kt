package com.example.worldofplantsapp.di

import android.content.Context
import androidx.room.Room
import com.example.data.local.room.PlantCacheDataSource
import com.example.data.local.room.PlantCacheDataSourceImpl
import com.example.data.local.room.PlantDao
import com.example.data.local.room.PlantDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {

    @Provides
    fun provideMoviesDao(
        database: PlantDataBase
    ): PlantDao = database.getPlantDao()

    @Singleton
    @Provides
    fun provideRoomDatabase(
        @ApplicationContext context: Context
    ): PlantDataBase = Room.databaseBuilder(
        context = context, PlantDataBase::class.java, "movie_database"
    ).build()

    @Provides
    fun providePlantCacheDataSource(
        movieDao: PlantDao
    ): PlantCacheDataSource = PlantCacheDataSourceImpl(movieDao)
}