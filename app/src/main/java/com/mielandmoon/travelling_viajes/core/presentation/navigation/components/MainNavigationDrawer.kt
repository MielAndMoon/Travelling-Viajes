package com.mielandmoon.travelling_viajes.core.presentation.navigation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.automirrored.outlined.Logout
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Bookmarks
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.DrawerState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mielandmoon.travelling_viajes.auth.domain.model.User
import com.mielandmoon.travelling_viajes.core.presentation.model.DrawerItemModel
import com.mielandmoon.travelling_viajes.core.presentation.navigation.FavoritePlacesRoute
import com.mielandmoon.travelling_viajes.core.presentation.navigation.HomeRoute
import com.mielandmoon.travelling_viajes.core.presentation.navigation.Routes

@Composable
fun MainNavigationDrawer(
    user: User?,
    modifier: Modifier = Modifier,
    drawerState: DrawerState,
    itemSelected: Int,
    onLogout: () -> Unit,
    onClick: (Routes) -> Unit,
    updateSelectedItem: (Int) -> Unit,
    content: @Composable () -> Unit,
) {

    val items = listOf(
        DrawerItemModel(
            icon = Icons.Outlined.Home,
            iconSelected = Icons.Default.Home,
            label = "Destinos",
            route = HomeRoute
        ),
        DrawerItemModel(
            icon = Icons.Outlined.FavoriteBorder,
            iconSelected = Icons.Default.Favorite,
            label = "Destinos Favoritos",
            route = FavoritePlacesRoute
        ),
//        DrawerItemModel(
//            icon = Icons.Outlined.Bookmarks,
//            iconSelected = Icons.Default.Bookmarks,
//            label = "Bookings",
//            route = HomeRoute
//        ),
    )

    val logoutItem = DrawerItemModel(
        icon = Icons.AutoMirrored.Outlined.Logout,
        iconSelected = Icons.AutoMirrored.Filled.Logout,
        label = "Logout",
        route = HomeRoute
    )

    ModalNavigationDrawer(
        modifier = modifier,
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = MaterialTheme.colorScheme.primary,
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AsyncImage(
                        model = user?.imageUrl ?: "https://images.pexels.com/photos/2906662/pexels-photo-2906662.png?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
                        contentDescription = "User Image",
                        modifier = Modifier
                            .padding(16.dp)
                            .size(64.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.FillWidth
                    )
                    Column {
                        Text(
                            text = user?.firstName ?: "User Name",
                            style = MaterialTheme.typography.headlineSmall,
                        )
                        Text(
                            text = "@${user?.username ?: "Username"}",
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }
                }

                // Add a divider
                HorizontalDivider(
                    color = MaterialTheme.colorScheme.surface.copy(alpha = 0.20f),
                    modifier = Modifier.padding(vertical = 16.dp)
                )

                items.forEachIndexed() { index, item ->
                    NavigationBarItem(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        drawerItem = item,
                        selected = itemSelected == index,
                        onClick = {
                            updateSelectedItem(index)
                            onClick(item.route)
                        }
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                NavigationBarItem(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    drawerItem = logoutItem,
                    selected = false,
                    onClick = {
                        onLogout()
                    }
                )
            }
        },
        drawerState = drawerState,
    ) {
        content()
    }
}