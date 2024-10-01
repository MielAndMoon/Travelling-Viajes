package com.mielandmoon.travelling_viajes.core.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
object HomeRoute

@Serializable
data class PlaceDetailRoute(
    val placeId: Int
)

@Serializable
object SignInRoute

@Serializable
object SignUpRoute