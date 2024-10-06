package com.mielandmoon.travelling_viajes.destination.domain.state

import com.mielandmoon.travelling_viajes.destination.domain.model.Destination

data class DestinationDetailUiState(
    val destination: Destination? = null,
    val isFavorite: Boolean = false,
)
