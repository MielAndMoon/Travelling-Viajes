package com.mielandmoon.travelling_viajes.core

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.mielandmoon.travelling_viajes.core.data.repository.UserDataStoreRepositoryImpl
import com.mielandmoon.travelling_viajes.core.domain.repository.UserDataStoreRepository
import com.mielandmoon.travelling_viajes.core.domain.usecase.SaveUserDataStoreUseCase
import com.mielandmoon.travelling_viajes.core.domain.usecase.GetUserDataStoreUseCase
import com.mielandmoon.travelling_viajes.core.domain.usecase.ClearUserDataStoreUseCase
import com.mielandmoon.travelling_viajes.core.presentation.viewmodel.MainViewModel
import com.mielandmoon.travelling_viajes.core.utils.preferencesDataStore
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    single<DataStore<Preferences>> {
        androidContext().preferencesDataStore
    }
    single<UserDataStoreRepository> { UserDataStoreRepositoryImpl(get()) }
    factoryOf(::SaveUserDataStoreUseCase)
    factoryOf(::GetUserDataStoreUseCase)
    factoryOf(::ClearUserDataStoreUseCase)

    viewModelOf(::MainViewModel)
}