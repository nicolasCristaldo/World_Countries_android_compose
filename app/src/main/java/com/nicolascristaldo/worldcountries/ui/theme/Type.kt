package com.nicolascristaldo.worldcountries.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.nicolascristaldo.worldcountries.R

val UbuntuCondensed = FontFamily(
    Font(R.font.ubuntu_condensed, FontWeight.Normal)
)

val Lexend = FontFamily(
    Font(R.font.lexend_regular, FontWeight.Normal),
    Font(R.font.lexend_thin, FontWeight.Thin),
    Font(R.font.lexend_bold, FontWeight.Bold)
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = Lexend,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 18.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = Lexend,
        fontWeight = FontWeight.Thin,
        fontSize = 16.sp,
        lineHeight = 18.sp,
        letterSpacing = 1.sp
    ),
    bodySmall = TextStyle(
        fontFamily = Lexend,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        lineHeight = 18.sp
    ),
    displayLarge = TextStyle(
        fontFamily = UbuntuCondensed,
        fontWeight = FontWeight.Normal,
        fontSize = 38.sp,
    ),
    displayMedium = TextStyle(
        fontFamily = UbuntuCondensed,
        fontWeight = FontWeight.Normal,
        fontSize = 32.sp,
        lineHeight = 34.sp,
        letterSpacing = 0.sp
    ),
    displaySmall = TextStyle(
        fontFamily = Lexend,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.sp
    )
)