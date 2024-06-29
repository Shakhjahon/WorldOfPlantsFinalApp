package com.example.domain.usecases.local.delete

import com.example.domain.repository.detail.repository.DetailsRepository

class DeletePlantByIdUseCaseImpl(
    private val repository: DetailsRepository
) : DeletePlantByIdUseCase {
    override suspend fun invoke(plantId: String) {
        return repository.deletePlantsById(plantId)
    }
}