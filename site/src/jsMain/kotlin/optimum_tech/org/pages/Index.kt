package optimum_tech.org.pages

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.css.*
import com.varabyte.kobweb.compose.css.GridEntry.TrackSize.Companion.fitContent
import com.varabyte.kobweb.compose.css.functions.LinearGradient
import com.varabyte.kobweb.compose.css.functions.blur
import com.varabyte.kobweb.compose.css.functions.linearGradient
import com.varabyte.kobweb.compose.css.functions.saturate
import com.varabyte.kobweb.compose.foundation.layout.Column
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
import com.varabyte.kobweb.silk.components.forms.Button
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.base
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.style.breakpoint.displayIfAtLeast
import com.varabyte.kobweb.silk.style.selectors.hover
import com.varabyte.kobweb.silk.style.toAttrs
import com.varabyte.kobweb.silk.style.toModifier
import optimum_tech.org.components.layouts.PageLayoutData
import org.jetbrains.compose.web.ExperimentalComposeWebApi
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Text
import com.varabyte.kobweb.compose.css.JustifyContent
import com.varabyte.kobweb.compose.css.functions.Gradient
import com.varabyte.kobweb.compose.css.functions.colorMix
import org.jetbrains.compose.web.css.AlignItems
import kotlin.time.Duration
fun Modifier.allTransition(duration: CSSNumericValue<out CSSUnitTime> = 3.s) = this.transition(Transition.of("all",duration, TransitionTimingFunction.EaseInOut))

// Hero Section Styles
val HeroSectionStyle = CssStyle.base {
    Modifier
        .minHeight(100.vh)
        .minWidth(80.vw)
        .backgroundImage(
            linearGradient(
                Color.rgb(15, 23, 42),
                Color.rgb(15, 23, 42),
                LinearGradient.Direction.ToBottomRight
            )
        )
        .color(Colors.White)
        .padding(5.cssRem, 1.cssRem)
        .position(Position.Relative)
        .overflow(Overflow.Hidden)
}

val HeroContainerStyle = CssStyle {
    base {
        Modifier
            .maxWidth(1280.px)
            .margin(0.px, autoLength)
            .gap(3.cssRem)
            .display(DisplayStyle.Flex)
            .flexDirection(FlexDirection.Column)
    }
    Breakpoint.LG {
        Modifier.grid {
            columns { size(1.fr); size(1.fr) }
        }
    }
}

val HeroTextStyle = CssStyle {
    base {
        Modifier.textAlign(TextAlign.Center)
    }
    Breakpoint.LG {
        Modifier.textAlign(TextAlign.Left)
    }
}

val HeroTitleStyle = CssStyle {
    base {
        Modifier
            .fontSize(3.cssRem)
            .fontWeight(FontWeight.Bold)
            .lineHeight(1.1)
            .margin(bottom = 1.5.cssRem)
    }
    Breakpoint.LG {
        Modifier.fontSize(4.5.cssRem)
    }
}

val GradientTextStyle = CssStyle.base {
    Modifier
        .backgroundImage(
            linearGradient(
                Color.rgb(168, 85, 247),
                Color.rgb(236, 72, 153),
                LinearGradient.Direction.ToRight
            )
        )
        .backgroundClip(BackgroundClip.Text)
        .color(Colors.Transparent)
}

val HeroDescriptionStyle = CssStyle.base {
    Modifier
        .fontSize(1.25.cssRem)
        .color(Color.rgb(203, 213, 225))
        .lineHeight(1.6)
        .margin(bottom = 2.cssRem)
}

val ButtonGroupStyle = CssStyle {
    base {
        Modifier
            .display(DisplayStyle.Flex)
            .flexDirection(FlexDirection.Column)
            .gap(1.cssRem)
            .justifyContent(JustifyContent.Center)
    }
    Breakpoint.SM {
        Modifier.flexDirection(FlexDirection.Row)
    }
    Breakpoint.LG {
        Modifier.justifyContent(JustifyContent.Start)
    }
}

