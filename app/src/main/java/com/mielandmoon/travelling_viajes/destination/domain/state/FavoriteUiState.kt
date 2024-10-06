package com.mielandmoon.travelling_viajes.destination.domain.state

import com.mielandmoon.travelling_viajes.auth.domain.model.User
import com.mielandmoon.travelling_viajes.destination.domain.model.Destination

data class FavoriteUiState(
    val favoriteDestinations: List<Destination>,
    val userDataStore: User? = null,
)