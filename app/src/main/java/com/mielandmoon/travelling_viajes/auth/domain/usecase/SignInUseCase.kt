package com.mielandmoon.travelling_viajes.auth.domain.usecase

import com.mielandmoon.travelling_viajes.auth.domain.model.User
import com.mielandmoon.travelling_viajes.auth.domain.repository.UserRepository
import com.mielandmoon.travelling_viajes.common.domain.model.Result
import com.mielandmoon.travelling_viajes.common.domain.model.SignInError

class SignInUseCase(
    private val repository: UserRepository
) {
    suspend fun execute(email: String, password: String): Result<User, SignInError> {
        return repository.signIn(email, password)
    }
}