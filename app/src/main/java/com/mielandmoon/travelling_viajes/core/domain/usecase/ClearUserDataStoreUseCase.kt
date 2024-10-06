package com.mielandmoon.travelling_viajes.core.domain.usecase

import com.mielandmoon.travelling_viajes.core.domain.repository.UserDataStoreRepository

class ClearUserDataStoreUseCase(
    private val repository: UserDataStoreRepository
) {
    suspend fun execute() = repository.clearUserDataStore()
}