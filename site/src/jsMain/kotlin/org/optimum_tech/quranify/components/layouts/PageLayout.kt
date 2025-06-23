package org.optimum_tech.quranify.components.layouts

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.varabyte.kobweb.compose.css.autoLength
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
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import kotlinx.browser.document
import org.jetbrains.compose.web.css.cssRem
import org.jetbrains.compose.web.dom.Div
import org.optimum_tech.quranify.components.sections.Footer
import org.optimum_tech.quranify.components.sections.NavHeader
import org.optimum_tech.quranify.toSitePalette
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
        modifier = Modifier.Companion
            .background(ColorMode.current.toSitePalette().surface.darkened(0.1f))
            .fillMaxSize()
            .minWidth(90.vw)
            .margin(leftRight = autoLength),
        contentAlignment = Alignment.Center){
        Div(Modifier.fillMaxSize().align(Alignment.Center).toAttrs()){
            Column (
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally){
                NavHeader()
                Div(Modifier
                    .fillMaxWidth()
                    .flexFlow(FlexDirection.Column, FlexWrap.Wrap)
                    .padding(top = 1.cssRem, leftRight = 2.cssRem, bottom = 3.cssRem)
                    .toAttrs()) {
                    content()
                }
                Footer(Modifier.fillMaxWidth())
            }
        }
    }
}
