package com.example.emanage.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Define custom colors
private val HackerGreen = Color(0xFF00FF00) // Bright green
private val HackerRed = Color(0xFFFF0000)   // Bright red
private val HackerDarkBackground = Color(0xFF000000) // Pure black
private val HackerLightBackground = Color(0xFFFFFFFF) // Pure white
private val HackerLightSecondSurface = Color(0xFFC5FFAA)
private val HackerDarkSecondSurface = Color(0xDA1C3014)

private val DarkColorScheme = darkColorScheme(
//    primary = Purple80,
//    secondary = PurpleGrey80,
     tertiary = HackerDarkSecondSurface,


    primary = HackerGreen, // Use green for primary components
    secondary = HackerRed, // Use red for accents or secondary components
    background = HackerDarkBackground,
    surface = HackerDarkBackground,
    onPrimary = HackerDarkBackground,
    onSecondary = HackerDarkBackground,
    onBackground = HackerGreen,
    onSurface = HackerGreen,



)

private val LightColorScheme = lightColorScheme(
//    primary = Purple40,
//    secondary = PurpleGrey40,
    tertiary = HackerLightSecondSurface,

    primary = HackerGreen, // Use green for primary components
    secondary = HackerRed, // Use red for accents or secondary components
    background = HackerLightBackground,
    surface = HackerLightBackground,
    onPrimary = HackerDarkBackground,
    onSecondary = HackerDarkBackground,
    onBackground = HackerGreen,
    onSurface = HackerGreen,




)

val typographyCustom : Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = FontFamily.Monospace,
        fontWeight = FontWeight.Normal,
        fontSize = 57.sp
    ),
    displayMedium = TextStyle(
        fontFamily = FontFamily.Monospace,
        fontWeight = FontWeight.Normal,
        fontSize = 45.sp
    ),
    displaySmall = TextStyle(
        fontFamily = FontFamily.Monospace,
        fontWeight = FontWeight.Normal,
        fontSize = 36.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = FontFamily.Monospace,
        fontWeight = FontWeight.Normal,
        fontSize = 32.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = FontFamily.Monospace,
        fontWeight = FontWeight.Normal,
        fontSize = 28.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = FontFamily.Monospace,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Monospace,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily.Monospace,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    bodySmall = TextStyle(
        fontFamily = FontFamily.Monospace,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
)

val LocalExtendedColors = compositionLocalOf {
    ExtendedColors(
        fourth = Color.Unspecified,
        fifth = Color.Unspecified,
        sixth = Color.Unspecified
    )
}

@Composable
fun EManageTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
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

    val extendedColors = if (darkTheme) {
        ExtendedColors(
            fourth = Color.Gray,
            fifth = Color.DarkGray,
            sixth = Color(0xFFEFB8C8)
        )
    } else {
        ExtendedColors(
            fourth = Color.Blue,
            fifth = Color.LightGray,
            sixth = Color(0xFFEFB8C8)
        )
    }

    CompositionLocalProvider(LocalExtendedColors provides extendedColors) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = typographyCustom,
            content = content
        )

    }
}

data class ExtendedColors(
    val fourth: Color,
    val fifth: Color,
    val sixth : Color
)