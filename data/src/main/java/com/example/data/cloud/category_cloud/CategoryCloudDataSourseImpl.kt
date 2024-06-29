package com.example.data.cloud.category_cloud

import com.example.data.base.BaseDataSource
import com.example.data.models.world.GetWorldOfPlantResponceModel
import com.example.data.service.UserService
import com.example.domain.ResultStatus
import javax.inject.Inject

class CategoryCloudDataSourseImpl @Inject constructor(
    private val service: UserService
) : CategoryCloudDataSourse, BaseDataSource() {
    override suspend fun getCategories(
        categoryId: String,
    ): ResultStatus<GetWorldOfPlantResponceModel> {
        val responce = invokeResponseRequest {
            service.getPlantItemsDetails(categoryId)
        }
        return ResultStatus(
            status = responce.status,
            data = responce.data,
            errorThrowable = responce.errorThrowable
        )
    }
}