package com.mielandmoon.travelling_viajes.auth.domain.repository

import com.mielandmoon.travelling_viajes.auth.domain.model.User
import com.mielandmoon.travelling_viajes.common.domain.model.Result
import com.mielandmoon.travelling_viajes.common.domain.model.SignInError
import com.mielandmoon.travelling_viajes.common.domain.model.SignUpError

interface UserRepository {
    //    fun getUsers(): Flow<List<User>>
    suspend fun signIn(email: String, password: String): Result<User, SignInError>
    suspend fun signUp(
        username: String,
        firstName: String,
        lastName: String,
        email: String,
        password: String,
    ): Result<User, SignUpError>
}