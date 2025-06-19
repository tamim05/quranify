package optimum_tech.org.pages.apps

import androidx.compose.runtime.Composable
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
import com.varabyte.kobweb.compose.ui.modifiers.textAlign
import com.varabyte.kobweb.compose.ui.modifiers.transition
import com.varabyte.kobweb.compose.ui.modifiers.width
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
    val isFeatured: Boolean = false
)

val appList = listOf(
    AppItem(
        name = "VocaMaster",
        emoji = "🧠",
        description = "Master Japanese vocabulary with JLPT-ready flashcards and spaced repetition algorithms.",
        subdomain = "vocamaster",
        playStoreUrl = "https://play.google.com/store/apps/details?id=com.optimumtech.vocamaster",
        appStoreUrl = "https://apps.apple.com/app/vocamaster/id123456789",
        screenshots = listOf(
            "https://images.unsplash.com/photo-1434030216411-0b793f4b4173?w=300&h=600&fit=crop",
            "https://images.unsplash.com/photo-1513475382585-d06e58bcb0e0?w=300&h=600&fit=crop",
            "https://images.unsplash.com/photo-1456513080510-7bf3a84b82f8?w=300&h=600&fit=crop"
        ),
        isFeatured = true
    ),
    AppItem(
        name = "KanjiCrafter",
        emoji = "🈶",
        description = "Learn Kanji through interactive drawing and advanced recognition technology.",
        subdomain = "kanjicrafter",
        playStoreUrl = "https://play.google.com/store/apps/details?id=com.optimumtech.kanjicrafter",
        screenshots = listOf(
            "https://images.unsplash.com/photo-1503676260728-1c00da094a0b?w=300&h=600&fit=crop",
            "https://images.unsplash.com/photo-1488590528505-98d2b5aba04b?w=300&h=600&fit=crop",
            "https://images.unsplash.com/photo-1484417894907-623942c8ee29?w=300&h=600&fit=crop"
        ),
        isFeatured = true
    ),
    AppItem(
        name = "GrammarHero",
        emoji = "📘",
        description = "Tackle essential Japanese grammar with interactive exercises and real-world examples.",
        subdomain = "grammarhero",
        appStoreUrl = "https://apps.apple.com/app/grammarhero/id987654321",
        screenshots = listOf(
            "https://images.unsplash.com/photo-1516321318423-f06f85e504b3?w=300&h=600&fit=crop",
            "https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?w=300&h=600&fit=crop",
            "https://images.unsplash.com/photo-1517077304055-6e89abbf09b0?w=300&h=600&fit=crop"
        )
    )
)

@Composable
fun AppCard(app: AppItem, isFeatured: Boolean = false) {
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
//                            .backgroundImage(CSSUrl(""))
//                            .background("url($screenshot)")
                            .backgroundSize(BackgroundSize.Cover)
                            .backgroundPosition(BackgroundPosition.of(CSSPosition.Center))
                            .borderRadius(1.cssRem)
                            .toAttrs {
                                style {
                                    opacity(if (index == 0) 1.0 else 0.0)
//                                    transition("opacity 0.5s ease-in-out")
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

@Page("/apps")
@Layout(".components.layouts.PageLayout")
@Composable
fun Apps() {
    val ctx = rememberPageContext()
    val featuredApps = appList.filter { it.isFeatured }
    val regularApps = appList.filter { !it.isFeatured }

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
                        //.backgroundImage("linear-gradient(135deg, #667eea 0%, #764ba2 100%)")
                        .color(transparent)
//                        .toAttrs {
//                            style {
//                                property("-webkit-background-clip", "text")
//                                property("background-clip", "text")
//                            }
//                        }
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
                            AppCard(app, isFeatured = true)
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
                            AppCard(app, isFeatured = false)
                        }
                    }
                }
            }
        }
    }
}