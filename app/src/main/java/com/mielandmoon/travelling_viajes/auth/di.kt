package com.mielandmoon.travelling_viajes.auth

import com.mielandmoon.travelling_viajes.auth.data.remote.AuthService
import com.mielandmoon.travelling_viajes.auth.data.remote.AuthServiceImpl
import com.mielandmoon.travelling_viajes.auth.data.repository.UserRepositoryImpl
import com.mielandmoon.travelling_viajes.auth.domain.repository.UserRepository
import com.mielandmoon.travelling_viajes.auth.domain.usecase.SignInUseCase
import com.mielandmoon.travelling_viajes.auth.domain.usecase.SignUpUseCase
import com.mielandmoon.travelling_viajes.auth.domain.usecase.validation.ValidateEmailUseCase
import com.mielandmoon.travelling_viajes.auth.domain.usecase.validation.ValidateFirstNameUseCase
import com.mielandmoon.travelling_viajes.auth.domain.usecase.validation.ValidateLastNameUseCase
import com.mielandmoon.travelling_viajes.auth.domain.usecase.validation.ValidatePasswordUseCase
import com.mielandmoon.travelling_viajes.auth.domain.usecase.validation.ValidateRepeatedPasswordUseCase
import com.mielandmoon.travelling_viajes.auth.domain.usecase.validation.ValidateUsernameUseCase
import com.mielandmoon.travelling_viajes.auth.presentation.viewmodel.SignUpViewModel
import com.mielandmoon.travelling_viajes.auth.presentation.viewmodel.SignInViewModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val authModule = module {
    factory { ValidateEmailUseCase() }
    factory { ValidateUsernameUseCase() }
    factory { ValidateFirstNameUseCase() }
    factory { ValidateLastNameUseCase() }
    factory { ValidatePasswordUseCase() }
    factory { ValidateRepeatedPasswordUseCase() }

    single {
        HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                })
            }
        }
    }

    single<AuthService> { AuthServiceImpl(get()) }

    single<UserRepository> { UserRepositoryImpl(get()) }
    factory { SignInUseCase(get()) }
    factory { SignUpUseCase(get()) }

    viewModelOf(::SignUpViewModel)
    viewModelOf(::SignInViewModel)
}