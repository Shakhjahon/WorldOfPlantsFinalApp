package com.example.domain.repository.category.repository

import com.example.domain.ResultStatus
import com.example.domain.models.plant.GetItemsPlantDomainModel
import com.example.domain.models.plant.GetWorldOfPlantDomainModel

interface CategoryRepository {
    suspend fun category(categoryId: String): ResultStatus<GetWorldOfPlantDomainModel>

}