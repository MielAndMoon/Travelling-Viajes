package com.mielandmoon.travelling_viajes.destination.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FavoriteRequest(
    @SerialName("user_id") val userId: Int,
    @SerialName("destination_id") val destinationId: Int
)