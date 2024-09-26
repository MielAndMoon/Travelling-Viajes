package com.mielandmoon.travelling_viajes.core

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.rememberNavController
import com.mielandmoon.travelling_viajes.place.domain.model.Place
import com.mielandmoon.travelling_viajes.core.presentation.navigation.MainNavHost
import com.mielandmoon.travelling_viajes.core.presentation.theme.TravellingViajesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val places = listOf(
            Place(
                id = 1,
                name = "Peru",
                description = "Peru is a country in South America. It is the seventh largest country in the world by area. for the 2019 census, it was the world's 12th most populous country. Peru is the third most populous country in South America, after Brazil and Argentina. It is the most populous country in the Andes, and the second most populous country in South America. Peru is a country in South America. It is the seventh largest country in the world by area. for the 2019 census, it was the world's 12th most populous country. Peru is the third most populous country in South America, after Brazil and Argentina. It is the most populous country in the Andes, and the second most populous country in South America. Peru is a country in South America. It is the seventh largest country in the world by area. for the 2019 census, it was the world's 12th most populous country. Peru is the third most populous country in South America, after Brazil and Argentina. It is the most populous country in the Andes, and the second most populous country in South America. for the 2019 census, it was the world's 12th most populous country. Peru is the third most populous country in South America, after Brazil and Argentina. It is the most populous country in the Andes, and the second most populous country in South America. In 2019, Peru was the world's 12th most populous country. Peru is the third most populous country in South America, after Brazil and Argentina. It is the most populous country in the Andes, and the second most populous country in South America. just a few years ago, Peru was the world's 12th most populous country.",
                image = "https://cdn.pixabay.com/photo/2012/04/26/22/48/machu-picchu-43387_1280.jpg",
                location = "Lima, Peru",
                rating = 5,
                reviews = 20000
            ),
            Place(
                id = 2,
                name = "Mexico",
                description = "Mexico is a country in North America. It is the 28th largest country in the world by area.",
                image = "https://cdn.pixabay.com/photo/2020/03/20/21/13/mexico-4952057_1280.jpg",
                location = "Mexico City, Mexico",
                rating = 5,
                reviews = 100900
            ),
            Place(
                id = 3,
                name = "Argentina",
                description = "Argentina is a country in South America. It is the third largest country in the world by area.",
                image = "https://cdn.pixabay.com/photo/2017/06/10/14/11/colon-theater-2389927_1280.jpg",
                location = "Buenos Aires, Argentina",
                rating = 5,
                reviews = 9000
            ),
            Place(
                id = 4,
                name = "Chile",
                description = "Chile is a country in South America. It is the fifth largest country in the world by area.",
                image = "https://cdn.pixabay.com/photo/2016/09/11/15/39/easter-island-1661655_960_720.jpg",
                location = "Santiago, Chile",
                rating = 5,
                reviews = 144400
            ),
            Place(
                id = 5,
                name = "Ecuador",
                description = "Ecuador is a country in South America. It is the sixth largest country in the world by area.",
                image = "https://cdn.pixabay.com/photo/2019/08/26/03/35/cathedral-4430675_1280.jpg",
                location = "Quito, Ecuador",
                rating = 3,
                reviews = 12900
            ),
            Place(
                id = 6,
                name = "Colombia",
                description = "Colombia is a country in South America. It is the seventh largest country in the world by area.",
                image = "https://cdn.pixabay.com/photo/2017/09/20/19/44/tradiciconal-2769786_1280.jpg",
                location = "Bogotá, Colombia",
                rating = 4,
                reviews = 10000
            ),
            Place(
                id = 7,
                name = "Canada",
                description = "Canada is a country in North America. It is the 11th largest country in the world by area.",
                image = "https://cdn.pixabay.com/photo/2020/07/04/19/04/canada-day-5370627_1280.jpg",
                location = "Ottawa, Canada",
                rating = 3,
                reviews = 100
            ),
            Place(
                id = 8,
                name = "United States",
                description = "The United States is a country in North America. It is the 25th largest country in the world by area.",
                image = "https://cdn.pixabay.com/photo/2021/12/09/11/53/empire-state-building-6858030_960_720.jpg",
                location = "Washington, D.C., United States",
                rating = 4,
                reviews = 1100
            ),
            Place(
                id = 9,
                name = "Brazil",
                description = "Brazil is a country in South America. It is the 22nd largest country in the world by area.",
                image = "https://cdn.pixabay.com/photo/2017/01/08/19/30/rio-de-janeiro-1963744_1280.jpg",
                location = "Brasília, Brazil",
                rating = 5,
                reviews = 1500
            ),

            )
        setContent {

            val scope = rememberCoroutineScope()
            val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
            val navController = rememberNavController()

            TravellingViajesTheme {
                MainNavHost(
                    scope = scope,
                    drawerState = drawerState,
                    places = places,
                    navController = navController
                )
            }
        }
    }
}

