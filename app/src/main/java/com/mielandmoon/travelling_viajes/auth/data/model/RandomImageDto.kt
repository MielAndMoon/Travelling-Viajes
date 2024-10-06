package com.mielandmoon.travelling_viajes.auth.data.model

import kotlinx.serialization.Serializable

@Serializable
data class RandomImageDto(
    val results: List<Result>,
    val info: Info
)

@Serializable
data class Result(
    val picture: Picture
)

@Serializable
data class Picture(
    val large: String,
    val medium: String,
    val thumbnail: String
)

@Serializable
data class Info(
    val seed: String,
    val results: Int,
    val page: Int,
    val version: String
)
