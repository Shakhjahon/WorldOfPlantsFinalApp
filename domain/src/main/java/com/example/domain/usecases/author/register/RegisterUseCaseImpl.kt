package com.example.domain.usecases.author.register

import com.example.domain.ResultStatus
import com.example.domain.models.profile.UserProfileDomainModel
import com.example.domain.models.profile.UserSignDomainModel
import com.example.domain.repository.author.repository.AuthorRepository

class RegisterUseCaseImpl(
    private val repository: AuthorRepository
) : RegisterUseCase {
    override suspend fun invoke(
        userSignDomainModel: UserSignDomainModel
    ): ResultStatus<UserProfileDomainModel> {
        val responce = repository.register(userSignDomainModel)

        return ResultStatus(
            status = responce.status,
            errorThrowable = responce.errorThrowable,
            data = responce.data
        )
    }
}