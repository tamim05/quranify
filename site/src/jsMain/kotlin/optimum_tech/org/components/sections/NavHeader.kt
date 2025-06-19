package optimum_tech.org.components.sections

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
import com.varabyte.kobweb.compose.foundation.layout.Spacer
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
import com.varabyte.kobweb.silk.components.navigation.UncoloredLinkVariant
import com.varabyte.kobweb.silk.components.navigation.UndecoratedLinkVariant
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
import org.jetbrains.compose.web.css.*
import optimum_tech.org.components.widgets.IconButton
import optimum_tech.org.pages.allTransition
import optimum_tech.org.toSitePalette
import org.jetbrains.compose.web.css.Color.white
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Text

// Local styles for the NavHeader component
val NavBarStyle = CssStyle.base {
    Modifier
        .fillMaxWidth()
        .backgroundColor(colorMode.toSitePalette().nearBackground)
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
                Color.rgb(147, 51, 234),
                Color.rgb(236, 72, 153),
                LinearGradient.Direction.ToBottomRight
            )
        )
        .color(Colors.White)
        .fontWeight(FontWeight.Bold)
        .fontSize(1.25.cssRem)
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
            .color(colorMode.toSitePalette().accent)
            .fontWeight(FontWeight.Medium)
            .padding(0.5.cssRem, 1.cssRem)
            .borderRadius(8.px)
            .allTransition(300.ms)
    }
    hover {
        Modifier
            .backgroundColor(colorMode.toSitePalette().nearBackground)
            .color(Color.rgb(147, 51, 234))
    }
    // Add a custom class for active/current
    cssRule(".selected") {
        Modifier
            .backgroundColor(Color.rgb(147, 51, 234))
            .color(white)
    }
}

@Composable
private fun MenuItems() {
    val ctx = rememberPageContext()
    val currentPath = ctx.route.path
LaunchedEffect(currentPath){
    console.error(currentPath)
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
    Link( "/apps", "Apps", navModifier("/apps"))
    Link("/services", "Services", navModifier("/services"))
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
            SpanText("OT")
        }
        Column {
            SpanText(
                "Optimum-Tech",
                Modifier.fontSize(1.25.cssRem).fontWeight(FontWeight.Bold)
                    .color(color = colorMode.toSitePalette().brand.primary)
            )
            SpanText(
                "Kotlin Excellence",
                Modifier.fontSize(0.75.cssRem).color(colorMode.toSitePalette().brand.secondary)
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
                    .backgroundColor(colorMode.toSitePalette().nearBackground)
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
                        onClick = { /* Add your CTA action here */ },
                        CTAButtonStyle.toModifier().alignSelf(AlignSelf.Stretch)
                    ) {
                        Text("Get Started")
                    }
                }
            }
        }
    }
}