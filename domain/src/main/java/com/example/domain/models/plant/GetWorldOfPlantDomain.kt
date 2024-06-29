package com.example.domain.models.plant

import java.io.Serializable


data class GetWorldOfPlantDomainModel(
    val getPlants: List<GetPlantDomainModel>
) : Serializable {
    companion object {
        val unknown = GetWorldOfPlantDomainModel(
            getPlants = emptyList()
        )
    }
}