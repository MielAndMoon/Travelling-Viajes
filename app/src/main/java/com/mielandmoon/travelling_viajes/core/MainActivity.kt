package com.mielandmoon.travelling_viajes.core

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.compose.rememberNavController
import com.mielandmoon.travelling_viajes.destination.domain.model.Destination
import com.mielandmoon.travelling_viajes.core.presentation.navigation.MainNavHost
import com.mielandmoon.travelling_viajes.core.presentation.theme.TravellingViajesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val favoriteDestinations = listOf(
            Destination(
                id = 1,
                name = "Peru",
                description = "Peru is a country in South America. It is the seventh largest country in the world by area. for the 2019 census, it was the world's 12th most populous country. Peru is the third most populous country in South America, after Brazil and Argentina. It is the most populous country in the Andes, and the second most populous country in South America. Peru is a country in South America. It is the seventh largest country in the world by area. for the 2019 census, it was the world's 12th most populous country. Peru is the third most populous country in South America, after Brazil and Argentina. It is the most populous country in the Andes, and the second most populous country in South America. Peru is a country in South America. It is the seventh largest country in the world by area. for the 2019 census, it was the world's 12th most populous country. Peru is the third most populous country in South America, after Brazil and Argentina. It is the most popu",
                imageUrl = "https://cdn.pixabay.com/photo/2012/04/26/22/48/machu-picchu-43387_1280.jpg",
                location = "Lima, Peru",

            ),
            Destination(
                id = 2,
                name = "Mexico",
                description = "Mexico is a country in North America. It is the 28th largest country in the world by area.",
                imageUrl = "https://cdn.pixabay.com/photo/2020/03/20/21/13/mexico-4952057_1280.jpg",
                location = "Mexico City, Mexico",

            ),
            Destination(
                id = 3,
                name = "Argentina",
                description = "Argentina is a country in South America. It is the third largest country in the world by area.",
                imageUrl = "https://cdn.pixabay.com/photo/2017/06/10/14/11/colon-theater-2389927_1280.jpg",
                location = "Buenos Aires, Argentina",

            ),
        )
        setContent {

            val scope = rememberCoroutineScope()
            val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
            val navController = rememberNavController()
            var selectedItem by rememberSaveable { mutableStateOf(0) }

            fun updateSelectedItem(index: Int) {
                selectedItem = index
            }


            TravellingViajesTheme {
                MainNavHost(
                    itemSelected = selectedItem,
                    drawerState = drawerState,
                    navController = navController,
                    updateSelectedItem = { updateSelectedItem(it) }
                )
            }
        }
    }
}

