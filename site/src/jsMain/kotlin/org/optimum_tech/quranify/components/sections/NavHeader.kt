package org.optimum_tech.quranify.components.sections

import androidx.compose.runtime.*
import com.varabyte.kobweb.browser.dom.ElementTarget
import com.varabyte.kobweb.compose.css.*
import com.varabyte.kobweb.compose.css.functions.LinearGradient
import com.varabyte.kobweb.compose.css.functions.blur
import com.varabyte.kobweb.compose.css.functions.clamp
import com.varabyte.kobweb.compose.css.functions.linearGradient
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.thenIf
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
import org.optimum_tech.quranify.components.widgets.IconButton
import org.optimum_tech.quranify.pages.allTransition
import org.optimum_tech.quranify.toSitePalette
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Text
val NavBarStyle = CssStyle.base {
    Modifier
        .fillMaxWidth()
        .backgroundColor(colorMode.toSitePalette().background.darkened(0.2f))
        .borderBottom(1.px, LineStyle.Solid, colorMode.toSitePalette().border)
        .backdropFilter(blur(10.px))
        .position(Position.Sticky)
        .top(0.px)
        .zIndex(1000)
}

val NavContainerStyle = CssStyle.base {
    Modifier
        .fillMaxWidth()
        .maxWidth(1280.px)
        .margin(leftRight = autoLength)
        .padding(leftRight = 2.cssRem)
}

val LogoStyle = CssStyle.base {
    Modifier
        .width(3.5.cssRem)
        .height(3.5.cssRem)
        .borderRadius(50.percent)
        .background(colorMode.toSitePalette().primary)
        .color(colorMode.toSitePalette().textOnColor)
        .fontWeight(FontWeight.Bold)
        .fontSize(1.5.cssRem)
}

val CTAButtonStyle = CssStyle {
    base {
        Modifier
            .background(colorMode.toSitePalette().primary)
            .padding(0.75.cssRem, 1.5.cssRem)
            .borderRadius(50.px)
            .color(colorMode.toSitePalette().textOnColor)
            .fontWeight(FontWeight.SemiBold)
            .border(0.px)
            .cursor(Cursor.Pointer)
            .allTransition()
    }
    hover {
        Modifier
            .transform { scale(1.05) }
            .boxShadow(BoxShadow.of(0.px, 10.px, 25.px, color = colorMode.toSitePalette().primary.darkened(byPercent = 0.3f)))
    }
}

val SideMenuSlideInAnim = Keyframes {
    from {
        Modifier.translateX(100.percent)
    }
    to {
        Modifier.translateX(0.percent)
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
            .color(colorMode.toSitePalette().text)
            .fontWeight(FontWeight.Medium)
            .padding(0.5.cssRem, 1.cssRem)
            .borderRadius(8.px)
            .allTransition(200.ms)
    }
    hover {
        Modifier
            .color(colorMode.toSitePalette().primary)
            .background(colorMode.toSitePalette().primary.darkened(byPercent = 0.1f))
    }
    // Active/selected state
    cssRule(".selected") {
        Modifier
            .color(colorMode.toSitePalette().primary)
            .border(3.px, style = LineStyle.Double ,color = colorMode.toSitePalette().secondary)
            .fontWeight(FontWeight.SemiBold)
    }
}

@Composable
private fun MenuItems() {
    val ctx = rememberPageContext()
    val currentPath = ctx.route.path
    val colorMode = ColorMode.current.toSitePalette()

    @Composable
    fun navModifier(path: String): Modifier {
        val isSelected = currentPath == path
        return NavLinkStyle.toModifier()
            .onClick {
                ctx.router.navigateTo(path)
            }
            .then(if (isSelected) Modifier.classNames("selected") else Modifier)
    }

    Link("/", "Home", navModifier("/"))
    Link("/delete-account", "Delete Account", navModifier("/delete-account").color(color = colorMode.warning))
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
            .alignItems(org.jetbrains.compose.web.css.AlignItems.Center)
            .gap(1.cssRem)
    ) {
        Image(
            src = "/quranify_logo.png",
            modifier = LogoStyle.toModifier()
                .padding(0.5.cssRem)
        )
        Column {
            SpanText(
                "Quranify",
                Modifier.fontSize(1.5.cssRem).fontWeight(FontWeight.Bold)
                    .color(colorMode.toSitePalette().text)
            )
            SpanText(
                "Quranic Vocabulary",
                Modifier.fontSize(0.85.cssRem).color(colorMode.toSitePalette().textMuted)
            )
        }
    }
}

