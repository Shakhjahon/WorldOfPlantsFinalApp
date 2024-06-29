package com.example.data.cloud.plant.cloud

import com.example.data.models.world.GetItemsResponceModel
import com.example.data.models.world.GetPlantResponceModel
import com.example.domain.ResultStatus
import com.example.domain.models.plant.GetItemsDomainModel
import com.example.domain.models.plant.GetPlantDomainModel

interface GetPlantCloudDataSourse {

    suspend fun getPlant(): ResultStatus<List<GetPlantResponceModel>>

    suspend fun getPlantItem(): ResultStatus<List<GetItemsResponceModel>>
}