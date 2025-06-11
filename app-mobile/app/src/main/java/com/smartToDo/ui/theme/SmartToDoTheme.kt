package com.smartToDo.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColors = lightColorScheme(
    primary = ColorPrimary,
    onPrimary = ColorOnPrimary,
    background = ColorBackground,
    surface = ColorSurface,
    onSurface = ColorOnSurface,
)

private val DarkColors = darkColorScheme(
    primary = ColorPrimaryLight,
    onPrimary = ColorOnPrimary,
    background = ColorBackground,
    surface = ColorSurface,
    onSurface = ColorOnSurface,
)

@Composable
fun SmartToDoTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColors else LightColors

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
