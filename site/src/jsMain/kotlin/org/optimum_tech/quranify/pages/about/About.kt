package org.optimum_tech.quranify.pages.about

import androidx.compose.runtime.*
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.layout.Layout
import com.varabyte.kobweb.compose.css.*
import com.varabyte.kobweb.compose.css.functions.LinearGradient
import com.varabyte.kobweb.compose.css.functions.RadialGradient
import com.varabyte.kobweb.compose.css.functions.blur
import com.varabyte.kobweb.compose.css.functions.dropShadow
import com.varabyte.kobweb.compose.css.functions.linearGradient
import com.varabyte.kobweb.compose.css.functions.radialGradient
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.graphics.lightened
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.data.add
import com.varabyte.kobweb.core.init.InitRoute
import com.varabyte.kobweb.core.init.InitRouteContext
import com.varabyte.kobweb.framework.annotations.DelicateApi
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.animation.Keyframes
import com.varabyte.kobweb.silk.style.animation.toAnimation
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.style.selectors.hover
import com.varabyte.kobweb.silk.style.toAttrs
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.theme.breakpoint.rememberBreakpoint
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import kotlinx.coroutines.delay
import org.optimum_tech.quranify.components.layouts.PageLayoutData
import org.optimum_tech.quranify.toSitePalette
import org.jetbrains.compose.web.css.AlignItems
import org.jetbrains.compose.web.css.JustifyContent
import org.optimum_tech.quranify.SitePalette
import org.optimum_tech.quranify.copy

// Animation keyframes
val floatAnimation = Keyframes {
    from { Modifier.transform { translateY(0.px) } }
    to { Modifier.transform { translateY((-20).px) } }
}

val pulseAnimation = Keyframes {
    from { Modifier.transform { scale(1) } }
    to { Modifier.transform { scale(1.05) } }
}

// Style variables
val animationDelay by StyleVariable<CSSTimeNumericValue>()
val glowColor by StyleVariable<CSSColorValue>()

// Styles
val heroContainerStyle = CssStyle {
    base {
        Modifier
            .fillMaxWidth()
            .minHeight(70.vh)
            .display(DisplayStyle.Flex)
            .alignItems(AlignItems.Center)
            .justifyContent(JustifyContent.Center)
            .position(Position.Relative)
            .overflow(Overflow.Hidden)
    }
}

val sectionCardStyle = CssStyle {
    base {
        Modifier
            .backgroundColor(colorMode.toSitePalette().surface.copy(alpha = 0.8f))
            .backdropFilter(blur(20.px))
            .border(1.px, LineStyle.Solid, colorMode.toSitePalette().border)
            .borderRadius(1.5.cssRem)
            .padding(2.5.cssRem)
            .margin(bottom = 2.cssRem)
            .transition(Transition.of("all", 0.4.s, TransitionTimingFunction.EaseOut))
            .position(Position.Relative)
            .boxShadow(BoxShadow.of(0.px, 8.px, 32.px, color = rgba(0, 0, 0, 0.1)))
    }
    hover {
        Modifier
            .backgroundColor(colorMode.toSitePalette().surface)
            .border(1.px, LineStyle.Solid, colorMode.toSitePalette().primary.copy(alpha = 0.5f))
            .transform { translateY((-12).px) }
            .boxShadow(
                BoxShadow.of(0.px, 25.px, 50.px, color = rgba(0, 0, 0, 0.2)),
                BoxShadow.of(0.px, 0.px, 40.px, color = glowColor.value())
            )
    }
}

val floatingElementStyle = CssStyle {
    base {
        Modifier
            .position(Position.Absolute)
            .borderRadius(50.percent)
            .backgroundImage(
                radialGradient(
                    colorMode.toSitePalette().primary.copy(alpha = 0.3f),
                    colorMode.toSitePalette().secondary.copy(alpha = 0.1f),
                    RadialGradient.Shape.Circle
                )
            )
            .filter(blur(60.px))
            .animation(
                floatAnimation.toAnimation(
                    duration = animationDelay.value(8.s),
                    iterationCount = AnimationIterationCount.Infinite,
                    direction = AnimationDirection.Alternate,
                    timingFunction = AnimationTimingFunction.EaseInOut
                )
            )
    }
}

