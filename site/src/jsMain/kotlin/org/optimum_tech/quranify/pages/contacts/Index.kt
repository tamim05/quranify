package org.optimum_tech.quranify.pages.contacts

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.data.add
import com.varabyte.kobweb.core.init.InitRoute
import com.varabyte.kobweb.core.init.InitRouteContext
import com.varabyte.kobweb.core.layout.Layout
import org.optimum_tech.quranify.components.layouts.PageLayoutData
import org.jetbrains.compose.web.dom.Text
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.css.*
import com.varabyte.kobweb.compose.css.functions.blur
import com.varabyte.kobweb.compose.ui.modifiers.id
import com.varabyte.kobweb.compose.ui.modifiers.title
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.base
import com.varabyte.kobweb.silk.style.selectors.hover
import com.varabyte.kobweb.silk.style.toModifier
import kotlinx.browser.window
import kotlinx.coroutines.await
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.attributes.name
import org.jetbrains.compose.web.attributes.placeholder
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.css.filter
import org.jetbrains.compose.web.dom.*
import org.optimum_tech.quranify.pages.allTransition
import org.w3c.fetch.Headers
import org.w3c.fetch.RequestInit
import kotlin.js.Date

// ===== NOTIFICATION STYLES =====

enum class NotificationPosition {
    TOP_START, TOP_END, BOTTOM_START, BOTTOM_END
}

enum class NotificationType {
    SUCCESS, ERROR, WARNING, INFO
}

val NotificationContainerStyle = CssStyle.base {
    Modifier
        .position(Position.Fixed)
        .zIndex(9999)
        .padding(1.cssRem)
        .pointerEvents(PointerEvents.None)
}

val NotificationStyle = CssStyle.base {
    Modifier
        .minWidth(20.cssRem)
        .maxWidth(25.cssRem)
        .padding(1.cssRem)
        .borderRadius(8.px)
        .boxShadow(BoxShadow.of(0.px, 4.px, 12.px, color = rgba(0, 0, 0, 0.15)))
        .pointerEvents(PointerEvents.Auto)
        .cursor(Cursor.Pointer)
        .transition {
            property("transform", "0.3s")
        }
        .transform { translateY(0.px) }
}

val NotificationTitleStyle = CssStyle.base {
    Modifier
        .fontWeight(FontWeight.SemiBold)
        .fontSize(0.875.cssRem)
        .margin(bottom = 0.25.cssRem)
}

val NotificationMessageStyle = CssStyle.base {
    Modifier
        .fontSize(0.75.cssRem)
        .lineHeight(1.4)
        .opacity(0.8)
}


@InitRoute
fun initContactPage(ctx: InitRouteContext) {
    ctx.data.add(PageLayoutData("Contact Us"))
}

