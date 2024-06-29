package com.example.worldofplantsapp.presentation.author.register

import androidx.compose.runtime.Immutable
import com.example.domain.models.profile.UserProfileDomainModel

@Immutable
sealed interface RegisterInteraction {

    @Immutable
    data class OnUserNameChanget(val userFirstName: String) : RegisterInteraction

    @Immutable
    data class OnUserLastNameChanget(val userLastName: String) : RegisterInteraction

    @Immutable
    data class OnUserEmailChanget(val userEmail: String) : RegisterInteraction

    @Immutable
    data class OnUserPasswordChanget(val userPassword: String) : RegisterInteraction

    data class OnNavigatyMainScreen(val objectId: UserProfileDomainModel) : RegisterInteraction

    data object OnRegisterButtonClick : RegisterInteraction

    data object OnNavigateUp : RegisterInteraction

}