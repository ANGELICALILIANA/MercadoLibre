package com.example.mercadolibre.ui.theme

import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = YellowML,
    primaryContainer = YellowML,
    secondary = YellowML,
    secondaryContainer = YellowML,
    tertiary = Color(0xFF3483FA),
    surface = DarkGrayML,
    onPrimary = TextDarkML,
    onSecondary = DarkGrayML,
    onTertiary = Color.White,
    background = Color.Black,
    onBackground = TextLightML,
    onSurface = TextLightML,
    outline = Color(0xFF444444)
)

private val LightColorScheme = lightColorScheme(
    primary = YellowML,
    primaryContainer = YellowML,
    secondary = DarkBlueML,
    secondaryContainer = DarkBlueML,
    tertiary = Color(0xFF3483FA),
    surface = Color.White,
    onPrimary = TextDarkML,
    onSecondary = Color.White,
    onTertiary = Color.White,
    background = LightGrayML,
    onBackground = TextDarkML,
    onSurface = TextDarkML,
    outline = Color(0xFFCCCCCC)
)

@Composable
fun MercadoLibreTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val gradientBackground = Brush.verticalGradient(
        colors = listOf(YellowML, LightGrayML),
        startY = 0f,
        endY = 1500f
    )

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(gradientBackground)
            ) {
                content()
            }
        }
    )
}