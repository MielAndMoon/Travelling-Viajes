package com.mielandmoon.travelling_viajes.presentation.navigation

import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.mielandmoon.travelling_viajes.domain.model.Place
import com.mielandmoon.travelling_viajes.presentation.components.BaseScaffold
import com.mielandmoon.travelling_viajes.presentation.components.PlaceDetail
import com.mielandmoon.travelling_viajes.presentation.components.PlacesList
import kotlinx.coroutines.CoroutineScope

@Composable
fun MainNavHost(
    navController: NavHostController,
    scope: CoroutineScope,
    places: List<Place>,
    drawerState: DrawerState,
) {
    NavHost(
        navController = navController,
        startDestination = HomeScreen
    ) {
        composable<HomeScreen> {
            BaseScaffold(
                scope = scope,
                drawerState = drawerState,
            ) {
                PlacesList(
                    places = places,
                    padding = it,
                )
            }
        }
    }
}