package org.optimum_tech.quranify.pages

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.css.*
import com.varabyte.kobweb.compose.css.Transition
import com.varabyte.kobweb.compose.css.functions.LinearGradient
import com.varabyte.kobweb.compose.css.functions.RadialGradient
import com.varabyte.kobweb.compose.css.functions.linearGradient
import com.varabyte.kobweb.compose.css.functions.radialGradient
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.ColumnScope
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
import com.varabyte.kobweb.framework.annotations.DelicateApi
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.style.selectors.hover
import com.varabyte.kobweb.silk.style.toAttrs
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.theme.breakpoint.rememberBreakpoint
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import kotlinx.browser.window
import kotlinx.coroutines.delay
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.css.JustifyContent
import org.jetbrains.compose.web.dom.*
import org.optimum_tech.quranify.components.layouts.PageLayoutData
import org.optimum_tech.quranify.components.widgets.DownloadSection
import org.optimum_tech.quranify.components.widgets.RowColumnFlex
import org.optimum_tech.quranify.toSitePalette

// region Styles and Data
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
        downloadLink = "https://github.com/tamim05/QuranVocabulary/releases/download/v1.1.0/quran-vocabulary-1.1.0.msi",
        description = "Download MSI installer"
    ),
    Platform(
        name = "macOS",
        emoji = "🍎",
        status = "Available",
        downloadLink = "https://github.com/tamim05/QuranVocabulary/releases/download/v1.1.0/quran-vocabulary-1.1.0.dmg",
        description = "Download DMG installer"
    ),
    Platform(
        name = "Linux",
        emoji = "🐧",
        status = "Available",
        downloadLink = "https://github.com/tamim05/QuranVocabulary/releases/download/v1.1.0/quran-vocabulary-1.1.0.deb",
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
        imageUrl = "/screenshot/screenshot_1.jpg"
    ),
    ScreenshotSlide(
        title = "Progress Tracking",
        description = "Track your learning journey with detailed analytics",
        imageUrl = "/screenshot/screenshot_2.jpg"
    ),
    ScreenshotSlide(
        title = "Dark Mode Interface",
        description = "Easy on the eyes for extended study sessions",
        imageUrl = "/screenshot/screenshot_3.jpg"
    ),
    ScreenshotSlide(
        title = "Spaced Repetition",
        description = "Smart algorithm optimizes your review schedule",
        imageUrl = "/screenshot/screenshot_4.jpg"
    ),
    ScreenshotSlide(
        title = "Verse Context",
        description = "Learn vocabulary with Quranic verse context",
        imageUrl = "/screenshot/screenshot_5.jpg"
    ),
    ScreenshotSlide(
        title = "Beautiful Flashcards",
        description = "Interactive Arabic vocabulary cards with pronunciation guides",
        imageUrl = "/screenshot/screenshot_6.jpg"
    ),
    ScreenshotSlide(
        title = "Progress Tracking",
        description = "Track your learning journey with detailed analytics",
        imageUrl = "/screenshot/screenshot_7.jpg"
    ),
    ScreenshotSlide(
        title = "Dark Mode Interface",
        description = "Easy on the eyes for extended study sessions",
        imageUrl = "/screenshot/screenshot_8.jpg"
    ),
    ScreenshotSlide(
        title = "Spaced Repetition",
        description = "Smart algorithm optimizes your review schedule",
        imageUrl = "/screenshot/screenshot_9.jpg"
    ),
    ScreenshotSlide(
        title = "Verse Context",
        description = "Learn vocabulary with Quranic verse context",
        imageUrl = "/screenshot/screenshot_10.jpg"
    ),
    ScreenshotSlide(
        title = "Beautiful Flashcards",
        description = "Interactive Arabic vocabulary cards with pronunciation guides",
        imageUrl = "/screenshot/screenshot_11.jpg"
    ),
    ScreenshotSlide(
        title = "Progress Tracking",
        description = "Track your learning journey with detailed analytics",
        imageUrl = "/screenshot/screenshot_12.jpg"
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
            .backgroundColor(colorMode.toSitePalette().surface)
            .borderRadius(0.75.cssRem)
            .padding(1.5.cssRem)
            .margin(bottom = 1.cssRem)
            .border(1.px, LineStyle.Solid, colorMode.toSitePalette().border)
            .allTransition()
    }
    hover {
        Modifier
            .backgroundColor(colorMode.toSitePalette().surface.darkened(0.3f))
            .border(1.px, LineStyle.Solid, colorMode.toSitePalette().border)
    }
}

val mainLayoutStyle = CssStyle {
    base {
        Modifier
            .fillMaxWidth()
            .height(100.vh)
            .padding(top = 6.cssRem, bottom = 6.cssRem)
            .display(DisplayStyle.Flex)
            .flexDirection(FlexDirection.Column)
            .gap(4.cssRem)
            .justifyContent(JustifyContent.Center)
    }
}
// endregion

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

    Column(
        modifier = Modifier
            .backgroundColor(palette.background)
            .color(palette.text),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeroSection()
        DownloadSection()
        FeaturesSection()
    }
}

