package com.mielandmoon.travelling_viajes.core.domain.usecase

import com.mielandmoon.travelling_viajes.auth.domain.model.User
import com.mielandmoon.travelling_viajes.core.domain.repository.UserDataStoreRepository

class SaveUserDataStoreUseCase(
    private val repository: UserDataStoreRepository
) {
    suspend fun execute(user: User) = repository.saveUserDataStore(user)
}