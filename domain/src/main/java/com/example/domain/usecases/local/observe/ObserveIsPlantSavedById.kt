package com.example.domain.usecases.local.observe

import kotlinx.coroutines.flow.Flow

interface ObserveIsPlantSavedById {
    operator fun invoke(movieId: String): Flow<Boolean>
}