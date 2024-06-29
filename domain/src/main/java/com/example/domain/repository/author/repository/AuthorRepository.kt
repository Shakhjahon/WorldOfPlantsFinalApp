package com.example.domain.repository.author.repository

import com.example.domain.ResultStatus
import com.example.domain.models.profile.UserProfileDomainModel
import com.example.domain.models.profile.UserSignDomainModel

interface AuthorRepository {

    suspend fun register(
        userSignModel: UserSignDomainModel
    ): ResultStatus<UserProfileDomainModel>

    suspend fun login(
        userName: String,
        userPassword: String,
    ): ResultStatus<UserProfileDomainModel>

}