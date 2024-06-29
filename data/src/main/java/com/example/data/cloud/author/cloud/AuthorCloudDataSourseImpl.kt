package com.example.data.cloud.author.cloud

import com.example.data.base.BaseDataSource
import com.example.data.mappers.toDomain
import com.example.data.models.profile.UserProfileResponceModel
import com.example.data.models.profile.UserResponceModel
import com.example.data.service.UserService
import com.example.domain.ResultStatus
import com.example.domain.models.profile.UserProfileDomainModel
import javax.inject.Inject

class AuthorCloudDataSourseImpl @Inject constructor(
    private val userService: UserService
) : AuthorCloudDataSourse, BaseDataSource() {

    override suspend fun register(
        userResponceModel: UserResponceModel
    ): ResultStatus<UserProfileResponceModel> {
        val responce = invokeResponseRequest {
            userService.registerUser(userResponceModel)
        }
        return ResultStatus(
            status = responce.status,
            errorThrowable = responce.errorThrowable,
            data = responce.data
        )
    }

    override suspend fun login(
        userName: String, userPassword: String
    ): ResultStatus<UserProfileResponceModel> {
        val responce = invokeResponseRequest {
            userService.loginUser(userName, userPassword)
        }
        return ResultStatus(
            status = responce.status,
            errorThrowable = responce.errorThrowable,
            data = responce.data
        )
    }
}