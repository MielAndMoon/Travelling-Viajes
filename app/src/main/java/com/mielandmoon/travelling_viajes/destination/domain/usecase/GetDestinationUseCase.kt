package com.mielandmoon.travelling_viajes.destination.domain.usecase

import com.mielandmoon.travelling_viajes.destination.domain.repository.DestinationRepository

class GetDestinationUseCase(
    private val repository: DestinationRepository
) {
    suspend fun execute(id: Int) = repository.getDestination(id)
}