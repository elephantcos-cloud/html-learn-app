package com.htmllearn.app.ui.screens.lessons

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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.htmllearn.app.ui.AppViewModel
import com.htmllearn.app.ui.theme.*

@Composable
fun LessonDetailScreen(lessonId: String, onQuiz: () -> Unit, onBack: () -> Unit,
                       vm: AppViewModel = viewModel()) {
    val lesson = vm.getLesson(lessonId) ?: return
    val chapter = vm.chapters.find { it.id == lesson.chapterId }
    val chColor = chapter?.let { Color(it.color) } ?: Primary

    Column(Modifier.fillMaxSize().background(BgDark)) {
        // Top bar
        Surface(color = BgSurface, tonalElevation = 2.dp) {
            Row(Modifier.fillMaxWidth().padding(4.dp, 40.dp, 16.dp, 8.dp),
                verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = onBack) {
                    Icon(Icons.Filled.ArrowBack, null, tint = TextPrimary)
                }
                Column(Modifier.weight(1f)) {
                    Text(lesson.title, style = MaterialTheme.typography.titleLarge)
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text(lesson.duration, style = MaterialTheme.typography.labelSmall)
                        Text("•", style = MaterialTheme.typography.labelSmall, color = TextHint)
                        Text("+${lesson.xp} XP", style = MaterialTheme.typography.labelSmall, color = chColor)
                    }
                }
            }
        }

        Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
            // Explanation
            Card(modifier = Modifier.fillMaxWidth().padding(16.dp),
                colors = CardDefaults.cardColors(containerColor = BgCard), shape = RoundedCornerShape(16.dp)) {
                Column(Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Filled.MenuBook, null, tint = chColor, modifier = Modifier.size(18.dp))
                        Spacer(Modifier.width(8.dp))
                        Text("ব্যাখ্যা", style = MaterialTheme.typography.titleMedium, color = chColor)
                    }
                    Spacer(Modifier.height(12.dp))
                    Text(lesson.explanation, style = MaterialTheme.typography.bodyMedium, lineHeight = 24.sp)
                }
            }

            // Code Example
            Card(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF0D1117)), shape = RoundedCornerShape(16.dp)) {
                Column(Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Filled.Code, null, tint = Secondary, modifier = Modifier.size(18.dp))
                        Spacer(Modifier.width(8.dp))
                        Text("কোড উদাহরণ", style = MaterialTheme.typography.titleMedium, color = Secondary)
                    }
                    Spacer(Modifier.height(12.dp))
                    Text(lesson.codeExample,
                        fontFamily = FontFamily.Monospace, fontSize = 13.sp, color = Color(0xFFE6EDF3),
                        lineHeight = 22.sp)
                }
            }

            Spacer(Modifier.height(12.dp))

            // Quiz Button
            Button(onClick = {
                vm.completeLesson(lesson)
                onQuiz()
            }, modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp).height(52.dp),
                colors = ButtonDefaults.buttonColors(containerColor = chColor),
                shape = RoundedCornerShape(14.dp)) {
                Icon(Icons.Filled.Quiz, null)
                Spacer(Modifier.width(8.dp))
                Text("কুইজ দাও ও XP জিতো", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }

            Spacer(Modifier.height(24.dp))
        }
    }
}
