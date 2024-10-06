package com.mielandmoon.travelling_viajes.core.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

const val USER_DATASTORE = "user_datastore"

val Context.preferencesDataStore: DataStore<Preferences> by preferencesDataStore(name = USER_DATASTORE)
