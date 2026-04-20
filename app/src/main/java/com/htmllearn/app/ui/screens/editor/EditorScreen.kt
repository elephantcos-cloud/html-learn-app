package com.htmllearn.app.ui.screens.editor

import android.annotation.SuppressLint
import android.webkit.WebView
import android.webkit.WebViewClient
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
import androidx.compose.ui.unit.*
import androidx.compose.ui.viewinterop.AndroidView
import com.htmllearn.app.ui.theme.*

private val starterCode = """<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <style>
    body {
      background: #0f0f0f;
      color: white;
      font-family: Arial, sans-serif;
      padding: 20px;
    }
    h1 { color: #6C63FF; }
    .card {
      background: #1e1e1e;
      padding: 20px;
      border-radius: 12px;
      margin-top: 16px;
    }
    button {
      background: #6C63FF;
      color: white;
      padding: 10px 20px;
      border: none;
      border-radius: 8px;
      cursor: pointer;
      margin-top: 12px;
    }
  </style>
</head>
<body>
  <h1>Live Editor!</h1>
  <div class="card">
    <p>এখানে কোড লিখো এবং output দেখো!</p>
    <button onclick="alert('কাজ করছে!')">
      Click করো
    </button>
  </div>
</body>
</html>"""

private val snippets = listOf(
    "h1" to "<h1>শিরোনাম</h1>",
    "p" to "<p>paragraph text</p>",
    "div" to "<div class=\"box\">\n  content\n</div>",
    "img" to "<img src=\"\" alt=\"ছবি\" width=\"200\">",
    "a" to "<a href=\"#\">link</a>",
    "button" to "<button onclick=\"alert('clicked!')\">Click</button>",
    "ul" to "<ul>\n  <li>item 1</li>\n  <li>item 2</li>\n</ul>",
    "table" to "<table border=\"1\">\n  <tr><th>নাম</th><th>বয়স</th></tr>\n  <tr><td>রাহেলা</td><td>18</td></tr>\n</table>",
    "form" to "<form>\n  <input type=\"text\" placeholder=\"নাম\">\n  <button type=\"submit\">জমা</button>\n</form>",
    "style" to "<style>\n  body { background: #111; color: white; }\n</style>"
)

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun EditorScreen() {
    var code by remember { mutableStateOf(starterCode) }
    var previewHtml by remember { mutableStateOf(starterCode) }
    var showPreview by remember { mutableStateOf(false) }
    var webViewRef by remember { mutableStateOf<WebView?>(null) }

    Column(Modifier.fillMaxSize().background(BgDark)) {
        // Top Bar
        Surface(color = BgSurface, tonalElevation = 2.dp) {
            Column(Modifier.fillMaxWidth().padding(16.dp, 48.dp, 16.dp, 8.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Filled.Code, null, tint = Secondary, modifier = Modifier.size(22.dp))
                    Spacer(Modifier.width(8.dp))
                    Text("Live Code Editor", style = MaterialTheme.typography.headlineSmall)
                    Spacer(Modifier.weight(1f))
                    // Toggle Editor/Preview
                    Row(Modifier.background(BgCard, RoundedCornerShape(8.dp)).padding(2.dp)) {
                        listOf("কোড" to false, "Output" to true).forEach { (label, preview) ->
                            Surface(shape = RoundedCornerShape(6.dp),
                                color = if (showPreview == preview) Primary else Color.Transparent,
                                modifier = Modifier.clickable { showPreview = preview }) {
                                Text(label, modifier = Modifier.padding(10.dp, 6.dp),
                                    color = if (showPreview == preview) TextPrimary else TextSecondary,
                                    style = MaterialTheme.typography.labelLarge)
                            }
                        }
                    }
                }
                Spacer(Modifier.height(8.dp))
                // Run Button
                Button(onClick = { previewHtml = code; showPreview = true },
                    modifier = Modifier.fillMaxWidth().height(42.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Secondary),
                    shape = RoundedCornerShape(10.dp)) {
                    Icon(Icons.Filled.PlayArrow, null, modifier = Modifier.size(18.dp))
                    Spacer(Modifier.width(6.dp))
                    Text("Run করো", style = MaterialTheme.typography.titleMedium)
                }
            }
        }

        // Snippets Row
        Row(Modifier.fillMaxWidth().background(BgSurface).horizontalScroll(rememberScrollState()).padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            snippets.forEach { (label, snippet) ->
                Surface(shape = RoundedCornerShape(6.dp), color = BgElevated,
                    modifier = Modifier.clickable { code += "\n$snippet" }) {
                    Text("<$label>", modifier = Modifier.padding(8.dp, 4.dp),
                        style = MaterialTheme.typography.labelLarge, color = Secondary,
                        fontFamily = FontFamily.Monospace)
                }
            }
        }

        if (!showPreview) {
            // Code Editor
            TextField(
                value = code, onValueChange = { code = it },
                modifier = Modifier.fillMaxSize().padding(8.dp),
                textStyle = androidx.compose.ui.text.TextStyle(
                    fontFamily = FontFamily.Monospace, fontSize = 13.sp, color = Color(0xFFE6EDF3), lineHeight = 22.sp
                ),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor   = Color(0xFF0D1117),
                    unfocusedContainerColor = Color(0xFF0D1117),
                    focusedIndicatorColor   = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = Primary
                )
            )
        } else {
            // WebView Preview
            AndroidView(factory = { ctx ->
                WebView(ctx).apply {
                    settings.javaScriptEnabled = true
                    settings.domStorageEnabled = true
                    webViewClient = WebViewClient()
                    loadDataWithBaseURL(null, previewHtml, "text/html", "utf-8", null)
                    webViewRef = this
                }
            }, update = { wv ->
                wv.loadDataWithBaseURL(null, previewHtml, "text/html", "utf-8", null)
            }, modifier = Modifier.fillMaxSize())
        }
    }
}
