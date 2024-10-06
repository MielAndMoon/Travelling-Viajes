package com.mielandmoon.travelling_viajes.auth.data.model

import com.mielandmoon.travelling_viajes.auth.domain.model.User
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    @SerialName("first_name") val firstName: String,
    @SerialName("last_name") val lastName: String,
    val username: String,
    val email: String,
    @SerialName("image_url") val imageUrl: String,
    val id: Int
) {
    fun toDomain() = User(
        id = id,
        firstName = firstName,
        lastName = lastName,
        username = username,
        email = email,
        imageUrl = imageUrl
    )
}
