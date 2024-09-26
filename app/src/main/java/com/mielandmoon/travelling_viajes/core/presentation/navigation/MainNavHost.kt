package com.mielandmoon.travelling_viajes.core.presentation.navigation

import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mielandmoon.travelling_viajes.place.domain.model.Place
import com.mielandmoon.travelling_viajes.core.presentation.navigation.components.BaseScaffold
import com.mielandmoon.travelling_viajes.place.presentation.screens.PlacesListScreen
import kotlinx.coroutines.CoroutineScope

@Composable
fun MainNavHost(
    navController: NavHostController,
    scope: CoroutineScope,
    places: List<Place>,
    drawerState: DrawerState,
) {
    NavHost(
        navController = navController, startDestination = HomeRoute
    ) {
        composable<HomeRoute> {
            BaseScaffold(
                scope = scope,
                drawerState = drawerState,
            ) {
                PlacesListScreen(
                    places = places,
                    padding = it,
                )
            }
        }
    }
}