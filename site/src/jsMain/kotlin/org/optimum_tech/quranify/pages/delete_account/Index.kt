package org.optimum_tech.quranify.pages.delete_account

import androidx.compose.runtime.*
import com.varabyte.kobweb.browser.http.HttpMethod
import com.varabyte.kobweb.browser.http.fetch
import com.varabyte.kobweb.compose.css.*
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.data.add
import com.varabyte.kobweb.core.init.InitRoute
import com.varabyte.kobweb.core.init.InitRouteContext
import com.varabyte.kobweb.core.layout.Layout
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.theme.colors.ColorMode
import kotlinx.atomicfu.TraceBase.None.append
import kotlinx.browser.window
import kotlinx.coroutines.await
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.attributes.placeholder
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.css.AlignSelf
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Input
import org.jetbrains.compose.web.dom.Text
import org.optimum_tech.quranify.components.layouts.PageLayoutData
import org.optimum_tech.quranify.toSitePalette
import org.w3c.dom.get
import org.w3c.dom.set
import org.w3c.fetch.CORS
import org.w3c.fetch.Headers
import org.w3c.fetch.RequestInit
import org.w3c.fetch.RequestMode
import kotlin.js.Json
import kotlin.js.json

private val HOST_BETA="https://xvontntektlknyhcykpg.supabase.co"
private const val ANON_KEY_BETA = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Inh2b250bnRla3Rsa255aGN5a3BnIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDg3NjU2NTAsImV4cCI6MjA2NDM0MTY1MH0.WNnNSesClOn2W3FXPFCHiVdbQfFw65BwSX6DlXcwqmw"
private const val HOST = "https://wgjitktuzvxgfztrsxyd.supabase.co"
private const val ANON_KEY = "sb_publishable_bTSjUGNGqfHDPsN5WB3ifQ_qr_iggGd"
suspend fun requestDeleteAccount(email: String, password: String): Result<String> {
    return try {
            val login = try {
                window.fetch(
                    input = "$HOST/auth/v1/token?grant_type=password",
                    init = RequestInit(
                        method = "POST",
                        headers = Headers().apply {
                            append("apikey", ANON_KEY)
                            append("Content-Type", "application/json")
                        },
                        body = JSON.stringify(
                            json(
                                "email" to email,
                                "password" to password
                            )
                        )
                    )
                ).await()
            } catch (ex: Exception) {
                console.error("Login failed", ex)
                throw RuntimeException("Login failed")
            }
        val token = try {
            if (login.ok){
                login.json().await().asDynamic().access_token as String
            }else throw RuntimeException("Invalid Credentials.")
        }catch (ex: Exception){
            throw RuntimeException("Invalid Credentials.")
        }
        window.localStorage.set("delete_token",token)
        val response = window.fetch(
            "$HOST/functions/v1/request-delete-account/",
            object : RequestInit {
                override var method: String? = "POST"
                override var headers = Headers().apply {
//                    append("apikey", ANON_KEY)
                    append("Authorization", "Bearer $token")
                    append("Content-Type", "application/json")
                }
                override var mode: RequestMode? = RequestMode.CORS
                override var body = JSON.stringify(json(
                    "email" to email,
                    "password" to password
                ))
            }
        ).await()

        if (response.ok) {
            val data = response.json().await()
            console.log("data: ")
            console.log(data)
            Result.success("OTP sent successfully")
        } else {
            val errorData = response.json().await()

//            val errorMessage = errorData.get("message") as? String ?: "Request failed"
            Result.failure(Exception(errorData.asDynamic().error as String))
        }
    } catch (e: Exception) {
        Result.failure(e)
    }
}

