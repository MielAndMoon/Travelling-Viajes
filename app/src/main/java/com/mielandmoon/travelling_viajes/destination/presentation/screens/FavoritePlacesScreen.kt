package com.mielandmoon.travelling_viajes.destination.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mielandmoon.travelling_viajes.core.presentation.viewmodel.MainViewModel
import com.mielandmoon.travelling_viajes.destination.domain.model.Destination
import com.mielandmoon.travelling_viajes.destination.presentation.components.favorite_places.FavoritePlaceItem
import com.mielandmoon.travelling_viajes.destination.presentation.viewmodel.DestinationDetailViewModel
import com.mielandmoon.travelling_viajes.destination.presentation.viewmodel.FavoriteViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun FavoritePlacesScreen(
    padding: PaddingValues,
    modifier: Modifier = Modifier,
    viewModel: FavoriteViewModel = koinViewModel(),
    detailViewModel: DestinationDetailViewModel = koinViewModel(),
    onClick: (placeId: Int) -> Unit = {},
    userDataStore: Int,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = userDataStore) {
        viewModel.fetchFavoriteDestinations(userDataStore)
    }

    if (state.favoriteDestinations.isEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text("No destinations found")
        }
    } else {
        LazyColumn(
            modifier = modifier,
            contentPadding = padding
        ) {
            items(state.favoriteDestinations, key = { it.id }) { place ->
                FavoritePlaceItem(
                    place, onClick = onClick,
                    onDeleteFavorite = {
                        detailViewModel.deleteFavorite(place.id, userDataStore)
                        viewModel.fetchFavoriteDestinations(userDataStore)
                    }
                )
            }
        }
    }
}