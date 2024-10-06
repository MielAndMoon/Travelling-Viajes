package com.mielandmoon.travelling_viajes.destination.data.repository

import com.mielandmoon.travelling_viajes.common.domain.model.DestinationError
import com.mielandmoon.travelling_viajes.common.domain.model.Result
import com.mielandmoon.travelling_viajes.destination.data.model.DestinationDto
import com.mielandmoon.travelling_viajes.destination.data.model.FavoriteDto
import com.mielandmoon.travelling_viajes.destination.data.remote.DestinationService
import com.mielandmoon.travelling_viajes.destination.domain.model.Destination
import com.mielandmoon.travelling_viajes.destination.domain.model.Favorite
import com.mielandmoon.travelling_viajes.destination.domain.repository.DestinationRepository
import io.ktor.client.call.body
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DestinationRepositoryImpl(
    private val destinationService: DestinationService
) : DestinationRepository {
    override suspend fun getDestinations(): Flow<Result<List<Destination>, DestinationError>> =
        flow {
            try {
                val response = destinationService.getDestinations()

                when (response.status) {
                    io.ktor.http.HttpStatusCode.OK -> {
                        val destinationDtos: List<DestinationDto> = response.body()
                        val destinations = destinationDtos.map { it.toDomain() }
                        emit(Result.Success(destinations))
                    }

                    else -> {
                        emit(Result.Error(DestinationError.SERVER_ERROR))
                    }
                }
            } catch (e: Exception) {
                emit(Result.Error(DestinationError.NETWORK_ERROR))
            }

        }

    override suspend fun getDestination(id: Int): Result<Destination, DestinationError> {
        return try {
            val response = destinationService.getDestination(id)

            when (response.status) {
                io.ktor.http.HttpStatusCode.OK -> {
                    val destinationDto: DestinationDto = response.body()
                    Result.Success(destinationDto.toDomain())
                }

                else -> {
                    Result.Error(DestinationError.SERVER_ERROR)
                }
            }
        } catch (e: Exception) {
            Result.Error(DestinationError.NETWORK_ERROR)
        }
    }

    override suspend fun getFavoriteDestinationsByUserId(userId: Int): Flow<Result<List<Destination>, DestinationError>> {
        return flow {
            try {
                val response = destinationService.getFavoriteDestinationsByUserId(userId)

                when (response.status) {
                    io.ktor.http.HttpStatusCode.OK -> {
                        val destinationDtos: List<DestinationDto> = response.body()
                        val destinations = destinationDtos.map { it.toDomain() }
                        emit(Result.Success(destinations))
                    }

                    else -> {
                        emit(Result.Error(DestinationError.SERVER_ERROR))
                    }
                }
            } catch (e: Exception) {
                emit(Result.Error(DestinationError.NETWORK_ERROR))
            }
        }
    }

    override suspend fun isFavoriteDestination(
        destinationId: Int,
        userId: Int
    ): Result<Boolean, DestinationError> {
        return try {
            val response = destinationService.isFavoriteDestination(destinationId, userId)
            when (response.status) {
                io.ktor.http.HttpStatusCode.OK -> {
                    val isFavorite: Boolean = response.body()
                    Result.Success(isFavorite)
                }

                else -> {
                    Result.Error(DestinationError.SERVER_ERROR)
                }
            }
        } catch (e: Exception) {
            Result.Error(DestinationError.NETWORK_ERROR)
        }
    }

    override suspend fun addFavoriteDestination(
        destinationId: Int,
        userId: Int
    ): Result<Favorite, DestinationError> {
        return try {
            val response = destinationService.addFavoriteDestination(destinationId, userId)
            when (response.status) {
                io.ktor.http.HttpStatusCode.OK -> {
                    val favorite: FavoriteDto = response.body()
                    Result.Success(favorite.toDomain())
                }

                else -> {
                    Result.Error(DestinationError.SERVER_ERROR)
                }
            }
        } catch (e: Exception) {
            Result.Error(DestinationError.NETWORK_ERROR)
        }
    }

    override suspend fun removeFavoriteDestination(
        destinationId: Int,
        userId: Int
    ): Result<Favorite, DestinationError> {
        return try {
            val response = destinationService.removeFavoriteDestination(destinationId, userId)
            when (response.status) {
                io.ktor.http.HttpStatusCode.OK -> {
                    val favorite: FavoriteDto = response.body()
                    Result.Success(favorite.toDomain())
                }

                else -> {
                    Result.Error(DestinationError.SERVER_ERROR)
                }
            }
        } catch (e: Exception) {
            Result.Error(DestinationError.NETWORK_ERROR)
        }
    }
}