package com.example.domain.usecases.local.save

import com.example.domain.models.plant.GetPlantDomainModel

interface SavePlantsUseCase {

    suspend operator fun invoke(getPlantDomainModel: List<GetPlantDomainModel>)
}