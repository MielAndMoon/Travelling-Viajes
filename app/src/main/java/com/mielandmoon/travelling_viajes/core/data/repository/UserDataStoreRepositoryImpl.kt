package com.mielandmoon.travelling_viajes.core.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.mielandmoon.travelling_viajes.auth.domain.model.User
import com.mielandmoon.travelling_viajes.core.domain.repository.UserDataStoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserDataStoreRepositoryImpl(
    private val dataStore: DataStore<Preferences>
) : UserDataStoreRepository {

    companion object {
        val EMAIL = stringPreferencesKey("EMAIL")
        val FIRST_NAME = stringPreferencesKey("FIRST_NAME")
        val LAST_NAME = stringPreferencesKey("LAST_NAME")
        val USERNAME = stringPreferencesKey("USERNAME")
        val IMAGE_URL = stringPreferencesKey("IMAGE_URL")
        val ID = stringPreferencesKey("ID")
    }

    override fun getUserDataStore(): Flow<User> {
        return dataStore.data.map {
            User(
                firstName = it[FIRST_NAME] ?: "",
                lastName = it[LAST_NAME] ?: "",
                username = it[USERNAME] ?: "",
                email = it[EMAIL] ?: "",
                imageUrl = it[IMAGE_URL] ?: "",
                id = it[ID]?.toInt() ?: 0
            )
        }
    }

    override suspend fun saveUserDataStore(user: User) {
        dataStore.edit {
            it[EMAIL] = user.email
            it[FIRST_NAME] = user.firstName
            it[LAST_NAME] = user.lastName
            it[USERNAME] = user.username
            it[IMAGE_URL] = user.imageUrl
            it[ID] = user.id.toString()
        }
    }

    override suspend fun clearUserDataStore() {
        dataStore.edit { it.clear() }
    }
}