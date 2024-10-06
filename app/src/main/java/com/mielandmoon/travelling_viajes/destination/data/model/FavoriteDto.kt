package com.mielandmoon.travelling_viajes.destination.data.model

import com.mielandmoon.travelling_viajes.destination.domain.model.Favorite
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FavoriteDto(
    @SerialName("user_id") val userId: Int,
    @SerialName("destination_id") val destinationId: Int,
    val id: Int
) {
    fun toDomain() = Favorite(
        userId = userId,
        destinationId = destinationId,
        id = id
    )
}
