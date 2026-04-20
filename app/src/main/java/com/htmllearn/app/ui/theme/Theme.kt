package com.htmllearn.app.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary         = Primary,
    onPrimary       = TextPrimary,
    primaryContainer= PrimaryDark,
    secondary       = Secondary,
    onSecondary     = TextPrimary,
    background      = BgDark,
    onBackground    = TextPrimary,
    surface         = BgSurface,
    onSurface       = TextPrimary,
    surfaceVariant  = BgCard,
    onSurfaceVariant= TextSecondary,
    error           = Error,
    outline         = Divider
)

@Composable
fun HtmlLearnTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography  = AppTypography,
        content     = content
    )
}
