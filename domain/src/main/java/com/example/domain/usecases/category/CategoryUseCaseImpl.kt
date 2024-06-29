package com.example.domain.usecases.category

import com.example.domain.ResultStatus
import com.example.domain.models.plant.GetWorldOfPlantDomainModel
import com.example.domain.repository.category.repository.CategoryRepository

class CategoryUseCaseImpl(
    private val repository: CategoryRepository
) : CategoryUseCase {
    override suspend operator fun invoke(
        categoryId: String,
    ): ResultStatus<GetWorldOfPlantDomainModel> {
        val responce = repository.category(categoryId)

        return ResultStatus(
            status = responce.status,
            data = responce.data,
            errorThrowable = responce.errorThrowable,
        )
    }
}