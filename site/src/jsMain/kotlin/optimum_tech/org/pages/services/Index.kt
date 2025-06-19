package optimum_tech.org.pages.services

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.*
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
import com.varabyte.kobweb.silk.components.text.SpanText
import optimum_tech.org.components.layouts.PageLayoutData
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Div

data class Service(
    val title: String,
    val emoji: String,
    val description: String
)

val services = listOf(
    Service(
        title = "Android Development",
        emoji = "📱",
        description = "Beautiful, performant Android apps written in modern Kotlin — no ads, no fluff."
    ),
    Service(
        title = "Kotlin Multiplatform",
        emoji = "🌍",
        description = "Write once, run anywhere — build Android, iOS, and Desktop apps from a single Kotlin codebase."
    ),
    Service(
        title = "Custom Solutions",
        emoji = "💻",
        description = "Need something unique? Let’s design software that fits your needs and values."
    ),
    Service(
        title = "UI/UX Design",
        emoji = "🎨",
        description = "Intuitive and minimal interfaces optimized for speed and clarity."
    ),
    Service(
        title = "Cross-Platform Desktop",
        emoji = "🖥️",
        description = "Build native desktop apps for Windows, macOS, and Linux using KMP and Compose."
    ),
    Service(
        title = "Backend Integration",
        emoji = "🛠️",
        description = "Seamless integration with Supabase, Firebase, or custom APIs."
    )
)

@InitRoute
fun initServicesPage(ctx: InitRouteContext) {
    ctx.data.add(PageLayoutData("Our Services"))
}

@Page("/services")
@Layout(".components.layouts.PageLayout")
@Composable
fun Services() {
    Div(
        Modifier
            .padding(top = 6.cssRem, bottom = 4.cssRem)
            .backgroundColor(Color.rgb(15, 23, 42))
            .color(Colors.White)
            .minHeight(100.vh)
            .toAttrs()
    ) {
        Div(
            Modifier
                .maxWidth(1280.px)
                .margin(leftRight = autoLength)
                .padding(2.cssRem)
                .toAttrs()
        ) {
            SpanText(
                "Services We Offer",
                Modifier
                    .fontSize(2.5.cssRem)
                    .fontWeight(FontWeight.Bold)
                    .margin(bottom = 2.cssRem)
            )

            Div(
                Modifier
                    .display(DisplayStyle.Grid)
                    .gap(2.cssRem)
                    .gridTemplateColumns {
                        repeat(GridEntry.Repeat.Auto.Type.AutoFit){
                            minmax(250.px,1.fr)
                        }
                    }
                    .toAttrs()
            ) {
                services.forEach { service ->
                    Div(
                        Modifier
                            .padding(2.cssRem)
                            .backgroundColor(rgba(255, 255, 255, 0.05))
                            .border(1.px, LineStyle.Solid, rgba(255, 255, 255, 0.1))
                            .borderRadius(1.cssRem)
                            .boxShadow(BoxShadow.of(0.px, 4.px, 12.px, color = rgba(0, 0, 0, 0.2)))
//                            .hover { Modifier.transform { scale(1.02) } }
                            .transition(Transition.of("transform", 0.3.s))
                            .toAttrs()
                    ) {
                        SpanText(
                            service.emoji,
                            Modifier.fontSize(2.cssRem)
                                .display(DisplayStyle.Block)
                                .textAlign(TextAlign.Center)
                                .margin(bottom = 1.cssRem)
                        )

                        SpanText(
                            service.title,
                            Modifier
                                .fontSize(1.25.cssRem)
                                .fontWeight(FontWeight.SemiBold)
                                .margin(bottom = 1.cssRem)
                                .textAlign(TextAlign.Center)
                        )

                        SpanText(
                            service.description,
                            Modifier
                                .color(Color.rgb(203, 213, 225))
                                .fontSize(1.cssRem)
                                .lineHeight(1.6)
                                .textAlign(TextAlign.Center)
                        )
                    }
                }
            }
        }
    }
}
