package org.optimum_tech.quranify.pages

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.css.*
import com.varabyte.kobweb.compose.css.Transition
import com.varabyte.kobweb.compose.css.functions.*
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.data.add
import com.varabyte.kobweb.core.init.InitRoute
import com.varabyte.kobweb.core.init.InitRouteContext
import com.varabyte.kobweb.core.layout.Layout
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.selectors.hover
import com.varabyte.kobweb.silk.style.toAttrs
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import kotlinx.browser.window
import kotlinx.coroutines.delay
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.css.AlignItems
import org.jetbrains.compose.web.css.JustifyContent
import org.jetbrains.compose.web.dom.*
import org.optimum_tech.quranify.components.layouts.PageLayoutData
import org.optimum_tech.quranify.toSitePalette

fun Modifier.allTransition(
    duration: CSSTimeNumericValue? = null
) = this.transition(Transition.of("all", duration = duration))

data class Platform(
    val name: String,
    val emoji: String,
    val status: String,
    val downloadLink: String?,
    val description: String,
    val buttonText: String = "Download"
)

data class MacOSStep(
    val step: Int,
    val title: String,
    val description: String,
    val warning: Boolean = false
)

data class ScreenshotSlide(
    val title: String,
    val description: String,
    val imageUrl: String
)

data class Feature(
    val icon: String,
    val title: String,
    val description: String
)

val platforms = listOf(
    Platform(
        name = "Android",
        emoji = "📱",
        status = "Available",
        downloadLink = "https://play.google.com/store/apps/details?id=org.optimum_tech.quranic",
        description = "Download from Google Play Store",
        buttonText = "Get it on Play Store"
    ),
    Platform(
        name = "Windows",
        emoji = "🪟",
        status = "Available",
        downloadLink = "https://github.com/your-repo/quranify/releases/latest/download/quranify-windows.msi",
        description = "Download MSI installer"
    ),
    Platform(
        name = "macOS",
        emoji = "🍎",
        status = "Available",
        downloadLink = "https://github.com/your-repo/quranify/releases/latest/download/quranify-macos.dmg",
        description = "Download DMG installer"
    ),
    Platform(
        name = "Linux",
        emoji = "🐧",
        status = "Available",
        downloadLink = "https://github.com/your-repo/quranify/releases/latest/download/quranify-linux.deb",
        description = "Download DEB package"
    ),
    Platform(
        name = "iOS",
        emoji = "📱",
        status = "Coming Soon",
        downloadLink = null,
        description = "Available in the future"
    )
)

val macOSSteps = listOf(
    MacOSStep(
        step = 1,
        title = "Download the DMG file",
        description = "Click the macOS download button above to get the latest Quranify.dmg file."
    ),
    MacOSStep(
        step = 2,
        title = "Open the DMG file",
        description = "Double-click the downloaded DMG file to mount it. You'll see the Quranify app icon."
    ),
    MacOSStep(
        step = 3,
        title = "Drag to Applications",
        description = "Drag the Quranify app icon to your Applications folder to install it."
    ),
    MacOSStep(
        step = 4,
        title = "First Launch (Important!)",
        description = "When you first try to open Quranify, macOS will show a security warning because the app isn't signed with an Apple Developer certificate.",
        warning = true
    ),
    MacOSStep(
        step = 5,
        title = "Allow the App",
        description = "Go to System Preferences > Security & Privacy > General. You'll see a message about Quranify being blocked. Click 'Open Anyway'."
    ),
    MacOSStep(
        step = 6,
        title = "Confirm Opening",
        description = "Click 'Open' when macOS asks for confirmation. The app will now launch successfully."
    ),
    MacOSStep(
        step = 7,
        title = "Enjoy!",
        description = "Quranify is now installed and ready to use. Future launches won't require these security steps."
    )
)

