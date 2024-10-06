package com.mielandmoon.travelling_viajes.auth.domain.usecase

import com.mielandmoon.travelling_viajes.auth.domain.repository.UserRepository

class SignUpUseCase(
    private val repository: UserRepository
) {
    suspend fun execute(
        username: String,
        firstName: String,
        lastName: String,
        email: String,
        password: String,
    ) = repository.signUp(
        username = username,
        firstName = firstName,
        lastName = lastName,
        email = email,
        password = password,
    )
}