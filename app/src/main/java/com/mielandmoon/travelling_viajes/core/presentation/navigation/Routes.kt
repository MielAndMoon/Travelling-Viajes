package com.mielandmoon.travelling_viajes.core.presentation.navigation

import androidx.navigation.NavHostController
import kotlinx.serialization.Serializable

interface Routes

@Serializable
object HomeRoute : Routes

@Serializable
data class PlaceDetailRoute(
    val placeId: Int
) : Routes

@Serializable
object SignInRoute : Routes

@Serializable
object SignUpRoute : Routes

@Serializable
object FavoritePlacesRoute : Routes

fun navigateTo(navController: NavHostController, route: Routes) {
    navController.navigate(route)
}