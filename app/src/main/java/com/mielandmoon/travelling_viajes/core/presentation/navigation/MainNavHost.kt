package com.mielandmoon.travelling_viajes.core.presentation.navigation

import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.mielandmoon.travelling_viajes.auth.presentation.screens.SignInScreen
import com.mielandmoon.travelling_viajes.auth.presentation.screens.SignUpScreen
import com.mielandmoon.travelling_viajes.core.presentation.navigation.components.BaseScaffold
import com.mielandmoon.travelling_viajes.core.presentation.viewmodel.MainViewModel
import com.mielandmoon.travelling_viajes.destination.domain.model.Destination
import com.mielandmoon.travelling_viajes.destination.presentation.screens.FavoritePlacesScreen
import com.mielandmoon.travelling_viajes.destination.presentation.screens.PlaceDetailScreen
import com.mielandmoon.travelling_viajes.destination.presentation.screens.PlacesListScreen
import com.mielandmoon.travelling_viajes.destination.presentation.viewmodel.DestinationViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainNavHost(
    viewModel: MainViewModel = koinViewModel<MainViewModel>(),
    destinationViewModel: DestinationViewModel = koinViewModel(),
    navController: NavHostController,
    itemSelected: Int,
    drawerState: DrawerState,
    updateSelectedItem: (Int) -> Unit,
) {
    val state by viewModel.userDataStore.collectAsStateWithLifecycle()
    val stateDestination by destinationViewModel.state.collectAsStateWithLifecycle()

    val scope = rememberCoroutineScope()

    val startDestination = if (state != null) {
        HomeRoute
    } else {
        SignInRoute
    }

    NavHost(
        navController = navController, startDestination = startDestination
    ) {
        composable<SignInRoute> {
            SignInScreen(
                onNavigateToHome = {
                    navController.navigate(HomeRoute)
                },
                onNavigateToSignUp = {
                    navController.navigate(SignUpRoute)
                },
            )
        }
        composable<SignUpRoute> {
            SignUpScreen(
                onNavigateToHome = {
                    navController.navigate(HomeRoute)
                },
                onNavigateToSignIn = {
                    navController.navigate(SignInRoute)
                },
            )
        }
        composable<HomeRoute> {
            BaseScaffold(
                title = "Travelling Viajes",
                scope = scope,
                drawerState = drawerState,
                itemSelected = itemSelected,
                updateSelectedItem = updateSelectedItem,
                onNavigate = { route ->
                    navigateTo(navController, route)
                },
                onNavigateRoute = {
                    scope.launch {
                        drawerState.close()
                    }
                    navController.navigate(it)
                }
            ) {
                PlacesListScreen(
                    destinations = stateDestination.destinations,
                    padding = it,
                    onClick = { placeId ->
                        navController.navigate(PlaceDetailRoute(placeId))
                    }
                )
            }
        }

        composable<PlaceDetailRoute> {
            val args = it.toRoute<PlaceDetailRoute>()

            PlaceDetailScreen(
                destinationId = args.placeId,
                userId = state?.id!!,
                onBackClick = {
                    navController.navigateUp()
                },
            )
        }

        composable<FavoritePlacesRoute> {
            BaseScaffold(
                title = "Favorite Places",
                itemSelected = itemSelected,
                drawerState = drawerState,
                scope = scope,
                onNavigate = { route ->
                    navigateTo(navController, route)
                },
                onNavigateRoute = {
                    scope.launch {
                        drawerState.close()
                    }
                    navController.navigate(it)
                },
                updateSelectedItem = updateSelectedItem,
            ) {
                FavoritePlacesScreen(
                    userDataStore = state?.id!!,
                    padding = it,
                    onClick = { placeId ->
                        navController.navigate(PlaceDetailRoute(placeId = placeId))
                    },
                )
            }
        }
    }
}
