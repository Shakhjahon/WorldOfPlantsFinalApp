package com.example.domain.models.plant


import com.example.domain.models.profile.UserAvatarDomainModel

data class GetItemsDomainModel(
    val category: String,
    val createdAt: String,
    val image: UserAvatarDomainModel,
    val name: String,
    val objectId: String,
    val updatedAt: String
)