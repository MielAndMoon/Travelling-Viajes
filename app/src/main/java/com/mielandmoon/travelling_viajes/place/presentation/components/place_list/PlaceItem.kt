package com.mielandmoon.travelling_viajes.place.presentation.components.place_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mielandmoon.travelling_viajes.place.domain.model.Place

@Composable
fun PlaceItem(
    place: Place,
    modifier: Modifier = Modifier,
    onClick: (placeId: Int) -> Unit = {}
) {
    val openDialog = remember { mutableStateOf(false) }

    if (openDialog.value) {
        PlaceDialog(
            place = place,
            onDismissRequest = {
                openDialog.value = false
            },
            onMore = onClick
        )
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        onClick = { onClick(place.id) }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            AsyncImage(
                model = place.image,
                contentScale = ContentScale.FillBounds,
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
                    text = place.name,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = place.location,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
            IconButton(
                onClick = {
                    openDialog.value = true
                },
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Icon(
                    tint = MaterialTheme.colorScheme.onPrimary,
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "Account icon",
                    modifier = Modifier
                        .size(24.dp)
                )
            }
        }
    }
}