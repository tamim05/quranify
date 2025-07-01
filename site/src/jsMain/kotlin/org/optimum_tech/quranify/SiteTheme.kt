package org.optimum_tech.quranify

import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.silk.init.InitSilk
import com.varabyte.kobweb.silk.init.InitSilkContext
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import com.varabyte.kobweb.silk.theme.colors.palette.background
import com.varabyte.kobweb.silk.theme.colors.palette.color

data class SitePalette(
    // Base colors
    val background: Color,
    val surface: Color,
    val border: Color,

    // Text colors
    val text: Color,
    val textMuted: Color,
    val textOnColor: Color,  // White text for colored backgrounds

    // Main colors
    val primary: Color,
    val secondary: Color,

    // Status colors
    val success: Color,
    val warning: Color,
    val error: Color,

    // Gradient
    val gradientStart: Color,
    val gradientEnd: Color,
)

object SitePalettes {
    val light = SitePalette(
        // Base colors
        background = Color.rgb(0xF8F9FA),       // Lighter gray background
        surface = Color.rgb(0xFFFFFF),          // White cards/surfaces
        border = Color.rgb(0xE9ECEF),           // Lighter border

        // Text colors
        text = Color.rgb(0x212529),             // Darker text for better contrast
        textMuted = Color.rgb(0x6C757D),        // Standard muted gray
        textOnColor = Color.rgb(0xFFFFFF),      // White text on colored backgrounds

        // Main colors
        primary = Color.rgb(0x0D6EFD),          // Bootstrap blue
        secondary = Color.rgb(0x6C757D),        // Bootstrap secondary gray

        // Status colors
        success = Color.rgb(0x198754),          // Bootstrap success green
        warning = Color.rgb(0xFFC107),          // Bootstrap warning yellow
        error = Color.rgb(0xDC3545),            // Bootstrap danger red

        // Beautiful gradient
        gradientStart = Color.rgb(0x0D6EFD),    // Bootstrap blue
        gradientEnd = Color.rgb(0x6610F2),      // Indigo
    )

    val dark = SitePalette(
        // Base colors
        background = Color.rgb(0x0B0C10),       // Very dark background
        surface = Color.rgb(0x1A1A2E),          // Dark blueish surface
        border = Color.rgb(0x2C2C44),           // Darker border

        // Text colors
        text = Color.rgb(0xE0E0E0),             // Light gray text
        textMuted = Color.rgb(0x8F8F9E),        // Muted gray
        textOnColor = Color.rgb(0xFFFFFF),      // White text on colored backgrounds

        // Main colors
        primary = Color.rgb(0x4A90E2),          // Brighter blue
        secondary = Color.rgb(0xBEBEE2),        // Light purple/blue

        // Status colors
        success = Color.rgb(0x28A745),          // Green
        warning = Color.rgb(0xFFC107),          // Yellow
        error = Color.rgb(0xDC3545),            // Red

        // Beautiful gradient
        gradientStart = Color.rgb(0x4A90E2),    // Brighter blue
        gradientEnd = Color.rgb(0x904AE2),      // Purple
    )
}

/**
 * Get palette based on color mode
 */
fun ColorMode.toSitePalette(): SitePalette {
    return when (this) {
        ColorMode.LIGHT -> SitePalettes.light
        ColorMode.DARK -> SitePalettes.dark
    }
}

@InitSilk
fun initTheme(ctx: InitSilkContext) {
    // Light theme
    ctx.theme.palettes.light.background = SitePalettes.light.background
    ctx.theme.palettes.light.color = SitePalettes.light.text

    // Dark theme
    ctx.theme.palettes.dark.background = SitePalettes.dark.background
    ctx.theme.palettes.dark.color = SitePalettes.dark.text
}
