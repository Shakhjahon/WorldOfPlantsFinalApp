package com.example.data.models.profile

import androidx.compose.runtime.Immutable
import com.google.gson.annotations.SerializedName

@Immutable
data class UserResponceModel(
    @SerializedName("username")
    val userName: String,
    @SerializedName("lastname")
    val userLastNames: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("email")
    val email: String,
)