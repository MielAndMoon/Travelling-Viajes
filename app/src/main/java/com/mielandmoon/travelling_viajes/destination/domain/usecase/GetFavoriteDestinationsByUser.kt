package com.mielandmoon.travelling_viajes.destination.domain.usecase

import com.mielandmoon.travelling_viajes.destination.domain.repository.DestinationRepository

class GetFavoriteDestinationsByUser(
    private val repository: DestinationRepository
) {
    suspend operator fun invoke(userId: Int) = repository.getFavoriteDestinationsByUserId(userId)
}