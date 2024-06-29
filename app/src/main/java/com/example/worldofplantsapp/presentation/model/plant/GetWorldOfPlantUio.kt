package com.example.worldofplantsapp.presentation.model.plant

import androidx.compose.runtime.Immutable
import kotlinx.collections.immutable.persistentListOf
import java.io.Serializable

@Immutable
data class GetWorldOfPlantUioModel(
    val result: List<GetPlantUioModel>
) : Serializable {
    companion object {
        val unknown = GetWorldOfPlantUioModel(
            result = persistentListOf()
        )
    }
}