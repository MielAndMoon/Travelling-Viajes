package com.mielandmoon.travelling_viajes.auth.data.remote

import com.mielandmoon.travelling_viajes.auth.data.model.LoginRequest
import com.mielandmoon.travelling_viajes.auth.data.model.RandomImageDto
import com.mielandmoon.travelling_viajes.auth.data.model.SignUpRequest
import com.mielandmoon.travelling_viajes.core.utils.API_IMAGE_URL
import com.mielandmoon.travelling_viajes.core.utils.API_URL
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType

interface AuthService {
    suspend fun getUsers(): HttpResponse
    suspend fun signIn(email: String, password: String): HttpResponse
    suspend fun signUp(
        email: String,
        password: String,
        username: String,
        firstName: String,
        lastName: String,
    ): HttpResponse
}

class AuthServiceImpl(
    private val client: HttpClient
) : AuthService {
    override suspend fun getUsers(): HttpResponse = client.get("$API_URL/users")

    override suspend fun signIn(email: String, password: String): HttpResponse {
        return client.post("$API_URL/users/signin") {
            contentType(ContentType.Application.Json)
            setBody(LoginRequest(email = email, password = password))
        }
    }

    override suspend fun signUp(
        email: String,
        password: String,
        username: String,
        firstName: String,
        lastName: String,
    ): HttpResponse {
        val randomImageDto: RandomImageDto = client.get(API_IMAGE_URL).body()
        val imageUrl = randomImageDto.results.first().picture.large

        return client.post("$API_URL/users/register") {
            contentType(ContentType.Application.Json)
            setBody(
                SignUpRequest(
                    email = email,
                    password = password,
                    username = username,
                    firstName = firstName,
                    lastName = lastName,
                    imageUrl = imageUrl
                )
            )
        }
    }
}