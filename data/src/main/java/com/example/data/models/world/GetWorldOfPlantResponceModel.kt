package com.example.data.models.world


import androidx.compose.runtime.Immutable
import com.google.gson.annotations.SerializedName

@Immutable
data class GetWorldOfPlantResponceModel(
    @SerializedName("results")
    val getPlants: List<GetPlantResponceModel>,
)