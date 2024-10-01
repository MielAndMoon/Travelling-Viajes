package com.mielandmoon.travelling_viajes.core.presentation.navigation

import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.mielandmoon.travelling_viajes.auth.presentation.screens.SignInScreen
import com.mielandmoon.travelling_viajes.auth.presentation.screens.SignUpScreen
import com.mielandmoon.travelling_viajes.place.domain.model.Place
import com.mielandmoon.travelling_viajes.core.presentation.navigation.components.BaseScaffold
import com.mielandmoon.travelling_viajes.place.presentation.screens.PlaceDetailScreen
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
        navController = navController, startDestination = SignInRoute
    ) {
        composable<SignInRoute> {
            SignInScreen(
                onNavigateToHome = {
                    navController.navigate(HomeRoute)
                }
            ) {
                navController.navigate(SignUpRoute)
            }
        }
        composable<SignUpRoute> {
            SignUpScreen(
                onNavigateToHome = {
                    navController.navigate(HomeRoute)
                }
            ) {
                navController.navigate(SignInRoute)
            }
        }
        composable<HomeRoute> {
            BaseScaffold(
                scope = scope,
                drawerState = drawerState,
            ) {
                PlacesListScreen(
                    places = places,
                    padding = it,
                    onClick = { placeId ->
                        navController.navigate(PlaceDetailRoute(placeId))
                    }
                )
            }
        }

        composable<PlaceDetailRoute> {
            val args = it.toRoute<PlaceDetailRoute>()
            val place = places.find { place -> place.id == args.placeId }

            PlaceDetailScreen(
                place = place!!,
                onBackClick = {
                    navController.popBackStack()
                },
            )
        }
    }
}