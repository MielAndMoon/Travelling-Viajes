package com.mielandmoon.travelling_viajes.destination.presentation.components.favorite_places

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mielandmoon.travelling_viajes.core.presentation.theme.TravellingViajesTheme
import com.mielandmoon.travelling_viajes.destination.domain.model.Destination

@Composable
fun FavoritePlaceItem(
    destination: Destination,
    modifier: Modifier = Modifier,
    onClick: (placeId: Int) -> Unit = {},
    onDeleteFavorite: () -> Unit = {}
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        onClick = {
            onClick(destination.id)
        }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            AsyncImage(
                model = destination.imageUrl,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(128.dp)
                    .clip(
                        RoundedCornerShape(16.dp)
                    ),
                contentDescription = "Translated description of what the image contains"
            )
            Column(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .weight(1f)
            ) {
                Text(
                    text = destination.name,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
            }
            IconButton(
                onClick = {
                    onDeleteFavorite()
                },
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Icon(
                    tint = MaterialTheme.colorScheme.onPrimary,
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Account icon",
                    modifier = Modifier
                        .size(24.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun FavoritePlacesItemPreview() {
    TravellingViajesTheme {
        FavoritePlaceItem(
            destination = Destination(
                id = 1,
                name = "Peru",
                description = "Peru is a country in South America. It is the seventh largest country in the world by area. for the 2019 census, it was the world's 12th most populous country. Peru is the third most populous country in South America, after Brazil and Argentina. It is the most populous country in the Andes, and the second most populous country in South America. Peru is a country in South America. It is the seventh largest country in the world by area. for the 2019 census, it was the world's 12th most populous country. Peru is the third most populous country in South America, after Brazil and Argentina. It is the most populous country in the Andes, and the second most populous country in South America. Peru is a country in South America. It is the seventh largest country in the world by area. for the 2019 census, it was the world's 12",
                imageUrl = "https://cdn.pixabay.com/photo/2012/04/26/22/48/machu-picchu-43387_1280.jpg",
                location = "Lima, Peru",
            )

        )
    }
}