package com.mielandmoon.travelling_viajes.auth.presentation.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Abc
import androidx.compose.material.icons.filled.AlternateEmail
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mielandmoon.travelling_viajes.auth.presentation.viewmodel.SignUpFormEvent
import com.mielandmoon.travelling_viajes.auth.presentation.viewmodel.SignUpViewModel
import com.mielandmoon.travelling_viajes.auth.presentation.components.AlternativeTextButton
import com.mielandmoon.travelling_viajes.auth.presentation.components.CustomButton
import com.mielandmoon.travelling_viajes.core.presentation.theme.TravellingViajesTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    onNavigateToHome: () -> Unit = {},
    onNavigateToSignIn: () -> Unit = {},
    viewModel: SignUpViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsState()

    val context = LocalContext.current

    LaunchedEffect(key1 = context) {
        viewModel.validationEvents.collect { event ->
            when (event) {
                is SignUpViewModel.ValidationEvent.Success -> {
                    Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
                }

                SignUpViewModel.ValidationEvent.Error -> {
                    Toast.makeText(context, "${state.error}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    Scaffold {
        Column(
            modifier = modifier
                .padding(it)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Registrarse",
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.align(Alignment.Start)
            )
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                maxLines = 1,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Abc,
                        contentDescription = "First Name"
                    )
                },
                value = state.firstName,
                onValueChange = {
                    viewModel.onEvent(SignUpFormEvent.FirstNameChanged(it))
                },
                isError = state.firstNameError != null,
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text("Nombres")
                },
                placeholder = {
                    Text("Nombres")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                supportingText = {
                    if (state.firstNameError != null) {
                        Text(
                            text = state.firstNameError!!,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }
            )

            OutlinedTextField(
                maxLines = 1,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Abc,
                        contentDescription = "Last Name"
                    )
                },
                value = state.lastName,
                onValueChange = {
                    viewModel.onEvent(SignUpFormEvent.LastNameChanged(it))
                },
                isError = state.lastNameError != null,
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text("Apellidos")
                },
                placeholder = {
                    Text("Apellidos")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                supportingText = {
                    if (state.lastNameError != null) {
                        Text(
                            text = state.lastNameError!!,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }
            )

            OutlinedTextField(
                maxLines = 1,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Face,
                        contentDescription = "Username icon"
                    )
                },
                value = state.username,
                onValueChange = {
                    viewModel.onEvent(SignUpFormEvent.UsernameChanged(it))
                },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text("Nombre de Usuario")
                },
                placeholder = {
                    Text("Nombre de Usuario")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                supportingText = {
                    if (state.usernameError != null) {
                        Text(
                            text = state.usernameError!!,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }
            )

            OutlinedTextField(
                maxLines = 1,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.AlternateEmail,
                        contentDescription = "Email"
                    )
                },
                value = state.email,
                onValueChange = {
                    viewModel.onEvent(SignUpFormEvent.EmailChanged(it))
                },
                isError = state.emailError != null,
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text("Correo Electrono")
                },
                placeholder = {
                    Text("Correo Electronico")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                supportingText = {
                    if (state.emailError != null) {
                        Text(
                            text = state.emailError!!,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }
            )

            OutlinedTextField(
                maxLines = 1,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Password icon"
                    )
                },
                value = state.password,
                onValueChange = {
                    viewModel.onEvent(SignUpFormEvent.PasswordChanged(it))
                },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text("Contraseña")
                },
                placeholder = {
                    Text("Contraseña")
                },
                trailingIcon = {
                    IconButton(
                        onClick = {
                            viewModel.onEvent(SignUpFormEvent.PasswordVisibilityChanged)
                        }
                    ) {
                        Icon(
                            imageVector = if (state.passwordVisible) {
                                Icons.Default.Visibility
                            } else {
                                Icons.Default.VisibilityOff
                            },
                            contentDescription = ""
                        )
                    }
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Next
                ),
                visualTransformation = if (state.passwordVisible) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                },
                supportingText = {
                    if (state.passwordError != null) {
                        Text(
                            text = state.passwordError!!,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }
            )

            OutlinedTextField(
                maxLines = 1,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Password icon"
                    )
                },
                value = state.confirmPassword,
                onValueChange = {
                    viewModel.onEvent(SignUpFormEvent.ConfirmPasswordChanged(it))
                },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text("Confirmar Contraseña")
                },
                placeholder = {
                    Text("Confirmar Contraseña")
                },
                trailingIcon = {
                    IconButton(onClick = {
                        viewModel.onEvent(SignUpFormEvent.ConfirmPasswordVisibilityChanged)
                    }) {
                        Icon(
                            imageVector = if (state.confirmPasswordVisible) {
                                Icons.Default.Visibility
                            } else {
                                Icons.Default.VisibilityOff
                            },
                            contentDescription = ""
                        )
                    }

                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                visualTransformation = if (state.confirmPasswordVisible) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                },
                supportingText = {
                    if (state.confirmPasswordError != null) {
                        Text(
                            text = state.confirmPasswordError!!,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }
            )

            Spacer(modifier = Modifier.weight(1f))
            CustomButton(
                text = "Registrarse",
                onClick = {
                    viewModel.onEvent(SignUpFormEvent.Submit)
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            AlternativeTextButton(
                alternativeText = "¿Ya tienes una cuenta?",
                textButton = "Iniciar Sesión",
                onNavigate = onNavigateToSignIn
            )
        }
    }
}

@Preview(
    showBackground = true,
//    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun SignUpScreenPreview() {
    TravellingViajesTheme {
        SignUpScreen()
    }
}