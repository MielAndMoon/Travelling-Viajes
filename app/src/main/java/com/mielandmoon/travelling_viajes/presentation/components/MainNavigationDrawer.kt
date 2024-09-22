package com.mielandmoon.travelling_viajes.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.DrawerState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mielandmoon.travelling_viajes.presentation.model.DrawerItemModel

@Composable
fun MainNavigationDrawer(
    modifier: Modifier = Modifier,
    drawerState: DrawerState,
    onClick: () -> Unit,
    content: @Composable () -> Unit
) {

    val items = listOf(
        DrawerItemModel(icon = Icons.Outlined.Home, iconSelected = Icons.Default.Home, label = "Home"),
        DrawerItemModel(
            icon = Icons.Outlined.Search,
            iconSelected = Icons.Default.Search,
            label = "Search"
        ),
        DrawerItemModel(
            icon = Icons.Outlined.Person,
            iconSelected = Icons.Default.Person,
            label = "Profile"
        ),
        DrawerItemModel(
            icon = Icons.Outlined.Settings,
            iconSelected = Icons.Default.Settings,
            label = "Settings"
        )
    )

    val selectedItem by remember { mutableStateOf(0) }

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
                        model = "https://images.pexels.com/photos/2906662/pexels-photo-2906662.png?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
                        contentDescription = "Account icon",
                        modifier = Modifier
                            .padding(16.dp)
                            .size(64.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.FillWidth
                    )
                    Column {
                        Text(
                            text = "Katty Milene",
                            style = MaterialTheme.typography.headlineSmall,
                        )
                        Text(
                            text = "@kattymilene",
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
                        selected = selectedItem == index,
                        onClick = onClick
                    )
                }
            }
        },
        drawerState = drawerState,
    ) {
        content()
    }
}