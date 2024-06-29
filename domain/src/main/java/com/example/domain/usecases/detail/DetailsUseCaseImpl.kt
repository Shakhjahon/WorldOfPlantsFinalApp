package com.example.domain.usecases.detail

import com.example.domain.ResultStatus
import com.example.domain.models.plant.GetWorldOfPlantDomainModel
import com.example.domain.repository.detail.repository.DetailsRepository

class DetailsUseCaseImpl(
    private val repository: DetailsRepository
): DetailsUseCase {
    override suspend fun details(plantId: String): ResultStatus<GetWorldOfPlantDomainModel> {
        val responce = repository.details(plantId)

        return ResultStatus(
            status = responce.status,
            data = responce.data,
            errorThrowable = responce.errorThrowable,
        )
    }
}