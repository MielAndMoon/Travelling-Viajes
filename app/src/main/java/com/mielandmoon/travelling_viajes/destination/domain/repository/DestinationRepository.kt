package com.mielandmoon.travelling_viajes.destination.domain.repository

import com.mielandmoon.travelling_viajes.common.domain.model.DestinationError
import com.mielandmoon.travelling_viajes.destination.domain.model.Destination
import com.mielandmoon.travelling_viajes.common.domain.model.Result
import com.mielandmoon.travelling_viajes.destination.domain.model.Favorite
import kotlinx.coroutines.flow.Flow

interface DestinationRepository {
    suspend fun getDestinations(): Flow<Result<List<Destination>, DestinationError>>
    suspend fun getDestination(id: Int): Result<Destination, DestinationError>
    suspend fun getFavoriteDestinationsByUserId(userId: Int): Flow<Result<List<Destination>, DestinationError>>
    suspend fun isFavoriteDestination(
        destinationId: Int,
        userId: Int
    ): Result<Boolean, DestinationError>

    suspend fun addFavoriteDestination(
        destinationId: Int,
        userId: Int
    ): Result<Favorite, DestinationError>

    suspend fun removeFavoriteDestination(
        destinationId: Int,
        userId: Int
    ): Result<Favorite, DestinationError>
}