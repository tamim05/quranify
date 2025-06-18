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

fun Modifier.allTransition() = this.transition(Transition.of("all",3.s, TransitionTimingFunction.EaseInOut))
// Navigation Bar Styles
@OptIn(ExperimentalComposeWebApi::class)
val NavBarStyle = CssStyle {
    base {
        Modifier
            .fillMaxWidth()
            .position(Position.Fixed)
            .top(0.px)
            .zIndex(50)
            .backgroundColor(rgba(15, 23, 42, 0.95))
            .backdropFilter(saturate(180.percent), blur(10.px))
            .borderBottom(1.px, LineStyle.Solid, rgba(148, 163, 184, 0.2))
            .allTransition()
    }
}

val NavContainerStyle = CssStyle.base {
    Modifier
        .maxWidth(1280.px)
        .margin(0.px, 0.px)
        .padding(0.px, 1.cssRem)
        .height(4.cssRem)
}

val LogoStyle = CssStyle.base {
    Modifier
        .width(2.5.cssRem)
        .height(2.5.cssRem)
        .backgroundImage(
            linearGradient(
                Color.rgb(168, 85, 247),
                Color.rgb(236, 72, 153),
                LinearGradient.Direction.ToBottomRight,
            )
        )
        .borderRadius(0.5.cssRem)
        .color(Colors.White)
        .fontSize(1.25.cssRem)
        .fontWeight(FontWeight.Bold)
}

val NavLinkStyle = CssStyle {
    base {
        Modifier
            .color(Color.rgba(203, 213, 225, 1))
            .textDecorationLine(TextDecorationLine.None)
            .fontWeight(FontWeight.Medium)
            .padding(0.5.cssRem, 1.cssRem)
            .borderRadius(0.5.cssRem)
             .allTransition()
    }
    hover {
        Modifier
            .color(Color.rgb(196, 181, 253))
            .backgroundColor(rgba(147, 51, 234, 0.1))
    }
}

val CTAButtonStyle = CssStyle {
    base {
        Modifier
            .backgroundImage(
                linearGradient(
                    Color.rgb(147, 51, 234),
                    Color.rgb(236, 72, 153),
                    LinearGradient.Direction.ToRight
                )
            )
            .padding(0.75.cssRem, 1.5.cssRem)
            .borderRadius(50.px)
            .color(Colors.White)
            .fontWeight(FontWeight.Medium)
            .border(0.px)
            .cursor(Cursor.Pointer)
             .allTransition()
    }
    hover {
        Modifier
            .transform { scale(1.05) }
            .boxShadow(BoxShadow.of(0.px, 10.px, 25.px, color = rgba(147, 51, 234, 0.4)))
        
    }
}

