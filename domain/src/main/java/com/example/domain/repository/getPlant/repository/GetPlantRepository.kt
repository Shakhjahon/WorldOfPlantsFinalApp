package com.example.domain.repository.getPlant.repository

import com.example.domain.ResultStatus
import com.example.domain.models.plant.GetItemsDomainModel
import com.example.domain.models.plant.GetItemsPlantDomainModel
import com.example.domain.models.plant.GetPlantDomainModel
import com.example.domain.models.plant.GetWorldOfPlantDomainModel

interface GetPlantRepository {

    suspend fun fetchWorldPlant(): ResultStatus<List<GetPlantDomainModel>>

    suspend fun fetchItemsPlant(): ResultStatus<List<GetItemsDomainModel>>
}