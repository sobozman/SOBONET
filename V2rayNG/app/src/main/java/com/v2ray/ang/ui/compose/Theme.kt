package com.v2ray.ang.ui.compose

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.v2ray.ang.AppConfig
import com.v2ray.ang.handler.MmkvManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

// رنگ‌های نئونی و دارک سایبرپانک SOBONET
val CyberBg = Color(0xFF090D16)          // پس‌زمینه عمیق تیره
val CyberSurface = Color(0xFF121826)     // پس‌زمینه کارت‌ها و ردیف‌ها
val CyberSurfaceVariant = Color(0xFF1A2333)
val CyberCyan = Color(0xFF00F0FF)        // نئون فیروزه‌ای اصلی
val CyberPink = Color(0xFFFF0055)        // نئون صورتی/سرخابی
val CyberGreen = Color(0xFF00FF9D)       // نئون سبز متصل
val CyberText = Color(0xFFFFFFFF)        // متن سفید
val CyberSubText = Color(0xFF8A99AD)     // متن دوم خاکستری ملایم
val CyberBorder = Color(0xFF222F44)      // کادر دور کارت‌ها

private val CyberDarkColorScheme = darkColorScheme(
    primary = CyberCyan,
    onPrimary = CyberBg,
    primaryContainer = CyberSurfaceVariant,
    onPrimaryContainer = CyberCyan,
    secondary = CyberPink,
    onSecondary = Color.White,
    secondaryContainer = CyberSurfaceVariant,
    onSecondaryContainer = CyberPink,
    tertiary = CyberGreen,
    onTertiary = CyberBg,
    background = CyberBg,
    onBackground = CyberText,
    surface = CyberSurface,
    onSurface = CyberText,
    surfaceVariant = CyberSurfaceVariant,
    onSurfaceVariant = CyberSubText,
    outline = CyberBorder,
    error = CyberPink,
    onError = Color.White
)

@Composable
fun Theme(
    darkTheme: Boolean = true, // تم همیشه روی دارک قفل است
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = CyberDarkColorScheme
    val view = LocalView.current

    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = CyberBg.value.toLong().toInt()
            window.navigationBarColor = CyberBg.value.toLong().toInt()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
            WindowCompat.getInsetsController(window, view).isAppearanceLightNavigationBars = false
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        content = {
            Box(modifier = Modifier.fillMaxSize()) {
                content()
            }
        }
    )
}
