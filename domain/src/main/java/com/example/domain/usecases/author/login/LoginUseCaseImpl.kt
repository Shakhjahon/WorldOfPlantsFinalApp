package com.example.domain.usecases.author.login

import com.example.domain.ResultStatus
import com.example.domain.models.profile.UserProfileDomainModel
import com.example.domain.repository.author.repository.AuthorRepository

class LoginUseCaseImpl(
    private val repository: AuthorRepository,
) : LoginUseCase {

    override suspend fun invoke(
        userName: String,
        userPassword: String
    ): ResultStatus<UserProfileDomainModel> {
        val responce = repository.login(userName, userPassword)

        return ResultStatus(
            status = responce.status,
            errorThrowable = responce.errorThrowable,
            data = responce.data
        )
    }
}
