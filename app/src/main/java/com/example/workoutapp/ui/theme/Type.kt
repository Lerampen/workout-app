package com.example.workoutapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.workoutapp.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)
val robotoFontFamily = FontFamily(
    Font(R.font.robotoserif_28pt_bold, FontWeight.Bold),
    Font(R.font.robotoserif_28pt_light, FontWeight.Light),
    Font(R.font.robotoserif_28pt_medium, FontWeight.Medium),
    Font(R.font.robotoserif_28pt_regular, FontWeight.Normal),
    Font(R.font.robotoserif_28pt_semibold, FontWeight.SemiBold),
    Font(R.font.robotoserif_28pt_thin, FontWeight.Thin)



)