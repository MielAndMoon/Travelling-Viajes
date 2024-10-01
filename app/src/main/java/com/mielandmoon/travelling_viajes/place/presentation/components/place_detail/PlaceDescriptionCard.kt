package com.mielandmoon.travelling_viajes.place.presentation.components.place_detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mielandmoon.travelling_viajes.core.presentation.theme.TravellingViajesTheme
import com.mielandmoon.travelling_viajes.place.domain.model.Place

@Composable
fun PlaceDescriptionCard(
    place: Place,
    modifier: Modifier = Modifier,
    scrollState: LazyListState,
) {
    LazyColumn(
        state = scrollState,
        contentPadding = PaddingValues(top = 400.dp)
    ) {
        item {
            Card(
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.background
                ),
                modifier = modifier
                    .fillMaxWidth()
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
                    Text(text = place.description, style = MaterialTheme.typography.bodyLarge)
                }
            }
        }
    }

}

//@Preview(
//)
//@Composable
//fun PlaceDescriptionCardPreview() {
//    TravellingViajesTheme {
//        PlaceDescriptionCard(
//            place = Place(
//                id = 1,
//                name = "Peru",
//                description = "Peru is a country in South America. It is the seventh largest country in the world by area. for the 2019 census, it was the world's 12th most populous country. Peru is the third most populous country in South America, after Brazil and Argentina. It is the most populous country in the Andes, and the second most populous country in South America. Peru is a country in South America. It is the seventh largest country in the world by area. for the 2019 census, it was the world's 12th most populous country. Peru is the third most populous country in South America, after Brazil and Argentina. It is the most populous country in the Andes, and the second most populous country in South America. Peru is a country in South America. It is the seventh largest country in the world by area. for the 2019 census, it was the world's 12th most populous country. Peru is the third most populous country in South America, after Brazil and",
//                image = "https://cdn.pixabay.com/photo/2012/04/26/22/48/machu-picchu-43387_1280.jpg",
//                location = "Lima, Peru",
//                rating = 5,
//                reviews = 20000
//            ),
//            scrollState = scrollState
//        )
//    }
//}
