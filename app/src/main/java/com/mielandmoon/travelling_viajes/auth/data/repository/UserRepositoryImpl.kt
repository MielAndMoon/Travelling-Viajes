package com.mielandmoon.travelling_viajes.auth.data.repository

import com.mielandmoon.travelling_viajes.auth.data.model.UserDto
import com.mielandmoon.travelling_viajes.auth.data.remote.AuthService
import com.mielandmoon.travelling_viajes.auth.domain.model.User
import com.mielandmoon.travelling_viajes.auth.domain.repository.UserRepository
import com.mielandmoon.travelling_viajes.common.domain.model.SignInError
import com.mielandmoon.travelling_viajes.common.domain.model.Result
import com.mielandmoon.travelling_viajes.common.domain.model.SignUpError
import io.ktor.client.call.body

class UserRepositoryImpl(
    private val authService: AuthService
) : UserRepository {
//    override fun getUsers(): Flow<List<User>> = flow {
//        val userDtos: List<UserDto> = apiService.getUsers()
//        val users = userDtos.map { it.toDomain() }
//        emit(users)
//    }

    override suspend fun signIn(email: String, password: String): Result<User, SignInError> {
        return try {
            val response = authService.signIn(email, password)

            when (val status = response.status) {
                io.ktor.http.HttpStatusCode.OK -> {
                    val userDto: UserDto = response.body()
                    Result.Success(userDto.toDomain())
                }

                io.ktor.http.HttpStatusCode.Unauthorized, io.ktor.http.HttpStatusCode.NotFound -> {
                    Result.Error(SignInError.INVALID_CREDENTIALS)
                }

                else -> {
                    Result.Error(SignInError.SERVER_ERROR)
                }
            }
        } catch (e: Exception) {
            Result.Error(SignInError.NETWORK_ERROR)
        }
    }

    override suspend fun signUp(
        username: String,
        firstName: String,
        lastName: String,
        email: String,
        password: String,
    ): Result<User, SignUpError> {
        return try {
            val response = authService.signUp(
                email = email,
                password = password,
                username = username,
                firstName = firstName,
                lastName = lastName,
            )

            when (val status = response.status) {
                io.ktor.http.HttpStatusCode.OK -> {
                    val userDto: UserDto = response.body()
                    Result.Success(userDto.toDomain())
                }

                else -> {
                    Result.Error(SignUpError.SERVER_ERROR)
                }
            }
        } catch (e: Exception) {
            Result.Error(SignUpError.NETWORK_ERROR)
        }
    }
}