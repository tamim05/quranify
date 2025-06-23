package org.optimum_tech.quranify.pages.about
import androidx.compose.runtime.*
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.layout.Layout
import com.varabyte.kobweb.compose.css.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.data.add
import com.varabyte.kobweb.core.init.InitRoute
import com.varabyte.kobweb.core.init.InitRouteContext
import com.varabyte.kobweb.silk.components.text.SpanText
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import kotlinx.coroutines.delay
import org.optimum_tech.quranify.components.layouts.PageLayoutData

@InitRoute
fun initAboutPage(ctx: InitRouteContext){
    ctx.data.add(PageLayoutData("About Us"))
}

@Page("/about")
@Layout(".components.layouts.PageLayout")
@Composable
fun About() {
    var show by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        delay(200)
        show = true
    }

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
                .maxWidth(960.px)
                .margin(leftRight = autoLength)
                .padding(2.cssRem)
                .toAttrs()
        ) {
            // Header
            Div(
                Modifier
                    .transition(Transition.of("all", 1.s))
                    .opacity(if (show) 1.0 else 0.0)
                    .translateY(if (show) 0.px else 50.px)
                    .toAttrs()
            ) {
                SpanText(
                    "About Optimum-Tech",
                    Modifier
                        .fontSize(2.75.cssRem)
                        .fontWeight(FontWeight.Bold)
                        .margin(bottom = 1.cssRem)
                )

                SpanText(
                    "A solo-driven initiative to build meaningful, ethical, and high-quality software for the modern world.",
                    Modifier
                        .fontSize(1.25.cssRem)
                        .color(Color.rgb(203, 213, 225))
                        .lineHeight(1.6)
                        .margin(bottom = 3.cssRem)
                )
            }

            // Who I Am
            SectionBlock(
                title = "👨‍💻 Who I Am",
                content = "I’m Abu Naser, a passionate solo developer from Bangladesh focused on building clean, halal, and productive apps using Kotlin Multiplatform. I love making tools that solve real problems, especially in the language learning and productivity space. My apps are built to work everywhere — Android, iOS, macOS, Windows, and Linux — with the same codebase."
            )

            // Why Optimum-Tech
            SectionBlock(
                title = "💡 Why Optimum-Tech",
                content = "Optimum-Tech is not just a brand — it’s a mindset. Every app I build is crafted with clarity, minimalism, and Islamic principles in mind. No ads, no tracking, no shady stuff. Just powerful apps that respect users and devices."
            )

            // Tech Stack
            SectionBlock(
                title = "🛠️ Tech Stack",
                content = "I use Kotlin Multiplatform for shared code, Jetpack Compose for UI, and deploy on Android, iOS, Desktop (Compose for Desktop), and Web. Backend services are powered by Supabase, Ktor, and custom APIs — designed to be fast, scalable, and open."
            )

            // Mission
            SectionBlock(
                title = "🌍 Mission",
                content = "To empower individuals with ethical, accessible, and effective tools — built with intention and care — and to demonstrate that one person can create beautiful, functional, and principled software for the world."
            )
        }
    }
}

@Composable
fun SectionBlock(title: String, content: String) {
    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(100)
        visible = true
    }

    Div(
        Modifier
            .transition(Transition.of("all", 0.8.s))
            .opacity(if (visible) 1.0 else 0.0)
            .translateY(if (visible) 0.px else 20.px)
            .margin(bottom = 3.cssRem)
            .toAttrs()
    ) {
        SpanText(
            title,
            Modifier
                .fontSize(1.5.cssRem)
                .fontWeight(FontWeight.Bold)
                .margin(bottom = 0.75.cssRem)
        )

        SpanText(
            content,
            Modifier
                .fontSize(1.125.cssRem)
                .lineHeight(1.75)
                .color(Color.rgb(203, 213, 225))
        )
    }
}