val PrimaryButtonStyle = CssStyle {
    base {
        Modifier
            .backgroundImage(
                linearGradient(
                    Color.rgb(147, 51, 234),
                    Color.rgb(236, 72, 153),
                    LinearGradient.Direction.ToRight
                )
            )
            .padding(1.cssRem, 2.cssRem)
            .borderRadius(50.px)
            .color(Colors.White)
            .fontWeight(FontWeight.SemiBold)
            .fontSize(1.125.cssRem)
            .border(0.px)
            .cursor(Cursor.Pointer)
            .allTransition(500.ms)
    }
    hover {
        Modifier
            .transform { scale(1.05) }
            .boxShadow(BoxShadow.of(0.px, 10.px, 25.px, color = rgba(147, 51, 234, 0.4)))
    }
}

val SecondaryButtonStyle = CssStyle {
    base {
        Modifier
            .textOverflow(TextOverflow.Ellipsis)
            .border(2.px, LineStyle.Solid, Color.rgb(147, 51, 234))
            .padding(1.cssRem, 2.cssRem)
            .borderRadius(50.px)
            .color(Colors.White)
            .fontWeight(FontWeight.SemiBold)
            .fontSize(1.125.cssRem)
            .backgroundColor(Colors.Transparent)
            .cursor(Cursor.Pointer)
            .allTransition(500.ms)
    }
    hover {
        Modifier.backgroundColor(rgba(147, 51, 234, 0.1))
    }
}

// Demo Grid Styles
val DemoGridStyle = CssStyle.base {
    Modifier
        .maxWidth(28.cssRem)
        .margin(0.px, autoLength)
        .grid {
            columns { repeat(3) { size(1.fr) } }
            rows { repeat(4) { size(1.fr) } }
        }
        .gap(1.cssRem)
}

val GridCardStyle = CssStyle {
    base {
        Modifier
            .backgroundColor(rgba(30, 41, 59, 0.8))
            .backdropFilter(blur(10.px))
            .border(1.px, LineStyle.Solid, rgba(147, 51, 234, 0.3))
            .borderRadius(1.5.cssRem)
            .padding(1.5.cssRem)
            .allTransition()
            .cursor(Cursor.Pointer)
    }
    hover {
        Modifier
            .transform { scale(1.05) }
            .border(1.px, LineStyle.Solid, rgba(147, 51, 234, 0.6))
    }
}

val MainCardStyle = CssStyle {
    base {
        Modifier
            .gridArea("1 / 1 / 3 / 3")
            .backgroundColor(rgba(30, 41, 59, 0.8))
            .backgroundImage(
                linearGradient(
                    rgba(147, 51, 234, 0.1),
                    rgba(236, 72, 153, 0.1),
                    LinearGradient.Direction.ToBottomRight
                )
            )
            .backdropFilter(blur(10.px))
            .border(1.px, LineStyle.Solid, rgba(147, 51, 234, 0.3))
            .borderRadius(1.5.cssRem)
            .padding(1.5.cssRem)
            .allTransition()
            .cursor(Cursor.Pointer)
    }
    hover {
        Modifier
            .transform { scale(1.05) }
            .border(1.px, LineStyle.Solid, rgba(147, 51, 234, 0.6))
    }
}

// Services Section Styles
val ServicesSectionStyle = CssStyle.base {
    Modifier
        .padding(5.cssRem, 1.cssRem)
        .minWidth(80.vw)
        .margin(leftRight = autoLength)
        .backgroundColor(Color.rgb(15, 23, 42))
        .color(Colors.White)
}

val ServicesGridStyle = CssStyle {
    base {
        Modifier
            .display(DisplayStyle.Flex)
            .flexDirection(FlexDirection.Column)
            .gap(2.cssRem)
    }
    Breakpoint.MD {
        Modifier.grid {
            columns { repeat(2) { size(1.fr) } }
        }
    }
    Breakpoint.LG {
        Modifier.grid {
            columns { repeat(3) { size(1.fr) } }
        }
    }
}

val ServiceCardStyle = CssStyle {
    base {
        Modifier
            .backgroundColor(Color.rgb(30, 41, 59))
            .backdropFilter(blur(10.px))
            .borderRadius(1.cssRem)
            .padding(2.cssRem)
            .border(1.px, LineStyle.Solid, Color.rgb(51, 65, 85))
            .allTransition()
            .cursor(Cursor.Pointer)
    }
    hover {
        Modifier
            .border(1.px, LineStyle.Solid, Color.rgb(147, 51, 234))
            .transform { scale(1.02) }
            .boxShadow(BoxShadow.of(0.px, 10.px, 25.px, color = rgba(147, 51, 234, 0.2)))
    }
}

