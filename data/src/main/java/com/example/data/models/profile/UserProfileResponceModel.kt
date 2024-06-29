package com.example.data.models.profile

import androidx.compose.runtime.Immutable
import com.example.data.models.all.UserAvatarResponceModel
import com.google.gson.annotations.SerializedName

@Immutable
data class UserProfileResponceModel(
    @SerializedName("objectId")
    val objectId: String,
    @SerializedName("username")
    val userName: String,
    @SerializedName("lastname")
    val lastname: String,
    @SerializedName("userAvatar")
    val userAvatar: UserAvatarResponceModel,
) {
    companion object {
        val unknown = UserProfileResponceModel(
            objectId = String(),
            userName = String(),
            lastname = String(),
            userAvatar = UserAvatarResponceModel.unknown
        )
    }
}