@Page("/contact")
@Layout(".components.layouts.PageLayout")
@Composable
fun Contact() {
    val scope = rememberCoroutineScope()
    var notifications by remember { mutableStateOf(listOf<NotificationData>()) }

    val contactLinks = listOf(
        ContactMethod("💬", "WhatsApp", "https://wa.me/01722005382"),
        ContactMethod("📘", "Facebook", "https://www.facebook.com/people/Optimum-Techorg/61577869407915/"),
        ContactMethod("💼", "LinkedIn", "https://www.linkedin.com/company/optimum-tech-org"),
        ContactMethod("✉️", "Email", "mailto:contact@optimum-tech.org")
    )
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
                                scope.launch {
                                    if (email.isNotBlank() && message.isNotBlank()) {
                                        val apiUrl = "https://contact.optimum-tech.org/messages"
                                        val apiKey = "mutIDuD5UiqYxr9bnvRVmSh7SW0dt3JNIv0aPCGrWNHrgiVxxzEjAGM4rKBmSln3IFN1qmapq1lUazp5bViUPiyuVSLIcr0SyeNPA3M4Xy72cGWKya2oJRL1bO7iQM05"
                                        val payload = """
                                            {
                                                "email":"$email",
                                                "message":"$message"
                                            }
                                        """.trimIndent()

                                        val headers = Headers().apply {
                                            append("Content-Type", "application/json")
                                            append(
                                                "X-API-Key",
                                                apiKey
                                            ) // or "x-api-key" if API expects that
                                        }

                                        val request = RequestInit(
                                            method = "POST",
                                            headers = headers,
                                            body = payload
                                        )

                                        try {
                                            val response = window.fetch(apiUrl, request).await()
                                            if (!response.ok) {
                                                submitted = false
                                                notifications += notifications + NotificationData(
                                                    id = Date.now().toString(),
                                                    title = "An Error Detected!",
                                                    message = "error > ${response.text().await()}",
                                                    type = NotificationType.ERROR
                                                )
                                                console.error("HTTP error:", response.status)
                                            } else {
                                                val data = response.json().await()
                                                submitted = true
                                                // In a real app, you'd send the data to your backend here
                                                email = ""
                                                message = ""
                                                notifications += notifications + NotificationData(
                                                    id = Date.now().toString(),
                                                    title = "Thank You!",
                                                    message = "Message received successfully !",
                                                    type = NotificationType.SUCCESS
                                                )
                                                console.log("Response:", data)
                                            }
                                        } catch (e: Throwable) {
                                            notifications += notifications + NotificationData(
                                                id = Date.now().toString(),
                                                title = "An Error Detected! ",
                                                message = "error :${e.message}",
                                                type = NotificationType.ERROR
                                            )
                                            console.error("Error:", e)
                                        }
                                    }
                                }
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
                // Notifications
                NotificationContainer(NotificationPosition.BOTTOM_END) {
                    notifications.forEach { notification ->
                        Notification(
                            title = notification.title,
                            message = notification.message,
                            type = notification.type,
                            autoHideDuration = 5000,
                            onDismiss = {
                                notifications = notifications.filter { it.id != notification.id }
                            },
                            modifier = Modifier.margin(bottom = 0.5.cssRem)
                        )
                    }
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

@Composable
fun NotificationContainer(
    position: NotificationPosition,
    children: @Composable () -> Unit
) {
    val positionModifier = when (position) {
        NotificationPosition.TOP_START -> Modifier.top(0.px).left(0.px)
        NotificationPosition.TOP_END -> Modifier.top(0.px).right(0.px)
        NotificationPosition.BOTTOM_START -> Modifier.bottom(0.px).left(0.px)
        NotificationPosition.BOTTOM_END -> Modifier.bottom(0.px).right(0.px)
    }

    Div(
        NotificationContainerStyle.toModifier()
            .zIndex(9999999f)
            .then(positionModifier)
            .toAttrs()
    ) {
        children()
    }
}

@Composable
fun Notification(
    title: String,
    message: String,
    type: NotificationType = NotificationType.INFO,
    autoHideDuration: Int = 5000, // milliseconds
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    var isVisible by remember { mutableStateOf(true) }

    val typeModifier = when (type) {
        NotificationType.SUCCESS -> Modifier.backgroundColor(org.jetbrains.compose.web.css.Color.green).color(org.jetbrains.compose.web.css.Color.white)
        NotificationType.ERROR -> Modifier.backgroundColor(org.jetbrains.compose.web.css.Color.red).color(org.jetbrains.compose.web.css.Color.white)
        NotificationType.WARNING -> Modifier.backgroundColor(org.jetbrains.compose.web.css.Color.orange).color(org.jetbrains.compose.web.css.Color.white)
        NotificationType.INFO -> Modifier.backgroundColor(org.jetbrains.compose.web.css.Color.blue).color(org.jetbrains.compose.web.css.Color.white)
    }

    LaunchedEffect(Unit) {
        if (autoHideDuration > 0) {
            delay(autoHideDuration.toLong())
            isVisible = false
            onDismiss()
        }
    }

    if (isVisible) {
        Column(
            NotificationStyle.toModifier()
                .zIndex(9999999f)
                .then(typeModifier)
                .then(modifier)
                .onClick { onDismiss() }
        ) {
            SpanText(title, NotificationTitleStyle.toModifier())
            SpanText(message, NotificationMessageStyle.toModifier())
        }
    }
}
data class NotificationData(
    val id: String,
    val title: String,
    val message: String,
    val type: NotificationType
)