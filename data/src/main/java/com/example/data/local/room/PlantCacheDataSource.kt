package com.example.data.local.room

import kotlinx.coroutines.flow.Flow

interface PlantCacheDataSource {

    suspend fun addMovieToCache(cacheModel: List<PlantCacheModel>)

    fun fetchAllCacheMovies(): Flow<List<PlantCacheModel>>

    suspend fun deleteMovieById(movieId: String)

    suspend fun clearTable()

    fun observeIsMovieSaved(movieId: String): Flow<Boolean>
}