package com.mielandmoon.travelling_viajes.destination.presentation.components.place_detail

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mielandmoon.travelling_viajes.core.utils.COLLAPSED_TOOLBAR_HEIGHT
import com.mielandmoon.travelling_viajes.core.utils.EXPANDED_TOOLBAR_HEIGHT
import com.mielandmoon.travelling_viajes.destination.domain.model.Destination

@Composable
fun CollapsedToolbar(
    isFavorite: Boolean,
    destination: Destination,
    onAddToFavorite: () -> Unit,
    isCollapsed: Boolean,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {}
) {
    val color: Color by animateColorAsState(
        if (isCollapsed) MaterialTheme.colorScheme.background else Color.Transparent, label = ""
    )

    AnimatedVisibility(
        visible = isCollapsed,
        modifier = modifier
            .background(color)
            .statusBarsPadding()
            .fillMaxWidth()
            .height(COLLAPSED_TOOLBAR_HEIGHT),
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            HeaderButton(
                icon = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                contentDescription = "Account icon",
                modifier = Modifier
                    .padding(16.dp),
                onClick = onBackClick
            )
            Text(
                destination.name,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(16.dp)
            )
            HeaderButton(
                icon = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                contentDescription = "Account icon",
                onClick = {
                    onAddToFavorite()
                },
                modifier = Modifier
                    .padding(16.dp)
            )
        }
    }
}

@Composable
fun ExpandedToolbar(
    onAddToFavorite: () -> Unit,
    destination: Destination,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    isFavorite: Boolean
) {
    Box(
        modifier = modifier
            .background(MaterialTheme.colorScheme.primaryContainer)
            .fillMaxWidth()
            .height(EXPANDED_TOOLBAR_HEIGHT),

        contentAlignment = Alignment.BottomStart
    ) {
        AsyncImage(
            model = destination.imageUrl,
            contentScale = ContentScale.Crop,
            contentDescription = "Image of ${destination.name}",
            modifier = Modifier
                .fillMaxSize()
        )
        HeaderButton(
            icon = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
            contentDescription = "Account icon",
            modifier = Modifier
                .align(Alignment.TopStart)
                .statusBarsPadding()
                .padding(16.dp),
            onClick = onBackClick
        )
        HeaderButton(
            icon = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
            contentDescription = "Account icon",
            onClick = {
                onAddToFavorite()
            },
            modifier = Modifier
                .align(Alignment.TopEnd)
                .statusBarsPadding()
                .padding(16.dp)
        )
        PlaceInfo(destination = destination, modifier = Modifier.align(Alignment.BottomStart))
    }
}