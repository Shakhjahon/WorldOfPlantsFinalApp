package com.example.data.cloud.author.cloud

import com.example.data.models.profile.UserProfileResponceModel
import com.example.data.models.profile.UserResponceModel
import com.example.domain.ResultStatus
import com.example.domain.models.profile.UserProfileDomainModel
import com.example.domain.models.profile.UserSignDomainModel

interface AuthorCloudDataSourse {

    suspend fun register(
        userResponceModel: UserResponceModel
    ): ResultStatus<UserProfileResponceModel>

    suspend fun login(
        userName: String,
        userPassword: String,
    ): ResultStatus<UserProfileResponceModel>
}