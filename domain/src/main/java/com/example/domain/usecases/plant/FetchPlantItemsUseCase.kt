package com.example.domain.usecases.plant

import com.example.domain.ResultStatus
import com.example.domain.models.plant.GetItemsDomainModel
import com.example.domain.models.plant.GetItemsPlantDomainModel

interface FetchPlantItemsUseCase {

    suspend operator fun invoke(): ResultStatus<List<GetItemsDomainModel>>
}