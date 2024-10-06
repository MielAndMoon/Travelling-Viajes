package com.mielandmoon.travelling_viajes.auth.domain.model

data class User(
    val id: Int? = null,
    val firstName: String,
    val lastName: String,
    val username: String,
    val email: String,
    val imageUrl: String,
)
