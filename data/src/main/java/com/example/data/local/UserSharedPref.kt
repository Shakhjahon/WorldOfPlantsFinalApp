package com.example.data.local

import com.example.domain.models.profile.UserProfileDomainModel

interface UserSharedPref {

    suspend fun saveIsUserSigned()

    suspend fun observeIsUserSigned(): Boolean

    fun saveCurrentUser(users: UserProfileDomainModel?)

    fun getCurrentUser(): UserProfileDomainModel?

    fun clear()
}