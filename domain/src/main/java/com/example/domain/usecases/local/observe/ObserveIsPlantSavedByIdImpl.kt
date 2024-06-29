package com.example.domain.usecases.local.observe

import com.example.domain.repository.detail.repository.DetailsRepository
import kotlinx.coroutines.flow.Flow

class ObserveIsPlantSavedByIdImpl(
    private val repository: DetailsRepository
) : ObserveIsPlantSavedById {
    override fun invoke(movieId: String): Flow<Boolean> {
        return repository.observeIsPlantsSaved(movieId)
    }
}