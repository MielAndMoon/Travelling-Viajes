package com.mielandmoon.travelling_viajes.destination.presentation.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mielandmoon.travelling_viajes.destination.domain.model.Destination
import com.mielandmoon.travelling_viajes.destination.presentation.components.place_list.PlaceItem
import com.mielandmoon.travelling_viajes.destination.presentation.viewmodel.DestinationViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun PlacesListScreen(
    padding: PaddingValues,
    destinations: List<Destination>,
    modifier: Modifier = Modifier,
    onClick: (placeId: Int) -> Unit = {},
) {

    LazyColumn(
        modifier = modifier,
        contentPadding = padding
    ) {
        if (destinations.isEmpty()) {
            item {
                Text("No destinations found")
            }

        } else {
            items(destinations, key = { it.id }) { place ->
                PlaceItem(place, onClick = onClick)
            }
        }
    }
}
