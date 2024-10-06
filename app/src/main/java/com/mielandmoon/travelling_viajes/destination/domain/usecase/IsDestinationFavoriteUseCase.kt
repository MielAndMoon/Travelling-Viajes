package com.mielandmoon.travelling_viajes.destination.domain.usecase

import com.mielandmoon.travelling_viajes.destination.domain.repository.DestinationRepository

class IsDestinationFavoriteUseCase(
    private val repository: DestinationRepository
) {
    suspend fun execute(destinationId: Int, userId: Int) =
        repository.isFavoriteDestination(destinationId, userId)

}