suspend fun verifyDeleteAccount(otp: String): Result<String> {
    return try {
        val token = window.localStorage.get("delete_token")?: {
            throw RuntimeException("Please refresh this page,")
        }
        val response = window.fetch(
            "$HOST/functions/v1/verify-delete-account",
            object : RequestInit {
                override var method: String? = "POST"
                override var headers = Headers().apply {
                    append("Authorization", "Bearer $token")
                    append("Content-Type", "application/json")
                }
                override var body = JSON.stringify(json(
                    "otp" to otp
                ))
            }
        ).await()

        if (response.ok) {
            val data = response.json().await()
            console.log("data: ")
            console.log(data)
            Result.success("Account deleted successfully")
        } else {
            val errorData = response.json().await()
            console.log("error $errorData")
            val errorMessage = (errorData as? Json)?.get("message") as? String ?: "Verification failed"
            Result.failure(Exception(errorMessage))
        }
    } catch (e: Exception) {
        Result.failure(e)
    }
}
enum class DeleteStep{
    CREDENTIAL_VERIFICATION,
    OTP_VERIFICATION,
    ACCOUNT_DELETED
}

@InitRoute
fun initDeletePage(ctx: InitRouteContext) {
    ctx.data.add(PageLayoutData("Delete Account"))
}

