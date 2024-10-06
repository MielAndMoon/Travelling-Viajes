package com.mielandmoon.travelling_viajes.common.domain.model

enum class EmailError: Error {
    EMPTY,
    INVALID
}

enum class PasswordError : Error {
    SHORT
}

enum class NamesError : Error {
    EMPTY
}

enum class TextError : Error {
    EMPTY
}

enum class RepeatedPasswordError : Error {
    SAME
}