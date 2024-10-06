package com.mielandmoon.travelling_viajes.destination.domain.usecase

import com.mielandmoon.travelling_viajes.destination.domain.repository.DestinationRepository

class GetDestinationsUseCase(
    private val repository: DestinationRepository
) {
    suspend fun execute() = repository.getDestinations()
}