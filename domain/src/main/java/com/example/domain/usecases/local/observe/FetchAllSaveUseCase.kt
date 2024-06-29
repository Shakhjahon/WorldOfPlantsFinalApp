package com.example.domain.usecases.local.observe

import com.example.domain.models.plant.GetPlantDomainModel
import kotlinx.coroutines.flow.Flow

interface FetchAllSaveUseCase {

    operator fun invoke(): Flow<List<GetPlantDomainModel>>
}