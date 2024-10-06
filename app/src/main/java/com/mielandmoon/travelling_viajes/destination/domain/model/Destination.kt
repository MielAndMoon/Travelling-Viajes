package com.mielandmoon.travelling_viajes.destination.domain.model

data class Destination(
    val id: Int,
    val name: String,
    val description: String,
    val imageUrl: String,
    val location: String,
)
