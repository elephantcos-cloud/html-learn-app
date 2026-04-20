package com.htmllearn.app.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val AppTypography = Typography(
    headlineLarge  = TextStyle(fontSize = 28.sp, fontWeight = FontWeight.Bold,   color = TextPrimary),
    headlineMedium = TextStyle(fontSize = 22.sp, fontWeight = FontWeight.Bold,   color = TextPrimary),
    headlineSmall  = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.SemiBold,color = TextPrimary),
    titleLarge     = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.SemiBold,color = TextPrimary),
    titleMedium    = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Medium,  color = TextPrimary),
    bodyLarge      = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Normal,  color = TextSecondary),
    bodyMedium     = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Normal,  color = TextSecondary),
    bodySmall      = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Normal,  color = TextHint),
    labelLarge     = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Medium,  color = TextSecondary),
    labelSmall     = TextStyle(fontSize = 10.sp, fontWeight = FontWeight.Normal,  color = TextHint),
)
