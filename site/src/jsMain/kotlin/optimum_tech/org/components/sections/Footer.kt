package optimum_tech.org.components.sections

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.css.WhiteSpace
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
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.dom.Span
import optimum_tech.org.toSitePalette
import org.jetbrains.compose.web.css.px
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.time.toJSDate

val FooterStyle = CssStyle.base {
    Modifier
        .backgroundColor(colorMode.toSitePalette().nearBackground)
        .padding(topBottom = 1.5.cssRem, leftRight = 10.percent)
}

@OptIn(ExperimentalTime::class)
@Composable
fun Footer(modifier: Modifier = Modifier) {
    Box(FooterStyle.toModifier().then(modifier), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            val sitePalette = ColorMode.current.toSitePalette()

            Span(Modifier.textAlign(TextAlign.Center).toAttrs()) {
                SpanText("Built with ")
                Link(
                    "https://github.com/varabyte/kobweb",
                    "Kobweb",
                    Modifier.setVariable(ColorVar, sitePalette.brand.primary),
                    variant = UncoloredLinkVariant
                )
            }

            Span(Modifier.textAlign(TextAlign.Center).margin(top = 4.px).toAttrs()) {
                val year = remember { Clock.System.now().toJSDate().getFullYear().toString() }
                SpanText("© $year optimum-tech.org. All rights reserved.")
            }
        }
    }
}
