package org.optimum_tech.quranify.components.widgets

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.css.*
import com.varabyte.kobweb.compose.css.Transition
import com.varabyte.kobweb.compose.css.functions.LinearGradient
import com.varabyte.kobweb.compose.css.functions.linearGradient
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.selectors.hover
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import kotlinx.browser.window
import kotlinx.coroutines.await
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.css.AlignItems
import org.jetbrains.compose.web.css.JustifyContent
import org.jetbrains.compose.web.dom.Button
import org.optimum_tech.quranify.pages.MacOSStep
import org.optimum_tech.quranify.pages.Platform
import org.optimum_tech.quranify.pages.macOSSteps
import org.optimum_tech.quranify.pages.platforms
import org.optimum_tech.quranify.toSitePalette

// CSS Styles for hover effects
val PlatformCardStyle = CssStyle {
    base {
        Modifier
            .padding(2.5.cssRem)
            .borderRadius(1.5.cssRem)
            .transition(Transition.of("all", 0.3.s))
    }
    hover {
        Modifier
            .boxShadow(BoxShadow.of(offsetX = 0.px, offsetY = 10.px, blurRadius = 30.px, color = rgba(59, 130, 246, 0.15)))
            .transform { translateY((-5).px) }
    }
}

val CloseButtonStyle = CssStyle {
    base {
        Modifier
            .width(40.px)
            .height(40.px)
            .borderRadius(50.percent)
            .cursor(Cursor.Pointer)
            .fontSize(1.2.cssRem)
            .transition(Transition.of("all", 0.2.s))
    }
    hover {
        Modifier
            .transform { scale(1.1) }
    }
}

val StepCardStyle = CssStyle {
    base {
        Modifier
            .borderRadius(1.cssRem)
            .transition(Transition.of("all", 0.3.s))
            .cursor(Cursor.Pointer)
    }
    hover {
        Modifier
            .boxShadow(BoxShadow.of(offsetX = 0.px, offsetY = 8.px, blurRadius = 25.px, color = rgba(59, 130, 246, 0.1)))
            .transform { translateY((-2).px) }
    }
}

val DownloadButtonStyle = CssStyle {
    base {
        Modifier
            .padding(1.cssRem, 2.cssRem)
            .borderRadius(0.75.cssRem)
            .fontSize(1.cssRem)
            .fontWeight(FontWeight.SemiBold)
            .border(0.px)
            .cursor(Cursor.Pointer)
            .transition(Transition.of("all", 0.3.s))
            .margin(bottom = 1.5.cssRem)
            .boxShadow(BoxShadow.of(offsetX = 0.px, offsetY = 4.px, blurRadius = 15.px, color = rgba(59, 130, 246, 0.4)))
    }
    hover {
        Modifier
            .transform { translateY((-2).px) }
            .boxShadow(BoxShadow.of(offsetX = 0.px, offsetY = 8.px, blurRadius = 25.px, color = rgba(59, 130, 246, 0.6)))
    }
}

val PlayStoreImageStyle = CssStyle {
    base {
        Modifier
            .display(DisplayStyle.InlineBlock)
            .margin(bottom = 1.5.cssRem)
            .transition(Transition.of("transform", 0.3.s))
    }
    hover {
        Modifier.transform { scale(1.05) }
    }
}

@Composable
fun DownloadSection() {
    val palette = ColorMode.current.toSitePalette()
    var showMacOSGuide by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .id("download-section")
            .fillMaxWidth()
            .padding(top = 5.cssRem, bottom = 5.cssRem)
            .backgroundColor(palette.surface),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SpanText(
            "Choose Your Platform",
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
                .display(DisplayStyle.Block)
        )

        SpanText(
            "Available on all major platforms with seamless sync across devices",
            Modifier
                .fontSize(1.2.cssRem)
                .color(palette.textMuted)
                .textAlign(TextAlign.Center)
                .margin(bottom = 4.cssRem)
                .maxWidth(600.px)
                .display(DisplayStyle.Block)
        )

        RowColumnFlexAlternative(
            modifier = Modifier.padding { topBottom(6.px);leftRight(60.px) }.margin(6.px)
        ) {
            platforms.forEach { platform ->
                PlatformCard(platform) {
                    if (platform.name.lowercase().contains("mac")) {
                        showMacOSGuide = true
                    } else {
                        window.open(platform.downloadLink.toString(), "_blank")
                    }
                }
            }
        }

        // macOS Guide Dialog
        if (showMacOSGuide) {
            MacOSGuideDialog(
                onClose = { showMacOSGuide = false }
            )
        }
    }
}

