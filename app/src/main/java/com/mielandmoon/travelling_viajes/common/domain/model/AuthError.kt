package com.mielandmoon.travelling_viajes.common.domain.model

enum class SignUpError: Error {
    SERVER_ERROR,
    NETWORK_ERROR
}

enum class SignInError: Error {
    INVALID_CREDENTIALS,
    SERVER_ERROR,
    NETWORK_ERROR
}
