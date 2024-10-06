package com.mielandmoon.travelling_viajes.destination.domain.state

import com.mielandmoon.travelling_viajes.destination.domain.model.Destination

data class DestinationUiState(
    val destinations: List<Destination>,
    val selectedDestination: Destination? = null,
)