package com.example.data.repository

import com.example.data.cloud.author.cloud.AuthorCloudDataSourse
import com.example.data.mappers.toData
import com.example.data.mappers.toDomain
import com.example.domain.ResultStatus
import com.example.domain.models.profile.UserProfileDomainModel
import com.example.domain.models.profile.UserSignDomainModel
import com.example.domain.repository.author.repository.AuthorRepository
import javax.inject.Inject

class AuthorRepositoryImpl @Inject constructor(
    private val cloudDataSourse: AuthorCloudDataSourse
) : AuthorRepository {
    override suspend fun register(userSignModel: UserSignDomainModel): ResultStatus<UserProfileDomainModel> {
        val responce = cloudDataSourse.register(userSignModel.toData())

        return ResultStatus(
            status = responce.status,
            data = responce.data?.toDomain(),
            errorThrowable = responce.errorThrowable,
        )
    }

    override suspend fun login(
        userName: String, userPassword: String
    ): ResultStatus<UserProfileDomainModel> {
        val responce = cloudDataSourse.login(userName, userPassword)

        return ResultStatus(
            status = responce.status,
            data = responce.data?.toDomain(),
            errorThrowable = responce.errorThrowable,
        )
    }
}