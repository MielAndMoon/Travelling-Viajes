package com.mielandmoon.travelling_viajes.place.domain.model

data class Place(
    val id: Int,
    val name: String,
    val description: String,
    val image: String,
    val location: String,
    val rating: Int,
    val reviews: Int,
)