@Page("/delete-account")
@Layout(".components.layouts.PageLayout")
@Composable
fun DeleteAccount() {
    val colorMode by ColorMode.currentState
    val palette = colorMode.toSitePalette()

    var currentStep by remember { mutableStateOf(DeleteStep.CREDENTIAL_VERIFICATION) }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var otp by remember { mutableStateOf("") }
    var error by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var otpSentCount by remember { mutableStateOf(0) }
    var resendDelay by remember { mutableStateOf(60) }
    var canResend by remember { mutableStateOf(true) }
    var countdown by remember { mutableStateOf(0) }

    // Countdown timer for resend OTP
    LaunchedEffect(countdown) {
        if (countdown > 0) {
            delay(1000)
            countdown--
        } else {
            canResend = true
        }
    }

    Div(
        Modifier
            .padding(top = 6.cssRem, bottom = 4.cssRem)
            .backgroundColor(palette.background)
            .color(palette.text)
            .minHeight(100.vh)
            .toAttrs()
    ) {
        Div(
            Modifier
                .maxWidth(600.px)
                .margin(leftRight = autoLength)
                .padding(2.cssRem)
                .toAttrs()
        ) {
            // Header with warning
            Div(
                Modifier
                    .backgroundColor(palette.surface)
                    .borderRadius(1.cssRem)
                    .padding(2.cssRem)
                    .border(1.px, LineStyle.Solid, palette.border)
                    .margin(bottom = 2.cssRem)
                    .toAttrs()
            ) {
                Column {
                    SpanText(
                        "⚠️ Delete Account",
                        Modifier
                            .fontSize(2.cssRem)
                            .fontWeight(FontWeight.Bold)
                            .color(palette.error)
                            .margin(bottom = 1.cssRem)
                    )

                    SpanText(
                        "This action is permanent and cannot be undone. All your data, settings, and content will be permanently deleted.",
                        Modifier
                            .fontSize(1.cssRem)
                            .lineHeight(1.6)
                            .color(palette.textMuted)
                    )
                }
            }

            // Progress indicator
            Div(
                Modifier
                    .display(DisplayStyle.Flex)
                    .justifyContent(org.jetbrains.compose.web.css.JustifyContent.Center)
                    .margin(bottom = 3.cssRem)
                    .toAttrs()
            ) {
                repeat(3) { index ->
                    val isActive = index <= currentStep.ordinal
                    val isCompleted = index < currentStep.ordinal

                    Div(
                        Modifier
                            .width(40.px)
                            .height(40.px)
                            .borderRadius(50.percent)
                            .backgroundColor(
                                when {
                                    isCompleted -> palette.success
                                    isActive -> palette.primary
                                    else -> palette.border
                                }
                            )
                            .color(if (isActive || isCompleted) palette.textOnColor else palette.textMuted)
                            .display(DisplayStyle.Flex)
                            .alignItems(org.jetbrains.compose.web.css.AlignItems.Center)
                            .justifyContent(org.jetbrains.compose.web.css.JustifyContent.Center)
                            .fontWeight(FontWeight.Bold)
                            .margin(right = if (index < 2) 1.cssRem else 0.cssRem)
                            .toAttrs()
                    ) {
                        SpanText(
                            if (isCompleted) "✓" else (index + 1).toString()
                        )
                    }

                    if (index < 2) {
                        Div(
                            Modifier
                                .width(2.cssRem)
                                .height(2.px)
                                .backgroundColor(if (index < currentStep.ordinal) palette.success else palette.border)
                                .alignSelf(AlignSelf.Center)
                                .margin(right = 1.cssRem)
                                .toAttrs()
                        )
                    }
                }
            }

            // Main content card
            Div(
                Modifier
                    .backgroundColor(palette.surface)
                    .borderRadius(1.cssRem)
                    .padding(2.cssRem)
                    .border(1.px, LineStyle.Solid, palette.border)
                    .toAttrs()
            ) {
                when (currentStep) {
                    DeleteStep.CREDENTIAL_VERIFICATION -> {
                        Column {
                            SpanText(
                                "Step 1: Verify Your Identity",
                                Modifier
                                    .fontSize(1.5.cssRem)
                                    .fontWeight(FontWeight.SemiBold)
                                    .margin(bottom = 1.cssRem)
                            )

                            SpanText(
                                "Please enter your email and password to confirm your identity.",
                                Modifier
                                    .color(palette.textMuted)
                                    .margin(bottom = 2.cssRem)
                            )

                            Div(
                                Modifier
                                    .display(DisplayStyle.Flex)
                                    .flexDirection(FlexDirection.Column)
                                    .gap(1.5.cssRem)
                                    .toAttrs()
                            ) {
                                Input(
                                    type = InputType.Email,
                                    attrs = Modifier
                                        .padding(1.cssRem)
                                        .fontSize(1.cssRem)
                                        .width(100.percent)
                                        .borderRadius(0.5.cssRem)
                                        .border(1.px, LineStyle.Solid, palette.border)
                                        .backgroundColor(palette.background)
                                        .color(palette.text)
                                        .toAttrs {
                                            placeholder("Enter your email")
                                            value(email)
                                            onInput { email = it.value }
                                        }
                                )

                                Input(
                                    type = InputType.Password,
                                    attrs = Modifier
                                        .padding(1.cssRem)
                                        .fontSize(1.cssRem)
                                        .width(100.percent)
                                        .borderRadius(0.5.cssRem)
                                        .border(1.px, LineStyle.Solid, palette.border)
                                        .backgroundColor(palette.background)
                                        .color(palette.text)
                                        .toAttrs {
                                            placeholder("Enter your password")
                                            value(password)
                                            onInput { password = it.value }
                                        }
                                )

                                if (error.isNotEmpty()) {
                                    SpanText(
                                        error,
                                        Modifier
                                            .color(palette.error)
                                            .fontSize(0.875.cssRem)
                                            .padding(0.5.cssRem)
                                            .backgroundColor(rgba(239, 68, 68, 0.1))
                                            .borderRadius(0.375.cssRem)
                                    )
                                }

                                Button(
                                    attrs = Modifier
                                        .backgroundColor(palette.error)
                                        .padding(1.cssRem, 2.cssRem)
                                        .color(palette.textOnColor)
                                        .border(0.px)
                                        .borderRadius(0.5.cssRem)
                                        .fontWeight(FontWeight.Medium)
                                        .fontSize(1.cssRem)
                                        .cursor(if (isLoading) Cursor.NotAllowed else Cursor.Pointer)
                                        .opacity(if (isLoading) 0.7 else 1.0)
                                        .width(100.percent)
                                        .onClick {
                                            if (!isLoading && email.isNotEmpty() && password.isNotEmpty()) {
                                                isLoading = true
                                                error = ""

                                                // Real API call
                                                kotlinx.coroutines.MainScope().launch {
                                                    try {
                                                        val result = requestDeleteAccount(email, password)
                                                        result.fold(
                                                            onSuccess = { message ->
                                                                currentStep = DeleteStep.OTP_VERIFICATION
                                                                otpSentCount = 1
                                                                isLoading = false
                                                            },
                                                            onFailure = { exception ->
                                                                error = "❌ ${exception.message ?: "Request failed. Please try again."}"
                                                                isLoading = false
                                                            }
                                                        )
                                                    } catch (e: Exception) {
                                                        error = "❌ Network error. Please check your connection."
                                                        isLoading = false
                                                    }
                                                }
                                            }
                                        }
                                        .toAttrs()
                                ) {
                                    Text(if (isLoading) "Verifying..." else "Send Verification Code")
                                }
                            }
                        }
                    }

                    DeleteStep.OTP_VERIFICATION -> {
                        Column {
                            SpanText(
                                "Step 2: Enter Verification Code",
                                Modifier
                                    .fontSize(1.5.cssRem)
                                    .fontWeight(FontWeight.SemiBold)
                                    .margin(bottom = 1.cssRem)
                            )

                            SpanText(
                                "We've sent a 6-digit code to $email. Please enter it below.",
                                Modifier
                                    .color(palette.textMuted)
                                    .margin(bottom = 2.cssRem)
                            )

                            Div(
                                Modifier
                                    .display(DisplayStyle.Flex)
                                    .flexDirection(FlexDirection.Column)
                                    .gap(1.5.cssRem)
                                    .toAttrs()
                            ) {
                                Input(
                                    type = InputType.Text,
                                    attrs = Modifier
                                        .padding(1.cssRem)
                                        .fontSize(1.5.cssRem)
                                        .width(100.percent)
                                        .borderRadius(0.5.cssRem)
                                        .border(1.px, LineStyle.Solid, palette.border)
                                        .backgroundColor(palette.background)
                                        .color(palette.text)
                                        .textAlign(TextAlign.Center)
                                        .letterSpacing(0.5.cssRem)
                                        .toAttrs {
                                            placeholder("000000")
                                            value(otp)
                                            onInput {
                                                if (it.value.length <= 6 && it.value.all { char -> char.isDigit() }) {
                                                    otp = it.value
                                                }
                                            }
                                        }
                                )

                                if (error.isNotEmpty()) {
                                    SpanText(
                                        error,
                                        Modifier
                                            .color(palette.error)
                                            .fontSize(0.875.cssRem)
                                            .padding(0.5.cssRem)
                                            .backgroundColor(rgba(239, 68, 68, 0.1))
                                            .borderRadius(0.375.cssRem)
                                    )
                                }

                                Button(
                                    attrs = Modifier
                                        .backgroundColor(palette.error)
                                        .padding(1.cssRem, 2.cssRem)
                                        .color(palette.textOnColor)
                                        .border(0.px)
                                        .borderRadius(0.5.cssRem)
                                        .fontWeight(FontWeight.Medium)
                                        .fontSize(1.cssRem)
                                        .cursor(if (isLoading || otp.length != 6) Cursor.NotAllowed else Cursor.Pointer)
                                        .opacity(if (isLoading || otp.length != 6) 0.7 else 1.0)
                                        .width(100.percent)
                                        .onClick {
                                            if (!isLoading && otp.length == 6) {
                                                isLoading = true
                                                error = ""

                                                // Real API call
                                                kotlinx.coroutines.MainScope().launch {
                                                    try {
                                                        val result = verifyDeleteAccount(otp)
                                                        result.fold(
                                                            onSuccess = { message ->
                                                                currentStep = DeleteStep.ACCOUNT_DELETED
                                                                isLoading = false
                                                            },
                                                            onFailure = { exception ->
                                                                error = "❌ ${exception.message ?: "Invalid verification code. Please try again."}"
                                                                isLoading = false
                                                            }
                                                        )
                                                    } catch (e: Exception) {
                                                        error = "❌ Network error. Please check your connection."
                                                        isLoading = false
                                                    }
                                                }
                                            }
                                        }
                                        .toAttrs()
                                ) {
                                    Text(if (isLoading) "Verifying..." else "Delete Account Permanently")
                                }

                                // Resend OTP section
                                Div(
                                    Modifier
                                        .display(DisplayStyle.Flex)
                                        .flexDirection(FlexDirection.Column)
                                        .alignItems(org.jetbrains.compose.web.css.AlignItems.Center)
                                        .gap(0.5.cssRem)
                                        .toAttrs()
                                ) {
                                    if (otpSentCount >= 3) {
                                        SpanText(
                                            "❌ Maximum attempts reached. Please try again later.",
                                            Modifier
                                                .color(palette.error)
                                                .fontSize(0.875.cssRem)
                                        )
                                    } else {
                                        if (canResend) {
                                            Button(
                                                attrs = Modifier
                                                    .backgroundColor(Colors.Transparent)
                                                    .color(palette.primary)
                                                    .border(0.px)
                                                    .fontSize(0.875.cssRem)
                                                    .cursor(Cursor.Pointer)
                                                    .textDecorationLine(TextDecorationLine.Underline)
                                                    .onClick {
                                                        if (otpSentCount < 3) {
                                                            otpSentCount++
                                                            canResend = false
                                                            countdown = resendDelay
                                                            resendDelay *= 2 // Double the delay

                                                            // Call resend API (same as initial request)
                                                            kotlinx.coroutines.MainScope().launch {
                                                                try {
                                                                    requestDeleteAccount(email, password)
                                                                } catch (e: Exception) {
                                                                    // Handle resend error if needed
                                                                }
                                                            }
                                                        }
                                                    }
                                                    .toAttrs()
                                            ) {
                                                Text("Resend Code")
                                            }
                                        } else {
                                            SpanText(
                                                "Resend code in ${countdown}s",
                                                Modifier
                                                    .color(palette.textMuted)
                                                    .fontSize(0.875.cssRem)
                                            )
                                        }

                                        SpanText(
                                            "Attempts: $otpSentCount/3",
                                            Modifier
                                                .color(palette.textMuted)
                                                .fontSize(0.75.cssRem)
                                        )
                                    }
                                }
                            }
                        }
                    }

                    DeleteStep.ACCOUNT_DELETED -> {
                        Column {
                            Div(
                                Modifier
                                    .display(DisplayStyle.Flex)
                                    .flexDirection(FlexDirection.Column)
                                    .alignItems(org.jetbrains.compose.web.css.AlignItems.Center)
                                    .textAlign(TextAlign.Center)
                                    .toAttrs()
                            ) {
                                SpanText(
                                    "✅",
                                    Modifier
                                        .fontSize(4.cssRem)
                                        .margin(bottom = 1.cssRem)
                                )

                                SpanText(
                                    "Account Deleted Successfully",
                                    Modifier
                                        .fontSize(1.5.cssRem)
                                        .fontWeight(FontWeight.SemiBold)
                                        .color(palette.success)
                                        .margin(bottom = 1.cssRem)
                                )

                                SpanText(
                                    "Your account and all associated data have been permanently deleted. You will be redirected to the homepage shortly.",
                                    Modifier
                                        .color(palette.textMuted)
                                        .lineHeight(1.6)
                                        .margin(bottom = 2.cssRem)
                                )

                                Button(
                                    attrs = Modifier
                                        .backgroundColor(palette.primary)
                                        .padding(1.cssRem, 2.cssRem)
                                        .color(palette.textOnColor)
                                        .border(0.px)
                                        .borderRadius(0.5.cssRem)
                                        .fontWeight(FontWeight.Medium)
                                        .fontSize(1.cssRem)
                                        .cursor(Cursor.Pointer)
                                        .onClick {
                                            // Redirect to homepage
                                            window.location.href = "/"
                                        }
                                        .toAttrs()
                                ) {
                                    Text("Go to Homepage")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}