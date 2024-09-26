package com.mielandmoon.travelling_viajes.place.presentation.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mielandmoon.travelling_viajes.place.domain.model.Place
import com.mielandmoon.travelling_viajes.place.presentation.components.PlaceItem

@Composable
fun PlacesListScreen(
    places: List<Place>,
    padding: PaddingValues,
    modifier: Modifier = Modifier,
    onClick: (placeId: Int) -> Unit = {},
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = padding
    ) {
        items(places, key = { it.id }) { place ->
            PlaceItem(place, onClick = onClick)
        }
    }
}
