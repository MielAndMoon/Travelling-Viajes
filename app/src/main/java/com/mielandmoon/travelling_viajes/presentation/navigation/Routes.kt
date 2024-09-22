package com.mielandmoon.travelling_viajes.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
object HomeScreen

@Serializable
data class PlaceDetailScreen(
    val placeId: Int
)