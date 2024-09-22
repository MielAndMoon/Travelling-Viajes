package com.mielandmoon.travelling_viajes.presentation.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mielandmoon.travelling_viajes.domain.model.Place

@Composable
fun PlacesList(
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
