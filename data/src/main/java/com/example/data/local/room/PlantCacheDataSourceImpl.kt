package com.example.data.local.room

import android.util.Log
import kotlinx.coroutines.flow.Flow

class PlantCacheDataSourceImpl(
    private val plantDao: PlantDao
) : PlantCacheDataSource {

    override suspend fun addMovieToCache(cacheModel: List<PlantCacheModel>) {
        Log.d("VVV", "$cacheModel")
        plantDao.addMovieToCache(cacheModel)
    }

    override fun fetchAllCacheMovies(): Flow<List<PlantCacheModel>> {
        return plantDao.addAllPlants()
    }

    override suspend fun deleteMovieById(movieId: String) {
        plantDao.deletePlantById(movieId)
    }

    override suspend fun clearTable() {
        plantDao.clearTable()
    }

    override fun observeIsMovieSaved(movieId: String): Flow<Boolean> {
        return plantDao.observeIsPlantsSaved(movieId)
    }
}