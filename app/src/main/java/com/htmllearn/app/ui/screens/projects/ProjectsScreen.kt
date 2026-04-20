package com.htmllearn.app.ui.screens.projects

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.htmllearn.app.ui.AppViewModel
import com.htmllearn.app.ui.theme.*

@Composable
fun ProjectsScreen(vm: AppViewModel = viewModel()) {
    val projects = vm.projects
    var selectedProject by remember { mutableStateOf<com.htmllearn.app.data.model.Project?>(null) }

    if (selectedProject != null) {
        ProjectDetailScreen(project = selectedProject!!, onBack = { selectedProject = null })
        return
    }

    Column(Modifier.fillMaxSize().background(BgDark)) {
        Surface(color = BgSurface, tonalElevation = 2.dp) {
            Column(Modifier.fillMaxWidth().padding(16.dp, 48.dp, 16.dp, 16.dp)) {
                Text("Projects", style = MaterialTheme.typography.headlineMedium)
                Text("হাতে-কলমে HTML project বানাও", style = MaterialTheme.typography.bodySmall, color = TextSecondary)
            }
        }

        LazyColumn(contentPadding = PaddingValues(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(projects) { project ->
                val diffColor = when(project.difficulty) {
                    "সহজ" -> Accent
                    "মাঝারি" -> Warning
                    "কঠিন" -> Error
                    else -> Color(0xFF9C27B0)
                }
                Card(colors = CardDefaults.cardColors(containerColor = BgCard),
                    shape = RoundedCornerShape(16.dp),
                    onClick = { selectedProject = project }) {
                    Column(Modifier.padding(16.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(Modifier.size(44.dp).background(Primary.copy(0.2f), RoundedCornerShape(10.dp)),
                                contentAlignment = Alignment.Center) {
                                Icon(Icons.Filled.Code, null, tint = Primary, modifier = Modifier.size(24.dp))
                            }
                            Spacer(Modifier.width(12.dp))
                            Column(Modifier.weight(1f)) {
                                Text(project.title, style = MaterialTheme.typography.titleLarge)
                                Surface(shape = RoundedCornerShape(4.dp), color = diffColor.copy(0.2f)) {
                                    Text(project.difficulty, modifier = Modifier.padding(6.dp, 2.dp),
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

@Composable
fun ProjectDetailScreen(project: com.htmllearn.app.data.model.Project, onBack: () -> Unit) {
    var showCode by remember { mutableStateOf(false) }
    Column(Modifier.fillMaxSize().background(BgDark)) {
        Surface(color = BgSurface, tonalElevation = 2.dp) {
            Row(Modifier.fillMaxWidth().padding(4.dp, 40.dp, 16.dp, 8.dp), verticalAlignment = Alignment.CenterVertically) {
                IconButton(onBack) { Icon(Icons.Filled.ArrowBack, null, tint = TextPrimary) }
                Text(project.title, style = MaterialTheme.typography.titleLarge)
            }
        }

        Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp)) {
            Text(project.description, style = MaterialTheme.typography.bodyMedium)
            Spacer(Modifier.height(16.dp))
            Text("💡 Hints:", style = MaterialTheme.typography.titleMedium, color = Warning)
            Spacer(Modifier.height(8.dp))
            project.hints.forEachIndexed { i, hint ->
                Row(Modifier.padding(vertical = 4.dp)) {
                    Text("${i+1}.", color = Primary, style = MaterialTheme.typography.titleMedium)
                    Spacer(Modifier.width(8.dp))
                    Text(hint, style = MaterialTheme.typography.bodyMedium)
                }
            }
            Spacer(Modifier.height(16.dp))
            Button(onClick = { showCode = !showCode }, modifier = Modifier.fillMaxWidth().height(48.dp),
                colors = ButtonDefaults.buttonColors(containerColor = if (showCode) BgElevated else Primary),
                shape = RoundedCornerShape(12.dp)) {
                Icon(Icons.Filled.Code, null)
                Spacer(Modifier.width(8.dp))
                Text(if (showCode) "Starter Code লুকাও" else "Starter Code দেখো")
            }
            if (showCode) {
                Spacer(Modifier.height(12.dp))
                Card(colors = CardDefaults.cardColors(containerColor = Color(0xFF0D1117)), shape = RoundedCornerShape(12.dp)) {
                    Text(project.starterCode, modifier = Modifier.padding(16.dp),
                        fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace,
                        fontSize = 12.sp, color = Color(0xFFE6EDF3), lineHeight = 20.sp)
                }
            }
        }
    }
}
