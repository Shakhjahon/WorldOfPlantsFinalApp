package com.example.worldofplantsapp.presentation.model.profile

import androidx.compose.runtime.Stable

@Stable
data class UserProfileUioModel(
    val objectId: String,
    val userName: String,
    val lastname: String,
    val userAvatar: UserAvatarUioModel,
) {
    companion object {
        val unknown = UserProfileUioModel(
            objectId = "",
            userName = "",
            lastname = "",
            userAvatar = UserAvatarUioModel.unknown,
        )
    }
}

@Stable
data class UserAvatarUioModel(
    val name: String,
    val fileType: String,
    val url: String,
) {
    companion object {
        val unknown = UserAvatarUioModel(
            name = "",
            fileType = "",
            url = "",
        )
    }
}
