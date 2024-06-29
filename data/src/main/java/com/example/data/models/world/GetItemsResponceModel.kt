package com.example.data.models.world


import androidx.compose.runtime.Immutable
import com.example.data.models.all.UserAvatarResponceModel
import com.google.gson.annotations.SerializedName

@Immutable
data class GetItemsResponceModel(
    @SerializedName("category")
    val category: String,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("image")
    val image: UserAvatarResponceModel,
    @SerializedName("name")
    val name: String,
    @SerializedName("objectId")
    val objectId: String,
    @SerializedName("updatedAt")
    val updatedAt: String
)