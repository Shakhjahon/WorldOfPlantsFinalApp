package com.example.domain.models.plant

import com.example.domain.models.profile.UserAvatarDomainModel
import java.io.Serializable

data class GetPlantDomainModel(
    val biologicalName: String,
    val category: String,
    val createdAt: String,
    val descriptions: String,
    val image: UserAvatarDomainModel,
    val imageDetails: UserAvatarDomainModel,
    val name: String,
    val objectId: String,
    val updatedAt: String,
    val little: UserAvatarDomainModel,
    val fertilizer: String,
    val keyFact: String,
    val earth: String,
    val climateZon: String,
    val famous: String,
    val plantHeight: String,
    val temperature: String,
    val water: String,
    val advice: String,
    val categoryId: String,
) : Serializable {
    companion object {
        val unknown = GetPlantDomainModel(
            biologicalName = "",
            category = "",
            createdAt = "",
            image = UserAvatarDomainModel.unknown,
            imageDetails = UserAvatarDomainModel.unknown,
            name = "",
            objectId = "",
            updatedAt = "",
            descriptions = "",
            little = UserAvatarDomainModel.unknown,
            fertilizer = "",
            keyFact = "",
            earth = "",
            climateZon = "",
            famous = "",
            plantHeight = "",
            temperature = "",
            water = "",
            advice = "",
            categoryId = ""
        )
    }
}