package com.mielandmoon.travelling_viajes.auth.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mielandmoon.travelling_viajes.auth.domain.state.SignUpFormState
import com.mielandmoon.travelling_viajes.auth.domain.usecase.SignUpUseCase
import com.mielandmoon.travelling_viajes.auth.domain.usecase.validation.ValidateEmailUseCase
import com.mielandmoon.travelling_viajes.auth.domain.usecase.validation.ValidateFirstNameUseCase
import com.mielandmoon.travelling_viajes.auth.domain.usecase.validation.ValidateLastNameUseCase
import com.mielandmoon.travelling_viajes.auth.domain.usecase.validation.ValidatePasswordUseCase
import com.mielandmoon.travelling_viajes.auth.domain.usecase.validation.ValidateRepeatedPasswordUseCase
import com.mielandmoon.travelling_viajes.auth.domain.usecase.validation.ValidateUsernameUseCase
import com.mielandmoon.travelling_viajes.common.domain.model.EmailError
import com.mielandmoon.travelling_viajes.common.domain.model.PasswordError
import com.mielandmoon.travelling_viajes.common.domain.model.RepeatedPasswordError
import com.mielandmoon.travelling_viajes.common.domain.model.Result
import com.mielandmoon.travelling_viajes.common.domain.model.SignUpError
import com.mielandmoon.travelling_viajes.common.domain.model.TextError
import com.mielandmoon.travelling_viajes.core.domain.usecase.SaveUserDataStoreUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val validateFirstNameUseCase: ValidateFirstNameUseCase,
    private val validateLastNameUseCase: ValidateLastNameUseCase,
    private val validateUsernameUseCase: ValidateUsernameUseCase,
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase,
    private val validateRepeatedPasswordUseCase: ValidateRepeatedPasswordUseCase,
    private val signUpUseCase: SignUpUseCase,
    private val saveUserDataStoreUseCase: SaveUserDataStoreUseCase,
) : ViewModel() {
    private var _state = MutableStateFlow(SignUpFormState())
    val state = _state.asStateFlow()

    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    fun onEvent(event: SignUpFormEvent) {
        when (event) {
            is SignUpFormEvent.ConfirmPasswordChanged -> {
                _state.value = _state.value.copy(
                    confirmPassword = event.confirmPassword
                )
            }

            is SignUpFormEvent.EmailChanged -> {
                _state.value = _state.value.copy(
                    email = event.email
                )
            }

            is SignUpFormEvent.FirstNameChanged -> {
                _state.value = _state.value.copy(
                    firstName = event.firstName
                )
            }

            is SignUpFormEvent.LastNameChanged -> {
                _state.value = _state.value.copy(
                    lastName = event.lastName
                )
            }

            is SignUpFormEvent.PasswordChanged -> {
                _state.value = _state.value.copy(
                    password = event.password
                )
            }

            is SignUpFormEvent.UsernameChanged -> {
                _state.value = _state.value.copy(
                    username = event.username
                )
            }

            is SignUpFormEvent.ConfirmPasswordVisibilityChanged -> {
                _state.value = _state.value.copy(
                    confirmPasswordVisible = !_state.value.confirmPasswordVisible
                )
            }

            is SignUpFormEvent.PasswordVisibilityChanged -> {
                _state.value = _state.value.copy(
                    passwordVisible = !_state.value.passwordVisible
                )
            }

            is SignUpFormEvent.Submit -> {
                submitData()
            }


        }
    }

    private fun submitData() {
        when (val firstNameResult = validateFirstNameUseCase.execute(_state.value.firstName)) {
            is Result.Error -> {
                when (firstNameResult.error) {
                    TextError.EMPTY -> _state.value = _state.value.copy(
                        firstNameError = "El nombre no puede estar vacío"
                    )
                }
            }

            is Result.Success -> _state.value = _state.value.copy(
                firstNameError = null
            )
        }
        when (val lastNameResult = validateLastNameUseCase.execute(_state.value.lastName)) {
            is Result.Error -> {
                when (lastNameResult.error) {
                    TextError.EMPTY -> _state.value = _state.value.copy(
                        lastNameError = "El apellido no puede estar vacío"
                    )
                }
            }

            is Result.Success -> _state.value = _state.value.copy(
                lastNameError = null
            )
        }

        when (val usernameResult = validateUsernameUseCase.execute(_state.value.username)) {
            is Result.Error -> {
                when (usernameResult.error) {
                    TextError.EMPTY -> _state.value = _state.value.copy(
                        usernameError = "El nombre de usuario no puede estar vacío"
                    )
                }
            }

            is Result.Success -> _state.value = _state.value.copy(
                usernameError = null
            )
        }

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

        when (val repeatedPasswordResult = validateRepeatedPasswordUseCase.execute(
            _state.value.password,
            _state.value.confirmPassword
        )) {
            is Result.Error -> {
                when (repeatedPasswordResult.error) {
                    RepeatedPasswordError.SAME -> _state.value = _state.value.copy(
                        confirmPasswordError = "Las contraseñas no coinciden"
                    )
                }
            }

            is Result.Success -> _state.value = _state.value.copy(
                confirmPasswordError = null
            )
        }

        val hasError = listOf(
            _state.value.firstNameError,
            _state.value.lastNameError,
            _state.value.usernameError,
            _state.value.emailError,
            _state.value.passwordError,
            _state.value.confirmPasswordError
        ).any { !it.isNullOrBlank() }

        if (!hasError) {
            viewModelScope.launch {
                when (val result = signUpUseCase.execute(
                    _state.value.username,
                    _state.value.firstName,
                    _state.value.lastName,
                    _state.value.email,
                    _state.value.password
                )) {
                    is Result.Error -> {
                        when (result.error) {
                            SignUpError.SERVER_ERROR -> {
                                _state.value = _state.value.copy(
                                    error = "Error al registrarse"
                                )
                                validationEventChannel.send(ValidationEvent.Error)
                            }

                            SignUpError.NETWORK_ERROR -> {
                                _state.value = _state.value.copy(
                                    error = "Error en la conexión"
                                )
                                validationEventChannel.send(ValidationEvent.Error)
                            }
                        }
                    }

                    is Result.Success -> {
                        _state.value = _state.value.copy(
                            userSignedUp = result.data,
                            error = null
                        )
                        saveUserDataStoreUseCase.execute(result.data)
                        validationEventChannel.send(ValidationEvent.Success)
                    }
                }

                validationEventChannel.send(ValidationEvent.Success)
            }
        }
    }

    sealed class ValidationEvent {
        object Success : ValidationEvent()
        object Error : ValidationEvent()
    }
}