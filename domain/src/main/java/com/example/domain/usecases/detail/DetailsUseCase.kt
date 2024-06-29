package com.example.domain.usecases.detail

import com.example.domain.ResultStatus
import com.example.domain.models.plant.GetWorldOfPlantDomainModel

interface DetailsUseCase {

    suspend fun details(plantId: String): ResultStatus<GetWorldOfPlantDomainModel>
}