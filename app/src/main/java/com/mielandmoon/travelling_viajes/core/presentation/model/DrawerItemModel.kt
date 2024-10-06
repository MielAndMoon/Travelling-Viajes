package com.mielandmoon.travelling_viajes.core.presentation.model

import androidx.compose.ui.graphics.vector.ImageVector
import com.mielandmoon.travelling_viajes.core.presentation.navigation.Routes

data class DrawerItemModel(
    val icon: ImageVector,
    val iconSelected: ImageVector,
    val label: String,
    val route: Routes
)