@Composable
fun NavHeader() {
    var menuState by remember { mutableStateOf(SideMenuState.CLOSED) }
    val ctx = rememberPageContext()

    LaunchedEffect(Unit) {
        if (menuState != SideMenuState.CLOSED) {
            menuState = SideMenuState.CLOSED
        }
    }

    Div(NavBarStyle.toAttrs()) {
        Row(
            NavContainerStyle.toModifier()
                .height(6.cssRem)
                .alignItems(org.jetbrains.compose.web.css.AlignItems.Center)
                .justifyContent(org.jetbrains.compose.web.css.JustifyContent.SpaceBetween)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Logo()

            Row(
                Modifier
                    .gap(2.cssRem)
                    .alignItems(org.jetbrains.compose.web.css.AlignItems.Center)
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
    val ctx = rememberPageContext()
    val currentPath = ctx.route.path

    @Composable
    fun navModifier(path: String): Modifier {
        val isSelected = currentPath == path
        return NavLinkStyle.toModifier()
            .onClick { ctx.router.navigateTo(path) }
            .then(if (isSelected) Modifier.classNames("selected") else Modifier)
    }

    Overlay(
        Modifier
            .setVariable(OverlayVars.BackgroundColor, colorMode.toSitePalette().background.darkened(byPercent = 0.5f))
            .onClick { close() }
    ) {
        key(menuState) {
            Column(
                Modifier
                    .fillMaxHeight()
                    .width(clamp(14.cssRem, 50.percent, 18.cssRem))
                    .align(Alignment.CenterEnd)
                    .padding(top = 1.5.cssRem, leftRight = 1.5.cssRem)
                    .gap(2.cssRem)
                    .backgroundColor(colorMode.toSitePalette().surface)
                    .borderLeft(1.px, LineStyle.Solid, colorMode.toSitePalette().border)
                    .animation(
                        SideMenuSlideInAnim.toAnimation(
                            duration = 300.ms,
                            timingFunction = if (menuState == SideMenuState.OPEN) AnimationTimingFunction.EaseOut else AnimationTimingFunction.EaseIn,
                            direction = if (menuState == SideMenuState.OPEN) AnimationDirection.Normal else AnimationDirection.Reverse,
                            fillMode = AnimationFillMode.Forwards
                        )
                    )
                    .boxShadow(BoxShadow.of( (-5).px, 0.px, blurRadius = 25.px, color = colorMode.toSitePalette().background.darkened(byPercent = 0.2f)))
                    .onClick { it.stopPropagation() }
                    .onAnimationEnd { onAnimationEnd() },
                horizontalAlignment = Alignment.End
            ) {
                CloseButton(onClick = { close() })

                Column(
                    Modifier
                        .padding(right = 1.cssRem)
                        .gap(1.5.cssRem)
                        .fontSize(1.25.cssRem)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.End
                ) {
                    MenuItems()

                    Div(
                        Modifier
                            .height(1.px)
                            .fillMaxWidth()
                            .backgroundColor(colorMode.toSitePalette().border)
                            .margin(topBottom = 1.cssRem)
                            .toAttrs()
                    )
                    Link("/contact", text = "Contact Me" , modifier = navModifier("/contact").then(Modifier.onClick { close() }))
                    Button(
                        onClick = {
                            window.open("#download-section", target = "_self")
                            close()
                                  },
                        CTAButtonStyle.toModifier().alignSelf(org.jetbrains.compose.web.css.AlignSelf.Stretch)
                    ) {
                        Text("Download")
                    }
                }
            }
        }
    }
}