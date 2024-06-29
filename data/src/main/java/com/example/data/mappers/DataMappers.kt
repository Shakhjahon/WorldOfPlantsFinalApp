package com.example.data.mappers

import com.example.data.local.room.PlantCacheModel
import com.example.data.models.all.UserAvatarResponceModel
import com.example.data.models.profile.UserResponceModel
import com.example.domain.models.plant.GetPlantDomainModel
import com.example.domain.models.profile.UserAvatarDomainModel
import com.example.domain.models.profile.UserSignDomainModel

fun UserSignDomainModel.toData() = UserResponceModel(
    userName = username,
    userLastNames = lastname,
    email = email,
    password = password,
)


fun UserAvatarDomainModel.toData() = UserAvatarResponceModel(
    name = name,
    fileType = fileType,
    url = url,
)

fun GetPlantDomainModel.toCache() = PlantCacheModel(
    biologicalName = biologicalName,
    category = category,
    createdAt = createdAt,
    image = image.toData(),
    imageDetails = imageDetails.toData(),
    name = name,
    objectId = objectId,
    updatedAt = updatedAt,
    descriptions = descriptions,
    little = little.toData(),
    fertilizer = fertilizer,
    keyFact = keyFact,
    earth = earth,
    climateZon = climateZon,
    famous = famous,
    plantHeight = plantHeight,
    temperature = temperature,
    water = water,
    advice = advice,
    categoryId = categoryId
)