package com.example.worldofplantsapp.presentation.mappers

import com.example.domain.models.plant.GetItemsDomainModel
import com.example.domain.models.plant.GetPlantDomainModel
import com.example.domain.models.plant.GetWorldOfPlantDomainModel
import com.example.domain.models.profile.UserAvatarDomainModel
import com.example.domain.models.profile.UserProfileDomainModel
import com.example.worldofplantsapp.presentation.model.plant.GetItemsUioModel
import com.example.worldofplantsapp.presentation.model.plant.GetPlantUioModel
import com.example.worldofplantsapp.presentation.model.plant.GetWorldOfPlantUioModel
import com.example.worldofplantsapp.presentation.model.profile.UserAvatarUioModel
import com.example.worldofplantsapp.presentation.model.profile.UserProfileUioModel

fun UserProfileDomainModel.toUio() = UserProfileUioModel(
    objectId = objectId,
    userName = userName.orEmpty(),
    lastname = lastname.orEmpty(),
    userAvatar = userAvatar.toUio()
)

fun UserAvatarDomainModel.toUio() = UserAvatarUioModel(
    name = name, fileType = fileType, url = url
)

fun GetPlantDomainModel.toUio() = GetPlantUioModel(
    biologicalName = biologicalName,
    category = category,
    createdAt = createdAt,
    image = image.toUio(),
    imageDetails = imageDetails.toUio(),
    name = name,
    objectId = objectId,
    updatedAt = updatedAt,
    descriptions = descriptions,
    little = little.toUio(),
    keyFact = keyFact,
    climateZon = climateZon,
    fertilizer = fertilizer,
    earth = earth,
    famous = famous,
    temperature = temperature,
    plantHeight = plantHeight,
    water = water,
    advice = advice,
    categoryId = categoryId
)

fun GetWorldOfPlantDomainModel.toUio() =
    GetWorldOfPlantUioModel(result = getPlants.map { it.toUio() })

fun GetItemsDomainModel.toUio() = GetItemsUioModel(
    category = category,
    createdAt = createdAt,
    image = image.toUio(),
    name = name,
    objectId = objectId,
    updatedAt = updatedAt,
)