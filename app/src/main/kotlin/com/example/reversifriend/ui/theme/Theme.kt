package com.example.reversifriend.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = Color(0xFF176B5B),
    onPrimary = Color.White,
    secondary = Color(0xFFC7553F),
    onSecondary = Color.White,
    tertiary = Color(0xFF27548A),
    onTertiary = Color.White,
    background = Color(0xFFF5F7F2),
    onBackground = Color(0xFF1E2422),
    surface = Color(0xFFFFFFFF),
    onSurface = Color(0xFF1E2422),
    surfaceVariant = Color(0xFFE6EEE7),
    onSurfaceVariant = Color(0xFF41504A),
)

@Composable
fun ReversiFriendTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColors,
        content = content,
    )
}

