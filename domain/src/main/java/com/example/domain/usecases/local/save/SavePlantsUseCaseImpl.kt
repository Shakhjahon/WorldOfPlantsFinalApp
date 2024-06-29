package com.example.domain.usecases.local.save

import com.example.domain.models.plant.GetPlantDomainModel
import com.example.domain.repository.detail.repository.DetailsRepository

class SavePlantsUseCaseImpl(
    private val movieRepository: DetailsRepository
) : SavePlantsUseCase {
    override suspend fun invoke(getPlantDomainModel: List<GetPlantDomainModel>) {
        movieRepository.savePlantsToCache(getPlantDomainModel)
    }
}