package com.mielandmoon.travelling_viajes.destination.presentation.components.place_detail

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mielandmoon.travelling_viajes.destination.domain.model.Destination

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaceHeader(
    destination: Destination,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    scrollState: LazyListState,

    ) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    LargeTopAppBar(
        scrollBehavior = scrollBehavior,
        title = {
            AsyncImage(
                model = destination.imageUrl,
                contentScale = ContentScale.FillBounds,
                contentDescription = "Image of ${destination.name}",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(340.dp)
            )
            PlaceInfo(destination = destination, modifier = Modifier)

        },
        navigationIcon = {
            HeaderButton(
                icon = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                contentDescription = "Account icon",
                onClick = onBackClick,
                modifier = Modifier
            )
        },
        actions = {
            HeaderButton(
                icon = Icons.Default.FavoriteBorder,
                contentDescription = "Account icon",
                onClick = {},
                modifier = Modifier
            )
        }
    )


//    Box(
//        modifier = modifier.fillMaxWidth(),
//    ) {
//        AsyncImage(
//            model = place.image,
//            contentScale = ContentScale.FillBounds,
//            contentDescription = "Image of ${place.name}",
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(340.dp)
//        )
//        HeaderButton(
//            icon = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
//            contentDescription = "Account icon",
//            onClick = onBackClick,
//            modifier = Modifier
//                .align(Alignment.TopStart)
//        )
//        HeaderButton(
//            icon = Icons.Default.FavoriteBorder,
//            contentDescription = "Account icon",
//            onClick = {},
//            modifier = Modifier
//                .align(Alignment.TopEnd)
//        )
//        PlaceInfo(place = place, modifier = Modifier.align(Alignment.BottomStart))
//
//    }
}
//
//@Preview
//@Composable
//fun PlaceHeaderPreview() {
//    TravellingViajesTheme {
//        PlaceHeader(
//            place = Place(
//                id = 1,
//                name = "Peru",
//                description = "Peru is a country in South America. It is the seventh largest country in the world by area. for the 2019 census, it was the world's 12th most populous country. Peru is the third most populous country in South America, after Brazil and Argentina. It is the most populous country in the Andes, and the second most populous country in South America. Peru is a country in South America. It is the seventh largest country in the world by area. for the 2019 census, it was the world's 12th most populous country. Peru is the third most populous country in South America, after Brazil and Argentina. It is the most populous country in the Andes, and the second most populous country in South America. Peru is a country in South America. It is the seventh largest country in the world by area. for the 2019 census, it was the world's 12th most populous country. Peru is the third most populous country in South America, after Brazil and",
//                image = "https://cdn.pixabay.com/photo/2012/04/26/22/48/machu-picchu-43387_1280.jpg",
//                location = "Lima, Peru",
//                rating = 5,
//                reviews = 20000
//            ),
//            onBackClick = {},
//            scrollState = scrollState
//        )
//    }
//}
