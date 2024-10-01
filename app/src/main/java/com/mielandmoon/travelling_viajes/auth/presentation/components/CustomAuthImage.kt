package com.mielandmoon.travelling_viajes.auth.presentation.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource

@Composable
fun CustomAuthImage(
    @DrawableRes image: Int,
    modifier: Modifier = Modifier,
) {
    Image(
        painter = painterResource(id = image),
        contentDescription = "Image for auth",
        modifier = modifier,
        colorFilter = if (isSystemInDarkTheme()) ColorFilter.tint(Color.White) else null
    )
}