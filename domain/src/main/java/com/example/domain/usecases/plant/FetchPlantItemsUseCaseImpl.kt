package com.example.domain.usecases.plant

import com.example.domain.ResultStatus
import com.example.domain.models.plant.GetItemsDomainModel
import com.example.domain.models.plant.GetItemsPlantDomainModel
import com.example.domain.repository.getPlant.repository.GetPlantRepository

class FetchPlantItemsUseCaseImpl(
    private val getPlantRepository: GetPlantRepository
) : FetchPlantItemsUseCase {
    override suspend fun invoke(): ResultStatus<List<GetItemsDomainModel>> {
        val responce = getPlantRepository.fetchItemsPlant()

        return ResultStatus(
            status = responce.status,
            errorThrowable = responce.errorThrowable,
            data = responce.data
        )
    }
}