package com.example.data.repository

import com.example.data.cloud.category_cloud.CategoryCloudDataSourse
import com.example.data.mappers.toDomain
import com.example.data.models.world.GetWorldOfPlantResponceModel
import com.example.domain.ResultStatus
import com.example.domain.models.plant.GetItemsPlantDomainModel
import com.example.domain.models.plant.GetWorldOfPlantDomainModel
import com.example.domain.repository.category.repository.CategoryRepository
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val cloudDataSource: CategoryCloudDataSourse,
) : CategoryRepository {
    override suspend fun category(categoryId: String): ResultStatus<GetWorldOfPlantDomainModel> {
        val id = "{\"categoryId\":\"$categoryId\"}"
        val responce = cloudDataSource.getCategories(id)

        return ResultStatus(
            status = responce.status,
            errorThrowable = responce.errorThrowable,
            data = responce.data?.toDomain()
        )
    }
}