package com.example.domain.usecases.author.login

import com.example.domain.ResultStatus
import com.example.domain.models.profile.UserProfileDomainModel

interface LoginUseCase {

    suspend operator fun invoke(
        userName: String,
        userPassword:String
    ): ResultStatus<UserProfileDomainModel>
}