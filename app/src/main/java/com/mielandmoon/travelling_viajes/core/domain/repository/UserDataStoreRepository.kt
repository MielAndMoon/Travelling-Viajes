package com.mielandmoon.travelling_viajes.core.domain.repository

import com.mielandmoon.travelling_viajes.auth.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserDataStoreRepository {
    fun getUserDataStore(): Flow<User>
    suspend fun saveUserDataStore(user: User)
    suspend fun clearUserDataStore()
}