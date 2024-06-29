package com.example.data.repository

import com.example.data.cloud.plant.cloud.GetPlantCloudDataSourse
import com.example.data.mappers.toDomain
import com.example.domain.ResultStatus
import com.example.domain.models.plant.GetItemsDomainModel
import com.example.domain.models.plant.GetPlantDomainModel
import com.example.domain.repository.getPlant.repository.GetPlantRepository
import javax.inject.Inject

class GetPlantRepositoryImpl @Inject constructor(
    private val cloudDataSourse: GetPlantCloudDataSourse

) : GetPlantRepository {

    override suspend fun fetchWorldPlant(): ResultStatus<List<GetPlantDomainModel>> {
        val responce = cloudDataSourse.getPlant()

        return ResultStatus(
            status = responce.status,
            data = responce.data?.map { it.toDomain() },
            errorThrowable = responce.errorThrowable,
        )
    }

    override suspend fun fetchItemsPlant(): ResultStatus<List<GetItemsDomainModel>> {
        val responce = cloudDataSourse.getPlantItem()

        return ResultStatus(
            status = responce.status,
            data = responce.data?.map { it.toDomain() },
            errorThrowable = responce.errorThrowable,
        )
    }
}