// Hero Section Styles
val HeroSectionStyle = CssStyle.base {
    Modifier
        .minHeight(100.vh)
        .backgroundImage(
            linearGradient(
                Color.rgb(15, 23, 42),
//                Color.rgb(76, 29, 149),
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
            .margin(0.px, 0.px)
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
                Color.rgb(196, 181, 253),
//                Color.rgb(244, 114, 182) to 50.percent,
                Color.rgb(196, 181, 253),
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
             .allTransition()
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
            .border(2.px, LineStyle.Solid, Color.rgb(147, 51, 234))
            .padding(1.cssRem, 2.cssRem)
            .borderRadius(50.px)
            .color(Colors.White)
            .fontWeight(FontWeight.SemiBold)
            .fontSize(1.125.cssRem)
            .backgroundColor(Colors.Transparent)
            .cursor(Cursor.Pointer)
             .allTransition()
    }
    hover {
        Modifier.backgroundColor(rgba(147, 51, 234, 0.1))
    }
}

// Demo Grid Styles
val DemoGridStyle = CssStyle.base {
    Modifier
        .maxWidth(28.cssRem)
        .margin(0.px, 0.px)
        .grid {
            columns { repeat(3) { size(1.fr) } }
            rows { repeat(4) { size(1.fr) } }
        }
        .gap(1.cssRem)
}

val GridCardStyle = CssStyle {
    base {
        Modifier
            .backgroundColor(rgba(100, 116, 139, 0.2))
            .backdropFilter(blur(10.px))
            .border(1.px, LineStyle.Solid, rgba(147, 51, 234, 0.3))
            .borderRadius(1.5.cssRem)
            .padding(1.5.cssRem)
             .allTransition()
            .cursor(Cursor.Pointer)
    }
    hover {
        Modifier.transform { scale(1.05) }
    }
}

val MainCardStyle = CssStyle {
    base {
        Modifier
            .gridArea("1 / 1 / 3 / 3")
            .backgroundImage(
                linearGradient(
                    rgba(147, 51, 234, 0.2),
                    rgba(236, 72, 153, 0.2),
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
        Modifier.transform { scale(1.05) }
    }
}

// Services Section Styles
val ServicesSectionStyle = CssStyle.base {
    Modifier
        .padding(5.cssRem, 1.cssRem)
        .backgroundColor(rgba(15, 23, 42, 0.5))
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
            .backgroundColor(rgba(51, 65, 85, 0.5))
            .backdropFilter(blur(10.px))
            .borderRadius(1.cssRem)
            .padding(2.cssRem)
            .border(1.px, LineStyle.Solid, Color.rgb(51, 65, 85))
             .allTransition()
            .cursor(Cursor.Pointer)
    }
    hover {
        Modifier
            .border { 
                color(rgba(147, 51, 234, 0.5))
            }
            .transform { scale(1.02) }
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
    // Navigation Bar
    Div(NavBarStyle.toAttrs()) {
        Row(
            NavContainerStyle.toModifier()
                .alignItems(AlignItems.Center)
                .justifyContent(JustifyContent.SpaceBetween),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                Modifier
                    .alignItems(AlignItems.Center)
                    .gap(0.75.cssRem)
            ) {
                Div(
                    LogoStyle.toModifier()
                        .alignItems(AlignItems.Center)
                        .justifyContent(JustifyContent.Center)
                        .display(DisplayStyle.Flex)
                        .toAttrs()
                ) {
                    SpanText("OT")
                }
                Column {
                    SpanText(
                        "Optimum-Tech",
                        Modifier.fontSize(1.25.cssRem).fontWeight(FontWeight.Bold)
                    )
                    SpanText(
                        "Kotlin Excellence",
                        Modifier.fontSize(0.75.cssRem).color(Color.rgb(156, 163, 175))
                    )
                }
            }

            Row(
                Modifier
                    .gap(2.cssRem)
                    .alignItems(AlignItems.Center)
                    .displayIfAtLeast(Breakpoint.MD),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Link("#home", "Home", NavLinkStyle.toModifier())
                Link("#apps", "Apps", NavLinkStyle.toModifier())
                Link("#services", "Services", NavLinkStyle.toModifier())
                Link("#about", "About", NavLinkStyle.toModifier())
                Link("#contact", "Contact", NavLinkStyle.toModifier())
                Button(
                    onClick = { },
                    CTAButtonStyle.toModifier()
                ) {
                    Text("Get Started")
                }
            }
        }
    }

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
                        .margin(0.px, 0.px)
//                        .apply {
//                            media(mediaMinWidth(Breakpoint.LG.minWidth)) {
//                                Modifier.margin(0.px)
//                            }
//                        }
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
                    SpanText("Build Once, ")
                    SpanText(
                        "Deploy Everywhere",
                        GradientTextStyle.toModifier()
                    )
                }

                Div(HeroDescriptionStyle.toAttrs()) {
                    SpanText(
                        "Transform your ideas into powerful cross-platform applications with Kotlin Multiplatform. " +
                                "We craft native-quality apps that run seamlessly on Android, iOS, and beyond."
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
                Div(MainCardStyle.toAttrs()) {
                    SpanText(
                        "📱 Android Apps",
                        Modifier.fontSize(1.125.cssRem)
                            .fontWeight(FontWeight.SemiBold)
                            .margin(bottom = 1.cssRem)
                    )
                    SpanText(
                        "Native Android development with modern Kotlin",
                        Modifier.color(Color.rgb(203, 213, 225))
                            .fontSize(0.875.cssRem)
                            .margin(bottom=1.cssRem)
                    )
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
                Div(
                    GridCardStyle.toModifier()
//                        .background(
//                            linearGradient(
//                                dir = LinearGradient.Direction.ToBottomRight,
//                                Color.rgba(59, 130, 246, 0.2) to 0.percent,
//                                Color.rgba(147, 51, 234, 0.2) to 100.percent
//                            )
//                        )
                        .toAttrs()
                ) {
                    SpanText("🌍", Modifier.fontSize(1.5.cssRem).margin(bottom=0.5.cssRem))
                    SpanText("Multiplatform", Modifier.fontSize(0.875.cssRem).fontWeight(FontWeight.Medium))
                }

                Div(
                    GridCardStyle.toModifier()
//                        .background(
//                            linearGradient(
//                                dir = LinearGradient.Direction.ToBottomRight,
//                                Color.rgba(236, 72, 153, 0.2) to 0.percent,
//                                Color.rgba(239, 68, 68, 0.2) to 100.percent
//                            )
//                        )
                        .toAttrs()
                ) {
                    SpanText("💻", Modifier.fontSize(1.5.cssRem).margin(bottom=0.5.cssRem))
                    SpanText("Clean Code", Modifier.fontSize(0.875.cssRem).fontWeight(FontWeight.Medium))
                }

                // Bottom Card
                Div(
                    GridCardStyle.toModifier()
                        .gridArea("4 / 1 / 5 / 4")
//                        .background(
//                            linearGradient(
//                                dir = LinearGradient.Direction.ToRight,
//                                Color.rgba(147, 51, 234, 0.1) to 0.percent,
//                                Color.rgba(236, 72, 153, 0.1) to 100.percent
//                            )
//                        )
                        .toAttrs()
                ) {
                    Row(Modifier.justifyContent(JustifyContent.SpaceBetween).alignItems(AlignItems.Center)) {
                        Column {
                            SpanText(
                                "Performance Optimized",
                                Modifier.fontWeight(FontWeight.SemiBold).fontSize(0.875.cssRem)
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
            Modifier.maxWidth(1280.px).margin(0.px, 0.px).gap(4.cssRem)
        ) {
            Column(Modifier.textAlign(TextAlign.Center).margin(bottom=4.cssRem)) {
                SpanText(
                    "Our Expertise",
                    Modifier.fontSize(2.5.cssRem)
                        .fontWeight(FontWeight.Bold)
                        .margin(bottom=1.cssRem)
                        .color(Colors.White)
                )
                SpanText(
                    "We specialize in cutting-edge Kotlin development, delivering exceptional mobile and cross-platform solutions",
                    Modifier.fontSize(1.25.cssRem)
                        .color(Color.rgb(203, 213, 225))
                        .maxWidth(48.cssRem)
                        .margin(0.px, 0.px)
                )
            }

            Div(ServicesGridStyle.toAttrs()) {
                // Android Development Service
                Div(ServiceCardStyle.toAttrs()) {
                    Div(
                        ServiceIconStyle.toModifier()
//                            .background(
//                                linearGradient(
//                                    dir = LinearGradient.Direction.ToRight,
//                                    Color.rgb(34, 197, 94) to 0.percent,
//                                    Color.rgb(16, 185, 129) to 100.percent
//                                )
//                            )
                            .toAttrs()
                    ) {
                        SpanText("📱", Modifier.fontSize(2.cssRem))
                    }
                    SpanText(
                        "Android Development",
                        Modifier.fontSize(1.25.cssRem)
                            .fontWeight(FontWeight.SemiBold)
                            .margin(bottom=1.cssRem)
                            .color(Colors.White)
                    )
                    SpanText(
                        "Native Android apps with modern Kotlin, following Material Design principles",
                        Modifier.color(Color.rgb(203, 213, 225)).lineHeight(1.6)
                    )
                }

                // Kotlin Multiplatform Service
                Div(ServiceCardStyle.toAttrs()) {
                    Div(
                        ServiceIconStyle.toModifier()
//                            .background(
//                                linearGradient(
//                                    dir = LinearGradient.Direction.ToRight,
//                                    Color.rgb(147, 51, 234) to 0.percent,
//                                    Color.rgb(139, 92, 246) to 100.percent
//                                )
//                            )
                            .toAttrs()
                    ) {
                        SpanText("🌍", Modifier.fontSize(2.cssRem))
                    }
                    SpanText(
                        "Kotlin Multiplatform",
                        Modifier.fontSize(1.25.cssRem)
                            .fontWeight(FontWeight.SemiBold)
                            .margin(bottom=1.cssRem)
                            .color(Colors.White)
                    )
                    SpanText(
                        "Share business logic across platforms while maintaining native performance",
                        Modifier.color(Color.rgb(203, 213, 225)).lineHeight(1.6)
                    )
                }

                // Custom Solutions Service
                Div(ServiceCardStyle.toAttrs()) {
                    Div(
                        ServiceIconStyle.toModifier()
//                            .background(
//                                linearGradient(
//                                    dir = LinearGradient.Direction.ToRight,
//                                    Color.rgb(59, 130, 246) to 0.percent,
//                                    Color.rgb(6, 182, 212) to 100.percent
//                                )
//                            )
                            .toAttrs()
                    ) {
                        SpanText("💻", Modifier.fontSize(2.cssRem))
                    }
                    SpanText(
                        "Custom Solutions",
                        Modifier.fontSize(1.25.cssRem)
                            .fontWeight(FontWeight.SemiBold)
                            .margin(bottom=1.cssRem)
                            .color(Colors.White)
                    )
                    SpanText(
                        "Tailored software solutions designed specifically for your business needs",
                        Modifier.color(Color.rgb(203, 213, 225)).lineHeight(1.6)
                    )
                }
            }
        }
    }

//    // CTA Section
//    Div(
//        Modifier
//            .padding(5.cssRem, 1.cssRem)
//            .textAlign(TextAlign.Center)
//            .color(Colors.White)
//            .toAttrs()
//    ) {
//        Column(
//            Modifier.maxWidth(64.cssRem).margin(0.px, 0.px).gap(2.cssRem),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            SpanText(
//                "Ready to Build Something Amazing?",
//                Modifier.fontSize(2.5.cssRem)
//                    .fontWeight(FontWeight.Bold)
//                    .margin(bottom=1.5.cssRem)
//            )
//            SpanText(
//                "Let's discuss your project and create innovative solutions together",
//                Modifier.fontSize(1.25.cssRem)
//                    .color(Color.rgb(203, 213, 225))
//                    .margin(bottom=2.cssRem)
//            )
//            Row(
//                Modifier.gap(1.cssRem)
//                    .justifyContent(JustifyContent.Center)
//                    .flexWrap(FlexWrap.Wrap)
//            ) {
//                Button(
//                    onClick = { },
//                    PrimaryButtonStyle.toModifier()
//                ) {
//                    Text("Start Your Project")
//                }
//                Button(
//                    onClick =

    // CTA Section
    Div(
        Modifier
            .padding(5.cssRem, 1.cssRem)
            .textAlign(TextAlign.Center)
            .color(Colors.White)
            .toAttrs()
    ) {
        Column(
            Modifier.maxWidth(64.cssRem).margin(0.px, 0.px).gap(2.cssRem),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SpanText(
                "Ready to Build Something Amazing?",
                Modifier.fontSize(2.5.cssRem).fontWeight(FontWeight.Bold).margin(bottom=1.5.cssRem)
            )
            SpanText(
                "Let's discuss your project and create innovative solutions together",
                Modifier.fontSize(1.25.cssRem).color(Color.rgb(203, 213, 225)).margin(bottom=2.cssRem)
            )
            Row(
                Modifier.gap(1.cssRem).justifyContent(JustifyContent.Center).flexWrap(FlexWrap.Wrap)
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