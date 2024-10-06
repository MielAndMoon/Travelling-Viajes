package com.mielandmoon.travelling_viajes.destination.domain.usecase

import com.mielandmoon.travelling_viajes.destination.domain.repository.DestinationRepository

class AddToFavoriteUseCase(
    private val repository: DestinationRepository
) {
    suspend fun execute(destinationId: Int, userId: Int) =
        repository.addFavoriteDestination(destinationId, userId)
}