package com.mielandmoon.travelling_viajes.destination.data.model

import com.mielandmoon.travelling_viajes.destination.domain.model.Destination
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DestinationDto(
    val id: Int,
    val name: String,
    val description: String,
    val location: String,
    @SerialName("image_url") val imageUrl: String,
) {
    fun toDomain() = Destination(
        id = id,
        name = name,
        description = description,
        location = location,
        imageUrl = imageUrl
    )
}