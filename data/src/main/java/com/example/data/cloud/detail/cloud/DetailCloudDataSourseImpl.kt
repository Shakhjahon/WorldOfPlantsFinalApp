package com.example.data.cloud.detail.cloud

import com.example.data.base.BaseDataSource
import com.example.data.models.world.GetWorldOfPlantResponceModel
import com.example.data.service.UserService
import com.example.domain.ResultStatus
import javax.inject.Inject

class DetailCloudDataSourseImpl @Inject constructor(
    private val service: UserService
) : DetailCloudDataSourse, BaseDataSource() {
    override suspend fun details(
        plantId: String
    ): ResultStatus<GetWorldOfPlantResponceModel> {
        val responce = invokeResponseRequest {
            service.getPlantDetails(plantId)
        }
        return ResultStatus(
            status = responce.status,
            data = responce.data,
            errorThrowable = responce.errorThrowable
        )
    }
}