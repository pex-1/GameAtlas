package com.example.core.presentation.designsystem

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

val MavenPro = FontFamily(
    Font(
        resId = R.font.spacegrotesk_light,
        weight = FontWeight.Light
    ),
    Font(
        resId = R.font.spacegrotesk_regular,
        weight = FontWeight.Normal
    ),
    Font(
        resId = R.font.spacegrotesk_medium,
        weight = FontWeight.Medium
    ),
    Font(
        resId = R.font.spacegrotesk_semibold,
        weight = FontWeight.SemiBold
    ),
    Font(
        resId = R.font.spacegrotesk_bold,
        weight = FontWeight.Bold
    ),
)

val Typography = Typography(
    bodySmall = TextStyle(
        fontFamily = MavenPro,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 20.sp,
        color = PurpleGray60
    ),
    bodyMedium = TextStyle(
        fontFamily = MavenPro,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        lineHeight = 22.sp,
    ),
    bodyLarge = TextStyle(
        fontFamily = MavenPro,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.6.sp,
    ),
    labelSmall = TextStyle(
        fontFamily = MavenPro,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        letterSpacing = 0.8.sp
    ),
    labelMedium = TextStyle(
        fontFamily = MavenPro,
        fontWeight = FontWeight.SemiBold,
        fontSize = 12.sp,
        letterSpacing = 0.5.sp
    ),
    labelLarge = TextStyle(
        fontFamily = MavenPro,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
    ),
    headlineMedium = TextStyle(
        fontFamily = MavenPro,
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp,
        color = Color.White
    ),
    titleLarge = TextStyle(
        fontFamily = MavenPro,
        fontWeight = FontWeight.Medium,
        fontSize = 30.sp,
        textAlign = TextAlign.Center,
        color = Neutral5,
        lineHeight = 34.sp
    )
)