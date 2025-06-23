package org.optimum_tech.quranify.components.sections

import androidx.compose.runtime.*
import com.varabyte.kobweb.browser.dom.ElementTarget
import com.varabyte.kobweb.compose.css.BoxShadow
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.JustifyContent
import com.varabyte.kobweb.compose.css.TextDecorationLine
import com.varabyte.kobweb.compose.css.autoLength
import com.varabyte.kobweb.compose.css.functions.LinearGradient
import com.varabyte.kobweb.compose.css.functions.blur
import com.varabyte.kobweb.compose.css.functions.clamp
import com.varabyte.kobweb.compose.css.functions.linearGradient
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.silk.components.forms.Button
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.icons.CloseIcon
import com.varabyte.kobweb.silk.components.icons.HamburgerIcon
import com.varabyte.kobweb.silk.components.icons.MoonIcon
import com.varabyte.kobweb.silk.components.icons.SunIcon
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.components.overlay.Overlay
import com.varabyte.kobweb.silk.components.overlay.OverlayVars
import com.varabyte.kobweb.silk.components.overlay.PopupPlacement
import com.varabyte.kobweb.silk.components.overlay.Tooltip
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.animation.Keyframes
import com.varabyte.kobweb.silk.style.animation.toAnimation
import com.varabyte.kobweb.silk.style.base
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.style.breakpoint.displayIfAtLeast
import com.varabyte.kobweb.silk.style.breakpoint.displayUntil
import com.varabyte.kobweb.silk.style.selectors.hover
import com.varabyte.kobweb.silk.style.toAttrs
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import com.varabyte.kobweb.silk.theme.shapes.RectF
import com.varabyte.kobweb.silk.theme.shapes.clip
import kotlinx.browser.window
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.css.Color.transparent
import org.optimum_tech.quranify.components.widgets.IconButton
import org.optimum_tech.quranify.pages.allTransition
import org.optimum_tech.quranify.toSitePalette
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Text

// Updated styles using SitePalette colors
val NavBarStyle = CssStyle.base {
    Modifier
        .fillMaxWidth()
        .backgroundColor(colorMode.toSitePalette().background)
        .borderBottom(1.px, LineStyle.Solid, colorMode.toSitePalette().border)
        .backdropFilter(blur(10.px))
        .position(Position.Sticky)
        .top(0.px)
        .zIndex(1000)
}

val NavContainerStyle = CssStyle.base {
    Modifier
        .fillMaxWidth()
        .maxWidth(1200.px)
        .margin(leftRight = autoLength)
        .padding(leftRight = 1.cssRem)
}

val LogoStyle = CssStyle.base {
    Modifier
        .width(3.cssRem)
        .height(3.cssRem)
        .borderRadius(12.px)
        .backgroundImage(
            linearGradient(
                colorMode.toSitePalette().gradientStart,
                colorMode.toSitePalette().gradientEnd,
                LinearGradient.Direction.ToBottomRight
            )
        )
        .color(colorMode.toSitePalette().textOnColor)
        .fontWeight(FontWeight.Bold)
        .fontSize(1.25.cssRem)
}

val CTAButtonStyle = CssStyle {
    base {
        Modifier
            .backgroundImage(
                linearGradient(
                    colorMode.toSitePalette().gradientStart,
                    colorMode.toSitePalette().gradientEnd,
                    LinearGradient.Direction.ToRight
                )
            )
            .padding(0.75.cssRem, 1.5.cssRem)
            .borderRadius(50.px)
            .color(colorMode.toSitePalette().textOnColor)
            .fontWeight(FontWeight.Medium)
            .border(0.px)
            .cursor(Cursor.Pointer)
            .allTransition()
    }
    hover {
        Modifier
            .transform { scale(1.05) }
            .boxShadow(BoxShadow.of(0.px, 10.px, 25.px, color = rgba(0, 0, 0, 0.2)))
    }
}

val SideMenuSlideInAnim = Keyframes {
    from {
        Modifier.translateX(100.percent)
    }
    to {
        Modifier
    }
}

enum class SideMenuState {
    CLOSED,
    OPEN,
    CLOSING;

    fun close() = when (this) {
        CLOSED -> CLOSED
        OPEN -> CLOSING
        CLOSING -> CLOSING
    }
}

val NavLinkStyle = CssStyle {
    base {
        Modifier
            .textDecorationLine(TextDecorationLine.None)
            .backgroundColor(transparent)
            .color(colorMode.toSitePalette().text)
            .fontWeight(FontWeight.Medium)
            .padding(0.5.cssRem, 1.cssRem)
            .borderRadius(8.px)
            .allTransition(300.ms)
    }
    hover {
        Modifier
            .backgroundColor(colorMode.toSitePalette().surface)
            .color(colorMode.toSitePalette().primary)
    }
    // Active/selected state
    cssRule(".selected") {
        Modifier
            .backgroundColor(colorMode.toSitePalette().primary)
            .color(colorMode.toSitePalette().textOnColor)
    }
}

@Composable
private fun MenuItems() {
    val ctx = rememberPageContext()
    val currentPath = ctx.route.path

    LaunchedEffect(currentPath) {
        console.log("Current path: $currentPath")
    }

    @Composable
    fun navModifier(path: String): Modifier {
        val isSelected = currentPath == path
        return NavLinkStyle.toModifier()
            .onClick {
                ctx.router.navigateTo(path)
            }.let {
                if (isSelected) it.classNames("selected") else it
            }
    }

    Link("/", "Home", navModifier("/"))
    Link("/apps", "Apps", navModifier("/apps"))
    Link("/privacy-policy", "Policy", navModifier("/privacy-policy"))
    Link("/about", "About", navModifier("/about"))
}

