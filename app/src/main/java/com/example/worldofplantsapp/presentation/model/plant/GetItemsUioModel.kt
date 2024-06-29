package com.example.worldofplantsapp.presentation.model.plant


import com.example.worldofplantsapp.presentation.model.profile.UserAvatarUioModel

data class GetItemsUioModel(
    val category: String,
    val createdAt: String,
    val image: UserAvatarUioModel,
    val name: String,
    val objectId: String,
    val updatedAt: String
)