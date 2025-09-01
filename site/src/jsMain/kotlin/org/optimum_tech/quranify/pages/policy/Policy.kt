package org.optimum_tech.quranify.pages.policy

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.*
import com.varabyte.kobweb.compose.css.functions.LinearGradient
import com.varabyte.kobweb.compose.css.functions.blur
import com.varabyte.kobweb.compose.css.functions.dropShadow
import com.varabyte.kobweb.compose.css.functions.linearGradient
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
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
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.style.selectors.hover
import com.varabyte.kobweb.silk.style.toAttrs
import com.varabyte.kobweb.silk.style.toModifier
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import org.optimum_tech.quranify.components.layouts.PageLayoutData
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Div
import org.optimum_tech.quranify.copy
import org.optimum_tech.quranify.toSitePalette
val mirrorBackground = CssStyle{
    val palette = colorMode.toSitePalette()
    base {
        Modifier
            .fillMaxWidth()
            .margin(top = 5.cssRem)
            .backgroundImage(
                linearGradient(
                    palette.primary.copy(alpha = 0.1f),
                    palette.secondary.copy(alpha = 0.1f),
                    LinearGradient.Direction.ToRight
                )
            )
            .backdropFilter(blur(20.px))
            .border(1.px, LineStyle.Solid, palette.primary.copy(alpha = 0.3f))
            .borderRadius(2.cssRem)
            .padding(3.cssRem)
            .transition(Transition.of("all", 1.2.s, TransitionTimingFunction.EaseOut))
            .opacity(0.9)
            .transform {
                translateY( 0.px )
                scale(0.9)
            }
            .boxShadow(
                BoxShadow.of(0.px, 15.px, 40.px, color = rgba(0, 0, 0, 0.1)),
                BoxShadow.of(0.px, 0.px, 50.px, color = palette.primary.copy(alpha = 0.1f))
            )
    }
    hover{
        Modifier.transform {
            translateY( 8.px )
            scale(1.0)
        }.opacity(1.0)
    }
}
data class PrivacySection(
    val title: String,
    val emoji: String,
    val content: String
)

val privacySections = listOf(
    PrivacySection(
        title = "Information We Collect",
        emoji = "📊",
        content = "User Account Information: When you sign up or log in, we collect basic account information such as email and username through our backend provider, Supabase. App Usage Data (Non-Personal): We may collect anonymous usage statistics to improve app functionality, such as flashcard progress and session durations. Manual Ads: We may display non-intrusive ads for Islamic products or services, hosted via Supabase or directly managed by us. These ads do not use any tracking technologies or third-party ad networks."
    ),
    PrivacySection(
        title = "How We Use Your Information",
        emoji = "🔧",
        content = "We use your information to provide account access and sync progress across devices, improve learning features and flashcard recommendations, show relevant content or announcements within the app, and maintain app security and prevent abuse."
    ),
    PrivacySection(
        title = "Permissions",
        emoji = "🔐",
        content = "Our app only requires access to the Internet. No special permissions (camera, location, storage, etc.) are requested or used."
    ),
    PrivacySection(
        title = "Data Sharing",
        emoji = "🚫",
        content = "We do not sell or share your personal data with any third parties. All data is stored securely via Supabase, a privacy-respecting backend platform."
    ),
    PrivacySection(
        title = "Account Deletion",
        emoji = "🗑️",
        content = "Users can delete their account directly from within the app at any time. When an account is deleted, all associated user data (including vocabulary progress and personal information) is permanently removed from our systems."
    ),
    PrivacySection(
        title = "Child Protection Compliance",
        emoji = "👶",
        content = "Quranify is built with family-friendly content in mind and adheres to the Google Play Families Policy, as well as international child protection regulations including COPPA and GDPR. All content is appropriate for younger audiences, ads are manually curated and non-personalized, and we do not collect personal data from children."
    ),
    PrivacySection(
        title = "Your Rights",
        emoji = "⚖️",
        content = "You may contact us to request information about your stored data, ask questions about data usage, or report any privacy concerns. For account deletion, you may also do so directly from within the app."
    ),
    PrivacySection(
        title = "Contact Us",
        emoji = "📧",
        content = "If you have any questions or concerns regarding your privacy, please contact us at: your-email@example.com"
    )
)