val ServiceIconStyle = CssStyle.base {
    Modifier
        .width(4.cssRem)
        .height(4.cssRem)
        .borderRadius(1.cssRem)
        .margin(bottom = 1.5.cssRem)
        .fontSize(2.cssRem)
        .display(DisplayStyle.Flex)
        .alignItems(AlignItems.Center)
        .justifyContent(JustifyContent.Center)
        .allTransition()
}

// Background Decoration Styles
val BackgroundDecorStyle = CssStyle.base {
    Modifier
        .position(Position.Absolute)
        .opacity(0.2)
        .pointerEvents(PointerEvents.None)
}

val PurpleBlurStyle = CssStyle.base {
    Modifier
        .top(5.cssRem)
        .left(2.5.cssRem)
        .width(18.cssRem)
        .height(18.cssRem)
        .backgroundColor(Color.rgb(147, 51, 234))
        .borderRadius(50.percent)
        .filter(Filter.of(blur(3.cssRem)))
}

val PinkBlurStyle = CssStyle.base {
    Modifier
        .bottom(5.cssRem)
        .right(2.5.cssRem)
        .width(24.cssRem)
        .height(24.cssRem)
        .backgroundColor(Color.rgb(236, 72, 153))
        .borderRadius(50.percent)
        .filter(Filter.of(blur(3.cssRem)))
}

@InitRoute
fun initHomePage(ctx: InitRouteContext) {
    ctx.data.add(PageLayoutData("Home"))
}