@Composable
private fun ColorModeButton() {
    var colorMode by ColorMode.currentState
    IconButton(onClick = { colorMode = colorMode.opposite }) {
        if (colorMode.isLight) MoonIcon() else SunIcon()
    }
    Tooltip(ElementTarget.PreviousSibling, "Toggle color mode", placement = PopupPlacement.BottomRight)
}

@Composable
private fun HamburgerButton(onClick: () -> Unit) {
    IconButton(onClick) {
        HamburgerIcon()
    }
}

@Composable
private fun CloseButton(onClick: () -> Unit) {
    IconButton(onClick) {
        CloseIcon()
    }
}

@Composable
private fun Logo() {
    val colorMode by ColorMode.currentState
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
            Image(
                src = "/quranify_logo.png",
                modifier = Modifier
                    .maxWidth(45.px)
                    .aspectRatio(1)
                    .clip(RectF(cornerRadius = 20.percent))
            )
        }
        Column {
            SpanText(
                "Quranify : Quranic Vocabulary",
                Modifier.fontSize(1.25.cssRem).fontWeight(FontWeight.Bold)
                    .color(colorMode.toSitePalette().text)
            )
            SpanText(
                "learn with ease",
                Modifier.fontSize(0.75.cssRem).color(colorMode.toSitePalette().textMuted)
            )
        }
    }
}

@Composable
fun NavHeader() {
    // State for mobile menu
    var menuState by remember { mutableStateOf(SideMenuState.CLOSED) }
    val ctx = rememberPageContext()

    // Effect to close menu when screen size changes
    LaunchedEffect(Unit) {
        // This will help reset the menu state when component recomposes due to screen size changes
        if (menuState != SideMenuState.CLOSED) {
            menuState = SideMenuState.CLOSED
        }
    }

    Div(NavBarStyle.toAttrs()) {
        Row(
            NavContainerStyle.toModifier()
                .alignItems(AlignItems.Center)
                .justifyContent(JustifyContent.SpaceBetween)
                .fillMaxWidth()
                .padding(1.cssRem),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Logo section
            Logo()

            // Desktop menu - visible on MD and larger screens
            Row(
                Modifier
                    .gap(2.cssRem)
                    .alignItems(AlignItems.Center)
                    .displayIfAtLeast(Breakpoint.MD),
                verticalAlignment = Alignment.CenterVertically
            ) {
                MenuItems()
                ColorModeButton()
                Button(
                    onClick = { ctx.router.navigateTo("/contact") },
                    CTAButtonStyle.toModifier()
                ) {
                    Text("Contact")
                }
            }

            // Mobile menu controls - visible until MD breakpoint
            Row(
                Modifier
                    .fontSize(1.5.cssRem)
                    .gap(1.cssRem)
                    .displayUntil(Breakpoint.MD),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ColorModeButton()
                HamburgerButton(onClick = {
                    menuState = if (menuState == SideMenuState.CLOSED) SideMenuState.OPEN else menuState.close()
                })

                // Only show side menu on mobile screens
                if (menuState != SideMenuState.CLOSED) {
                    SideMenu(
                        menuState = menuState,
                        close = { menuState = menuState.close() },
                        onAnimationEnd = {
                            if (menuState == SideMenuState.CLOSING) {
                                menuState = SideMenuState.CLOSED
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun SideMenu(menuState: SideMenuState, close: () -> Unit, onAnimationEnd: () -> Unit) {
    val colorMode by ColorMode.currentState
    Overlay(
        Modifier
            .setVariable(OverlayVars.BackgroundColor, rgba(0, 0, 0, 0.5))
            .onClick { close() }
    ) {
        key(menuState) {
            Column(
                Modifier
                    .fillMaxHeight()
                    .width(clamp(12.cssRem, 40.percent, 16.cssRem))
                    .align(Alignment.CenterEnd)
                    .padding(top = 1.cssRem, leftRight = 1.cssRem)
                    .gap(1.5.cssRem)
                    .backgroundColor(colorMode.toSitePalette().surface)
                    .borderLeft(1.px, LineStyle.Solid, colorMode.toSitePalette().border)
                    .animation(
                        SideMenuSlideInAnim.toAnimation(
                            duration = 250.ms,
                            timingFunction = if (menuState == SideMenuState.OPEN) AnimationTimingFunction.EaseOut else AnimationTimingFunction.EaseIn,
                            direction = if (menuState == SideMenuState.OPEN) AnimationDirection.Normal else AnimationDirection.Reverse,
                            fillMode = AnimationFillMode.Forwards
                        )
                    )
                    .borderRadius(topLeft = 1.cssRem)
                    .boxShadow(BoxShadow.of(-5.px, 0.px, 25.px, color = rgba(0, 0, 0, 0.1)))
                    .onClick { it.stopPropagation() }
                    .onAnimationEnd { onAnimationEnd() },
                horizontalAlignment = Alignment.End
            ) {
                CloseButton(onClick = { close() })

                Column(
                    Modifier
                        .padding(right = 0.75.cssRem)
                        .gap(1.cssRem)
                        .fontSize(1.1.cssRem)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.End
                ) {
                    MenuItems()

                    // Separator
                    Div(
                        Modifier
                            .height(1.px)
                            .fillMaxWidth()
                            .backgroundColor(colorMode.toSitePalette().border)
                            .margin(topBottom = 1.cssRem)
                            .toAttrs()
                    )

                    // CTA Button in mobile menu
                    Button(
                        onClick = { window.open("#download") },
                        CTAButtonStyle.toModifier().alignSelf(AlignSelf.Stretch)
                    ) {
                        Text("Get Started")
                    }
                }
            }
        }
    }
}