@InitRoute
fun initPrivacyPolicyPage(ctx: InitRouteContext) {
    ctx.data.add(PageLayoutData("Privacy Policy"))
}

@Page("/privacy-policy")
@Layout(".components.layouts.PageLayout")
@Composable
fun PrivacyPolicy() {
    val palette = ColorMode.current.toSitePalette()
    Div(
        Modifier
            .padding(top = 6.cssRem, bottom = 4.cssRem)
            .backgroundImage(
                linearGradient(
                    palette.background,
                    palette.background.copy(alpha = 0.5f),
                    LinearGradient.Direction.ToBottom
                )
            )
            .color(palette.text)
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
            // Header Section
            Div(
                Modifier
                    .flexFlow(FlexDirection.Column,FlexWrap.Wrap)
                    .textAlign(TextAlign.Center)
                    .margin(bottom = 3.cssRem)
                    .toAttrs()
            ) {
                Column (
                    modifier = mirrorBackground.toModifier().color(palette.text),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    SpanText(
                        "📜",
                        Modifier
                            .fontSize(3.cssRem)
                            .display(DisplayStyle.Block)
                            .margin(bottom = 1.cssRem)
                    )

                    SpanText(
                        "Privacy Policy for Quranify",
                        Modifier
                            .fontSize(2.5.cssRem)
                            .fontWeight(FontWeight.Bold)
                            .margin(bottom = 1.cssRem)
                    )

                    SpanText(
                        "Effective Date: December 2024",
                        Modifier
                            .fontSize(1.1.cssRem)
                            .margin(bottom = 1.cssRem)
                    )

                    SpanText(
                        "Quranify is committed to protecting your privacy. This Privacy Policy explains how your information is collected, used, and safeguarded when you use our app.",
                        Modifier
                            .fontSize(1.1.cssRem)
                            .lineHeight(1.6)
                            .maxWidth(800.px)
                            .margin(leftRight = autoLength)
                    )
                }
            }

            // Privacy Sections Grid
            Div(
                Modifier
                    .display(DisplayStyle.Grid)
                    .gap(2.cssRem)
                    .gridTemplateColumns {
                        repeat(GridEntry.Repeat.Auto.Type.AutoFit){
                            minmax(350.px, 1.fr)
                        }
                    }
                    .toAttrs()
            ) {
                privacySections.forEach { section ->
                    Div(
                        mirrorBackground.toAttrs()
                    ) {
                        Column {
                            SpanText(
                                section.emoji,
                                Modifier
                                    .fontSize(2.cssRem)
                                    .display(DisplayStyle.Block)
                                    .textAlign(TextAlign.Center)
                                    .margin(bottom = 1.cssRem)
                            )

                            SpanText(
                                section.title,
                                Modifier
                                    .fontSize(1.25.cssRem)
                                    .fontWeight(FontWeight.SemiBold)
                                    .margin(bottom = 1.cssRem)
                                    .textAlign(TextAlign.Center)
                                    .color(palette.text)
                            )

                            SpanText(
                                section.content,
                                Modifier
                                    .color(palette.textMuted)
                                    .fontSize(0.95.cssRem)
                                    .lineHeight(1.6)
                                    .textAlign(TextAlign.Left)
                            )
                        }
                    }
                }
            }

            // Footer Note
            Div(
                Modifier
                    .margin(top = 3.cssRem)
                    .padding(2.cssRem)
                    .backgroundColor(rgba(59, 130, 246, 0.1))
                    .border(1.px, LineStyle.Solid, rgba(59, 130, 246, 0.3))
                    .borderRadius(1.cssRem)
                    .textAlign(TextAlign.Center)
                    .toAttrs()
            ) {
                SpanText(
                    "📝 Changes to This Policy",
                    Modifier
                        .fontSize(1.1.cssRem)
                        .fontWeight(FontWeight.SemiBold)
                        .margin(bottom = 0.5.cssRem)
                        .display(DisplayStyle.Block)
                )

                SpanText(
                    "We may update this policy in the future. All updates will be posted within the app or on our official website.",
                    Modifier
                        .color(palette.text)
                        .fontSize(1.cssRem)
                        .lineHeight(1.6)
                )
            }
        }
    }
}