val screenshotSlides = listOf(
    ScreenshotSlide(
        title = "Beautiful Flashcards",
        description = "Interactive Arabic vocabulary cards with pronunciation guides",
        imageUrl = "https://images.pexels.com/photos/31594410/pexels-photo-31594410.jpeg" // Arabic script, paper, traditional
    ),
    ScreenshotSlide(
        title = "Progress Tracking",
        description = "Track your learning journey with detailed analytics",
        imageUrl = "https://images.pexels.com/photos/6693652/pexels-photo-6693652.jpeg" // Charts & graphs on screen
    ),
    ScreenshotSlide(
        title = "Dark Mode Interface",
        description = "Easy on the eyes for extended study sessions",
        imageUrl = "https://images.pexels.com/photos/19168134/pexels-photo-19168134.jpeg" // Dark mode phone UI
    ),
    ScreenshotSlide(
        title = "Spaced Repetition",
        description = "Smart algorithm optimizes your review schedule",
        imageUrl = "https://images.pexels.com/photos/4144294/pexels-photo-4144294.jpeg" // Calendar and time elements
    ),
    ScreenshotSlide(
        title = "Verse Context",
        description = "Learn vocabulary with Quranic verse context",
        imageUrl = "https://images.pexels.com/photos/3747036/pexels-photo-3747036.jpeg" // Quran pages in Arabic
    )
)


val features = listOf(
    Feature(
        icon = "🧠",
        title = "Spaced Repetition System",
        description = "Advanced algorithm that shows you words exactly when you're about to forget them, maximizing retention efficiency."
    ),
    Feature(
        icon = "📖",
        title = "Quranic Context",
        description = "Every word is presented with its original Quranic verse, helping you understand usage and meaning in context."
    ),
    Feature(
        icon = "🔄",
        title = "Cross-Platform Sync",
        description = "Study on any device - your progress syncs seamlessly across Android, iOS, Windows, macOS, and Linux."
    ),
    Feature(
        icon = "🎯",
        title = "Progressive Learning",
        description = "Start with the most common words and gradually build your vocabulary with structured learning paths."
    ),
    Feature(
        icon = "📊",
        title = "Detailed Analytics",
        description = "Track your learning progress, see your strengths and areas for improvement with comprehensive statistics."
    ),
    Feature(
        icon = "🌙",
        title = "Beautiful Design",
        description = "Clean, distraction-free interface with dark mode support, designed specifically for Arabic text readability."
    )
)

val featureItemStyle = CssStyle {
    base {
        Modifier
            .backgroundColor(rgba(255, 255, 255, 0.05))
            .borderRadius(0.75.cssRem)
            .padding(1.5.cssRem)
            .margin(bottom = 1.cssRem)
            .border(1.px, LineStyle.Solid, rgba(255, 255, 255, 0.1))
            .allTransition()
    }
    hover {
        Modifier
            .backgroundColor(rgba(255, 255, 255, 0.08))
            .border(1.px, LineStyle.Solid, rgba(255, 255, 255, 0.2))
    }
}

@InitRoute
fun initHome(ctx: InitRouteContext) {
    ctx.data.add(PageLayoutData("Quranify - Understand Every Word of the Qur'an"))
}

