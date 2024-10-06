package com.mielandmoon.travelling_viajes.destination.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mielandmoon.travelling_viajes.core.utils.COLLAPSED_TOOLBAR_HEIGHT
import com.mielandmoon.travelling_viajes.core.utils.EXPANDED_TOOLBAR_HEIGHT
import com.mielandmoon.travelling_viajes.destination.presentation.components.place_detail.CollapsedToolbar
import com.mielandmoon.travelling_viajes.destination.presentation.components.place_detail.ExpandedToolbar
import com.mielandmoon.travelling_viajes.destination.presentation.viewmodel.DestinationDetailViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun PlaceDetailScreen(
    destinationId: Int,
    modifier: Modifier = Modifier,
    userId: Int,
    viewModel: DestinationDetailViewModel = koinViewModel(),
    onBackClick: () -> Unit = {},
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = destinationId) {
        viewModel.fetchDestination(destinationId)
        viewModel.isFavorite(destinationId, userId)
    }

    val scrollState = rememberLazyListState()

    val overlapHeightPx = with(LocalDensity.current) {
        EXPANDED_TOOLBAR_HEIGHT.toPx() - COLLAPSED_TOOLBAR_HEIGHT.toPx()
    }

    val isCollapsed: Boolean by remember {
        derivedStateOf {
            val isFirstItemHidden = scrollState.firstVisibleItemScrollOffset > overlapHeightPx
            isFirstItemHidden || scrollState.firstVisibleItemIndex > 0
        }
    }

    if (state.destination != null) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            CollapsedToolbar(
                isFavorite = state.isFavorite,
                destination = state.destination!!,
                isCollapsed = isCollapsed,
                onAddToFavorite = {
                    if (state.isFavorite) {
                        viewModel.deleteFavorite(destinationId, userId)
                    } else {
                        viewModel.addToFavorite(destinationId, userId)
                    }
                },
                modifier = Modifier.zIndex(2f),
                onBackClick = onBackClick
            )
            LazyColumn(state = scrollState) {
                item {
                    ExpandedToolbar(
                        onAddToFavorite = {
                            if (state.isFavorite) {
                                viewModel.deleteFavorite(destinationId, userId)
                            } else {
                                viewModel.addToFavorite(destinationId, userId)
                            }
                        },
                        destination = state.destination!!,
                        onBackClick = onBackClick,
                        isFavorite = state.isFavorite
                    )
                    Card(
                        shape = RoundedCornerShape(24.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.background
                        ),
                        modifier = modifier
                            .offset(y = (-20).dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Spacer(modifier = Modifier.height(32.dp))
                            Text(
                                "About",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = state.destination!!.description,
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier.padding(bottom = 80.dp)
                            )
                        }
                    }

                }
            }
            /*Box(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(16.dp)
                    .align(Alignment.BottomEnd)
            ) {
                Button(
                    onClick = {
                        onBackClick()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .navigationBarsPadding(),

                    shape = RoundedCornerShape(8.dp),
                    contentPadding = PaddingValues(16.dp),
                ) {
                    Icon(
                        imageVector = Icons.Default.ShoppingCart,
                        contentDescription = "Shopping cart icon",
                        tint = MaterialTheme.colorScheme.onPrimary,
                    )
                    Spacer(modifier = Modifier.padding(4.dp))
                    Text(
                        text = "Book now",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }

            }*/
        }
    } else {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(100.dp)
            )
        }
    }
}
