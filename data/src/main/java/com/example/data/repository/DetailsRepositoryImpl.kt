package com.example.data.repository

import com.example.data.cloud.detail.cloud.DetailCloudDataSourse
import com.example.data.local.room.PlantCacheDataSource
import com.example.data.mappers.toCache
import com.example.data.mappers.toDomain
import com.example.domain.ResultStatus
import com.example.domain.models.plant.GetPlantDomainModel
import com.example.domain.models.plant.GetWorldOfPlantDomainModel
import com.example.domain.repository.detail.repository.DetailsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DetailsRepositoryImpl @Inject constructor(
    private val cloudDataSourse: DetailCloudDataSourse,
    private val plantCacheDataSource: PlantCacheDataSource,
) : DetailsRepository {
    override suspend fun details(plantId: String): ResultStatus<GetWorldOfPlantDomainModel> {
        val id = "{\"objectId\":\"$plantId\"}"
        val responce = cloudDataSourse.details(id)

        return ResultStatus(
            status = responce.status,
            errorThrowable = responce.errorThrowable,
            data = responce.data?.toDomain()
        )
    }

    override suspend fun savePlantsToCache(model: List<GetPlantDomainModel>) {
        plantCacheDataSource.addMovieToCache(cacheModel = model.map { it.toCache() })
    }

    override fun addAllPlants(): Flow<List<GetPlantDomainModel>> {
        return plantCacheDataSource.fetchAllCacheMovies().map { it.map { it.toCache() } }
    }

    override suspend fun deletePlantsById(movieId: String) {
        return plantCacheDataSource.deleteMovieById(movieId)
    }

    override fun observeIsPlantsSaved(movieId: String): Flow<Boolean> {
        return plantCacheDataSource.observeIsMovieSaved(movieId)
    }

    override suspend fun clearTable() {
        return plantCacheDataSource.clearTable()
    }
}