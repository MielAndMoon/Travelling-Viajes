package com.mielandmoon.travelling_viajes.auth.presentation.viewmodel

sealed class SignInFormEvent {
    data class EmailChanged(val email: String) : SignInFormEvent()
    data class PasswordChanged(val password: String) : SignInFormEvent()
    object PasswordVisibilityChanged : SignInFormEvent()
    object Submit : SignInFormEvent()
}
