package com.example.domain.usecases.local.delete

interface DeletePlantByIdUseCase {
    suspend operator fun invoke(plantId: String)
}