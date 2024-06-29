package com.example.worldofplantsapp.presentation.model.plant

import androidx.compose.runtime.Immutable
import com.example.worldofplantsapp.presentation.model.profile.UserAvatarUioModel
import java.io.Serializable

@Immutable
data class GetPlantUioModel(
    val biologicalName: String,
    val category: String,
    val createdAt: String,
    val descriptions: String,
    val image: UserAvatarUioModel,
    val imageDetails: UserAvatarUioModel,
    val name: String,
    val objectId: String,
    val updatedAt: String,
    val little: UserAvatarUioModel,
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
        val unknown = GetPlantUioModel(
            biologicalName = "",
            category = "",
            createdAt = "",
            image = UserAvatarUioModel.unknown,
            imageDetails = UserAvatarUioModel.unknown,
            name = "Msad",
            objectId = "",
            updatedAt = "",
            descriptions = "",
            little = UserAvatarUioModel.unknown,
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