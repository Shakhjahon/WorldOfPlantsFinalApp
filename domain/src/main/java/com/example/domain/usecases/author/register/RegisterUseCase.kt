package com.example.domain.usecases.author.register

import com.example.domain.ResultStatus
import com.example.domain.models.profile.UserProfileDomainModel
import com.example.domain.models.profile.UserSignDomainModel

interface RegisterUseCase {
    suspend operator fun invoke(
        userSignDomainModel:
        UserSignDomainModel
    ): ResultStatus<UserProfileDomainModel>
}