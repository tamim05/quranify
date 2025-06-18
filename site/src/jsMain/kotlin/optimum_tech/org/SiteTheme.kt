package optimum_tech.org

import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.silk.init.InitSilk
import com.varabyte.kobweb.silk.init.InitSilkContext
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import com.varabyte.kobweb.silk.theme.colors.palette.background
import com.varabyte.kobweb.silk.theme.colors.palette.color

/**
 * Modern, UI/UX friendly color palette with careful attention to contrast and accessibility
 * @property nearBackground A subtle container color that differentiates from background
 * @property surface Elevated surface color for cards and containers
 * @property border Subtle border color for divisions and outlines
 * @property muted Muted text color for secondary information
 * @property accent Vibrant accent color for highlights and interactive elements
 * @property success Success state color
 * @property warning Warning state color
 * @property error Error state color
 * @property gradient Primary gradient colors for CTAs and highlights
 */
class SitePalette(
    val nearBackground: Color,
    val surface: Color,
    val border: Color,
    val muted: Color,
    val accent: Color,
    val success: Color,
    val warning: Color,
    val error: Color,
    val gradient: Gradient,
    val brand: Brand,
) {
    class Brand(
        val primary: Color,
        val secondary: Color,
        val tertiary: Color,
    )

    class Gradient(
        val start: Color,
        val end: Color,
        val accent: GradientAccent,
    ) {
        class GradientAccent(
            val start: Color,
            val end: Color,
        )
    }
}

object SitePalettes {
    val light = SitePalette(
        // Background variations - carefully crafted for depth
        nearBackground = Color.rgb(0xF8FAFC),      // Subtle gray-blue
        surface = Color.rgb(0xFFFFFF),             // Pure white for cards
        border = Color.rgb(0xE2E8F0),              // Soft border

        // Text variations
        muted = Color.rgb(0x64748B),               // Muted blue-gray

        // Interactive colors
        accent = Color.rgb(0x3B82F6),              // Modern blue
        success = Color.rgb(0x10B981),             // Emerald green
        warning = Color.rgb(0xF59E0B),             // Amber
        error = Color.rgb(0xEF4444),               // Red

        // Gradients - Purple to Pink (modern and vibrant)
        gradient = SitePalette.Gradient(
            start = Color.rgb(0x8B5CF6),           // Purple-500
            end = Color.rgb(0xEC4899),             // Pink-500
            accent = SitePalette.Gradient.GradientAccent(
                start = Color.rgb(0x7C3AED),       // Violet-600
                end = Color.rgb(0xDB2777),         // Pink-600
            )
        ),

        // Brand colors
        brand = SitePalette.Brand(
            primary = Color.rgb(0x6366F1),         // Indigo-500
            secondary = Color.rgb(0x8B5CF6),       // Purple-500
            tertiary = Color.rgb(0x06B6D4),        // Cyan-500
        )
    )

    val dark = SitePalette(
        // Background variations - rich dark theme
        nearBackground = Color.rgb(0x0F172A),      // Dark slate
        surface = Color.rgb(0x1E293B),             // Elevated dark surface
        border = Color.rgb(0x334155),              // Dark border

        // Text variations
        muted = Color.rgb(0x94A3B8),               // Light slate

        // Interactive colors - slightly adjusted for dark theme
        accent = Color.rgb(0x60A5FA),              // Light blue
        success = Color.rgb(0x34D399),             // Light emerald
        warning = Color.rgb(0xFBBF24),             // Light amber
        error = Color.rgb(0xF87171),               // Light red

        // Gradients - Same vibrant colors work well in dark
        gradient = SitePalette.Gradient(
            start = Color.rgb(0xA78BFA),           // Purple-400
            end = Color.rgb(0xF472B6),             // Pink-400
            accent = SitePalette.Gradient.GradientAccent(
                start = Color.rgb(0x8B5CF6),       // Purple-500
                end = Color.rgb(0xEC4899),         // Pink-500
            )
        ),

        // Brand colors - lighter variants for dark theme
        brand = SitePalette.Brand(
            primary = Color.rgb(0x818CF8),         // Indigo-400
            secondary = Color.rgb(0xA78BFA),       // Purple-400
            tertiary = Color.rgb(0x22D3EE),        // Cyan-400
        )
    )
}

fun ColorMode.toSitePalette(): SitePalette {
    return when (this) {
        ColorMode.LIGHT -> SitePalettes.light
        ColorMode.DARK -> SitePalettes.dark
    }
}

@InitSilk
fun initTheme(ctx: InitSilkContext) {
    // Light theme - Clean and modern
    ctx.theme.palettes.light.background = Color.rgb(0xFEFEFE)    // Off-white for better readability
    ctx.theme.palettes.light.color = Color.rgb(0x0F172A)        // Dark slate for excellent contrast

    // Dark theme - Rich and comfortable
    ctx.theme.palettes.dark.background = Color.rgb(0x020617)    // Very dark blue-black
    ctx.theme.palettes.dark.color = Color.rgb(0xF8FAFC)         // Off-white for comfortable reading
}

// Semantic color helpers
object SemanticColors {
    fun ColorMode.primary() = this.toSitePalette().brand.primary
    fun ColorMode.secondary() = this.toSitePalette().brand.secondary
    fun ColorMode.success() = this.toSitePalette().success
    fun ColorMode.warning() = this.toSitePalette().warning
    fun ColorMode.error() = this.toSitePalette().error
    fun ColorMode.muted() = this.toSitePalette().muted
}