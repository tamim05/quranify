package optimum_tech.org.pages.apps

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import com.varabyte.kobweb.compose.css.BackgroundPosition
import com.varabyte.kobweb.compose.css.BackgroundSize
import com.varabyte.kobweb.compose.css.BoxShadow
import com.varabyte.kobweb.compose.css.CSSPosition
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.JustifyContent
import com.varabyte.kobweb.compose.css.Overflow
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.css.Transition
import com.varabyte.kobweb.compose.css.TransitionTimingFunction
import com.varabyte.kobweb.compose.css.autoLength
import com.varabyte.kobweb.compose.css.functions.CSSUrl
import com.varabyte.kobweb.compose.css.functions.opacity
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Color.Companion.rgb
import com.varabyte.kobweb.compose.ui.modifiers.background
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.backgroundImage
import com.varabyte.kobweb.compose.ui.modifiers.backgroundPosition
import com.varabyte.kobweb.compose.ui.modifiers.backgroundSize
import com.varabyte.kobweb.compose.ui.modifiers.border
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.boxShadow
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.cursor
import com.varabyte.kobweb.compose.ui.modifiers.display
import com.varabyte.kobweb.compose.ui.modifiers.flexWrap
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.modifiers.gap
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.justifyContent
import com.varabyte.kobweb.compose.ui.modifiers.lineHeight
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.maxWidth
import com.varabyte.kobweb.compose.ui.modifiers.minHeight
import com.varabyte.kobweb.compose.ui.modifiers.overflow
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.position
import com.varabyte.kobweb.compose.ui.modifiers.right
import com.varabyte.kobweb.compose.ui.modifiers.textAlign
import com.varabyte.kobweb.compose.ui.modifiers.top
import com.varabyte.kobweb.compose.ui.modifiers.transition
import com.varabyte.kobweb.compose.ui.modifiers.width
import com.varabyte.kobweb.compose.ui.modifiers.zIndex
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.data.add
import com.varabyte.kobweb.core.init.InitRoute
import com.varabyte.kobweb.core.init.InitRouteContext
import com.varabyte.kobweb.core.layout.Layout
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.silk.components.forms.Button
import com.varabyte.kobweb.silk.components.text.SpanText
import kotlinx.browser.window
import optimum_tech.org.components.layouts.PageLayoutData
import org.jetbrains.compose.web.css.Color.transparent
import org.jetbrains.compose.web.css.Color.white
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.FlexWrap
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.rgba
import org.jetbrains.compose.web.css.s
import org.jetbrains.compose.web.css.transform
import org.jetbrains.compose.web.css.vh
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Text
import org.w3c.dom.get
import kotlin.let
@InitRoute
fun initAppsPage(ctx: InitRouteContext) {
    ctx.data.add(PageLayoutData("Apps"))
}

data class AppItem(
    val name: String,
    val emoji: String,
    val description: String,
    val subdomain: String,
    val platforms: List<String> = listOf("Android", "iOS", "Desktop"),
    val playStoreUrl: String? = null,
    val appStoreUrl: String? = null,
    val screenshots: List<String> = emptyList(),
    val isFeatured: Boolean = false,
    val longDescription: String = "",
    val features: List<String> = emptyList(),
    val requirements: String = "",
    val version: String = "1.0.0"
)