val statCardStyle = CssStyle {
    base {
        Modifier
            .backgroundColor(colorMode.toSitePalette().surface.copy(alpha = 0.6f))
            .backdropFilter(blur(15.px))
            .border(1.px, LineStyle.Solid, colorMode.toSitePalette().border)
            .borderRadius(1.2.cssRem)
            .padding(1.8.cssRem)
            .minWidth(140.px)
            .transition(Transition.of("all", 0.3.s, TransitionTimingFunction.EaseOut))
            .boxShadow(BoxShadow.of(0.px, 4.px, 20.px, color = rgba(0, 0, 0, 0.1)))
    }
    hover {
        Modifier
            .backgroundColor(colorMode.toSitePalette().surface)
            .transform {
                translateY((-6).px)
                scale(1.02)
            }
            .boxShadow(
                BoxShadow.of(0.px, 15.px, 35.px, color = rgba(0, 0, 0, 0.15)),
                BoxShadow.of(0.px, 0.px, 30.px, color = glowColor.value())
            )
    }
}

val contactButtonStyle = CssStyle {
    base {
        Modifier
            .backgroundColor(colorMode.toSitePalette().surface.copy(alpha = 0.7f))
            .backdropFilter(blur(10.px))
            .border(1.px, LineStyle.Solid, colorMode.toSitePalette().border)
            .borderRadius(0.75.cssRem)
            .padding(1.cssRem, 2.cssRem)
            .color(colorMode.toSitePalette().text)
            .textDecorationLine(TextDecorationLine.None)
            .transition(Transition.of("all", 0.3.s))
            .cursor(Cursor.Pointer)
            .fontWeight(FontWeight.SemiBold)
    }
    hover {
        Modifier
            .backgroundColor(colorMode.toSitePalette().primary)
            .border(1.px, LineStyle.Solid, colorMode.toSitePalette().primary)
            .color(colorMode.toSitePalette().textOnColor)
            .transform { translateY((-3).px); scale(1.05) }
            .boxShadow(BoxShadow.of(0.px, 12.px, 25.px, color = colorMode.toSitePalette().primary.copy(alpha = 0.4f)))
    }
}

@InitRoute
fun initAboutPage(ctx: InitRouteContext) {
    ctx.data.add(PageLayoutData("About Us - Optimum Tech"))
}

@OptIn(DelicateApi::class)
@Page("/about")
@Layout(".components.layouts.PageLayout")
@Composable
fun About() {
    val palette = ColorMode.current.toSitePalette()
    val breakpoint = rememberBreakpoint()
    val isMobile = breakpoint <= Breakpoint.MD

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .minHeight(100.vh)
            .backgroundImage(
                linearGradient(
                    palette.background.copy(alpha = 0.5f),
                    palette.surface.copy(alpha = 0.5f),
                    LinearGradient.Direction.ToBottom
                )
            )
            .color(palette.text)
            .position(Position.Relative)
    ) {
        // Enhanced floating background elements
        Box(
            modifier = floatingElementStyle.toModifier()
                .top(15.percent)
                .left(10.percent)
                .width(300.px)
                .height(300.px)
                .setVariable(animationDelay, 8.s)
        )
        Box(
            modifier = floatingElementStyle.toModifier()
                .top(50.percent)
                .right(15.percent)
                .width(200.px)
                .height(200.px)
                .setVariable(animationDelay, 6.s)
        )
        Box(
            modifier = floatingElementStyle.toModifier()
                .bottom(25.percent)
                .left(20.percent)
                .width(150.px)
                .height(150.px)
                .setVariable(animationDelay, 10.s)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.cssRem, bottom = 4.cssRem),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HeroSection(isMobile, palette)
            ContentSection(isMobile, palette)
        }
    }
}

