package com.example.reversifriend.ui.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

object ReversiDesign {
    val ScreenTop = Color(0xFFEAF8FF)
    val ScreenMiddle = Color(0xFFFFFEFB)
    val ScreenBottom = Color(0xFFFFF6F9)

    val Primary = Color(0xFF157A6E)
    val PrimaryDark = Color(0xFF126B61)
    val TextStrong = Color(0xFF302A24)
    val TextMuted = Color(0xFF6B4B5F)
    val SoftSurface = Color(0xFFFFFFFF)

    val CpuSection = Color(0xFFFFE7C2)
    val ColorSection = Color(0xFFE6F6FF)
    val CharacterSection = Color(0xFFFFEAF3)
    val GameInfoSection = Color(0xFFFFF6E7)
    val TurnSection = Color(0xFFEAF7FF)
    val ConversationSection = Color(0xFFFFF5EC)

    val RikaSelected = Color(0xFFFFD978)
    val MaiSelected = Color(0xFFD8C5FF)
    val RikaMessage = Color(0xFFFFF0C6)
    val MaiMessage = Color(0xFFECE8F8)

    val BoardSurface = Color(0xFF2D706A)
    val BoardBorder = Color(0xFF23534F)
    val BoardLine = Color(0x66244945)
    val BoardLastMove = Color(0xFF3F887F)
    val LegalMove = Color(0xFFE3A16F)
    val BlackStone = Color(0xFF202322)
    val WhiteStone = Color(0xFFFFFCF2)

    fun screenBrush(): Brush =
        Brush.verticalGradient(
            colors = listOf(ScreenTop, ScreenMiddle, ScreenBottom),
        )
}