val appList = listOf(
    AppItem(
        name = "VocaMaster",
        emoji = "🧠",
        description = "Master Japanese vocabulary with JLPT-ready flashcards and spaced repetition algorithms.",
        longDescription = "VocaMaster is the ultimate Japanese vocabulary learning app designed specifically for JLPT preparation. Using scientifically-proven spaced repetition algorithms, it helps you memorize and retain thousands of Japanese words efficiently. The app includes comprehensive vocabulary sets for all JLPT levels (N5-N1) with native pronunciation, example sentences, and contextual usage.",
        subdomain = "vocamaster",
        playStoreUrl = "https://play.google.com/store/apps/details?id=com.optimumtech.vocamaster",
        appStoreUrl = "https://apps.apple.com/app/vocamaster/id123456789",
        screenshots = listOf(
            "https://images.unsplash.com/photo-1434030216411-0b793f4b4173?w=300&h=600&fit=crop",
            "https://images.unsplash.com/photo-1513475382585-d06e58bcb0e0?w=300&h=600&fit=crop",
            "https://images.unsplash.com/photo-1456513080510-7bf3a84b82f8?w=300&h=600&fit=crop"
        ),
        features = listOf(
            "JLPT N5-N1 vocabulary sets",
            "Spaced repetition algorithm",
            "Native pronunciation audio",
            "Progress tracking and statistics",
            "Offline mode support",
            "Custom study sessions"
        ),
        requirements = "Android 7.0+ / iOS 12.0+",
        version = "2.1.4",
        isFeatured = true
    ),
    AppItem(
        name = "KanjiCrafter",
        emoji = "🈶",
        description = "Learn Kanji through interactive drawing and advanced recognition technology.",
        longDescription = "KanjiCrafter revolutionizes Kanji learning through interactive stroke-by-stroke drawing exercises. Our advanced handwriting recognition technology provides real-time feedback on your writing, helping you master proper stroke order and character formation. The app covers all essential Kanji characters with detailed breakdowns, mnemonics, and practice exercises.",
        subdomain = "kanjicrafter",
        playStoreUrl = "https://play.google.com/store/apps/details?id=com.optimumtech.kanjicrafter",
        screenshots = listOf(
            "https://images.unsplash.com/photo-1503676260728-1c00da094a0b?w=300&h=600&fit=crop",
            "https://images.unsplash.com/photo-1488590528505-98d2b5aba04b?w=300&h=600&fit=crop",
            "https://images.unsplash.com/photo-1484417894907-623942c8ee29?w=300&h=600&fit=crop"
        ),
        features = listOf(
            "Interactive stroke drawing",
            "Real-time handwriting recognition",
            "Stroke order animations",
            "Kanji breakdown and radicals",
            "Mnemonic learning aids",
            "Writing practice sheets"
        ),
        requirements = "Android 8.0+ / iOS 13.0+",
        version = "1.8.2",
        isFeatured = true
    ),
    AppItem(
        name = "GrammarHero",
        emoji = "📘",
        description = "Tackle essential Japanese grammar with interactive exercises and real-world examples.",
        longDescription = "GrammarHero makes Japanese grammar accessible and engaging through interactive lessons and real-world examples. From basic particles to complex grammatical structures, our comprehensive curriculum covers all essential grammar points with clear explanations, practice exercises, and contextual examples from everyday Japanese.",
        subdomain = "grammarhero",
        appStoreUrl = "https://apps.apple.com/app/grammarhero/id987654321",
        screenshots = listOf(
            "https://images.unsplash.com/photo-1516321318423-f06f85e504b3?w=300&h=600&fit=crop",
            "https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?w=300&h=600&fit=crop",
            "https://images.unsplash.com/photo-1517077304055-6e89abbf09b0?w=300&h=600&fit=crop"
        ),
        features = listOf(
            "Comprehensive grammar lessons",
            "Interactive exercises",
            "Real-world example sentences",
            "Grammar pattern explanations",
            "Progress tracking",
            "Quiz and test modes"
        ),
        requirements = "iOS 12.0+ only",
        version = "1.5.1"
    )
)

