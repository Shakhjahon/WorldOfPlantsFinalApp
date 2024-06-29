package com.example.data.cloud.category_cloud

import com.example.data.models.world.GetWorldOfPlantResponceModel
import com.example.domain.ResultStatus

interface CategoryCloudDataSourse {
    suspend fun getCategories(
        categoryId: String,
    ): ResultStatus<GetWorldOfPlantResponceModel>
}