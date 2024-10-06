package com.mielandmoon.travelling_viajes.auth.domain.state

import com.mielandmoon.travelling_viajes.auth.domain.model.User

data class SignUpFormState(
    val firstName: String = "",
    val firstNameError: String? = null,
    val lastName: String = "",
    val lastNameError: String? = null,
    val username: String = "",
    val usernameError: String? = null,
    val email: String = "",
    val emailError: String? = null,
    val password: String = "",
    val passwordError: String? = null,
    val passwordVisible: Boolean = false,
    val confirmPassword: String = "",
    val confirmPasswordError: String? = null,
    val confirmPasswordVisible: Boolean = false,

    val userSignedUp: User? = null,
    val error: String? = null,
)
