package com.mielandmoon.travelling_viajes.core.presentation.navigation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mielandmoon.travelling_viajes.core.presentation.model.DrawerItemModel
import com.mielandmoon.travelling_viajes.core.presentation.navigation.Routes

@Composable
fun NavigationBarItem(
    modifier: Modifier = Modifier,
    drawerItem: DrawerItemModel,
    selected: Boolean,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        onClick = onClick,
        colors = CardDefaults.cardColors(
            containerColor = if (selected) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.primary
        ),
        shape = RoundedCornerShape(32.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = if (selected) drawerItem.iconSelected else drawerItem.icon,
                contentDescription = drawerItem.label,
                modifier = Modifier.padding(16.dp)
            )
            Text(
                text = drawerItem.label,
                style = MaterialTheme.typography.titleMedium,
            )
        }
    }
}