package com.example.domain.repository.detail.repository

import com.example.domain.ResultStatus
import com.example.domain.models.plant.GetPlantDomainModel
import com.example.domain.models.plant.GetWorldOfPlantDomainModel
import kotlinx.coroutines.flow.Flow

interface DetailsRepository {

    suspend fun details(plantId: String): ResultStatus<GetWorldOfPlantDomainModel>

    /** local **/
    suspend fun savePlantsToCache(model: List<GetPlantDomainModel>)

    fun addAllPlants(): Flow<List<GetPlantDomainModel>>

    suspend fun deletePlantsById(movieId: String)

    fun observeIsPlantsSaved(movieId: String): Flow<Boolean>

    suspend fun clearTable()

}