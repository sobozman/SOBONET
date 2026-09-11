package com.v2ray.ang.ui.compose

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

// پالت دارک سایبرپانک SOBONET
val CyberDarkBg = Color(0xFF090D16)
val CyberCardBg = Color(0xFF121826)
val CyberCardVariant = Color(0xFF1A2333)
val CyberNeonCyan = Color(0xFF00F0FF)
val CyberNeonPink = Color(0xFFFF0055)
val CyberNeonGreen = Color(0xFF00FF9D)
val CyberTextWhite = Color(0xFFFFFFFF)
val CyberTextDim = Color(0xFF8A99AD)
val CyberBorderDark = Color(0xFF222F44)

// متغیرهای موردنیاز سایر بخش‌ها
val colorFabActive = CyberNeonGreen
val colorFabInactiveDark = CyberNeonPink
val colorFabInactiveLight = CyberNeonPink
val colorPing = CyberNeonGreen
val colorPingRed = CyberNeonPink
val colorConfigType = CyberNeonCyan

val dividerColorDark = CyberBorderDark
val dividerColorLight = CyberBorderDark

val toastNormalBgDark = CyberCardBg
val toastNormalBgLight = CyberCardBg
val toastSuccessBg = CyberNeonGreen
val toastErrorBg = CyberNeonPink
val toastInfoBg = CyberNeonCyan
val toastTextColor = CyberTextWhite

val LocalDarkTheme = compositionLocalOf { true }

private val DarkColor = darkColorScheme(
    primary = CyberNeonCyan,
    onPrimary = CyberDarkBg,
    primaryContainer = CyberCardVariant,
    onPrimaryContainer = CyberNeonCyan,
    secondary = CyberNeonPink,
    onSecondary = Color.White,
    secondaryContainer = CyberCardVariant,
    onSecondaryContainer = CyberNeonPink,
    tertiary = CyberNeonGreen,
    onTertiary = CyberDarkBg,
    background = CyberDarkBg,
    onBackground = CyberTextWhite,
    surface = CyberCardBg,
    onSurface = CyberTextWhite,
    surfaceVariant = CyberCardVariant,
    onSurfaceVariant = CyberTextDim,
    outline = CyberBorderDark,
    error = CyberNeonPink,
    onError = Color.White
)

object ThemeManager {
    private val _themeMode = MutableStateFlow(AppCompatDelegate.MODE_NIGHT_YES)
    val themeMode: StateFlow<Int> = _themeMode.asStateFlow()

    fun init(context: Context) {
        setTheme(AppCompatDelegate.MODE_NIGHT_YES)
    }

    fun setTheme(mode: Int) {
        _themeMode.value = AppCompatDelegate.MODE_NIGHT_YES
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
    }

    fun applyTheme(context: Context) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
    }
}

@Composable
fun AppTheme(content: @Composable () -> Unit) {
    Theme(darkTheme = true, dynamicColor = false, content = content)
}

@Composable
fun Theme(
    darkTheme: Boolean = true,
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = DarkColor
    val view = LocalView.current

    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = CyberDarkBg.value.toLong().toInt()
            window.navigationBarColor = CyberDarkBg.value.toLong().toInt()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
            WindowCompat.getInsetsController(window, view).isAppearanceLightNavigationBars = false
        }
    }

    CompositionLocalProvider(LocalDarkTheme provides true) {
        MaterialTheme(
            colorScheme = colorScheme,
            content = {
                Box(modifier = Modifier.fillMaxSize()) {
                    content()
                }
            }
        )
    }
}
