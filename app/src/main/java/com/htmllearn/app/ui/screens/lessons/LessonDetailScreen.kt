package com.htmllearn.app.ui.screens.lessons

import android.annotation.SuppressLint
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import com.htmllearn.app.ui.AppViewModel
import com.htmllearn.app.ui.theme.*

@SuppressLint("SetJavaScriptEnabled")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LessonDetailScreen(
    lessonId: String,
    onQuiz: () -> Unit,
    onBack: () -> Unit,
    vm: AppViewModel = viewModel()
) {
    val lesson  = vm.getLesson(lessonId) ?: return
    val chapter = vm.chapters.find { it.id == lesson.chapterId }
    val chColor = chapter?.let { Color(it.color) } ?: Primary

    var showOutput by remember { mutableStateOf(false) }

    // Build full HTML for output preview
    val fullHtml = if (lesson.outputHtml.startsWith("<!DOCTYPE") || lesson.outputHtml.startsWith("<html")) {
        lesson.outputHtml
    } else {
        """<!DOCTYPE html><html><head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<style>
  body{margin:0;padding:16px;background:#0f0f0f;color:#fff;font-family:Arial,sans-serif}
  *{box-sizing:border-box}
</style></head>
<body>${lesson.outputHtml}</body></html>"""
    }

    Column(Modifier.fillMaxSize().background(BgDark)) {
        // Top bar
        Surface(color = BgSurface, shadowElevation = 2.dp) {
            Row(
                Modifier.fillMaxWidth().padding(4.dp, 40.dp, 16.dp, 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBack) {
                    Icon(Icons.Filled.ArrowBack, null, tint = TextPrimary)
                }
                Column(Modifier.weight(1f)) {
                    Text(lesson.title, style = MaterialTheme.typography.titleLarge)
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text(lesson.duration, style = MaterialTheme.typography.labelSmall)
                        Text("•", style = MaterialTheme.typography.labelSmall, color = TextHint)
                        Text("+${lesson.xp} XP",
                            style = MaterialTheme.typography.labelSmall, color = chColor)
                    }
                }
            }
        }

        Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {

            // ── Explanation ───────────────────────────────────────────
            Card(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                colors   = CardDefaults.cardColors(containerColor = BgCard),
                shape    = RoundedCornerShape(16.dp)
            ) {
                Column(Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Filled.MenuBook, null, tint = chColor, modifier = Modifier.size(18.dp))
                        Spacer(Modifier.width(8.dp))
                        Text("ব্যাখ্যা", style = MaterialTheme.typography.titleMedium, color = chColor)
                    }
                    Spacer(Modifier.height(12.dp))
                    Text(lesson.explanation, style = MaterialTheme.typography.bodyMedium, lineHeight = 26.sp)
                }
            }

            // ── Code Example ──────────────────────────────────────────
            Card(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                colors   = CardDefaults.cardColors(containerColor = Color(0xFF0D1117)),
                shape    = RoundedCornerShape(16.dp)
            ) {
                Column {
                    // Code header bar (macOS style)
                    Row(
                        Modifier.fillMaxWidth().background(Color(0xFF161B22)).padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Traffic lights
                        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                            Box(Modifier.size(10.dp).background(Color(0xFFFF5F57), CircleShape))
                            Box(Modifier.size(10.dp).background(Color(0xFFFFBD2E), CircleShape))
                            Box(Modifier.size(10.dp).background(Color(0xFF28C840), CircleShape))
                        }
                        Spacer(Modifier.weight(1f))
                        Surface(
                            shape = RoundedCornerShape(4.dp),
                            color = Color(0xFF21262D)
                        ) {
                            Text("HTML", modifier = Modifier.padding(6.dp, 2.dp),
                                fontFamily = FontFamily.Monospace, fontSize = 10.sp,
                                color = Color(0xFF8B949E))
                        }
                    }

                    // Code
                    Text(
                        text       = lesson.codeExample,
                        fontFamily = FontFamily.Monospace,
                        fontSize   = 13.sp,
                        color      = Color(0xFFE6EDF3),
                        lineHeight = 22.sp,
                        modifier   = Modifier.padding(16.dp)
                    )

                    // Output toggle button
                    Divider(color = Color(0xFF21262D))
                    Row(
                        Modifier.fillMaxWidth().padding(horizontal = 12.dp, vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment     = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Filled.Visibility, null, tint = Secondary, modifier = Modifier.size(16.dp))
                            Spacer(Modifier.width(6.dp))
                            Text("Output দেখো", style = MaterialTheme.typography.labelLarge, color = Secondary)
                        }
                        Switch(
                            checked         = showOutput,
                            onCheckedChange = { showOutput = it },
                            colors          = SwitchDefaults.colors(
                                checkedThumbColor  = Secondary,
                                checkedTrackColor  = Secondary.copy(0.3f),
                                uncheckedThumbColor = TextHint,
                                uncheckedTrackColor = BgElevated
                            )
                        )
                    }

                    // Output WebView
                    AnimatedVisibility(visible = showOutput) {
                        Column {
                            Divider(color = Color(0xFF21262D))
                            // Browser bar
                            Row(
                                Modifier.fillMaxWidth().background(Color(0xFF161B22)).padding(10.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Icon(Icons.Filled.Language, null, tint = Accent, modifier = Modifier.size(14.dp))
                                Surface(
                                    shape = RoundedCornerShape(6.dp),
                                    color = Color(0xFF21262D),
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Text("preview.html", modifier = Modifier.padding(8.dp, 4.dp),
                                        fontFamily = FontFamily.Monospace, fontSize = 11.sp,
                                        color = Color(0xFF8B949E))
                                }
                            }

                            AndroidView(
                                factory = { ctx ->
                                    WebView(ctx).apply {
                                        settings.javaScriptEnabled = true
                                        settings.domStorageEnabled  = true
                                        webChromeClient = WebChromeClient()
                                        webViewClient   = WebViewClient()
                                        loadDataWithBaseURL(
                                            "https://example.com", fullHtml, "text/html", "utf-8", null
                                        )
                                    }
                                },
                                update = { wv ->
                                    wv.loadDataWithBaseURL(
                                        "https://example.com", fullHtml, "text/html", "utf-8", null
                                    )
                                },
                                modifier = Modifier.fillMaxWidth().height(220.dp)
                            )
                        }
                    }
                }
            }

            Spacer(Modifier.height(16.dp))

            // ── Quiz Button ───────────────────────────────────────────
            Button(
                onClick  = { vm.completeLesson(lesson); onQuiz() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = chColor),
                shape  = RoundedCornerShape(16.dp)
            ) {
                Icon(Icons.Filled.Quiz, null)
                Spacer(Modifier.width(10.dp))
                Text("কুইজ দাও ও XP জিতো",
                    fontWeight = FontWeight.Bold, fontSize = 17.sp)
            }

            Spacer(Modifier.height(32.dp))
        }
    }
}
