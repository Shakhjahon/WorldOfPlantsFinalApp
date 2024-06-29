package com.example.data.models.all

import androidx.compose.runtime.Immutable
import com.google.gson.annotations.SerializedName

@Immutable
data class UserAvatarResponceModel(
    @SerializedName("name")
    val name: String,
    @SerializedName("__type")
    val fileType: String,
    @SerializedName("url")
    val url: String,
) {
    companion object {
        val unknown = UserAvatarResponceModel(
            name = "",
            fileType = "",
            url = "",
        )
    }
}