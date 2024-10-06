package com.mielandmoon.travelling_viajes.auth.presentation.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AlternateEmail
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mielandmoon.travelling_viajes.R
import com.mielandmoon.travelling_viajes.auth.presentation.components.AlternativeTextButton
import com.mielandmoon.travelling_viajes.auth.presentation.components.CustomAuthImage
import com.mielandmoon.travelling_viajes.auth.presentation.components.CustomButton
import com.mielandmoon.travelling_viajes.auth.presentation.viewmodel.SignInFormEvent
import com.mielandmoon.travelling_viajes.auth.presentation.viewmodel.SignInViewModel
import com.mielandmoon.travelling_viajes.core.presentation.theme.TravellingViajesTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun SignInScreen(
    modifier: Modifier = Modifier,
    onNavigateToHome: () -> Unit = {},
    onNavigateToSignUp: () -> Unit = {},
    viewModel: SignInViewModel = koinViewModel<SignInViewModel>(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(key1 = context) {
        viewModel.validationEvents.collect { event ->
            when (event) {
                is SignInViewModel.ValidationEvent.Success -> {
                    Toast.makeText(
                        context,
                        "Success ${state.userSignedIn?.username}",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                is SignInViewModel.ValidationEvent.Error -> {
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
            CustomAuthImage(
                image = R.drawable.sitreadingdoodle,
                modifier = Modifier.size(375.dp)
            )
            Text(
                text = "Sign in",
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.align(Alignment.Start)
            )
            Spacer(modifier = Modifier.height(16.dp))

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
                    viewModel.onEvent(SignInFormEvent.EmailChanged(it))
                },
                isError = state.emailError != null,
                supportingText = {
                    if (state.emailError != null) {
                        Text(
                            text = state.emailError!!,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text("Correo Electronico")
                },
                placeholder = {
                    Text("Correo Electronico")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
            )

            OutlinedTextField(
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Password"
                    )
                },
                value = state.password,
                onValueChange = {
                    viewModel.onEvent(SignInFormEvent.PasswordChanged(it))
                },
                isError = state.passwordError != null,
                supportingText = {
                    if (state.passwordError != null) {
                        Text(
                            text = state.passwordError!!,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text("Contraseña")
                },
                placeholder = {
                    Text("Contraseña")
                },
                visualTransformation = if (state.passwordVisible) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                },
                trailingIcon = {
                    IconButton(onClick = {
                        viewModel.onEvent(SignInFormEvent.PasswordVisibilityChanged)
                    }) {
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
                    imeAction = ImeAction.Done
                ),
                maxLines = 1,
            )

            Spacer(modifier = Modifier.weight(1f))
            CustomButton(
                text = "Iniciar Sesión",
                onClick = {
                    viewModel.onEvent(SignInFormEvent.Submit)
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            AlternativeTextButton(
                alternativeText = "¿No tienes una cuenta?",
                textButton = "Registrarse",
                onNavigate = onNavigateToSignUp
            )

        }
    }
}

@Preview(
    showBackground = true,
//    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun SignInScreenPreview() {
    TravellingViajesTheme {
        SignInScreen()
    }
}