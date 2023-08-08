package com.dojo.core_ui.styles

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.dojo.core_ui.theme.InputGray

val inputStyle = TextStyle(
    color = InputGray,
    fontWeight = FontWeight.Bold,
    fontSize = 14.sp,
)

val smallTextStyle = TextStyle(
    color = InputGray,
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Normal,
    fontStyle = FontStyle.Italic,
    fontSize = 12.sp,
)

val mediumTextStyle = TextStyle(
    color = InputGray,
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Normal,
    fontStyle = FontStyle.Italic,
    fontSize = 16.sp,
)

val largeTextStyle = TextStyle(
    color = InputGray,
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Bold,
    fontSize = 20.sp,
)