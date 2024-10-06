package com.mielandmoon.travelling_viajes.core.domain.usecase

import com.mielandmoon.travelling_viajes.auth.domain.model.User
import com.mielandmoon.travelling_viajes.core.domain.repository.UserDataStoreRepository
import kotlinx.coroutines.flow.Flow

class GetUserDataStoreUseCase(
    private val repository: UserDataStoreRepository
) {
    fun execute(): Flow<User> = repository.getUserDataStore()
}