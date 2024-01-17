package app.kotlin.unscramble.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import app.kotlin.unscramble.R

// Set of Material typography styles to start with
@Composable
fun TextStyle.fontScale(): TextStyle {
    val fontScale: Float = LocalConfiguration.current.fontScale
    return this.copy(fontSize = this.fontSize / fontScale)
}

val anonymousProFontFamily = FontFamily(
    Font(R.font.anonymous_pro_regular, FontWeight.Normal)
)

val displayLarge = TextStyle(
    fontFamily = anonymousProFontFamily,
    fontSize = 96.sp,
    fontWeight = FontWeight.Normal
)

val displayMedium = TextStyle(
    fontFamily = anonymousProFontFamily,
    fontWeight = FontWeight.Normal,
    fontSize = 64.sp
)

val displaySmall = TextStyle(
    fontFamily = anonymousProFontFamily,
    fontWeight = FontWeight.Normal,
    fontSize = 32.sp
)

val titleMedium = TextStyle(
    fontFamily = anonymousProFontFamily,
    fontWeight = FontWeight.Normal,
    fontSize = 40.sp
)

val bodySmall = TextStyle(
    fontWeight = FontWeight.Normal,
    fontSize = 14.sp,
    letterSpacing = 0.25.sp
)

val labelLarge = TextStyle(
    fontFamily = anonymousProFontFamily,
    fontSize = 24.sp,
    fontWeight = FontWeight.Normal
)

val labelMediumRoboto = TextStyle(
    fontWeight = FontWeight.Normal,
    fontSize = 16.sp,
    letterSpacing = 0.15.sp
)

val labelMedium = TextStyle(
    fontFamily = anonymousProFontFamily,
    fontWeight = FontWeight.Normal,
    fontSize = 16.sp,
    letterSpacing = 0.15.sp
)