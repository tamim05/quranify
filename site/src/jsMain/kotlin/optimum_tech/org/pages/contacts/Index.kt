package optimum_tech.org.pages.contacts

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.data.add
import com.varabyte.kobweb.core.init.InitRoute
import com.varabyte.kobweb.core.init.InitRouteContext
import com.varabyte.kobweb.core.layout.Layout
import optimum_tech.org.components.layouts.PageLayoutData
import org.jetbrains.compose.web.dom.Text
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.css.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.text.SpanText
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.attributes.name
import org.jetbrains.compose.web.attributes.placeholder
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.css.JustifyContent
import org.jetbrains.compose.web.dom.*

@InitRoute
fun initContactPage(ctx: InitRouteContext) {
    ctx.data.add(PageLayoutData("Contact Us"))
}

@Page("/contact")
@Layout(".components.layouts.PageLayout")
@Composable
fun Contact() {
    val contactLinks = listOf(
        ContactMethod("💬", "WhatsApp", "https://wa.me/your_number_here"),
        ContactMethod("📘", "Facebook Page", "https://facebook.com/yourpage"),
        ContactMethod("💼", "LinkedIn", "https://linkedin.com/in/yourprofile"),
        ContactMethod("✉️", "Email", "mailto:you@example.com")
    )

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }
    var submitted by remember { mutableStateOf(false) }

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
            Column {
                SpanText(
                    "Contact Me",
                    Modifier
                        .fontSize(2.5.cssRem)
                        .fontWeight(FontWeight.Bold)
                        .margin(bottom = 2.cssRem)
                )

                SpanText(
                    "I’d love to hear from you — whether it’s feedback, collaboration ideas, or questions about the apps.",
                    Modifier
                        .fontSize(1.125.cssRem)
                        .lineHeight(1.8)
                        .color(Color.rgb(203, 213, 225))
                        .margin(bottom = 2.cssRem)
                )
            }

            // Contact buttons
            Div(
                Modifier
                    .display(DisplayStyle.Flex)
                    .flexWrap(FlexWrap.Wrap)
                    .gap(1.5.cssRem)
                    .margin(bottom = 3.cssRem)
                    .toAttrs()
            ) {
                contactLinks.forEach { contact ->
                    A(
                        href = contact.url,
                        attrs = Modifier
                            .padding(1.cssRem)
                            .borderRadius(0.75.cssRem)
                            .backgroundColor(rgba(255, 255, 255, 0.05))
                            .border(1.px, LineStyle.Solid, rgba(255, 255, 255, 0.1))
                            .textDecorationLine(TextDecorationLine.None)
                            .color(Colors.White)
                            .display(DisplayStyle.InlineBlock)
                            .fontSize(1.125.cssRem)
                            .fontWeight(FontWeight.Medium)
                            .padding(1.cssRem, 1.5.cssRem)
                            .transition(Transition.of("transform", 0.3.s))
//                            .hover {
//                                Modifier
//                                    .transform { scale(1.05) }
//                                    .backgroundColor(rgba(147, 51, 234, 0.15))
//                            }
                            .toAttrs {
                                attr("target", "_blank")
                                attr("rel", "noopener noreferrer")
                            }
                    ) {
                        Text("${contact.icon} ${contact.name}")
                    }
                }
            }

            // Contact form
            SpanText(
                "Send a Message",
                Modifier
                    .fontSize(1.5.cssRem)
                    .fontWeight(FontWeight.SemiBold)
                    .margin(bottom = 2.cssRem)
            )

            Div(Modifier.display(DisplayStyle.Flex).flexDirection(FlexDirection.Column).gap(1.25.cssRem).toAttrs()) {
                Input(
                    type = InputType.Text,
                    attrs = Modifier
                        .padding(0.75.cssRem)
                        .fontSize(1.cssRem)
                        .width(100.percent)
                        .borderRadius(0.5.cssRem)
                        .border(1.px, LineStyle.Solid, Color.rgb(100, 116, 139))
                        .backgroundColor(Color.rgb(30, 41, 59))
                        .color(Colors.White)
                        .toAttrs {
                            placeholder("Your Name")
                            name(name)
                            onChange {
                                name = it.value
                            }
//                            attr("placeholder", "Your Name")
//                            onInput { name = it.value }
                        }
                )

                Input(
                    type = InputType.Email,
                    attrs = Modifier
                        .padding(0.75.cssRem)
                        .fontSize(1.cssRem)
                        .width(100.percent)
                        .borderRadius(0.5.cssRem)
                        .border(1.px, LineStyle.Solid, Color.rgb(100, 116, 139))
                        .backgroundColor(Color.rgb(30, 41, 59))
                        .color(Colors.White)
                        .toAttrs {
                            attr("placeholder", "Your Email")
                            onChange {
                                email = it.value
                            }
                        }
                )

                TextArea(
                    value = message,
                    attrs = Modifier
                        .padding(0.75.cssRem)
                        .fontSize(1.cssRem)
                        .width(100.percent)
                        .height(10.cssRem)
                        .borderRadius(0.5.cssRem)
                        .border(1.px, LineStyle.Solid, Color.rgb(100, 116, 139))
                        .backgroundColor(Color.rgb(30, 41, 59))
                        .color(Colors.White)
                        .toAttrs {
                            attr("placeholder", "Your Message")
                            onInput { message = it.value }
                        }
                )

                Button(
                    attrs =
                        Modifier
                            .backgroundColor(Color.rgb(147, 51, 234))
                            .padding(0.75.cssRem, 2.cssRem)
                            .color(Colors.White)
                            .border(0.px)
                            .borderRadius(0.75.cssRem)
                            .fontWeight(FontWeight.Medium)
                            .fontSize(1.cssRem)
                            .cursor(Cursor.Pointer)
                            .onClick {
                                submitted = true
                                name = ""
                                email = ""
                                message = ""
                            }
                            .toAttrs(),
//                        .hover { Modifier.backgroundColor(Color.rgb(126, 34, 206)) }
                ) {
                    Text("Send Message")
                }

                if (submitted) {
                    SpanText(
                        "✅ Message sent successfully!",
                        Modifier.color(Color.rgb(34, 197, 94)).margin(top = 1.cssRem)
                    )
                }
            }
        }
    }
}

data class ContactMethod(
    val icon: String,
    val name: String,
    val url: String
)
