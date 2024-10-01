package com.mielandmoon.travelling_viajes.place.presentation.components.place_detail

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
import com.mielandmoon.travelling_viajes.place.domain.model.Place

@Composable
fun CollapsedToolbar(
    place: Place,
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
                place.name,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(16.dp)
            )
            HeaderButton(
                icon = Icons.Default.FavoriteBorder,
                contentDescription = "Account icon",
                onClick = {},
                modifier = Modifier
                    .padding(16.dp)
            )
        }
    }
}

@Composable
fun ExpandedToolbar(
    place: Place,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .background(MaterialTheme.colorScheme.primaryContainer)
            .fillMaxWidth()
            .height(EXPANDED_TOOLBAR_HEIGHT),

        contentAlignment = Alignment.BottomStart
    ) {
        AsyncImage(
            model = place.image,
            contentScale = ContentScale.FillBounds,
            contentDescription = "Image of ${place.name}",
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
            icon = Icons.Default.FavoriteBorder,
            contentDescription = "Account icon",
            onClick = {},
            modifier = Modifier
                .align(Alignment.TopEnd)
                .statusBarsPadding()
                .padding(16.dp)
        )
        PlaceInfo(place = place, modifier = Modifier.align(Alignment.BottomStart))
    }
}