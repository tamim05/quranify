package org.optimum_tech.quranify.components.widgets

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.css.*
import com.varabyte.kobweb.compose.dom.ElementRefScope
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.ColumnScope
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.foundation.layout.RowScope
import com.varabyte.kobweb.compose.foundation.layout.Spacer
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.components.overlay.Overlay
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.base
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.theme.colors.palette.background
import com.varabyte.kobweb.silk.theme.colors.palette.toPalette
import org.jetbrains.compose.web.css.*
import org.w3c.dom.HTMLElement

val ModalStyle = CssStyle.base {
    Modifier
        .minWidth(20.cssRem)
        .maxWidth(30.cssRem)
        .backgroundColor(colorMode.toPalette().background)
        .margin(top = 6.vh)
        .padding(1.cssRem)
        .gap(1.cssRem)
        .borderRadius(2.percent)
}

val ModalContentColumnStyle = CssStyle {
    base {
        Modifier
            .fillMaxWidth()
            .gap(1.cssRem)
            .padding(1.cssRem)
            .maxHeight(60.vh)
            .overflow { y(Overflow.Auto) }
    }
}

val ModalTitleStyle = CssStyle.base {
    Modifier
        .fillMaxWidth()
        .margin(bottom = 1.cssRem)
        .fontSize(2.cssRem)
        .fontWeight(FontWeight.Bold)
}

val ModalButtonRowStyle = CssStyle {
    base {
        Modifier.fillMaxWidth().margin(top = 2.cssRem).gap(1.cssRem)
    }

    cssRule(" *") {
        Modifier.flexGrow(1)
    }
}

val FullWidthChildrenStyle = CssStyle {
    cssRule(" > *") {
        Modifier.fillMaxWidth()
    }
}


@Composable
fun ModalDialog(
    overlayModifier: Modifier = Modifier,
    dialogModifier: Modifier = Modifier,
    ref: ElementRefScope<HTMLElement>? = null,
    title: String? = null,
    bottomRow: (@Composable RowScope.() -> Unit)? = null,
    content: (@Composable ColumnScope.() -> Unit)? = null,
) {
    ModalDialog(
        overlayModifier,
        dialogModifier,
        ref,
        titleRow = @Suppress("NAME_SHADOWING") title?.let { title -> {
            Spacer()
            SpanText(title)
            Spacer()
        }},
        bottomRow = bottomRow,
        content = content
    )
}

@Composable
fun ModalDialog(
    overlayModifier: Modifier = Modifier,
    dialogModifier: Modifier = Modifier,
    ref: ElementRefScope<HTMLElement>? = null,
    titleRow: (@Composable RowScope.() -> Unit)? = null,
    bottomRow: (@Composable RowScope.() -> Unit)? = null,
    content: (@Composable ColumnScope.() -> Unit)? = null,
) {
    Overlay(overlayModifier) {
        Column(ModalStyle.toModifier().then(dialogModifier), ref = ref) {
            if (titleRow != null) {
                Row(ModalTitleStyle.toModifier()) { titleRow() }
            }

            content?.let { content ->
                Column(listOf(ModalContentColumnStyle, FullWidthChildrenStyle).toModifier()) {
                    content()
                }
            }
            if (bottomRow != null) {
                Row(ModalButtonRowStyle.toModifier()) {
                    bottomRow()
                }
            }
        }
    }
}