@Composable
fun AppCard(app: AppItem, isFeatured: Boolean = false, onCardClick: (AppItem) -> Unit) {
    val cardWidth = if (isFeatured) 28.cssRem else 24.cssRem
    val cardPadding = if (isFeatured) 2.5.cssRem else 2.cssRem

    Div(
        Modifier
            .width(100.percent)
            .maxWidth(cardWidth)
            .padding(cardPadding)
            .borderRadius(1.5.cssRem)
            .backgroundColor(rgba(255, 255, 255, 0.08))
            .border(1.px, LineStyle.Solid, rgba(255, 255, 255, 0.12))
            .boxShadow(BoxShadow.of(0.px, 10.px, 25.px, color = rgba(0, 0, 0, 0.25)))
            .transition(Transition.of("transform", 0.3.s))
            .cursor(Cursor.Pointer)
            .toAttrs {
                style {
                    property("backdrop-filter", "blur(10px)")
                    property("--webkit-backdrop-filter", "blur(10px)")
                }
                onClick { onCardClick(app) }
            }
    ) {
        if (app.screenshots.isNotEmpty()) {
            Div(
                Modifier
                    .width(100.percent)
                    .height(if (isFeatured) 12.cssRem else 10.cssRem)
                    .borderRadius(1.cssRem)
                    .margin(bottom = 1.5.cssRem)
                    .backgroundColor(rgba(255, 255, 255, 0.05))
                    .position(Position.Relative)
                    .overflow(Overflow.Hidden)
                    .toAttrs {
                        id("screenshot-${app.name.lowercase()}")
                    }
            ) {
                app.screenshots.forEachIndexed { index, screenshot ->
                    Div(
                        Modifier
                            .width(100.percent)
                            .height(100.percent)
                            .position(Position.Absolute)
                            .backgroundSize(BackgroundSize.Cover)
                            .backgroundPosition(BackgroundPosition.of(CSSPosition.Center))
                            .borderRadius(1.cssRem)
                            .toAttrs {
                                style {
                                    opacity(if (index == 0) 1.0 else 0.0)
                                    property("background-image", "url($screenshot)")
                                }
                                ref { element ->
                                    window.setInterval({
                                        val container = kotlinx.browser.document.getElementById("screenshot-${app.name.lowercase()}")
                                        container?.let {cont->
                                            val image = cont.children
                                            var currentIndex = 0
                                            for (i in 0 until image.length) {
                                                if (image.item(i)?.getAttribute("style")?.contains("opacity: 1") == true) {
                                                    currentIndex = i
                                                    break
                                                }
                                            }
                                            image.get(currentIndex)?.setAttribute("style", "opacity: 0; transition: opacity 0.5s ease-in-out; width: 100%; height: 100%; position: absolute; background-size: cover; background-position: center; border-radius: 1rem; background-image: url(${app.screenshots[currentIndex]})")
                                            val nextIndex = (currentIndex + 1) % app.screenshots.size
                                            image.get(nextIndex)?.setAttribute("style", "opacity: 1; transition: opacity 0.5s ease-in-out; width: 100%; height: 100%; position: absolute; background-size: cover; background-position: center; border-radius: 1rem; background-image: url(${app.screenshots[nextIndex]})")
                                        }
                                    }, 8000)
                                    onDispose { }
                                }
                            }
                    )
                }
            }
        }

        Div(Modifier.margin(bottom = 1.cssRem).fontSize(if (isFeatured) 2.5.cssRem else 2.cssRem).textAlign(TextAlign.Center).toAttrs()) {
            Text(app.emoji)
        }

        SpanText(
            app.name,
            Modifier
                .fontSize(if (isFeatured) 1.75.cssRem else 1.5.cssRem)
                .fontWeight(FontWeight.Bold)
                .textAlign(TextAlign.Center)
                .display(DisplayStyle.Block)
                .margin(bottom = 0.75.cssRem)
        )

        SpanText(
            app.description,
            Modifier
                .fontSize(if (isFeatured) 1.1.cssRem else 1.cssRem)
                .color(rgb(203, 213, 225))
                .textAlign(TextAlign.Center)
                .display(DisplayStyle.Block)
                .margin(bottom = 1.5.cssRem)
                .lineHeight(1.6)
        )

        Div(
            Modifier
                .display(DisplayStyle.Flex)
                .justifyContent(JustifyContent.Center)
                .gap(0.5.cssRem)
                .margin(bottom = 1.5.cssRem)
                .flexWrap(FlexWrap.Wrap)
                .toAttrs()
        ) {
            app.platforms.forEach { platform ->
                SpanText(
                    platform,
                    Modifier
                        .fontSize(0.75.cssRem)
                        .padding(0.375.cssRem, 0.75.cssRem)
                        .borderRadius(50.px)
                        .backgroundColor(rgb(51, 65, 85))
                        .color(white)
                        .fontWeight(FontWeight.Medium)
                )
            }
        }

        Div(
            Modifier
                .display(DisplayStyle.Flex)
                .justifyContent(JustifyContent.Center)
                .gap(0.75.cssRem)
                .flexWrap(FlexWrap.Wrap)
                .toAttrs()
        ) {
            Button(
                onClick = {
                    val url = "https://${app.subdomain}.optimum-tech.org"
                    window.open(url, "_blank")
                },
                Modifier
                    .padding(0.75.cssRem, 1.5.cssRem)
                    .borderRadius(50.px)
                    .backgroundColor(rgb(147, 51, 234))
                    .color(white)
                    .fontWeight(FontWeight.SemiBold)
                    .fontSize(0.875.cssRem)
                    .cursor(Cursor.Pointer)
                    .border(0.px, LineStyle.None, white)
                    .transition(Transition.of("background-color", 0.2.s))
            ) {
                Text("Visit App")
            }

            app.playStoreUrl?.let { url ->
                Button(
                    onClick = { window.open(url, "_blank") },
                    Modifier
                        .padding(0.75.cssRem, 1.5.cssRem)
                        .borderRadius(50.px)
                        .backgroundColor(rgba(255, 255, 255, 0.1))
                        .color(white)
                        .fontWeight(FontWeight.Medium)
                        .fontSize(0.875.cssRem)
                        .cursor(Cursor.Pointer)
                        .border(1.px, LineStyle.Solid, rgba(255, 255, 255, 0.2))
                        .transition(Transition.of("background-color", 0.2.s))
                ) {
                    Text("Play Store")
                }
            }

            app.appStoreUrl?.let { url ->
                Button(
                    onClick = { window.open(url, "_blank") },
                    Modifier
                        .padding(0.75.cssRem, 1.5.cssRem)
                        .borderRadius(50.px)
                        .backgroundColor(rgba(255, 255, 255, 0.1))
                        .color(white)
                        .fontWeight(FontWeight.Medium)
                        .fontSize(0.875.cssRem)
                        .cursor(Cursor.Pointer)
                        .border(1.px, LineStyle.Solid, rgba(255, 255, 255, 0.2))
                        .transition(Transition.of("background-color", 0.2.s))
                ) {
                    Text("App Store")
                }
            }
        }
    }
}

