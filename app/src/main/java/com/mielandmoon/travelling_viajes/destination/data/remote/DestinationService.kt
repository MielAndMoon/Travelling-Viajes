package com.mielandmoon.travelling_viajes.destination.data.remote

import com.mielandmoon.travelling_viajes.core.utils.API_URL
import com.mielandmoon.travelling_viajes.destination.data.model.FavoriteRequest
import io.ktor.client.HttpClient
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType

interface DestinationService {
    suspend fun getDestinations(): HttpResponse
    suspend fun getDestination(id: Int): HttpResponse
    suspend fun getFavoriteDestinationsByUserId(userId: Int): HttpResponse
    suspend fun isFavoriteDestination(destinationId: Int, userId: Int): HttpResponse
    suspend fun addFavoriteDestination(destinationId: Int, userId: Int): HttpResponse
    suspend fun removeFavoriteDestination(destinationId: Int, userId: Int): HttpResponse
}

class DestinationServiceImpl(
    private val client: HttpClient
) : DestinationService {
    override suspend fun getDestinations(): HttpResponse {
        return client.get("$API_URL/destinations")
    }

    override suspend fun getDestination(id: Int): HttpResponse {
        return client.get("$API_URL/destinations/$id")
    }

    override suspend fun getFavoriteDestinationsByUserId(userId: Int): HttpResponse {
        return client.get("$API_URL/favorites/$userId")
    }

    override suspend fun isFavoriteDestination(destinationId: Int, userId: Int): HttpResponse {
        return client.get("$API_URL/favorites/$userId/$destinationId")
    }

    override suspend fun addFavoriteDestination(destinationId: Int, userId: Int): HttpResponse {
        return client.post("$API_URL/favorites/add") {
            contentType(ContentType.Application.Json)
            setBody(
                FavoriteRequest(
                    userId = userId,
                    destinationId = destinationId
                )
            )
        }
    }

    override suspend fun removeFavoriteDestination(destinationId: Int, userId: Int): HttpResponse {
        return client.delete("$API_URL/favorites/remove/$userId/$destinationId")
    }
}