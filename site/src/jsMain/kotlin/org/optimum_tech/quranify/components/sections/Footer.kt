package org.optimum_tech.quranify.components.sections

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.components.navigation.UncoloredLinkVariant
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.base
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.style.vars.color.ColorVar
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.dom.Span
import org.optimum_tech.quranify.toSitePalette
import org.jetbrains.compose.web.css.px
import kotlin.js.Date

val FooterStyle = CssStyle.base {
    Modifier
        .fillMaxWidth()
        .backgroundColor(colorMode.toSitePalette().surface)
        .padding(topBottom = 2.cssRem, leftRight = 10.percent)
        .borderTop(1.px, LineStyle.Solid, colorMode.toSitePalette().border)
}

@Composable
fun Footer(modifier: Modifier = Modifier) {
    Box(FooterStyle.toModifier().then(modifier), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.gap(0.5.cssRem)) {
            val sitePalette = ColorMode.current.toSitePalette()

            Span(Modifier.textAlign(TextAlign.Center).toAttrs()) {
                SpanText("Built with ", Modifier.color(sitePalette.textMuted))
                Link(
                    "https://github.com/varabyte/kobweb",
                    "Kobweb",
                    Modifier.setVariable(ColorVar, sitePalette.primary),
                    variant = UncoloredLinkVariant
                )
            }

            Span(Modifier.textAlign(TextAlign.Center).toAttrs()) {
                val year = remember { Date().getFullYear().toString() }
                SpanText("© $year optimum-tech.org. All rights reserved.", Modifier.color(sitePalette.textMuted))
            }
        }
    }
}
