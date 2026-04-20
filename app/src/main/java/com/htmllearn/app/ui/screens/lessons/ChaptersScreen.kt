package com.htmllearn.app.ui.screens.lessons

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.htmllearn.app.ui.AppViewModel
import com.htmllearn.app.ui.theme.*

@Composable
fun ChaptersScreen(onLessonClick: (String) -> Unit, vm: AppViewModel = viewModel()) {
    val chapters = vm.chapters
    val progress by vm.progress.collectAsStateWithLifecycle()
    val completedIds = progress.filter { it.completed }.map { it.lessonId }.toSet()
    var expandedChapter by remember { mutableStateOf<String?>(chapters.firstOrNull()?.id) }

    Column(Modifier.fillMaxSize().background(BgDark)) {
        // Top bar
        Surface(color = BgSurface, tonalElevation = 2.dp) {
            Column(Modifier.fillMaxWidth().padding(16.dp, 48.dp, 16.dp, 12.dp)) {
                Text("পাঠসমূহ", style = MaterialTheme.typography.headlineMedium)
                Text("${completedIds.size} টি পাঠ সম্পন্ন", style = MaterialTheme.typography.bodySmall, color = Primary)
            }
        }

        LazyColumn(contentPadding = PaddingValues(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            items(chapters) { chapter ->
                val isExpanded = expandedChapter == chapter.id
                val chCompleted = completedIds.count { id -> chapter.lessons.any { it.id == id } }
                val chColor = Color(chapter.color)

                Card(colors = CardDefaults.cardColors(containerColor = BgCard),
                    shape = RoundedCornerShape(16.dp)) {
                    Column {
                        // Chapter header
                        Row(modifier = Modifier.fillMaxWidth().clickable {
                            expandedChapter = if (isExpanded) null else chapter.id
                        }.padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically) {
                            Box(Modifier.size(48.dp).background(chColor.copy(0.2f), CircleShape),
                                contentAlignment = Alignment.Center) {
                                Text("${chapters.indexOf(chapter)+1}", color = chColor, fontWeight = FontWeight.ExtraBold, fontSize = 18.sp)
                            }
                            Spacer(Modifier.width(12.dp))
                            Column(Modifier.weight(1f)) {
                                Text(chapter.title, style = MaterialTheme.typography.titleLarge)
                                Text(chapter.description, style = MaterialTheme.typography.bodySmall)
                                Spacer(Modifier.height(4.dp))
                                Text("$chCompleted/${chapter.lessons.size} পাঠ", style = MaterialTheme.typography.labelSmall, color = chColor)
                            }
                            Icon(if (isExpanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                                null, tint = TextSecondary)
                        }

                        // Chapter progress bar
                        if (chapter.lessons.isNotEmpty()) {
                            LinearProgressIndicator(
                                progress = { chCompleted.toFloat()/chapter.lessons.size },
                                modifier = Modifier.fillMaxWidth().height(3.dp),
                                color = chColor, trackColor = BgElevated
                            )
                        }

                        // Lessons list
                        if (isExpanded) {
                            chapter.lessons.forEachIndexed { idx, lesson ->
                                val isCompleted = lesson.id in completedIds
                                Divider(color = Divider, thickness = 0.5.dp)
                                Row(modifier = Modifier.fillMaxWidth().clickable { onLessonClick(lesson.id) }
                                    .padding(horizontal = 16.dp, vertical = 12.dp),
                                    verticalAlignment = Alignment.CenterVertically) {
                                    Box(Modifier.size(32.dp).background(
                                        if (isCompleted) Accent.copy(0.2f) else BgElevated, CircleShape),
                                        contentAlignment = Alignment.Center) {
                                        if (isCompleted) Icon(Icons.Filled.Check, null, tint = Accent, modifier = Modifier.size(16.dp))
                                        else Text("${idx+1}", style = MaterialTheme.typography.labelLarge, color = TextSecondary)
                                    }
                                    Spacer(Modifier.width(12.dp))
                                    Column(Modifier.weight(1f)) {
                                        Text(lesson.title, style = MaterialTheme.typography.titleMedium,
                                            color = if (isCompleted) TextSecondary else TextPrimary)
                                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                            Text(lesson.duration, style = MaterialTheme.typography.labelSmall)
                                            Text("•", style = MaterialTheme.typography.labelSmall, color = TextHint)
                                            Text("+${lesson.xp} XP", style = MaterialTheme.typography.labelSmall, color = chColor)
                                        }
                                    }
                                    Icon(Icons.Filled.ChevronRight, null, tint = TextHint, modifier = Modifier.size(18.dp))
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