@Composable
private fun HeroSection(isMobile: Boolean, palette: SitePalette) {
    var show by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(300)
        show = true
    }

    Box(
        modifier = heroContainerStyle.toModifier()
            .padding(leftRight = 2.cssRem),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Animated emoji with pulse effect
            SpanText(
                "👨‍💻",
                Modifier
                    .fontSize(if (isMobile) 4.cssRem else 6.cssRem)
                    .margin(bottom = 1.5.cssRem)
                    .transition(Transition.of("all", 1.2.s, TransitionTimingFunction.EaseOut))
                    .opacity(if (show) 1.0 else 0.0)
                    .transform {
                        if (show) scale(1) else scale(0.3)
                        if (show) rotateZ(0.deg) else rotateZ(270.deg)
                    }
                    .animation(
                        pulseAnimation.toAnimation(
                            duration = 3.s,
                            iterationCount = AnimationIterationCount.Infinite,
                            direction = AnimationDirection.Alternate,
                            timingFunction = AnimationTimingFunction.EaseInOut
                        )
                    )
            )

            // Main title with enhanced gradient
            H1(
                Modifier
                    .fontSize(if (isMobile) 3.cssRem else 4.5.cssRem)
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
                    .textAlign(TextAlign.Center)
                    .transition(Transition.of("all", 1.4.s, TransitionTimingFunction.EaseOut))
                    .opacity(if (show) 1.0 else 0.0)
                    .transform { translateY(if (show) 0.px else 60.px) }
                    .toAttrs()
            ) {
                Text("About Optimum-Tech")
            }

            // Enhanced subtitle
            P(
                Modifier
                    .fontSize(if (isMobile) 1.1.cssRem else 1.4.cssRem)
                    .color(palette.textMuted)
                    .textAlign(TextAlign.Center)
                    .maxWidth(if (isMobile) 100.percent else 700.px)
                    .lineHeight(1.6)
                    .margin(bottom = 3.cssRem)
                    .transition(Transition.of("all", 1.6.s, TransitionTimingFunction.EaseOut))
                    .opacity(if (show) 1.0 else 0.0)
                    .transform { translateY(if (show) 0.px else 40.px) }
                    .toAttrs()
            ) {
                Text("Building ethical, powerful software that respects users and creates meaningful impact. One developer, unlimited possibilities.")
            }

            // Enhanced animated stats
            Row(
                modifier = Modifier
                    .gap(if (isMobile) 1.5.cssRem else 3.cssRem)
                    .flexWrap(FlexWrap.Wrap)
                    .justifyContent(JustifyContent.Center)
                    .transition(Transition.of("all", 1.8.s, TransitionTimingFunction.EaseOut))
                    .opacity(if (show) 1.0 else 0.0)
                    .transform { translateY(if (show) 0.px else 30.px) }
            ) {
                StatCard("5+", "Years Experience", palette.success)
                StatCard("10+", "Projects Built", palette.primary)
                StatCard("∞", "Passion Level", palette.secondary)
            }
        }
    }
}