@Composable
private fun MacOSGuideDialog(onClose: () -> Unit) {
    val palette = ColorMode.current.toSitePalette()

    ModalDialog(
        dialogModifier = Modifier
            .maxWidth(700.px)
            .maxHeight(80.vh),
        titleRow = {
            SpanText(
                "🍎",
                Modifier
                    .fontSize(2.cssRem)
                    .margin(right = 1.cssRem)
            )

            SpanText(
                "macOS Installation Guide",
                Modifier
                    .fontSize(2.2.cssRem)
                    .fontWeight(FontWeight.Bold)
                    .color(palette.text)
                    .flexGrow(1)
            )

            // Close button
            Button(
                attrs = CloseButtonStyle.toModifier()
                    .backgroundColor(palette.surface)
                    .color(palette.textMuted)
                    .border(1.px, LineStyle.Solid, palette.border)
                    .toAttrs {
                        onClick { onClose() }
                    }
            ) {
                SpanText("×")
            }
        },
        content = {
            // Subtitle
            SpanText(
                "Follow these steps to install Quranify on your Mac. Don't worry, it's easier than it looks!",
                Modifier
                    .fontSize(1.1.cssRem)
                    .color(palette.textMuted)
                    .margin(bottom = 1.5.cssRem)
                    .lineHeight(1.5)
                    .display(DisplayStyle.Block)
            )
            Button(
                attrs = DownloadButtonStyle.toModifier()
                    .backgroundColor(palette.primary)
                    .color(palette.textOnColor)
                    .toAttrs {
                        onClick {
                            window.open(platforms.find { it.name.lowercase().contains("mac") }?.downloadLink?:"#", "_blank")
                        }
                    }
            ) {
                SpanText("Download")
            }
            // Steps
            macOSSteps.forEachIndexed { index, step ->
                MacOSStepCard(step, index + 1)
            }
        },
        bottomRow = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .backgroundColor(palette.surface)
                    .borderRadius(1.cssRem)
                    .padding(1.5.cssRem)
                    .border(1.px, LineStyle.Solid, palette.border)
            ) {
                SpanText(
                    "💡 Need help? Contact our support team or check the FAQ section.",
                    Modifier
                        .fontSize(0.95.cssRem)
                        .color(palette.textMuted)
                        .textAlign(TextAlign.Center)
                        .display(DisplayStyle.Block)
                        .width(100.percent)
                )
            }
        }
    )
}

