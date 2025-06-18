package optimum_tech.org.components.layouts

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.varabyte.kobweb.compose.css.autoLength
import com.varabyte.kobweb.compose.dom.svg.*
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.ColumnScope
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.PageContext
import com.varabyte.kobweb.core.data.getValue
import com.varabyte.kobweb.core.layout.Layout
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.style.toAttrs
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import kotlinx.browser.document
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.css.fr
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.css.vh
import optimum_tech.org.components.sections.Footer
import optimum_tech.org.components.sections.NavHeader
import optimum_tech.org.toSitePalette
import org.jetbrains.compose.web.css.FlexDirection
import org.jetbrains.compose.web.css.FlexWrap
import org.jetbrains.compose.web.css.vw
class PageLayoutData(val title: String)

@Composable
@Layout
fun PageLayout(ctx: PageContext, content: @Composable ColumnScope.() -> Unit) {
    val data = ctx.data.getValue<PageLayoutData>()
    LaunchedEffect(data.title) {
        document.title = "Optimum-Tech.org - ${data.title}"
    }
    Box (
        modifier = Modifier
            .background(ColorMode.current.toSitePalette().surface.darkened(0.1f))
            .fillMaxSize()
            .minWidth(80.vw)
            .margin(leftRight = autoLength),
        contentAlignment = Alignment.Center){
        Div(Modifier.fillMaxSize().toAttrs()){
            Column {
                NavHeader()
                Div(Modifier
                    .flexFlow(FlexDirection.Column, FlexWrap.Wrap)
                    .padding(top = 3.cssRem, leftRight = 4.cssRem, bottom = 3.cssRem)
                    .toAttrs()) {
                    content()
                }
                Footer(Modifier.fillMaxWidth())
            }
        }
    }
}
