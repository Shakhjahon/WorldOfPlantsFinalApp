package com.example.data.local.room

import android.util.Log
import kotlinx.coroutines.flow.Flow

class PlantCacheDataSourceImpl(
    private val movieDao: PlantDao
) : PlantCacheDataSource {

    override suspend fun addMovieToCache(cacheModel: List<PlantCacheModel>) {
        Log.d("VVV", "$cacheModel")
        movieDao.addMovieToCache(cacheModel)
    }

    override fun fetchAllCacheMovies(): Flow<List<PlantCacheModel>> {
        return movieDao.addAllPlants()
    }

    override suspend fun deleteMovieById(movieId: String) {
        movieDao.deletePlantById(movieId)
    }

    override suspend fun clearTable() {
        movieDao.clearTable()
    }

    override fun observeIsMovieSaved(movieId: String): Flow<Boolean> {
        return movieDao.observeIsPlantsSaved(movieId)
    }
}