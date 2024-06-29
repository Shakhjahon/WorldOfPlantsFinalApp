package com.example.worldofplantsapp.presentation.author.login

import com.example.domain.models.profile.UserProfileDomainModel
import javax.annotation.concurrent.Immutable

sealed interface LoginInteraction {

    @Immutable
    data class OnEmailChanget(val username: String) : LoginInteraction

    @Immutable
    data class OnUserPasswordChanget(val password: String) : LoginInteraction

    data object OnLoginButtonClick : LoginInteraction

    data class OnNavigatyMainScreen(val objectId: UserProfileDomainModel) : LoginInteraction

}