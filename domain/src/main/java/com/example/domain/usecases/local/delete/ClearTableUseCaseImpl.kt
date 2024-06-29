package com.example.domain.usecases.local.delete

import com.example.domain.repository.detail.repository.DetailsRepository

class ClearTableUseCaseImpl(
    private val repository: DetailsRepository
) : ClearTableUseCase {
    override suspend fun clearTable() {
        return repository.clearTable()
    }
}