@Composable
private fun StatCard(value: String, label: String, accentColor: Color) {
    Column(
        modifier = statCardStyle.toModifier()
            .setVariable(glowColor, accentColor.copy(alpha = 0.3f)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SpanText(
            value,
            Modifier
                .fontSize(2.2.cssRem)
                .fontWeight(FontWeight.ExtraBold)
                .color(accentColor)
                .display(DisplayStyle.Block)
                .margin(bottom = 0.5.cssRem)
                .textShadow(TextShadow.of(0.px, 0.px, 20.px, accentColor.copy(alpha = 0.5f)))
        )
        SpanText(
            label,
            Modifier
                .fontSize(0.95.cssRem)
                .color(ColorMode.current.toSitePalette().textMuted)
                .textAlign(TextAlign.Center)
                .fontWeight(FontWeight.Medium)
        )
    }
}

@Composable
private fun ContentSection(isMobile: Boolean, palette: SitePalette) {
    val sections = listOf(
        SectionData(
            icon = "🧑‍💻",
            title = "Who I Am",
            content = "I'm Abu Naser, a passionate solo developer from Bangladesh focused on building clean, halal, and productive apps using Kotlin Multiplatform. I love making tools that solve real problems, especially in the language learning and productivity space.",
            highlight = "My apps work everywhere — Android, iOS, macOS, Windows, and Linux — with the same beautiful codebase.",
            accentColor = palette.success
        ),
        SectionData(
            icon = "💡",
            title = "Why Optimum-Tech",
            content = "Optimum-Tech is not just a brand — it's a mindset. Every app I build is crafted with clarity, minimalism, and Islamic principles in mind.",
            highlight = "No ads, no tracking, no shady stuff. Just powerful apps that respect users and devices.",
            accentColor = palette.primary
        ),
        SectionData(
            icon = "🛠️",
            title = "Tech Stack",
            content = "I use Kotlin Multiplatform for shared code, Jetpack Compose for UI, and deploy across all platforms. Backend services are powered by Supabase, Ktor, and custom APIs.",
            highlight = "Designed to be fast, scalable, and completely open source.",
            accentColor = palette.secondary
        ),
        SectionData(
            icon = "🌍",
            title = "Mission",
            content = "To empower individuals with ethical, accessible, and effective tools — built with intention and care.",
            highlight = "Proving that one person can create beautiful, functional, and principled software for the world.",
            accentColor = palette.warning
        )
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .maxWidth(1200.px)
            .margin(leftRight = autoLength)
            .padding(leftRight = 2.cssRem)
    ) {
        Column {
            sections.forEachIndexed { index, section ->
                AnimatedSectionCard(section, index, isMobile, palette)
            }

            ContactSection(isMobile, palette)
        }
    }
}

data class SectionData(
    val icon: String,
    val title: String,
    val content: String,
    val highlight: String,
    val accentColor: Color
)

@Composable
private fun AnimatedSectionCard(section: SectionData, index: Int, isMobile: Boolean, palette: SitePalette) {
    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay((index * 300 + 800).toLong())
        visible = true
    }

    Box(
        modifier = sectionCardStyle.toModifier()
            .setVariable(glowColor, section.accentColor.copy(alpha = 0.2f))
            .transition(Transition.of("all", 0.8.s, TransitionTimingFunction.EaseOut))
            .opacity(if (visible) 1.0 else 0.0)
            .transform {
                translateX(if (visible) 0.px else (if (index % 2 == 0) (-80).px else 80.px))
                translateY(if (visible) 0.px else 40.px)
                if (!visible) rotateY(15.deg) else rotateY(0.deg)
            }
    ) {
        // Enhanced accent border with glow
        Box(
            modifier = Modifier
                .position(Position.Absolute)
                .left(0.px)
                .top(0.px)
                .width(5.px)
                .height(100.percent)
                .backgroundColor(section.accentColor)
                .borderRadius(topLeft = 1.5.cssRem, bottomLeft = 1.5.cssRem)
                .boxShadow(BoxShadow.of(0.px, 0.px, 15.px, color = section.accentColor.copy(alpha = 0.5f)))
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .gap(if (isMobile) 1.5.cssRem else 2.5.cssRem)
        ) {
            // Enhanced icon container
            Box(
                modifier = Modifier
                    .width(if (isMobile) 70.px else 90.px)
                    .height(if (isMobile) 70.px else 90.px)
                    .backgroundImage(
                        radialGradient(
                            section.accentColor.copy(alpha = 0.2f),
                            section.accentColor.copy(alpha = 0.05f),
                            RadialGradient.Shape.Circle
                        )
                    )
                    .border(2.px, LineStyle.Solid, section.accentColor.copy(alpha = 0.3f))
                    .borderRadius(1.2.cssRem)
                    .display(DisplayStyle.Flex)
                    .alignItems(AlignItems.Center)
                    .justifyContent(JustifyContent.Center)
                    .flexShrink(0)
                    .boxShadow(
                        BoxShadow.of(0.px, 8.px, 25.px, color = section.accentColor.copy(alpha = 0.2f)),
                        BoxShadow.of(0.px, 0.px, 20.px, color = section.accentColor.copy(alpha = 0.1f), inset = true)
                    )
            ) {
                SpanText(
                    section.icon,
                    Modifier
                        .fontSize(if (isMobile) 2.cssRem else 2.8.cssRem)
                        .filter(Filter.of(dropShadow(0.px, 2.px, 8.px, section.accentColor.copy(alpha = 0.3f))))
                )
            }

            Column(
                modifier = Modifier.flexGrow(1)
            ) {
                H3(
                    Modifier
                        .fontSize(if (isMobile) 1.5.cssRem else 1.9.cssRem)
                        .fontWeight(FontWeight.ExtraBold)
                        .color(palette.text)
                        .margin(bottom = 1.2.cssRem)
                        .textShadow(TextShadow.of(0.px, 2.px, 4.px, rgba(0, 0, 0, 0.1)))
                        .toAttrs()
                ) {
                    Text(section.title)
                }

                P(
                    Modifier
                        .fontSize(if (isMobile) 1.cssRem else 1.15.cssRem)
                        .color(palette.textMuted)
                        .lineHeight(1.7)
                        .margin(bottom = 1.5.cssRem)
                        .toAttrs()
                ) {
                    Text(section.content)
                }

                // Enhanced highlight box
                Box(
                    modifier = Modifier
                        .backgroundImage(
                            linearGradient(
                                section.accentColor.copy(alpha = 0.15f),
                                section.accentColor.copy(alpha = 0.05f),
                                LinearGradient.Direction.ToRight
                            )
                        )
                        .border(1.px, LineStyle.Solid, section.accentColor.copy(alpha = 0.4f))
                        .borderRadius(1.cssRem)
                        .padding(1.2.cssRem, 1.8.cssRem)
                        .boxShadow(
                            BoxShadow.of(0.px, 4.px, 15.px, color = section.accentColor.copy(alpha = 0.1f)),
                            BoxShadow.of(0.px, 0.px, 30.px, color = section.accentColor.copy(alpha = 0.05f), inset = true)
                        )
                ) {
                    P(
                        Modifier
                            .fontSize(if (isMobile) 0.95.cssRem else 1.05.cssRem)
                            .color(section.accentColor.lightened(0.2f))
                            .fontWeight(FontWeight.SemiBold)
                            .margin(0.px)
                            .lineHeight(1.5)
                            .toAttrs()
                    ) {
                        Text("✨ ${section.highlight}")
                    }
                }
            }
        }
    }
}