@Page("/")
@Layout(".components.layouts.PageLayout")
@Composable
fun HomeNew() {
    val colorMode by ColorMode.currentState
    val palette = colorMode.toSitePalette()
    var selectedPlatform by remember { mutableStateOf<Platform?>(null) }
    var currentSlide by remember { mutableStateOf(0) }
    var showMacOSGuide by remember { mutableStateOf(false) }

    // Auto-advance carousel
    LaunchedEffect(Unit) {
        while (true) {
            delay(5000)
            currentSlide = (currentSlide + 1) % screenshotSlides.size
        }
    }

    Div(
        Modifier
            .backgroundColor(palette.background)
            .color(palette.text)
            .minHeight(100.vh)
            .toAttrs()
    ) {
        Div(
            Modifier
                .padding(top = 6.cssRem, bottom = 6.cssRem)
                .backgroundImage(
                    radialGradient(
                        palette.background,
                        palette.background.darkened(0.5f),
                        RadialGradient.Shape.Circle
                    )
                )
                .display(DisplayStyle.Flex)
                .flexDirection(FlexDirection.Row)
                .alignItems(AlignItems.Center)
                .justifyContent(JustifyContent.Center)
                .gap(4.cssRem)
                .color(palette.textOnColor)
                .toAttrs()
        ) {
            // Left Content - 50% width
            Div(
                Modifier
                    .width(50.percent)
                    .display(DisplayStyle.Flex)
                    .flexDirection(FlexDirection.Column)
                    .justifyContent(JustifyContent.Center)
                    .alignItems(AlignItems.Center)
                    .toAttrs()
            ) {
                Image(
                    src = "/quranify_logo.png",
                    modifier = Modifier.maxWidth(256.px).aspectRatio(1)
                )
                H1(
                    Modifier
                        .fontSize(4.5.cssRem)
                        .fontWeight(FontWeight.ExtraBold)
                        .margin(bottom = 1.cssRem)
                        .backgroundImage(
                            linearGradient(
                                palette.primary,
                                palette.secondary,
                                LinearGradient.Direction.ToRight
                            )
                        )
                        .backgroundClip(BackgroundClip.Text)
                        .color(Colors.Transparent)
                        .lineHeight(1.1)
                        .textAlign(TextAlign.Center)
                        .toAttrs()
                ) {
                    Text("Quranify")
                }

                H2(
                    Modifier
                        .fontSize(1.8.cssRem)
                        .color(palette.textOnColor)
                        .margin(bottom = 1.5.cssRem)
                        .lineHeight(1.4)
                        .fontWeight(FontWeight.SemiBold)
                        .textAlign(TextAlign.Center)
                        .toAttrs()
                ) {
                    Text("Master Quranic Arabic with Beautiful Flashcards")
                }

                P(
                    Modifier
                        .fontSize(1.1.cssRem)
                        .color(palette.textOnColor)
                        .margin(bottom = 2.5.cssRem)
                        .lineHeight(1.6)
                        .textAlign(TextAlign.Center)
                        .maxWidth(600.px)
                        .toAttrs()
                ) {
                    Text("Interactive vocabulary learning with spaced repetition, beautiful design, and cross-platform sync. Start your journey to understanding the Quran today.")
                }

                // CTA Buttons
                Div(
                    Modifier
                        .display(DisplayStyle.Flex)
                        .gap(1.cssRem)
                        .flexWrap(FlexWrap.Wrap)
                        .justifyContent(JustifyContent.Center)
                        .toAttrs()
                ) {
                    // Primary Download Button
                    Button(
                        attrs = Modifier
                            .padding(1.2.cssRem, 2.5.cssRem)
                            .backgroundColor(palette.primary)
                            .color(palette.textOnColor)
                            .borderRadius(0.75.cssRem)
                            .fontSize(1.1.cssRem)
                            .fontWeight(FontWeight.SemiBold)
                            .border(0.px)
                            .cursor(Cursor.Pointer)
                            .boxShadow(BoxShadow.of(0.px, 8.px, 25.px, color = rgba(59, 130, 246, 0.4)))
                            .transition(Transition.of("all", 0.3.s))
                            .toAttrs {
                                onClick {
                                    js("document.getElementById('download-section').scrollIntoView({behavior: 'smooth'})")
                                }
                            }
                    ) {
                        Text("📱 Download Now")
                    }

                    // Secondary Button
                    Button(
                        attrs = Modifier
                            .padding(1.2.cssRem, 2.5.cssRem)
                            .backgroundColor(palette.secondary)
                            .color(palette.textOnColor)
                            .border(2.px, LineStyle.Solid, palette.border)
                            .borderRadius(0.75.cssRem)
                            .fontSize(1.1.cssRem)
                            .fontWeight(FontWeight.SemiBold)
                            .cursor(Cursor.Pointer)
                            .transition(Transition.of("all", 0.3.s))
                            .toAttrs {
                                onClick {
                                    js("document.getElementById('features-section').scrollIntoView({behavior: 'smooth'})")
                                }
                            }
                    ) {
                        Text("✨ Learn More")
                    }
                }

                // Stats Section
                Div(
                    Modifier
                        .margin(top = 3.cssRem)
                        .display(DisplayStyle.Flex)
                        .gap(2.cssRem)
                        .flexWrap(FlexWrap.Wrap)
                        .justifyContent(JustifyContent.Center)
                        .toAttrs()
                ) {
                    Div(
                        Modifier
                            .textAlign(TextAlign.Center)
                            .toAttrs()
                    ) {
                        SpanText(
                            "5000+",
                            Modifier
                                .fontSize(1.5.cssRem)
                                .fontWeight(FontWeight.Bold)
                                .color(palette.success)
                                .display(DisplayStyle.Block)
                        )
                        SpanText(
                            "Arabic Words",
                            Modifier
                                .fontSize(0.85.cssRem)
                                .color(palette.textMuted)
                        )
                    }
                    Div(
                        Modifier
                            .textAlign(TextAlign.Center)
                            .toAttrs()
                    ) {
                        SpanText(
                            "10K+",
                            Modifier
                                .fontSize(1.5.cssRem)
                                .fontWeight(FontWeight.Bold)
                                .color(palette.primary)
                                .display(DisplayStyle.Block)
                        )
                        SpanText(
                            "Active Users",
                            Modifier
                                .fontSize(0.85.cssRem)
                                .color(palette.textMuted)
                        )
                    }
                    Div(
                        Modifier
                            .textAlign(TextAlign.Center)
                            .toAttrs()
                    ) {
                        SpanText(
                            "5 ⭐",
                            Modifier
                                .fontSize(1.5.cssRem)
                                .fontWeight(FontWeight.Bold)
                                .color(palette.warning)
                                .display(DisplayStyle.Block)
                        )
                        SpanText(
                            "User Rating",
                            Modifier
                                .fontSize(0.85.cssRem)
                                .color(palette.textMuted)
                        )
                    }
                }
            }

            // Right Content - 50% width
            Div(
                Modifier
                    .width(50.percent)
                    .display(DisplayStyle.Flex)
                    .flexDirection(FlexDirection.Column)
                    .justifyContent(JustifyContent.Center)
                    .alignItems(AlignItems.Center)
                    .toAttrs()
            ) {
                // Enhanced Animated Carousel with Overlapping Images
                Div(
                    Modifier
                        .position(Position.Relative)
                        .display(DisplayStyle.Flex)
                        .flexDirection(FlexDirection.Column)
                        .justifyContent(JustifyContent.Center)
                        .alignItems(AlignItems.Center)
                        .height(550.px)
                        .width(100.percent)
                        .maxWidth(500.px)
                        .toAttrs()
                ) {
                    // Main image container
                    Div(
                        Modifier
                            .position(Position.Absolute)
                            .top(0.px)
                            .left(50.percent)
                            .transform { translateX((-50).percent) }
                            .width(60.percent)
                            .maxWidth(320.px)
                            .minWidth(280.px)
                            .height(500.px)
                            .overflow(Overflow.Hidden)
                            .borderRadius(2.cssRem)
                            .boxShadow(BoxShadow.of(0.px, 25.px, 50.px, color = rgba(0, 0, 0, 0.4)))
                            .zIndex(3)
                            .toAttrs()
                    ) {
                        // Main slides
                        screenshotSlides.forEachIndexed { index, slide ->
                            Div(
                                Modifier
                                    .position(Position.Absolute)
                                    .width(100.percent)
                                    .height(100.percent)
                                    .backgroundImage(url(slide.imageUrl))
                                    .backgroundSize(BackgroundSize.Cover)
                                    .backgroundPosition(BackgroundPosition.of(CSSPosition.Center))
                                    .opacity(if (index == currentSlide) 1.0 else 0.0)
                                    .transition(Transition.of("opacity", 0.8.s))
                                    .toAttrs()
                            )
                        }

                        // Gradient overlay
                        Div(
                            Modifier
                                .position(Position.Absolute)
                                .bottom(0.px)
                                .left(0.px)
                                .right(0.px)
                                .height(160.px)
                                .backgroundImage(
                                    linearGradient(
                                        rgba(0, 0, 0, 0),
                                        rgba(0, 0, 0, 0.9),
                                        LinearGradient.Direction.ToBottom
                                    )
                                )
                                .toAttrs()
                        )

                        // Slide content
                        Div(
                            Modifier
                                .position(Position.Absolute)
                                .bottom(2.cssRem)
                                .left(1.5.cssRem)
                                .right(1.5.cssRem)
                                .toAttrs()
                        ) {
                            H3(
                                Modifier
                                    .fontSize(1.3.cssRem)
                                    .fontWeight(FontWeight.Bold)
                                    .margin(bottom = 0.5.cssRem)
                                    .color(Colors.White).toAttrs()
                            ) {
                                Text(screenshotSlides[currentSlide].title)
                            }

                            P(
                                Modifier
                                    .color(Color.rgb(203, 213, 225))
                                    .fontSize(0.9.cssRem)
                                    .lineHeight(1.4)
                                    .margin(0.px).toAttrs()
                            ) {
                                Text(screenshotSlides[currentSlide].description)
                            }
                        }
                    }

                    // Background image (previous slide)
                    Div(
                        Modifier
                            .position(Position.Absolute)
                            .top(40.px)
                            .left(10.percent)
                            .width(50.percent)
                            .maxWidth(250.px)
                            .minWidth(200.px)
                            .height(450.px)
                            .overflow(Overflow.Hidden)
                            .borderRadius(1.5.cssRem)
                            .opacity(0.3)
                            .zIndex(1)
                            .toAttrs()
                    ) {
                        val prevSlide = if (currentSlide == 0) screenshotSlides.size - 1 else currentSlide - 1
                        Div(
                            Modifier
                                .width(100.percent)
                                .height(100.percent)
                                .backgroundImage(url(screenshotSlides[prevSlide].imageUrl))
                                .backgroundSize(BackgroundSize.Cover)
                                .backgroundPosition(BackgroundPosition.of(CSSPosition.Center))
                                .filter(Filter.of(blur(2.px)))
                                .toAttrs()
                        )
                    }

                    // Next slide preview
                    Div(
                        Modifier
                            .position(Position.Absolute)
                            .top(80.px)
                            .right(10.percent)
                            .width(40.percent)
                            .maxWidth(180.px)
                            .minWidth(150.px)
                            .height(300.px)
                            .overflow(Overflow.Hidden)
                            .borderRadius(1.cssRem)
                            .opacity(0.2)
                            .zIndex(2)
                            .toAttrs()
                    ) {
                        val nextSlide = (currentSlide + 1) % screenshotSlides.size
                        Div(
                            Modifier
                                .width(100.percent)
                                .height(100.percent)
                                .backgroundImage(url(screenshotSlides[nextSlide].imageUrl))
                                .backgroundSize(BackgroundSize.Cover)
                                .backgroundPosition(BackgroundPosition.of(CSSPosition.Center))
                                .filter(Filter.of(blur(2.px)))
                                .toAttrs()
                        )
                    }

                    // Dots indicator
                    Div(
                        Modifier
                            .position(Position.Absolute)
                            .bottom((-30).px)
                            .left(50.percent)
                            .transform { translateX((-50).percent) }
                            .display(DisplayStyle.Flex)
                            .gap(0.75.cssRem)
                            .padding(1.cssRem)
                            .backgroundColor(palette.surface)
                            .borderRadius(2.cssRem)
                            .backdropFilter(BackdropFilter.of(blur(10.px)))
                            .toAttrs()
                    ) {
                        screenshotSlides.forEachIndexed { index, _ ->
                            Button(
                                attrs = Modifier
                                    .width(12.px)
                                    .height(12.px)
                                    .borderRadius(50.percent)
                                    .backgroundColor(
                                        if (index == currentSlide) palette.primary
                                        else palette.textMuted
                                    )
                                    .border(0.px)
                                    .transition(Transition.of("all", 0.3.s))
                                    .cursor(Cursor.Pointer)
                                    .toAttrs {
                                        onClick { currentSlide = index }
                                    }
                            )
                        }
                    }
                }
            }
        }
    }
    // Download Section
    Div(
        attrs = Modifier
            .padding(top = 5.cssRem, bottom = 5.cssRem)
            .backgroundColor(palette.surface)
            .toAttrs {
                id("download-section")
            }
    ) {
        Div(
            Modifier
                .maxWidth(1280.px)
                .margin(leftRight = autoLength)
                .padding(2.cssRem)
                .toAttrs()
        ) {
            H2(
                Modifier
                    .fontSize(2.8.cssRem)
                    .fontWeight(FontWeight.Bold)
                    .textAlign(TextAlign.Center)
                    .margin(bottom = 1.cssRem)
                    .backgroundImage(
                        linearGradient(
                            palette.primary,
                            palette.secondary,
                            LinearGradient.Direction.ToRight
                        )
                    )
                    .backgroundClip(BackgroundClip.Text)
                    .color(Colors.Transparent)
                    .toAttrs()
            ) {
                Text("Choose Your Platform")
            }

            P(
                Modifier
                    .fontSize(1.2.cssRem)
                    .color(palette.textMuted)
                    .textAlign(TextAlign.Center)
                    .margin(bottom = 4.cssRem)
                    .maxWidth(600.px)
                    .margin(leftRight = autoLength)
                    .margin(bottom = 4.cssRem)
                    .toAttrs()
            ) {
                Text("Available on all major platforms with seamless sync across devices")
            }

            Div(
                Modifier
                    .display(DisplayStyle.Grid)
                    .gap(2.cssRem)
                    .gridTemplateColumns {
                        repeat(
                            type = GridEntry.Repeat.Auto.Type.AutoFit
                        ) {
                            minmax(
                                300.px, 1.fr
                            )
                        }
                    }
                    .toAttrs()
            ) {
                platforms.forEach { platform ->
                    Div(
                        Modifier
                            .padding(2.5.cssRem)
                            .backgroundColor(palette.background)
                            .border(1.px, LineStyle.Solid, palette.border)
                            .borderRadius(1.5.cssRem)
                            .textAlign(TextAlign.Center)
                            .transition(Transition.of("all", 0.3.s))
                            .position(Position.Relative)
                            .overflow(Overflow.Hidden)
                            .toAttrs()
                    ) {
                        SpanText(
                            platform.emoji,
                            Modifier
                                .fontSize(3.cssRem)
                                .display(DisplayStyle.Block)
                                .margin(bottom = 1.5.cssRem)
                        )

                        H3(
                            Modifier
                                .fontSize(1.5.cssRem)
                                .fontWeight(FontWeight.SemiBold)
                                .margin(bottom = 0.75.cssRem)
                                .color(palette.text)
                                .toAttrs()
                        ) {
                            Text(platform.name)
                        }

                        Div(
                            Modifier
                                .fontSize(0.9.cssRem)
                                .color(
                                    if (platform.status == "Available") palette.success
                                    else palette.warning
                                )
                                .fontWeight(FontWeight.Medium)
                                .margin(bottom = 2.cssRem)
                                .padding(0.5.cssRem, 1.cssRem)
                                .backgroundColor(
                                    if (platform.status == "Available") rgba(34, 197, 94, 0.15)
                                    else rgba(251, 191, 36, 0.15)
                                )
                                .borderRadius(1.5.cssRem)
                                .display(DisplayStyle.InlineBlock)
                                .border(
                                    1.px, LineStyle.Solid,
                                    if (platform.status == "Available") palette.success
                                    else palette.warning
                                )
                                .toAttrs()
                        ) {
                            SpanText(platform.status)
                        }

                        if (platform.downloadLink != null) {
                            if (platform.name == "Android") {
                                // Play Store Button
                                A(
                                    href = platform.downloadLink,
                                    attrs = Modifier
                                        .display(DisplayStyle.InlineBlock)
                                        .margin(bottom = 1.5.cssRem)
                                        .transition(Transition.of("transform", 0.3.s))
                                        .toAttrs()
                                ) {
                                    Img(
                                        src = "https://play.google.com/intl/en_us/badges/static/images/badges/en_badge_web_generic.png",
                                        alt = "Get it on Google Play",
                                        attrs = Modifier
                                            .height(80.px)
                                            .borderRadius(0.5.cssRem)
                                            .toAttrs()
                                    )
                                }
                            } else {
                                Button(
                                    attrs = Modifier
                                        .padding(1.cssRem, 2.cssRem)
                                        .backgroundColor(palette.primary)
                                        .color(palette.textOnColor)
                                        .borderRadius(0.75.cssRem)
                                        .fontSize(1.cssRem)
                                        .fontWeight(FontWeight.SemiBold)
                                        .border(0.px)
                                        .cursor(Cursor.Pointer)
                                        .transition(Transition.of("all", 0.3.s))
                                        .margin(bottom = 1.5.cssRem)
                                        .boxShadow(BoxShadow.of(0.px, 4.px, 15.px, color = rgba(59, 130, 246, 0.4)))
                                        .toAttrs {
                                            onClick {
                                                if (platform.name.lowercase().contains("mac")) {
                                                    showMacOSGuide = true
                                                } else {
                                                    window.open(platform.downloadLink, "_blank")
                                                }
                                            }
                                        }
                                ) {
                                    Text(platform.buttonText)
                                }
                            }
                        }
                    }
                }
            }

            if (showMacOSGuide) {
                Div(
                    Modifier
                        .padding(top = 4.cssRem, bottom = 4.cssRem)
                        .backgroundColor(palette.surface)
                        .toAttrs()
                ) {
                    Div(
                        Modifier
                            .maxWidth(800.px)
                            .margin(leftRight = autoLength)
                            .padding(2.cssRem)
                            .toAttrs()
                    ) {
                        H2(
                            Modifier
                                .fontSize(2.2.cssRem)
                                .fontWeight(FontWeight.Bold)
                                .textAlign(TextAlign.Center)
                                .margin(bottom = 2.cssRem)
                                .color(palette.text)
                                .toAttrs()
                        ) {
                            Text("How to Install on macOS")
                        }

                        macOSSteps.forEach { step ->
                            Div(
                                Modifier
                                    .margin(bottom = 2.cssRem)
                                    .padding(1.cssRem)
                                    .backgroundColor(
                                        if (step.warning) rgba(251, 191, 36, 0.1)
                                        else palette.background
                                    )
                                    .borderRadius(1.cssRem)
                                    .border(1.px, LineStyle.Solid, palette.border)
                                    .toAttrs()
                            ) {
                                H4(
                                    Modifier
                                        .fontSize(1.2.cssRem)
                                        .fontWeight(FontWeight.SemiBold)
                                        .color(
                                            if (step.warning) palette.warning
                                            else palette.text
                                        )
                                        .margin(bottom = 0.5.cssRem)
                                        .toAttrs()
                                ) {
                                    Text("Step ${step.step}: ${step.title}")
                                }

                                P(
                                    Modifier
                                        .color(palette.textMuted)
                                        .fontSize(1.cssRem)
                                        .lineHeight(1.5)
                                        .toAttrs()
                                ) {
                                    Text(step.description)
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    //features section
    Div(
        Modifier
            .id("features-section")
            .padding(top = 6.cssRem, bottom = 6.cssRem)
            .backgroundColor(rgba(255, 255, 255, 0.02))
            .toAttrs()
    ) {
        Div(
            Modifier
                .maxWidth(1000.px)
                .margin(leftRight = autoLength)
                .padding(2.cssRem)
                .toAttrs()
        ) {
            H2(
                Modifier
                    .fontSize(2.5.cssRem)
                    .fontWeight(FontWeight.Bold)
                    .textAlign(TextAlign.Center)
                    .margin(bottom = 1.cssRem)
                    .backgroundImage(
                        linearGradient(
                            Color.rgb(59, 130, 246),
                            Color.rgb(147, 51, 234),
                            LinearGradient.Direction.ToRight
                        )
                    )
                    .backgroundClip(BackgroundClip.Text)
                    .color(Colors.Transparent)
                    .toAttrs()
            ) {
                Text("Why Choose Quranify?")
            }

            Div(
                Modifier
                    .display(DisplayStyle.Grid)
                    .gap(2.cssRem)
                    .gridTemplateColumns {
                        repeat(GridEntry.Repeat.Auto.Type.AutoFit) {
                            minmax(300.px, 1.fr)
                        }
                    }
                    .margin(top = 3.cssRem)
                    .toAttrs()
            ) {
                features.forEach { feature ->
                    Div(featureItemStyle.toAttrs()) {
                        SpanText(
                            feature.icon,
                            Modifier
                                .fontSize(2.cssRem)
                                .margin(bottom = 0.75.cssRem)
                                .display(DisplayStyle.Block)
                        )

                        H3(
                            Modifier
                                .fontSize(1.3.cssRem)
                                .fontWeight(FontWeight.Bold)
                                .margin(bottom = 0.5.cssRem)
                                .color(Colors.White)
                                .toAttrs()
                        ) {
                            Text(feature.title)
                        }

                        P(
                            Modifier
                                .fontSize(0.95.cssRem)
                                .color(Color.rgb(203, 213, 225))
                                .lineHeight(1.5)
                                .toAttrs()
                        ) {
                            Text(feature.description)
                        }
                    }
                }
            }
        }
    }
}
