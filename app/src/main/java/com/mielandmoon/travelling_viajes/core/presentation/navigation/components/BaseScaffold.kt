package com.mielandmoon.travelling_viajes.core.presentation.navigation.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mielandmoon.travelling_viajes.core.presentation.navigation.Routes
import com.mielandmoon.travelling_viajes.core.presentation.navigation.SignInRoute
import com.mielandmoon.travelling_viajes.core.presentation.viewmodel.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun BaseScaffold(
    viewModel: MainViewModel = koinViewModel<MainViewModel>(),
    onNavigate: (Routes) -> Unit,
    title: String,
    itemSelected: Int,
    drawerState: DrawerState,
    scope: CoroutineScope,
    onNavigateRoute: (Routes) -> Unit,
    updateSelectedItem: (Int) -> Unit,
    content: @Composable (innerPadding: PaddingValues) -> Unit,
) {
    val state by viewModel.userDataStore.collectAsStateWithLifecycle()

    MainNavigationDrawer(
        user = state,
        drawerState = drawerState,
        itemSelected = itemSelected,
        onClick = {
            onNavigateRoute(it)
        },
        onLogout = {
            onNavigate(SignInRoute)
            viewModel.logout()
        },
        updateSelectedItem = updateSelectedItem
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopBar(
                    title = title,
                    onClick = {
                        scope.launch {
                            drawerState.open()
                        }
                    })
            }
        ) { innerPadding ->
            content(innerPadding)
        }
    }
}