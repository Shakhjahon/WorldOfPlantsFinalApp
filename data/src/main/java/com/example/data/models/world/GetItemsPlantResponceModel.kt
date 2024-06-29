package com.example.data.models.world


import com.google.gson.annotations.SerializedName

data class GetItemsPlantResponceModel(
    @SerializedName("results")
    val getItemResponceModels: List<GetItemsResponceModel>
)