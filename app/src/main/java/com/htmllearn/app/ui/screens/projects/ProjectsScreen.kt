package com.htmllearn.app.ui.screens.projects

import android.annotation.SuppressLint
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import com.htmllearn.app.data.model.Project
import com.htmllearn.app.ui.AppViewModel
import com.htmllearn.app.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProjectsScreen(vm: AppViewModel = viewModel()) {
    val projects = vm.projects
    var selected by remember { mutableStateOf<Project?>(null) }

    if (selected != null) {
        ProjectDetailScreen(project = selected!!, onBack = { selected = null })
        return
    }

    Column(Modifier.fillMaxSize().background(BgDark)) {
        Surface(color = BgSurface, shadowElevation = 2.dp) {
            Column(Modifier.fillMaxWidth().padding(16.dp, 48.dp, 16.dp, 16.dp)) {
                Text("Projects", style = MaterialTheme.typography.headlineMedium)
                Text("হাতে-কলমে HTML project বানাও",
                    style = MaterialTheme.typography.bodySmall, color = TextSecondary)
            }
        }

        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(projects) { project ->
                val diffColor = when (project.difficulty) {
                    "সহজ" -> Accent; "মাঝারি" -> Warning; "কঠিন" -> Error; else -> Color(0xFF9C27B0)
                }
                Card(
                    colors  = CardDefaults.cardColors(containerColor = BgCard),
                    shape   = RoundedCornerShape(16.dp),
                    onClick = { selected = project }
                ) {
                    Column(Modifier.padding(16.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                Modifier.size(44.dp).background(Primary.copy(0.18f), RoundedCornerShape(10.dp)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(Icons.Filled.Code, null, tint = Primary, modifier = Modifier.size(24.dp))
                            }
                            Spacer(Modifier.width(12.dp))
                            Column(Modifier.weight(1f)) {
                                Text(project.title, style = MaterialTheme.typography.titleLarge)
                                Surface(shape = RoundedCornerShape(4.dp), color = diffColor.copy(0.18f)) {
                                    Text(project.difficulty, modifier = Modifier.padding(7.dp, 3.dp),
                                        style = MaterialTheme.typography.labelSmall, color = diffColor)
                                }
                            }
                            Icon(Icons.Filled.ChevronRight, null, tint = TextHint)
                        }
                        Spacer(Modifier.height(10.dp))
                        Text(project.description, style = MaterialTheme.typography.bodyMedium)
                        Spacer(Modifier.height(10.dp))
                        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                            project.tags.forEach { tag ->
                                Surface(shape = RoundedCornerShape(4.dp), color = BgElevated) {
                                    Text(tag, modifier = Modifier.padding(7.dp, 3.dp),
                                        style = MaterialTheme.typography.labelSmall, color = Secondary)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun ProjectDetailScreen(project: Project, onBack: () -> Unit) {
    var showCode    by remember { mutableStateOf(false) }
    var showPreview by remember { mutableStateOf(false) }

    val fullHtml = """<!DOCTYPE html>
<html><head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1">
<style>
  *{margin:0;padding:0;box-sizing:border-box}
  body{font-family:Arial,sans-serif;background:#0f0f0f;color:#fff;padding:16px}
  h1,h2,h3{color:#6C63FF;margin-bottom:8px}
  p{margin-bottom:8px;color:#ccc;line-height:1.6}
  a{color:#00BCD4}
  ul,ol{padding-left:20px;margin-bottom:8px;color:#ccc}
  li{margin-bottom:4px}
  table{width:100%;border-collapse:collapse;margin-bottom:12px}
  th{background:#6C63FF;color:#fff;padding:8px;text-align:left}
  td{padding:8px;border-bottom:1px solid #333;color:#ccc}
  form{display:flex;flex-direction:column;gap:8px}
  input,select,textarea{padding:10px;background:#1e1e1e;border:1px solid #444;border-radius:8px;color:#fff;font-size:14px}
  button{padding:12px;background:#6C63FF;color:#fff;border:none;border-radius:8px;font-size:15px;cursor:pointer}
  .card{background:#1e1e1e;padding:16px;border-radius:12px;margin-bottom:12px}
  .btn{background:#6C63FF;color:#fff;padding:10px 20px;border:none;border-radius:8px;cursor:pointer;font-size:14px}
  nav a{color:#fff;margin-right:16px;text-decoration:none}
</style></head>
<body>${project.starterCode.substringAfter("<body>").substringBefore("</body>").ifBlank { project.starterCode }}</body></html>"""

    Column(Modifier.fillMaxSize().background(BgDark)) {
        Surface(color = BgSurface, shadowElevation = 2.dp) {
            Row(Modifier.fillMaxWidth().padding(4.dp, 40.dp, 16.dp, 8.dp),
                verticalAlignment = Alignment.CenterVertically) {
                IconButton(onBack) { Icon(Icons.Filled.ArrowBack, null, tint = TextPrimary) }
                Column(Modifier.weight(1f)) {
                    Text(project.title, style = MaterialTheme.typography.titleLarge)
                    Text(project.difficulty, style = MaterialTheme.typography.labelSmall, color = Warning)
                }
            }
        }

        Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp)) {
            // Description
            Card(colors = CardDefaults.cardColors(BgCard), shape = RoundedCornerShape(14.dp)) {
                Column(Modifier.padding(16.dp)) {
                    Text(project.description, style = MaterialTheme.typography.bodyMedium)
                    Spacer(Modifier.height(12.dp))
                    // Tags
                    Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        project.tags.forEach { tag ->
                            Surface(shape = RoundedCornerShape(4.dp), color = BgElevated) {
                                Text(tag, modifier = Modifier.padding(7.dp, 3.dp),
                                    style = MaterialTheme.typography.labelSmall, color = Secondary)
                            }
                        }
                    }
                }
            }

            Spacer(Modifier.height(12.dp))

            // Hints
            Card(colors = CardDefaults.cardColors(BgCard), shape = RoundedCornerShape(14.dp)) {
                Column(Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Filled.Lightbulb, null, tint = Warning, modifier = Modifier.size(18.dp))
                        Spacer(Modifier.width(8.dp))
                        Text("Hints", style = MaterialTheme.typography.titleMedium, color = Warning)
                    }
                    Spacer(Modifier.height(10.dp))
                    project.hints.forEachIndexed { i, hint ->
                        Row(Modifier.padding(vertical = 4.dp), verticalAlignment = Alignment.Top) {
                            Surface(shape = RoundedCornerShape(6.dp), color = Warning.copy(0.15f)) {
                                Text("${i + 1}", modifier = Modifier.padding(7.dp, 3.dp),
                                    color = Warning, fontWeight = FontWeight.Bold,
                                    style = MaterialTheme.typography.labelLarge)
                            }
                            Spacer(Modifier.width(10.dp))
                            Text(hint, style = MaterialTheme.typography.bodyMedium, modifier = Modifier.weight(1f))
                        }
                    }
                }
            }

            Spacer(Modifier.height(12.dp))

            // Code + Preview card (macOS style)
            Card(
                colors = CardDefaults.cardColors(containerColor = Color(0xFF0D1117)),
                shape  = RoundedCornerShape(16.dp)
            ) {
                Column {
                    // macOS header
                    Row(
                        Modifier.fillMaxWidth().background(Color(0xFF161B22)).padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                            Box(Modifier.size(10.dp).background(Color(0xFFFF5F57), androidx.compose.foundation.shape.CircleShape))
                            Box(Modifier.size(10.dp).background(Color(0xFFFFBD2E), androidx.compose.foundation.shape.CircleShape))
                            Box(Modifier.size(10.dp).background(Color(0xFF28C840), androidx.compose.foundation.shape.CircleShape))
                        }
                        Spacer(Modifier.weight(1f))
                        Text("index.html", fontFamily = FontFamily.Monospace, fontSize = 11.sp, color = Color(0xFF8B949E))
                    }

                    // Toggle row
                    Row(
                        Modifier.fillMaxWidth().padding(12.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        // Code toggle
                        OutlinedButton(
                            onClick  = { showCode = !showCode; if (!showCode) showPreview = false },
                            modifier = Modifier.weight(1f),
                            shape    = RoundedCornerShape(10.dp),
                            colors   = ButtonDefaults.outlinedButtonColors(
                                containerColor = if (showCode) Primary.copy(0.15f) else Color.Transparent
                            ),
                            border = BorderStroke(1.dp, if (showCode) Primary else Color(0xFF30363D))
                        ) {
                            Icon(Icons.Filled.Code, null,
                                tint = if (showCode) Primary else TextSecondary,
                                modifier = Modifier.size(16.dp))
                            Spacer(Modifier.width(6.dp))
                            Text("Starter Code",
                                color = if (showCode) Primary else TextSecondary,
                                style = MaterialTheme.typography.labelLarge)
                        }

                        // Preview toggle
                        OutlinedButton(
                            onClick  = { showPreview = !showPreview; if (showPreview) showCode = true },
                            modifier = Modifier.weight(1f),
                            shape    = RoundedCornerShape(10.dp),
                            colors   = ButtonDefaults.outlinedButtonColors(
                                containerColor = if (showPreview) Accent.copy(0.15f) else Color.Transparent
                            ),
                            border = BorderStroke(1.dp, if (showPreview) Accent else Color(0xFF30363D))
                        ) {
                            Icon(Icons.Filled.Visibility, null,
                                tint = if (showPreview) Accent else TextSecondary,
                                modifier = Modifier.size(16.dp))
                            Spacer(Modifier.width(6.dp))
                            Text("Output",
                                color = if (showPreview) Accent else TextSecondary,
                                style = MaterialTheme.typography.labelLarge)
                        }
                    }

                    // Code display
                    AnimatedVisibility(visible = showCode) {
                        Column {
                            Divider(color = Color(0xFF21262D))
                            Text(
                                text       = project.starterCode,
                                modifier   = Modifier.padding(16.dp),
                                fontFamily = FontFamily.Monospace,
                                fontSize   = 12.sp,
                                color      = Color(0xFFE6EDF3),
                                lineHeight  = 20.sp
                            )
                        }
                    }

                    // Output WebView
                    AnimatedVisibility(visible = showPreview) {
                        Column {
                            Divider(color = Color(0xFF21262D))
                            // Browser bar
                            Row(
                                Modifier.fillMaxWidth().background(Color(0xFFF2F2F5)).padding(8.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Icon(Icons.Filled.Lock, null,
                                    tint = Color(0xFF555555), modifier = Modifier.size(12.dp))
                                Surface(
                                    shape = RoundedCornerShape(4.dp),
                                    color = Color.White,
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Text("preview • ${project.title.lowercase().replace(" ", "-")}.html",
                                        modifier = Modifier.padding(8.dp, 4.dp),
                                        fontFamily = FontFamily.Monospace, fontSize = 10.sp,
                                        color = Color(0xFF555555))
                                }
                            }
                            AndroidView(
                                factory = { ctx ->
                                    WebView(ctx).apply {
                                        settings.javaScriptEnabled = true
                                        settings.domStorageEnabled  = true
                                        webViewClient = WebViewClient()
                                        loadDataWithBaseURL(
                                            "https://example.com", fullHtml, "text/html", "utf-8", null
                                        )
                                    }
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(320.dp)
                                    .clip(RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp))
                            )
                        }
                    }
                }
            }

            Spacer(Modifier.height(24.dp))
        }
    }
}
