package com.mielandmoon.travelling_viajes.auth.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

@Composable
fun AlternativeTextButton(
    alternativeText: String,
    textButton: String,
    onNavigate: () -> Unit = {},
) {
    Text(
        text = "or",
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.secondary
    )
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = alternativeText,
            style = MaterialTheme.typography.bodyMedium,
        )
        TextButton(
            onClick = onNavigate
        ) {
            Text(
                text = textButton,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary,
            )
        }
    }
}