@Composable
private fun ContactSection(isMobile: Boolean, palette: SitePalette) {
    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(2000)
        visible = true
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .margin(top = 5.cssRem)
            .backgroundImage(
                linearGradient(
                    palette.primary.copy(alpha = 0.1f),
                    palette.secondary.copy(alpha = 0.1f),
                    LinearGradient.Direction.ToRight
                )
            )
            .backdropFilter(blur(20.px))
            .border(1.px, LineStyle.Solid, palette.primary.copy(alpha = 0.3f))
            .borderRadius(2.cssRem)
            .padding(if (isMobile) 2.5.cssRem else 4.cssRem)
            .transition(Transition.of("all", 1.2.s, TransitionTimingFunction.EaseOut))
            .opacity(if (visible) 1.0 else 0.0)
            .transform {
                translateY(if (visible) 0.px else 60.px)
                scale(if (visible) 1 else 0.9)
            }
            .boxShadow(
                BoxShadow.of(0.px, 15.px, 40.px, color = rgba(0, 0, 0, 0.1)),
                BoxShadow.of(0.px, 0.px, 50.px, color = palette.primary.copy(alpha = 0.1f))
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SpanText(
                "🚀",
                Modifier
                    .fontSize(4.cssRem)
                    .margin(bottom = 1.5.cssRem)
                    .display(DisplayStyle.Block)
                    .filter(Filter.of(dropShadow(0.px, 4.px, 15.px, palette.primary.copy(alpha = 0.3f))))
            )

            H3(
                Modifier
                    .fontSize(if (isMobile) 1.6.cssRem else 2.2.cssRem)
                    .fontWeight(FontWeight.ExtraBold)
                    .color(palette.text)
                    .textAlign(TextAlign.Center)
                    .margin(bottom = 1.cssRem)
                    .textShadow(TextShadow.of(0.px, 2.px, 8.px, rgba(0, 0, 0, 0.2)))
                    .toAttrs()
            ) {
                Text("Let's Build Something Amazing")
            }

            P(
                Modifier
                    .fontSize(if (isMobile) 1.cssRem else 1.25.cssRem)
                    .color(palette.textMuted)
                    .textAlign(TextAlign.Center)
                    .lineHeight(1.6)
                    .maxWidth(650.px)
                    .margin(bottom = 2.5.cssRem)
                    .toAttrs()
            ) {
                Text("Have an idea? Want to collaborate? Or just want to say hi? I'd love to hear from you!")
            }

            Row(
                modifier = Modifier
                    .gap(1.5.cssRem)
                    .flexWrap(FlexWrap.Wrap)
                    .justifyContent(JustifyContent.Center)
            ) {
                ContactButton("📧", "Email", "mailto:contact@optimum-tech.org")
                ContactButton("💼", "LinkedIn", "https://www.linkedin.com/company/optimum-tech-org")
                ContactButton("🐙", "GitHub", "https://naser09.github.io")
            }
        }
    }
}

@Composable
private fun ContactButton(icon: String, label: String, link: String) {
    A(
        href = link,
        attrs = contactButtonStyle.toAttrs()
    ) {
        SpanText(
            "$icon $label",
            Modifier
                .fontWeight(FontWeight.SemiBold)
                .transition(Transition.of("all", 0.3.s))
        )
    }
}