@Composable
private fun MacOSStepCard(step: MacOSStep, stepNumber: Int) {
    val palette = ColorMode.current.toSitePalette()
    var isExpanded by remember { mutableStateOf(false) }

    // Enhanced card design
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .backgroundColor(palette.surface)
            .borderRadius(1.2.cssRem)
            .border(
                2.px,
                LineStyle.Solid,
                if (step.warning) palette.warning else palette.border
            )
            .margin(bottom = 1.cssRem)
            .transition(Transition.of("all", 0.3.s))
            .cursor(Cursor.Pointer)
            .onClick { isExpanded = !isExpanded }
    ) {
        Column {
            // Main step header - always visible
            Row(
                modifier = Modifier
                    .padding(1.5.cssRem)
                    .alignItems(AlignItems.Center)
                    .gap(1.5.cssRem)
            ) {
                // Enhanced step number circle with icon
                Box(
                    modifier = Modifier
                        .width(60.px)
                        .height(60.px)
                        .borderRadius(50.percent)
                        .backgroundColor(
                            if (step.warning) palette.warning
                            else palette.primary
                        )
                        .display(DisplayStyle.Flex)
                        .alignItems(AlignItems.Center)
                        .justifyContent(JustifyContent.Center)
                        .flexShrink(0)
                        .boxShadow(
                            BoxShadow.of(
                                offsetX = 0.px,
                                offsetY = 4.px,
                                blurRadius = 12.px,
                                color = if (step.warning)
                                    rgba(251, 191, 36, 0.3)
                                else
                                    rgba(59, 130, 246, 0.3)
                            )
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    SpanText(
                        stepNumber.toString(),
                        Modifier
                            .fontSize(1.3.cssRem)
                            .fontWeight(FontWeight.Bold)
                            .color(palette.textOnColor)
                    )
                }

                Column(
                    modifier = Modifier
                        .flexGrow(1)
                        .gap(0.3.cssRem)
                ) {
                    // Step title with warning indicator
                    Row(
                        modifier = Modifier.alignItems(AlignItems.Center)
                    ) {
                        SpanText(
                            step.title,
                            Modifier
                                .fontSize(1.4.cssRem)
                                .fontWeight(FontWeight.SemiBold)
                                .color(palette.text)
                        )

                        if (step.warning) {
                            Box(
                                modifier = Modifier
                                    .margin(left = 1.cssRem)
                                    .padding(0.3.cssRem, 0.8.cssRem)
                                    .backgroundColor(rgba(251, 191, 36, 0.15))
                                    .borderRadius(1.cssRem)
                                    .border(1.px, LineStyle.Solid, palette.warning)
                            ) {
                                SpanText(
                                    "⚠️ Important",
                                    Modifier
                                        .fontSize(0.8.cssRem)
                                        .fontWeight(FontWeight.Medium)
                                        .color(palette.warning)
                                )
                            }
                        }
                    }

                    // Short preview of description
                    SpanText(
                        if (step.description.length > 80)
                            "${step.description.take(80)}..."
                        else
                            step.description,
                        Modifier
                            .fontSize(0.95.cssRem)
                            .color(palette.textMuted)
                            .lineHeight(1.4)
                    )
                }

                // Improved expand/collapse button
                Box(
                    modifier = Modifier
                        .width(36.px)
                        .height(36.px)
                        .borderRadius(50.percent)
                        .backgroundColor(palette.background)
                        .border(1.px, LineStyle.Solid, palette.border)
                        .display(DisplayStyle.Flex)
                        .alignItems(AlignItems.Center)
                        .justifyContent(JustifyContent.Center)
                        .transition(Transition.of("all", 0.3.s)),
                    contentAlignment = Alignment.Center
                ) {
                    SpanText(
                        if (isExpanded) "−" else "+",
                        Modifier
                            .fontSize(1.2.cssRem)
                            .fontWeight(FontWeight.Bold)
                            .color(palette.textMuted)
                            .transition(Transition.of("transform", 0.3.s))
                            .transform {
                                if (isExpanded) rotate(180.deg)
                                else rotate(0.deg)
                            }
                    )
                }
            }

            // Expanded content
            if (isExpanded) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .backgroundColor(palette.background)
                        .borderTop(1.px, LineStyle.Solid, palette.border)
                        .padding(1.5.cssRem)
                ) {
                    SpanText(
                        step.description,
                        Modifier
                            .fontSize(1.cssRem)
                            .color(palette.text)
                            .lineHeight(1.6)
                            .display(DisplayStyle.Block)
                    )
                }
            }
        }
    }
}

@Composable
private fun PlatformCard(platform: Platform, onDownload: () -> Unit) {
    val palette = ColorMode.current.toSitePalette()
    Column(
        modifier = PlatformCardStyle.toModifier()
            .margin(4.px)
            .backgroundColor(palette.background)
            .border(1.px, LineStyle.Solid, palette.border),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SpanText(
            platform.emoji,
            Modifier
                .fontSize(3.cssRem)
                .display(DisplayStyle.Block)
                .margin(bottom = 1.5.cssRem)
        )

        SpanText(
            platform.name,
            Modifier
                .fontSize(1.5.cssRem)
                .fontWeight(FontWeight.SemiBold)
                .margin(bottom = 0.75.cssRem)
                .color(palette.text)
                .display(DisplayStyle.Block)
        )

        Box(
            modifier = Modifier
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
        ) {
            SpanText(platform.status)
        }

        if (platform.downloadLink != null) {
            if (platform.name == "Android") {
                Link(
                    path = platform.downloadLink,
                    modifier = PlayStoreImageStyle.toModifier()
                ) {
                    Image(
                        src = "https://play.google.com/intl/en_us/badges/static/images/badges/en_badge_web_generic.png",
                        alt = "Get it on Google Play",
                        modifier = Modifier
                            .height(80.px)
                            .borderRadius(0.5.cssRem)
                    )
                }
            } else if (platform.name.lowercase().contains("mac")) {
                Button(
                    attrs = DownloadButtonStyle.toModifier()
                        .backgroundColor(palette.primary)
                        .color(palette.textOnColor)
                        .toAttrs {
                            onClick { onDownload() }
                        }
                ) {
                    SpanText("📥 ${platform.buttonText}")
                }
            } else {
                Button(
                    attrs = DownloadButtonStyle.toModifier()
                        .backgroundColor(palette.primary)
                        .color(palette.textOnColor)
                        .toAttrs {
                            onClick { onDownload() }
                        }
                ) {
                    SpanText(platform.buttonText)
                }
            }
        }
    }
}