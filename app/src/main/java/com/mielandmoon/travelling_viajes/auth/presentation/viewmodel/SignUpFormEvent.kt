package com.mielandmoon.travelling_viajes.auth.presentation.viewmodel

sealed class SignUpFormEvent {
    data class EmailChanged(val email: String) : SignUpFormEvent()
    data class FirstNameChanged(val firstName: String) : SignUpFormEvent()
    data class LastNameChanged(val lastName: String) : SignUpFormEvent()
    data class UsernameChanged(val username: String) : SignUpFormEvent()
    data class PasswordChanged(val password: String) : SignUpFormEvent()
    data class ConfirmPasswordChanged(val confirmPassword: String) : SignUpFormEvent()
    object PasswordVisibilityChanged : SignUpFormEvent()
    object ConfirmPasswordVisibilityChanged : SignUpFormEvent()
    object Submit : SignUpFormEvent()
}