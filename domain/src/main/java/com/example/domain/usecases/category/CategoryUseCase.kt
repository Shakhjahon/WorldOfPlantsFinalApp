package com.example.domain.usecases.category

import com.example.domain.ResultStatus
import com.example.domain.models.plant.GetItemsPlantDomainModel
import com.example.domain.models.plant.GetWorldOfPlantDomainModel

interface CategoryUseCase {
    suspend operator fun invoke(categoryId: String): ResultStatus<GetWorldOfPlantDomainModel>

}