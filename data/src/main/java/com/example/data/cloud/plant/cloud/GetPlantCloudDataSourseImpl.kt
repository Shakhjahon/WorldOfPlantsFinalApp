package com.example.data.cloud.plant.cloud

import com.example.data.base.BaseDataSource
import com.example.data.models.world.GetItemsResponceModel
import com.example.data.models.world.GetPlantResponceModel
import com.example.data.service.UserService
import com.example.domain.ResultStatus
import javax.inject.Inject

class GetPlantCloudDataSourseImpl @Inject constructor(
    private val service: UserService
) : GetPlantCloudDataSourse, BaseDataSource() {

    override suspend fun getPlant(
    ): ResultStatus<List<GetPlantResponceModel>> {
        val responce = invokeResponseRequest {
            service.getPlant()
        }
        return ResultStatus(
            status = responce.status,
            errorThrowable = responce.errorThrowable,
            data = responce.data?.getPlants
        )
    }

    override suspend fun getPlantItem(
    ): ResultStatus<List<GetItemsResponceModel>> {
        val responce = invokeResponseRequest {
            service.getPlantItems()
        }
        return ResultStatus(
            status = responce.status,
            errorThrowable = responce.errorThrowable,
            data = responce.data?.getItemResponceModels
        )
    }
}