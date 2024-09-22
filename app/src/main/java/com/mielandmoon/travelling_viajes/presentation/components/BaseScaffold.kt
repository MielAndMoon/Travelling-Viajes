package com.mielandmoon.travelling_viajes.presentation.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun BaseScaffold(
    drawerState: DrawerState,
    scope: CoroutineScope,
    content: @Composable (innerPadding: PaddingValues) -> Unit
) {
    MainNavigationDrawer (
        drawerState = drawerState,
        onClick = {
            scope.launch {
                drawerState.close()
            }
        }
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopBar(onClick = {
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