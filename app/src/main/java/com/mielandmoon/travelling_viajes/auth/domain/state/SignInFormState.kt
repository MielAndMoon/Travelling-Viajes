package com.mielandmoon.travelling_viajes.auth.domain.state

import com.mielandmoon.travelling_viajes.auth.domain.model.User

data class SignInFormState(
    val email: String = "",
    val emailError: String? = null,
    val password: String = "",
    val passwordError: String? = null,
    val passwordVisible: Boolean = false,
    val userSignedIn: User? = null,
    val error: String? = null,
)