package com.example.domain.usecases.plant

import com.example.domain.ResultStatus
import com.example.domain.models.plant.GetPlantDomainModel

interface FetchPlantUseCase {

    suspend operator fun invoke(): ResultStatus<List<GetPlantDomainModel>>
}