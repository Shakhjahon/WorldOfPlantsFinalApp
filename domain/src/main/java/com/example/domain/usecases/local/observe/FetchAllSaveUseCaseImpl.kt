package com.example.domain.usecases.local.observe

import com.example.domain.models.plant.GetPlantDomainModel
import com.example.domain.repository.detail.repository.DetailsRepository
import kotlinx.coroutines.flow.Flow

class FetchAllSaveUseCaseImpl(
    private val movieRepository: DetailsRepository
) : FetchAllSaveUseCase {
    override fun invoke(): Flow<List<GetPlantDomainModel>> {
        return movieRepository.addAllPlants()
    }
}