@OptIn(DelicateApi::class)
@Composable
private fun HeroSection() {
    val palette = ColorMode.current.toSitePalette()
    Box(
        modifier = mainLayoutStyle.toModifier()
            .backgroundImage(
                radialGradient(
                    palette.background,
                    palette.background.darkened(0.5f),
                    RadialGradient.Shape.Circle
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        RowColumnFlex(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.width(50.percent).padding(2.cssRem),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                HeroText()
                CtaButtons()
                Stats()
            }
            ScreenshotCarousel(screenshotSlides)
        }
    }
}

@Composable
private fun HeroText() {
    val palette = ColorMode.current.toSitePalette()
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
}

@Composable
private fun CtaButtons() {
    val palette = ColorMode.current.toSitePalette()
    Row(modifier = Modifier.gap(1.cssRem).flexWrap(FlexWrap.Wrap).justifyContent(JustifyContent.Center)) {
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
}

@Composable
private fun Stats() {
    val palette = ColorMode.current.toSitePalette()
    Row(
        modifier = Modifier
            .margin(top = 3.cssRem)
            .gap(2.cssRem)
            .flexWrap(FlexWrap.Wrap)
            .justifyContent(JustifyContent.Center)
    ) {
        Stat("5000+", "Arabic Words", palette.success)
        Stat("10K+", "Active Users", palette.primary)
        Stat("5 ⭐", "User Rating", palette.warning)
    }
}

@Composable
private fun Stat(value: String, label: String, color: CSSColorValue) {
    val palette = ColorMode.current.toSitePalette()
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        SpanText(
            value,
            Modifier
                .fontSize(1.5.cssRem)
                .fontWeight(FontWeight.Bold)
                .color(color)
                .display(DisplayStyle.Block)
        )
        SpanText(
            label,
            Modifier
                .fontSize(0.85.cssRem)
                .color(palette.textMuted)
        )
    }
}

@Composable
private fun ScreenshotCarousel(slides: List<ScreenshotSlide>) {
    var currentSlide by remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(5000)
            currentSlide = (currentSlide + 1) % slides.size
        }
    }

    Box(
        modifier = Modifier
            .position(Position.Relative)
            .width(320.px)
            .height(500.px)
            .borderRadius(2.cssRem)
            .overflow(Overflow.Hidden)
            .boxShadow(BoxShadow.of(0.px, 25.px, 50.px, color = rgba(0, 0, 0, 0.4))),
        contentAlignment = Alignment.BottomCenter
    ) {
        slides.forEachIndexed { index, slide ->
            Image(
                src = slide.imageUrl,
                modifier = Modifier
                    .fillMaxSize()
                    .opacity(if (index == currentSlide) 1.0 else 0.0)
                    .transition(Transition.of("opacity", 0.8.s))
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.px)
                .backgroundImage(
                    linearGradient(
                        rgba(0, 0, 0, 0),
                        rgba(0, 0, 0, 0.9),
                        LinearGradient.Direction.ToBottom
                    )
                )
        )

        Column(
            modifier = Modifier.fillMaxWidth().padding(2.cssRem)
        ) {
            H3(
                Modifier
                    .fontSize(1.3.cssRem)
                    .fontWeight(FontWeight.Bold)
                    .margin(bottom = 0.5.cssRem)
                    .color(Colors.White)
                    .toAttrs()
            ) {
                Text(slides[currentSlide].title)
            }

            P(
                Modifier
                    .color(Color.rgb(203, 213, 225))
                    .fontSize(0.9.cssRem)
                    .lineHeight(1.4)
                    .margin(0.px)
                    .toAttrs()
            ) {
                Text(slides[currentSlide].description)
            }
        }

        Row(
            modifier = Modifier.padding(bottom = 1.cssRem).gap(0.75.cssRem)
        ) {
            slides.forEachIndexed { index, _ ->
                Button(
                    attrs = Modifier
                        .width(12.px)
                        .height(12.px)
                        .borderRadius(50.percent)
                        .backgroundColor(
                            if (index == currentSlide) Colors.White
                            else rgba(255, 255, 255, 0.5)
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


@Composable
private fun FeaturesSection() {
    val palette = ColorMode.current.toSitePalette()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 6.cssRem, bottom = 6.cssRem)
            .backgroundColor(palette.background),
        horizontalAlignment = Alignment.CenterHorizontally
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
                FeatureItem(feature)
            }
        }
    }
}

@Composable
private fun FeatureItem(feature: Feature) {
    val pallet = ColorMode.current
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
                .color(pallet.toSitePalette().text)
                .toAttrs()
        ) {
            Text(feature.title)
        }

        P(
            Modifier
                .fontSize(0.95.cssRem)
                .color(color = pallet.toSitePalette().text)
                .lineHeight(1.5)
                .toAttrs()
        ) {
            Text(feature.description)
        }
    }
}