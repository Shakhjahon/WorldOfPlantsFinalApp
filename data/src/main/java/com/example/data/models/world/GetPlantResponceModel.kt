package com.example.data.models.world


import androidx.compose.runtime.Immutable
import com.example.data.models.all.UserAvatarResponceModel
import com.google.gson.annotations.SerializedName


@Immutable
data class GetPlantResponceModel(
    @SerializedName("biological_name")
    val biologicalName: String,
    @SerializedName("category")
    val category: String,
    @SerializedName("descriptions")
    val descriptions: String,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("image")
    val image: UserAvatarResponceModel,
    @SerializedName("image_details")
    val imageDetails: UserAvatarResponceModel,
    @SerializedName("name")
    val name: String,
    @SerializedName("objectId")
    val objectId: String,
    @SerializedName("updatedAt")
    val updatedAt: String,
    @SerializedName("little_sprout")
    val little: UserAvatarResponceModel,
    @SerializedName("fertilizer")
    val fertilizer: String,
    @SerializedName("key_facts")
    val keyFact: String,
    @SerializedName("earth")
    val earth: String,
    @SerializedName("climate_zon")
    val climateZon: String,
    @SerializedName("famous")
    val famous: String,
    @SerializedName("plant_height")
    val plantHeight: String,
    @SerializedName("temperature")
    val temperature: String,
    @SerializedName("water")
    val water: String,
    @SerializedName("advice")
    val advice: String,
    @SerializedName("categoryId")
    val categoryId: String?,
)