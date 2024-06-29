package com.example.domain.usecases.plant

import com.example.domain.ResultStatus
import com.example.domain.models.plant.GetPlantDomainModel
import com.example.domain.repository.getPlant.repository.GetPlantRepository

class FetchPlantUseCaseImpl(
    private val getPlantRepository: GetPlantRepository
) : FetchPlantUseCase {
    override suspend fun invoke(): ResultStatus<List<GetPlantDomainModel>> {
        val responce = getPlantRepository.fetchWorldPlant()

        return ResultStatus(
            status = responce.status,
            errorThrowable = responce.errorThrowable,
            data = responce.data
        )
    }
}