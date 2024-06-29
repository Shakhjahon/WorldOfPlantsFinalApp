package com.example.data.cloud.detail.cloud

import com.example.data.models.world.GetWorldOfPlantResponceModel
import com.example.domain.ResultStatus

interface DetailCloudDataSourse {
    suspend fun details(
        plantId: String
    ): ResultStatus<GetWorldOfPlantResponceModel>
}