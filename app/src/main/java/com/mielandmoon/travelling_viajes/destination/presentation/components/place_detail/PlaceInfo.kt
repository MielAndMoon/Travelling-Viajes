package com.mielandmoon.travelling_viajes.destination.presentation.components.place_detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mielandmoon.travelling_viajes.destination.domain.model.Destination

@Composable
fun PlaceInfo(
    destination: Destination,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 16.dp, bottom = 48.dp, end = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column{
            Text(
                text = destination.name,
                style = MaterialTheme.typography.displayMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Text(
                text = destination.location,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
//        Row (
//            modifier = Modifier.align(Alignment.Bottom)
//        ) {
//            Icon(
//                imageVector = Icons.Default.Star,
//                contentDescription = "Rating icon",
//                tint = MaterialTheme.colorScheme.onTertiary,
//            )
//            Text(
//                text = destination.rating.toString(),
//                style = MaterialTheme.typography.titleMedium,
//                color = MaterialTheme.colorScheme.onPrimary
//            )
//        }
    }
}

@Preview
@Composable
fun PlaceInfoPreview() {
    PlaceInfo(
        destination = Destination(
            id = 1,
            name = "Peru",
            description = "Peru is a country in South America. It is the seventh largest country in the world by area. for the 2019 census, it was the world's 12th most populous country. Peru is the third most populous country in South America, after Brazil and Argentina. It is the most populous country in the Andes, and the second most populous country in South America. Peru is a country in South America. It is the seventh largest country in the world by area. for the 2019 census, it was the world's 12th most populous country. Peru is the third most populous country in South America, after Brazil and Argentina. It is the most populous country in the Andes, and the second most populous country in South America. Peru is a country in South America. It is the seventh largest country in the world by area. for the 2019 census, it was the world's 12th most populous country. Peru is the third most populous country in South America, after Brazil and:",
            imageUrl = "https://cdn.pixabay.com/photo/2012/04/26/22/48/machu-picchu-43387_1280.jpg",
            location = "Lima, Peru",
        )
    )
}