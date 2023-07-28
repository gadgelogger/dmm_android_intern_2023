package com.dmm.bootcamp.yatter2023.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val darkColorScheme = darkColors(
  primary = Color(0xFF03A9F4),
  onPrimary = Color.White,
  onSecondary = Color(0xFF03A9F4)
)

private val lightColorScheme = lightColors(
  primary = Color(0xFF03A9F4),
  onPrimary = Color.Black,
  onSecondary = Color(0xFF03A9F4)

)

@Composable
fun Yatter2023Theme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  content: @Composable () -> Unit
) {
  val colorScheme =
    if (darkTheme) darkColorScheme else lightColorScheme

  MaterialTheme(
    colors = colorScheme,
    typography = Typography,
    content = content
  )
}