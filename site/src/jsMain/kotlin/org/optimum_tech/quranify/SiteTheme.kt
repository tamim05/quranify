package org.optimum_tech.quranify

import com.varabyte.kobweb.compose.css.functions.linearGradient
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
        background = Color.rgb(0xFAFAFA),       // Light gray background
        surface = Color.rgb(0xFFFFFF),          // White cards/surfaces
        border = Color.rgb(0xE5E7EB),           // Light border

        // Text colors
        text = Color.rgb(0x1F2937),             // Dark text
        textMuted = Color.rgb(0x6B7280),        // Gray text
        textOnColor = Color.rgb(0xFFFFFF),      // White text on colored backgrounds

        // Main colors
        primary = Color.rgb(0x3B82F6),          // Blue
        secondary = Color.rgb(0x8B5CF6),        // Purple

        // Status colors
        success = Color.rgb(0x10B981),          // Green
        warning = Color.rgb(0xF59E0B),          // Orange
        error = Color.rgb(0xEF4444),            // Red

        // Beautiful gradient
        gradientStart = Color.rgb(0x667EEA),    // Light blue
        gradientEnd = Color.rgb(0x764BA2),      // Purple
    )

    val dark = SitePalette(
        // Base colors
        background = Color.rgb(0x0F172A),       // Dark background
        surface = Color.rgb(0x1E293B),          // Dark cards/surfaces
        border = Color.rgb(0x374151),           // Dark border

        // Text colors
        text = Color.rgb(0xF9FAFB),             // Light text
        textMuted = Color.rgb(0x9CA3AF),        // Gray text
        textOnColor = Color.rgb(0xFFFFFF),      // White text on colored backgrounds

        // Main colors
        primary = Color.rgb(0x60A5FA),          // Light blue
        secondary = Color.rgb(0xA78BFA),        // Light purple

        // Status colors
        success = Color.rgb(0x34D399),          // Light green
        warning = Color.rgb(0xFBBF24),          // Light orange
        error = Color.rgb(0xF87171),            // Light red

        // Beautiful gradient
        gradientStart = Color.rgb(0x667EEA),    // Light blue
        gradientEnd = Color.rgb(0x764BA2),      // Purple
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