@Composable
fun AppDetailView(app: AppItem, onClose: () -> Unit) {
    Div(
        Modifier
            .position(Position.Fixed)
            .width(100.percent)
            .height(100.vh)
            .backgroundColor(rgba(15, 23, 42, 0.98))
            .color(white)
            .overflow(Overflow.Auto)
            .toAttrs {
                style {
                    property("top", "0")
                    property("left", "0")
                    property("z-index", "1000")
                    property("backdrop-filter", "blur(20px)")
                    property("animation", "zoomIn 0.4s cubic-bezier(0.25, 0.46, 0.45, 0.94)")
                }
            }
    ) {
        // Add CSS animations
        Div(
            Modifier.toAttrs {
                style {
                    property("display", "none")
                }
                ref { element ->
                    val style = kotlinx.browser.document.createElement("style")
                    style.textContent = """
                        @keyframes zoomIn {
                            from {
                                opacity: 0;
                                transform: scale(0.3);
                            }
                            to {
                                opacity: 1;
                                transform: scale(1);
                            }
                        }
                        @keyframes zoomOut {
                            from {
                                opacity: 1;
                                transform: scale(1);
                            }
                            to {
                                opacity: 0;
                                transform: scale(0.3);
                            }
                        }
                    """.trimIndent()
                    kotlinx.browser.document.head?.appendChild(style)
                    onDispose { }
                }
            }
        )

        // Close Button
        Button(
            onClick = {
                // Add exit animation before closing
                val element = kotlinx.browser.document.querySelector("[style*='zoomIn']") as? org.w3c.dom.HTMLElement
                element?.style?.animation = "zoomOut 0.3s cubic-bezier(0.55, 0.055, 0.675, 0.19)"
                window.setTimeout({ onClose() }, 300)
            },
            Modifier
                .position(Position.Fixed)
                .padding(1.cssRem)
                .borderRadius(50.px)
                .backgroundColor(rgba(255, 255, 255, 0.1))
                .color(white)
                .border(1.px, LineStyle.Solid, rgba(255, 255, 255, 0.2))
                .cursor(Cursor.Pointer)
                .fontSize(1.5.cssRem)
                .width(3.cssRem)
                .height(3.cssRem)
                .transition(Transition.of("background-color", 0.2.s))
                .top(2.cssRem)
                .right(2.cssRem)
                .zIndex(1001)
        ) {
            Text("×")
        }

        Div(
            Modifier
                .maxWidth(1200.px)
                .margin(leftRight = autoLength)
                .padding(4.cssRem, 2.cssRem)
                .toAttrs()
        ) {
            // Header Section
            Div(
                Modifier
                    .display(DisplayStyle.Flex)
                    .gap(3.cssRem)
                    .margin(bottom = 4.cssRem)
                    .toAttrs {
                        style {
                            property("align-items", "flex-start")
                        }
                    }
            ) {
                // App Icon and Basic Info
                Div(
                    Modifier
                        .toAttrs {
                            style {
                                property("flex", "0 0 auto")
                            }
                        }
                ) {
                    Div(
                        Modifier
                            .width(8.cssRem)
                            .height(8.cssRem)
                            .borderRadius(2.cssRem)
                            .backgroundColor(rgba(255, 255, 255, 0.1))
                            .display(DisplayStyle.Flex)
                            .justifyContent(JustifyContent.Center)
                            .fontSize(4.cssRem)
                            .margin(bottom = 1.cssRem)
                            .toAttrs {
                                style {
                                    property("align-items", "center")
                                }
                            }
                    ) {
                        Text(app.emoji)
                    }

                    Div(
                        Modifier
                            .display(DisplayStyle.Flex)
                            .gap(0.5.cssRem)
                            .flexWrap(FlexWrap.Wrap)
                            .toAttrs()
                    ) {
                        app.platforms.forEach { platform ->
                            SpanText(
                                platform,
                                Modifier
                                    .fontSize(0.75.cssRem)
                                    .padding(0.375.cssRem, 0.75.cssRem)
                                    .borderRadius(50.px)
                                    .backgroundColor(rgb(51, 65, 85))
                                    .color(white)
                                    .fontWeight(FontWeight.Medium)
                            )
                        }
                    }
                }

                // App Details
                Div(
                    Modifier
                        .toAttrs {
                            style {
                                property("flex", "1")
                            }
                        }
                ) {
                    SpanText(
                        app.name,
                        Modifier
                            .fontSize(3.cssRem)
                            .fontWeight(FontWeight.ExtraBold)
                            .display(DisplayStyle.Block)
                            .margin(bottom = 0.5.cssRem)
                    )

                    SpanText(
                        "Version ${app.version}",
                        Modifier
                            .fontSize(1.cssRem)
                            .color(rgb(148, 163, 184))
                            .display(DisplayStyle.Block)
                            .margin(bottom = 1.cssRem)
                    )

                    SpanText(
                        app.longDescription,
                        Modifier
                            .fontSize(1.25.cssRem)
                            .color(rgb(203, 213, 225))
                            .display(DisplayStyle.Block)
                            .lineHeight(1.7)
                            .margin(bottom = 2.cssRem)
                    )

                    // Action Buttons
                    Div(
                        Modifier
                            .display(DisplayStyle.Flex)
                            .gap(1.cssRem)
                            .flexWrap(FlexWrap.Wrap)
                            .toAttrs()
                    ) {
                        Button(
                            onClick = {
                                val url = "https://${app.subdomain}.optimum-tech.org"
                                window.open(url, "_blank")
                            },
                            Modifier
                                .padding(1.cssRem, 2.cssRem)
                                .borderRadius(50.px)
                                .backgroundColor(rgb(147, 51, 234))
                                .color(white)
                                .fontWeight(FontWeight.SemiBold)
                                .fontSize(1.cssRem)
                                .cursor(Cursor.Pointer)
                                .border(0.px, LineStyle.None, white)
                                .transition(Transition.of("background-color", 0.2.s))
                        ) {
                            Text("Launch App")
                        }

                        app.playStoreUrl?.let { url ->
                            Button(
                                onClick = { window.open(url, "_blank") },
                                Modifier
                                    .padding(1.cssRem, 2.cssRem)
                                    .borderRadius(50.px)
                                    .backgroundColor(rgba(255, 255, 255, 0.1))
                                    .color(white)
                                    .fontWeight(FontWeight.Medium)
                                    .fontSize(1.cssRem)
                                    .cursor(Cursor.Pointer)
                                    .border(1.px, LineStyle.Solid, rgba(255, 255, 255, 0.2))
                                    .transition(Transition.of("background-color", 0.2.s))
                            ) {
                                Text("Play Store")
                            }
                        }

                        app.appStoreUrl?.let { url ->
                            Button(
                                onClick = { window.open(url, "_blank") },
                                Modifier
                                    .padding(1.cssRem, 2.cssRem)
                                    .borderRadius(50.px)
                                    .backgroundColor(rgba(255, 255, 255, 0.1))
                                    .color(white)
                                    .fontWeight(FontWeight.Medium)
                                    .fontSize(1.cssRem)
                                    .cursor(Cursor.Pointer)
                                    .border(1.px, LineStyle.Solid, rgba(255, 255, 255, 0.2))
                                    .transition(Transition.of("background-color", 0.2.s))
                            ) {
                                Text("App Store")
                            }
                        }
                    }
                }
            }

            // Screenshots Section
            if (app.screenshots.isNotEmpty()) {
                Div(
                    Modifier
                        .margin(bottom = 4.cssRem)
                        .toAttrs()
                ) {
                    SpanText(
                        "Screenshots",
                        Modifier
                            .fontSize(2.cssRem)
                            .fontWeight(FontWeight.Bold)
                            .display(DisplayStyle.Block)
                            .margin(bottom = 2.cssRem)
                    )

                    Div(
                        Modifier
                            .display(DisplayStyle.Flex)
                            .gap(1.5.cssRem)
                            .overflow(Overflow.Auto)
                            .padding(bottom = 1.cssRem)
                            .toAttrs {
                                style {
                                    property("scroll-behavior", "smooth")
                                }
                            }
                    ) {
                        app.screenshots.forEach { screenshot ->
                            Div(
                                Modifier
                                    .width(12.cssRem)
                                    .height(24.cssRem)
                                    .borderRadius(1.cssRem)
                                    .backgroundColor(rgba(255, 255, 255, 0.05))
                                    .backgroundSize(BackgroundSize.Cover)
                                    .backgroundPosition(BackgroundPosition.of(CSSPosition.Center))
                                    .cursor(Cursor.Pointer)
                                    .transition(Transition.of("transform", 0.2.s))
                                    .toAttrs {
                                        style {
                                            property("background-image", "url($screenshot)")
                                            property("flex-shrink", "0")
                                        }
                                    }
                            )
                        }
                    }
                }
            }

            // Features and Requirements Section
            Div(
                Modifier
                    .display(DisplayStyle.Flex)
                    .gap(4.cssRem)
                    .toAttrs {
                        style {
                            property("align-items", "flex-start")
                        }
                    }
            ) {
                // Features
                if (app.features.isNotEmpty()) {
                    Div(
                        Modifier
                            .toAttrs {
                                style {
                                    property("flex", "1")
                                }
                            }
                    ) {
                        SpanText(
                            "Key Features",
                            Modifier
                                .fontSize(1.75.cssRem)
                                .fontWeight(FontWeight.Bold)
                                .display(DisplayStyle.Block)
                                .margin(bottom = 1.5.cssRem)
                        )

                        Column(
                            Modifier.gap(1.cssRem)
                        ) {
                            app.features.forEach { feature ->
                                Div(
                                    Modifier
                                        .display(DisplayStyle.Flex)
                                        .gap(0.75.cssRem)
                                        .toAttrs {
                                            style {
                                                property("align-items", "flex-start")
                                            }
                                        }
                                ) {
                                    SpanText(
                                        "✓",
                                        Modifier
                                            .color(rgb(34, 197, 94))
                                            .fontWeight(FontWeight.Bold)
                                            .fontSize(1.25.cssRem)
                                    )
                                    SpanText(
                                        feature,
                                        Modifier
                                            .color(rgb(203, 213, 225))
                                            .fontSize(1.1.cssRem)
                                            .lineHeight(1.6)
                                    )
                                }
                            }
                        }
                    }
                }

                // Requirements
                Div(
                    Modifier
                        .toAttrs {
                            style {
                                property("flex", "0 0 20rem")
                            }
                        }
                ) {
                    SpanText(
                        "Requirements",
                        Modifier
                            .fontSize(1.75.cssRem)
                            .fontWeight(FontWeight.Bold)
                            .display(DisplayStyle.Block)
                            .margin(bottom = 1.5.cssRem)
                    )

                    Div(
                        Modifier
                            .padding(1.5.cssRem)
                            .borderRadius(1.cssRem)
                            .backgroundColor(rgba(255, 255, 255, 0.05))
                            .border(1.px, LineStyle.Solid, rgba(255, 255, 255, 0.1))
                            .toAttrs()
                    ) {
                        SpanText(
                            "System Requirements",
                            Modifier
                                .fontSize(1.1.cssRem)
                                .fontWeight(FontWeight.SemiBold)
                                .display(DisplayStyle.Block)
                                .margin(bottom = 0.75.cssRem)
                        )

                        SpanText(
                            app.requirements,
                            Modifier
                                .color(rgb(203, 213, 225))
                                .fontSize(1.cssRem)
                                .lineHeight(1.6)
                        )
                    }
                }
            }
        }
    }
}
@Page("/apps")
@Layout(".components.layouts.PageLayout")
@Composable
fun Apps() {
    val ctx = rememberPageContext()
    val featuredApps = appList.filter { it.isFeatured }
    val regularApps = appList.filter { !it.isFeatured }

    // State for selected app
    var selectedApp by remember { mutableStateOf<AppItem?>(null) }

    Div(
        Modifier
            .padding(top = 6.cssRem, bottom = 4.cssRem)
            .backgroundColor(rgb(15, 23, 42))
            .color(white)
            .minHeight(100.vh)
            .toAttrs()
    ) {
        Div(
            Modifier
                .maxWidth(1400.px)
                .margin(leftRight = autoLength)
                .padding(2.cssRem)
                .toAttrs()
        ) {
            // Header Section
            Div(
                Modifier
                    .textAlign(TextAlign.Center)
                    .margin(bottom = 4.cssRem)
                    .toAttrs()
            ) {
                SpanText(
                    "Discover Our Applications",
                    Modifier
                        .fontSize(3.5.cssRem)
                        .fontWeight(FontWeight.ExtraBold)
                        .display(DisplayStyle.Block)
                        .margin(bottom = 1.cssRem)
                        .color(white)
                )

                SpanText(
                    "Innovative solutions designed to enhance your learning experience and productivity",
                    Modifier
                        .fontSize(1.25.cssRem)
                        .color(rgb(148, 163, 184))
                        .display(DisplayStyle.Block)
                        .maxWidth(40.cssRem)
                        .margin(leftRight = autoLength)
                        .lineHeight(1.6)
                )
            }

            // Featured Apps Section
            if (featuredApps.isNotEmpty()) {
                Div(
                    Modifier
                        .margin(bottom = 5.cssRem)
                        .toAttrs()
                ) {
                    SpanText(
                        "Featured Applications",
                        Modifier
                            .fontSize(2.5.cssRem)
                            .fontWeight(FontWeight.Bold)
                            .display(DisplayStyle.Block)
                            .margin(bottom = 1.cssRem)
                            .textAlign(TextAlign.Center)
                    )

                    SpanText(
                        "Our most popular and highly-rated applications",
                        Modifier
                            .fontSize(1.1.cssRem)
                            .color(rgb(148, 163, 184))
                            .display(DisplayStyle.Block)
                            .margin(bottom = 3.cssRem)
                            .textAlign(TextAlign.Center)
                    )

                    Div(
                        Modifier
                            .display(DisplayStyle.Flex)
                            .flexWrap(FlexWrap.Wrap)
                            .gap(2.5.cssRem)
                            .justifyContent(JustifyContent.Center)
                            .toAttrs()
                    ) {
                        featuredApps.forEach { app ->
                            AppCard(app, isFeatured = true) { selectedApp = it }
                        }
                    }
                }
            }

            // All Apps Section
            if (regularApps.isNotEmpty()) {
                Div(
                    Modifier
                        .toAttrs()
                ) {
                    SpanText(
                        "All Applications",
                        Modifier
                            .fontSize(2.25.cssRem)
                            .fontWeight(FontWeight.Bold)
                            .display(DisplayStyle.Block)
                            .margin(bottom = 1.cssRem)
                            .textAlign(TextAlign.Center)
                    )

                    SpanText(
                        "Explore our complete collection of apps",
                        Modifier
                            .fontSize(1.1.cssRem)
                            .color(rgb(148, 163, 184))
                            .display(DisplayStyle.Block)
                            .margin(bottom = 3.cssRem)
                            .textAlign(TextAlign.Center)
                    )

                    Div(
                        Modifier
                            .display(DisplayStyle.Flex)
                            .flexWrap(FlexWrap.Wrap)
                            .gap(2.cssRem)
                            .justifyContent(JustifyContent.Center)
                            .toAttrs()
                    ) {
                        regularApps.forEach { app ->
                            AppCard(app, isFeatured = false) { selectedApp = it }
                        }
                    }
                }
            }
        }

        // App Detail View Overlay
        selectedApp?.let { app ->
            AppDetailView(app) { selectedApp = null }
        }
    }
}