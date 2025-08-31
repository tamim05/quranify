package org.optimum_tech.quranify.components.widgets

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.framework.annotations.DelicateApi
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.theme.breakpoint.rememberBreakpoint
import org.jetbrains.compose.web.css.*

@OptIn(DelicateApi::class)
@Composable
fun RowColumnFlex(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit = {}
) {
    val breakpoint = rememberBreakpoint()

    when (breakpoint) {
        Breakpoint.ZERO -> Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            content()
        }

        Breakpoint.SM -> Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            content()
        }

        Breakpoint.MD, Breakpoint.LG, Breakpoint.XL -> {
            // Use CSS Flexbox with wrap for responsive row layout
            androidx.compose.runtime.key("flex-container") {
                org.jetbrains.compose.web.dom.Div(
                    attrs = {
                        style {
                            display(DisplayStyle.Flex)
                            flexDirection(FlexDirection.Row)
                            flexWrap(FlexWrap.Wrap)
                            justifyContent(JustifyContent.SpaceEvenly)
                            alignItems(AlignItems.Center)
                            gap(1.em) // Add some spacing between items
                            width(100.percent)
                        }
                    }
                ) {
                    content()
                }
            }
        }
    }
}

// Alternative implementation using Kobweb's built-in FlexBox if available
@OptIn(DelicateApi::class)
@Composable
fun RowColumnFlexAlternative(
    modifier: Modifier = Modifier,
    gap: CSSSizeValue<CSSUnit.em> = 1.em,
    content: @Composable () -> Unit = {}
) {
    val breakpoint = rememberBreakpoint()

    when (breakpoint) {
        Breakpoint.ZERO, Breakpoint.SM -> Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(gap)
        ) {
            content()
        }

        Breakpoint.MD, Breakpoint.LG, Breakpoint.XL -> {
            // Custom flex container with wrapping
            org.jetbrains.compose.web.dom.Div(
                attrs = {
                    style {
                        display(DisplayStyle.Flex)
                        flexDirection(FlexDirection.Row)
                        flexWrap(FlexWrap.Wrap)
                        justifyContent(JustifyContent.SpaceEvenly)
                        alignItems(AlignItems.Center)
                        gap(gap)
                        width(100.percent)
                    }
                }
            ) {
                content()
            }
        }
    }
}
