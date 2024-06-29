package com.example.data.mappers

import com.example.data.local.room.PlantCacheModel
import com.example.data.models.all.UserAvatarResponceModel
import com.example.data.models.profile.UserProfileResponceModel
import com.example.data.models.world.GetItemsPlantResponceModel
import com.example.data.models.world.GetItemsResponceModel
import com.example.data.models.world.GetPlantResponceModel
import com.example.data.models.world.GetWorldOfPlantResponceModel
import com.example.domain.models.plant.GetItemsDomainModel
import com.example.domain.models.plant.GetItemsPlantDomainModel
import com.example.domain.models.plant.GetPlantDomainModel
import com.example.domain.models.plant.GetWorldOfPlantDomainModel
import com.example.domain.models.profile.UserAvatarDomainModel
import com.example.domain.models.profile.UserProfileDomainModel


fun UserProfileResponceModel.toDomain() = UserProfileDomainModel(
    objectId = objectId,
    userName = userName,
    lastname = lastname,
    userAvatar = userAvatar.toDomain()
)

fun UserAvatarResponceModel.toDomain() = UserAvatarDomainModel(
    name = name,
    fileType = fileType,
    url = url,
)

fun GetWorldOfPlantResponceModel.toDomain() =
    GetWorldOfPlantDomainModel(getPlants = getPlants.map { it.toDomain() })

fun GetPlantResponceModel.toDomain() = GetPlantDomainModel(
    biologicalName = biologicalName,
    category = category,
    createdAt = createdAt,
    image = image.toDomain(),
    imageDetails = imageDetails.toDomain(),
    name = name,
    objectId = objectId,
    updatedAt = updatedAt,
    descriptions = descriptions,
    little = little.toDomain(),
    keyFact = keyFact,
    earth = earth,
    climateZon = climateZon,
    fertilizer = fertilizer,
    famous = famous,
    plantHeight = plantHeight,
    temperature = temperature,
    water = water,
    advice = advice,
    categoryId = categoryId.orEmpty()
)

fun GetItemsPlantResponceModel.toDomain() =
    GetItemsPlantDomainModel(getItemResponceModels = getItemResponceModels.map { it.toDomain() })

fun GetItemsResponceModel.toDomain() = GetItemsDomainModel(
    objectId = objectId,
    createdAt = createdAt,
    updatedAt = updatedAt,
    name = name,
    image = image.toDomain(),
    category = category,
)

fun PlantCacheModel.toCache() = GetPlantDomainModel(
    biologicalName = biologicalName,
    category = category,
    createdAt = createdAt,
    image = image.toDomain(),
    imageDetails = imageDetails.toDomain(),
    name = name,
    objectId = objectId,
    updatedAt = updatedAt,
    descriptions = descriptions,
    little = little.toDomain(),
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