@Page
@Layout(".components.layouts.PageLayout")
@Composable
fun HomePage() {
    // Hero Section
    Div(HeroSectionStyle.toAttrs()) {
        // Background decorations
        Div(BackgroundDecorStyle.toModifier().then(PurpleBlurStyle.toModifier()).toAttrs())
        Div(BackgroundDecorStyle.toModifier().then(PinkBlurStyle.toModifier()).toAttrs())

        Div(HeroContainerStyle.toModifier().alignItems(AlignItems.Center).toAttrs()) {
            // Text Content
            Column(HeroTextStyle.toModifier()) {
                Div(
                    Modifier
                        .backgroundColor(rgba(147, 51, 234, 0.2))
                        .padding(0.5.cssRem, 1.cssRem)
                        .borderRadius(50.px)
                        .border(1.px, LineStyle.Solid, rgba(147, 51, 234, 0.3))
                        .margin(bottom = 1.5.cssRem)
                        .width(Width.FitContent)
                        .margin(0.px, autoLength)
                        .toAttrs()
                ) {
                    SpanText(
                        "⚡ Kotlin Multiplatform Experts",
                        Modifier.color(Color.rgb(196, 181, 253))
                            .fontSize(0.875.cssRem)
                            .fontWeight(FontWeight.Medium)
                    )
                }

                Div(HeroTitleStyle.toAttrs()) {
                    SpanText("One Developer, ")
                    SpanText("Unlimited Possibilities", GradientTextStyle.toModifier())
                }

                Div(HeroDescriptionStyle.toAttrs()) {
                    SpanText(
                        "Explore a collection of simple, fast, and ethical apps. Built with Kotlin Multiplatform and crafted to run natively on Android, iOS, and Desktop."
                    )
                }

                Column(
                    ButtonGroupStyle.toModifier(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(
                        onClick = { },
                        PrimaryButtonStyle.toModifier()
                    ) {
                        Text("Explore Our Apps →")
                    }
                    Button(
                        onClick = { },
                        SecondaryButtonStyle.toModifier()
                    ) {
                        Text("View Portfolio")
                    }
                }
            }

            // Demo Grid
            Div(
                DemoGridStyle.toModifier().displayIfAtLeast(Breakpoint.MD).toAttrs()
            ) {
                // Main Feature Card
                Div(MainCardStyle.toModifier()
                    .flexFlow(FlexDirection.Column, FlexWrap.Wrap).toAttrs()) {
                    Column {
                        SpanText(
                            "📱 Android Apps",
                            Modifier.fontSize(1.125.cssRem)
                                .fontWeight(FontWeight.SemiBold)
                                .margin(bottom = 1.cssRem)
                                .color(Colors.White)
                        )
                        SpanText("Clean, fast, and modern Android apps — optimized for real users.",
                            Modifier.fontWeight(FontWeight.Thin))
                    }
                    Row(Modifier.gap(0.5.cssRem)) {
                        Div(
                            Modifier
                                .width(0.75.cssRem)
                                .height(0.75.cssRem)
                                .backgroundColor(Color.rgb(34, 197, 94))
                                .borderRadius(50.percent)
                                .toAttrs()
                        )
                        Div(
                            Modifier
                                .width(0.75.cssRem)
                                .height(0.75.cssRem)
                                .backgroundColor(Color.rgb(59, 130, 246))
                                .borderRadius(50.percent)
                                .toAttrs()
                        )
                        Div(
                            Modifier
                                .width(0.75.cssRem)
                                .height(0.75.cssRem)
                                .backgroundColor(Color.rgb(147, 51, 234))
                                .borderRadius(50.percent)
                                .toAttrs()
                        )
                    }
                }

                // Side Cards
                Div(GridCardStyle.toAttrs()) {
                    Column {
                        SpanText("🌍", Modifier.fontSize(1.5.cssRem).margin(bottom=0.5.cssRem))
                        SpanText("Multiplatform", Modifier.fontSize(0.875.cssRem).fontWeight(FontWeight.Medium).color(Colors.White))
                    }}

                Div(GridCardStyle.toAttrs()) {
                    Column {
                        SpanText("💻", Modifier.fontSize(1.5.cssRem).margin(bottom=0.5.cssRem))
                        SpanText("Clean Code", Modifier.fontSize(0.875.cssRem).fontWeight(FontWeight.Medium).color(Colors.White))
                    }
                }

                // Bottom Card
                Div(
                    GridCardStyle.toModifier()
                        .gridArea("4 / 1 / 5 / 4")
                        .backgroundImage(
                            linearGradient(
                                rgba(147, 51, 234, 0.1) ,
                                rgba(236, 72, 153, 0.1),
                                LinearGradient.Direction.ToRight
                            )
                        )
                        .toAttrs()
                ) {
                    Row(Modifier.justifyContent(JustifyContent.SpaceBetween).alignItems(AlignItems.Center)) {
                        Column {
                            SpanText(
                                "Performance Optimized",
                                Modifier.fontWeight(FontWeight.SemiBold).fontSize(0.875.cssRem).color(Colors.White)
                            )
                            SpanText(
                                "Lightning-fast native performance",
                                Modifier.fontSize(0.75.cssRem).color(Color.rgb(156, 163, 175))
                            )
                        }
                        Column(Modifier.textAlign(TextAlign.Right)) {
                            SpanText(
                                "99.9%",
                                Modifier.color(Color.rgb(34, 197, 94))
                                    .fontWeight(FontWeight.Bold)
                                    .fontSize(1.125.cssRem)
                            )
                            SpanText(
                                "Uptime",
                                Modifier.fontSize(0.75.cssRem).color(Color.rgb(156, 163, 175))
                            )
                        }
                    }
                }
            }
        }
    }

    // Services Section
    Div(ServicesSectionStyle.toAttrs()) {
        Column(
            Modifier.maxWidth(1280.px).margin(0.px, autoLength).gap(4.cssRem)
        ) {
            Column(Modifier.textAlign(TextAlign.Center).margin(bottom=4.cssRem)) {
                SpanText(
                    "What We Offer",
                    Modifier.fontSize(2.5.cssRem)
                        .fontWeight(FontWeight.Bold)
                        .margin(bottom=1.cssRem)
                        .color(Colors.White)
                )
                SpanText(
                    "From full-stack KMP apps to ethical design consulting — we build cross-platform tools with performance and purpose.",
                    Modifier.fontSize(1.25.cssRem)
                        .color(Color.rgb(203, 213, 225))
                        .maxWidth(48.cssRem)
                        .margin(0.px, autoLength)
                        .lineHeight(1.6)
                )
            }

            Div(ServicesGridStyle.toAttrs()) {
                // Android Development Service
                Div(ServiceCardStyle.toAttrs()) {
                    Div(
                        ServiceIconStyle.toModifier()
                            .backgroundImage(
                                linearGradient(
                                    Color.rgb(34, 197, 94) ,
                                    Color.rgb(16, 185, 129) ,
                                    LinearGradient.Direction.ToRight
                                )
                            )
                            .toAttrs()
                    ) {
                        SpanText("📱", Modifier.fontSize(2.cssRem))
                    }
                    Column {
                        SpanText(
                            "Android Development",
                            Modifier.fontSize(1.25.cssRem)
                                .fontWeight(FontWeight.SemiBold)
                                .margin(bottom=1.cssRem)
                                .color(Colors.White)
                        )
                        SpanText(
                            "Beautiful, performant Android apps written in modern Kotlin",
                            Modifier.color(Color.rgb(203, 213, 225)).lineHeight(1.6).fontSize(1.cssRem)
                        )
                    }
                }

                // Kotlin Multiplatform Service
                Div(ServiceCardStyle.toAttrs()) {
                    Div(
                        ServiceIconStyle.toModifier()
                            .backgroundImage(
                                linearGradient(
                                    Color.rgb(147, 51, 234),
                                    Color.rgb(139, 92, 246),
                                    LinearGradient.Direction.ToRight,
                                )
                            )
                            .toAttrs()
                    ) {
                        SpanText("🌍", Modifier.fontSize(2.cssRem))
                    }
                    Column {
                        SpanText(
                            "Kotlin Multiplatform",
                            Modifier.fontSize(1.25.cssRem)
                                .fontWeight(FontWeight.SemiBold)
                                .margin(bottom=1.cssRem)
                                .color(Colors.White)
                        )
                        SpanText(
                            "Write once, run anywhere — build Android, iOS, and Desktop apps from a single Kotlin codebase.",
                            Modifier.color(Color.rgb(203, 213, 225)).lineHeight(1.6).fontSize(1.cssRem)
                        )
                    }
                }

                // Custom Solutions Service
                Div(ServiceCardStyle.toAttrs()) {
                    Div(
                        ServiceIconStyle.toModifier()
                            .backgroundImage(
                                linearGradient(
                                    Color.rgb(59, 130, 246) ,
                                    Color.rgb(6, 182, 212) ,
                                    LinearGradient.Direction.ToRight,
                                )
                            )
                            .toAttrs()
                    ) {
                        SpanText("💻", Modifier.fontSize(2.cssRem))
                    }
                    Column {
                        SpanText(
                            "Custom Solutions",
                            Modifier.fontSize(1.25.cssRem)
                                .fontWeight(FontWeight.SemiBold)
                                .margin(bottom=1.cssRem)
                                .color(Colors.White)
                        )
                        SpanText(
                            "Need something unique? Let’s design software that fits your needs and values.",
                            Modifier.color(Color.rgb(203, 213, 225)).lineHeight(1.6).fontSize(1.cssRem)
                        )
                    }
                }
            }
        }
    }

    // CTA Section
    Div(
        Modifier
            .padding(5.cssRem, 1.cssRem)
            .textAlign(TextAlign.Center)
            .color(Colors.White)
            .backgroundColor(Color.rgb(15, 23, 42))
            .toAttrs()
    ) {
        Column(
            Modifier.maxWidth(64.cssRem).margin(0.px, autoLength).gap(2.cssRem),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SpanText(
                "Ready to Build Something Amazing?",
                Modifier.fontSize(2.5.cssRem)
                    .fontWeight(FontWeight.Bold)
                    .margin(bottom=1.5.cssRem)
            )
            SpanText(
                "Let's discuss your project and create innovative solutions together",
                Modifier.fontSize(1.25.cssRem)
                    .color(Color.rgb(203, 213, 225))
                    .margin(bottom=2.cssRem)
                    .lineHeight(1.6)
            )
            Row(
                Modifier.gap(1.cssRem)
                    .justifyContent(JustifyContent.Center)
                    .flexWrap(FlexWrap.Wrap)
            ) {
                Button(
                    onClick = { },
                    PrimaryButtonStyle.toModifier()
                ) {
                    Text("Start Your Project")
                }
                Button(
                    onClick = { },
                    SecondaryButtonStyle.toModifier()
                ) {
                    Text("Schedule Consultation")
                }
            }
        }
    }
}