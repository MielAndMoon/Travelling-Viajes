package com.mielandmoon.travelling_viajes.auth.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mielandmoon.travelling_viajes.auth.domain.state.SignInFormState
import com.mielandmoon.travelling_viajes.auth.domain.usecase.SignInUseCase
import com.mielandmoon.travelling_viajes.auth.domain.usecase.validation.ValidateEmailUseCase
import com.mielandmoon.travelling_viajes.auth.domain.usecase.validation.ValidatePasswordUseCase
import com.mielandmoon.travelling_viajes.common.domain.model.EmailError
import com.mielandmoon.travelling_viajes.common.domain.model.PasswordError
import com.mielandmoon.travelling_viajes.common.domain.model.Result
import com.mielandmoon.travelling_viajes.common.domain.model.SignInError
import com.mielandmoon.travelling_viajes.core.domain.usecase.SaveUserDataStoreUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class SignInViewModel(
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase,
    private val signInUseCase: SignInUseCase,
    private val saveUserDataStoreUseCase: SaveUserDataStoreUseCase,
) : ViewModel() {
    private var _state = MutableStateFlow(SignInFormState())
    val state = _state.asStateFlow()


    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    fun onEvent(event: SignInFormEvent) {
        when (event) {
            is SignInFormEvent.EmailChanged -> {
                _state.value = _state.value.copy(
                    email = event.email
                )
            }

            is SignInFormEvent.PasswordChanged -> {
                _state.value = _state.value.copy(
                    password = event.password
                )
            }

            is SignInFormEvent.PasswordVisibilityChanged -> {
                _state.value = _state.value.copy(
                    passwordVisible = !_state.value.passwordVisible
                )
            }

            is SignInFormEvent.Submit -> {
                submitData()
            }

        }
    }

    private fun submitData() {
        when (val emailResult = validateEmailUseCase.execute(_state.value.email)) {
            is Result.Error -> {
                when (emailResult.error) {
                    EmailError.EMPTY -> _state.value = _state.value.copy(
                        emailError = "El correo electrónico no puede estar vacío"
                    )

                    EmailError.INVALID -> _state.value = _state.value.copy(
                        emailError = "El correo electrónico no es válido"
                    )
                }
            }

            is Result.Success -> _state.value = _state.value.copy(
                emailError = null
            )
        }

        when (val passwordResult = validatePasswordUseCase.execute(_state.value.password)) {
            is Result.Error -> {
                when (passwordResult.error) {
                    PasswordError.SHORT -> _state.value = _state.value.copy(
                        passwordError = "La contraseña debe tener al menos 8 caracteres"
                    )
                }
            }

            is Result.Success -> _state.value = _state.value.copy(
                passwordError = null
            )
        }

        val hasError = listOf(
            _state.value.emailError,
            _state.value.passwordError
        ).any { !it.isNullOrBlank() }

        if (!hasError) {
            viewModelScope.launch {
                when (val result =
                    signInUseCase.execute(_state.value.email, _state.value.password)) {
                    is Result.Error -> {
                        when (result.error) {
                            SignInError.INVALID_CREDENTIALS -> {
                                _state.value = _state.value.copy(
                                    error = "El correo electrónico o la contraseña no son válidos"
                                )
                                validationEventChannel.send(ValidationEvent.Error)
                            }

                            SignInError.SERVER_ERROR -> {
                                _state.value = _state.value.copy(
                                    error = "Error al iniciar sesión"
                                )
                                validationEventChannel.send(ValidationEvent.Error)
                            }

                            SignInError.NETWORK_ERROR -> {
                                _state.value = _state.value.copy(
                                    error = "Error en la conexión"
                                )
                                validationEventChannel.send(ValidationEvent.Error)
                            }
                        }
                    }

                    is Result.Success -> {
                        _state.value = _state.value.copy(
                            userSignedIn = result.data,
                            error = null
                        )
                        saveUserDataStoreUseCase.execute(result.data)
                        validationEventChannel.send(ValidationEvent.Success)
                    }
                }
            }
        }
    }

    sealed class ValidationEvent {
        object Success : ValidationEvent()
        object Error : ValidationEvent()
    }
}