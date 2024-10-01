package com.mielandmoon.travelling_viajes.auth.presentation.screens

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AlternateEmail
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mielandmoon.travelling_viajes.R
import com.mielandmoon.travelling_viajes.auth.presentation.components.AlternativeTextButton
import com.mielandmoon.travelling_viajes.auth.presentation.components.CustomAuthImage
import com.mielandmoon.travelling_viajes.auth.presentation.components.CustomButton
import com.mielandmoon.travelling_viajes.core.presentation.theme.TravellingViajesTheme

@Composable
fun SignInScreen(
    modifier: Modifier = Modifier,
    onNavigateToHome: () -> Unit = {},
    onNavigateToSignUp: () -> Unit = {},
) {
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
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.AlternateEmail,
                        contentDescription = "Email"
                    )
                },
                value = "",
                onValueChange = {},
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text("Email")
                },
                placeholder = {
                    Text("Email")
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Password"
                    )
                },
                value = "",
                onValueChange = {},
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text("Password")
                },
                placeholder = {
                    Text("Password")
                },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.Visibility,
                        contentDescription = ""
                    )
                },
            )
            Spacer(modifier = Modifier.weight(1f))
            CustomButton(
                text = "Sign In",
                onClick = onNavigateToHome
            )
            Spacer(modifier = Modifier.height(16.dp))
            AlternativeTextButton(
                alternativeText = "Don't have an account?",
                textButton = "Sign Up",
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