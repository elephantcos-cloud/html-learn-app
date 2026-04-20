package com.htmllearn.app.ui.screens.quiz

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.htmllearn.app.ui.AppViewModel
import com.htmllearn.app.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizScreen(lessonId: String, onDone: () -> Unit, vm: AppViewModel = viewModel()) {
    val lesson  = vm.getLesson(lessonId) ?: return
    val quizzes = lesson.quizzes
    if (quizzes.isEmpty()) { onDone(); return }

    var currentIdx  by remember { mutableIntStateOf(0) }
    var selected    by remember { mutableStateOf<Int?>(null) }
    var showResult  by remember { mutableStateOf(false) }
    var score       by remember { mutableIntStateOf(0) }
    var quizDone    by remember { mutableStateOf(false) }

    val quiz      = quizzes[currentIdx]
    val isCorrect = selected == quiz.correctIndex

    if (quizDone) {
        QuizResultScreen(score = score, total = quizzes.size, onDone = {
            vm.saveQuizResult(lessonId, score, quizzes.size)
            onDone()
        })
        return
    }

    Column(Modifier.fillMaxSize().background(BgDark).padding(top = 48.dp)) {

        // Progress bar
        Column(Modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
            Text("প্রশ্ন ${currentIdx + 1} / ${quizzes.size}",
                style = MaterialTheme.typography.labelLarge, color = Primary)
            Spacer(Modifier.height(6.dp))
            LinearProgressIndicator(
                progress   = (currentIdx + 1).toFloat() / quizzes.size,
                modifier   = Modifier.fillMaxWidth().height(6.dp).clip(RoundedCornerShape(3.dp)),
                color      = Primary,
                trackColor = BgElevated
            )
        }
        Spacer(Modifier.height(24.dp))

        // Question
        Card(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            colors   = CardDefaults.cardColors(containerColor = BgCard),
            shape    = RoundedCornerShape(16.dp)
        ) {
            Text(quiz.question, style = MaterialTheme.typography.headlineSmall,
                lineHeight = 30.sp, modifier = Modifier.padding(16.dp))
        }
        Spacer(Modifier.height(20.dp))

        // Options
        quiz.options.forEachIndexed { idx, option ->
            val bgColor = when {
                !showResult -> if (selected == idx) Primary.copy(0.2f) else BgCard
                idx == quiz.correctIndex -> Accent.copy(0.2f)
                idx == selected && !isCorrect -> Error.copy(0.2f)
                else -> BgCard
            }
            val borderColor = when {
                !showResult -> if (selected == idx) Primary else Color.Transparent
                idx == quiz.correctIndex -> Accent
                idx == selected && !isCorrect -> Error
                else -> Color.Transparent
            }
            Card(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 5.dp),
                colors   = CardDefaults.cardColors(containerColor = bgColor),
                shape    = RoundedCornerShape(12.dp),
                border   = androidx.compose.foundation.BorderStroke(1.5.dp, borderColor),
                onClick  = { if (!showResult) { selected = idx } }
            ) {
                Row(Modifier.padding(14.dp), verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        Modifier.size(28.dp).background(BgElevated, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(('A' + idx).toString(), fontWeight = FontWeight.Bold,
                            color = if (selected == idx && !showResult) Primary else TextSecondary)
                    }
                    Spacer(Modifier.width(12.dp))
                    Text(option, style = MaterialTheme.typography.bodyMedium, color = TextPrimary)
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        // Explanation
        if (showResult) {
            Card(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                colors   = CardDefaults.cardColors(
                    containerColor = if (isCorrect) Accent.copy(0.1f) else Error.copy(0.1f)
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Row(Modifier.padding(12.dp), verticalAlignment = Alignment.Top) {
                    Icon(
                        if (isCorrect) Icons.Filled.CheckCircle else Icons.Filled.Cancel,
                        null, tint = if (isCorrect) Accent else Error,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(Modifier.width(8.dp))
                    Column {
                        Text(
                            if (isCorrect) "সঠিক উত্তর!" else "ভুল উত্তর!",
                            fontWeight = FontWeight.Bold,
                            color = if (isCorrect) Accent else Error
                        )
                        Spacer(Modifier.height(4.dp))
                        Text(quiz.explanation, style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
            Spacer(Modifier.height(16.dp))
        }

        Spacer(Modifier.weight(1f))

        // Action button
        Button(
            onClick = {
                if (!showResult && selected != null) {
                    showResult = true
                    if (selected == quiz.correctIndex) score++
                } else if (showResult) {
                    if (currentIdx + 1 < quizzes.size) {
                        currentIdx++; selected = null; showResult = false
                    } else quizDone = true
                }
            },
            modifier = Modifier.fillMaxWidth().padding(16.dp).height(52.dp),
            enabled  = selected != null || showResult,
            colors   = ButtonDefaults.buttonColors(containerColor = Primary),
            shape    = RoundedCornerShape(14.dp)
        ) {
            Text(
                when {
                    !showResult && selected != null -> "উত্তর দেখো"
                    showResult && currentIdx + 1 < quizzes.size -> "পরের প্রশ্ন"
                    showResult -> "ফলাফল দেখো"
                    else -> "বিকল্প বেছে নাও"
                },
                fontWeight = FontWeight.Bold, fontSize = 16.sp
            )
        }
    }
}

@Composable
fun QuizResultScreen(score: Int, total: Int, onDone: () -> Unit) {
    val pct   = if (total > 0) score * 100 / total else 0
    val color = when {
        pct >= 80 -> Accent
        pct >= 50 -> Warning
        else -> Error
    }
    Column(
        Modifier.fillMaxSize().background(BgDark),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(Modifier.size(120.dp).background(color.copy(0.2f), CircleShape),
            contentAlignment = Alignment.Center) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("$pct%", style = MaterialTheme.typography.headlineLarge,
                    color = color, fontWeight = FontWeight.ExtraBold)
                Text("$score/$total", style = MaterialTheme.typography.bodySmall)
            }
        }
        Spacer(Modifier.height(24.dp))
        Text(
            when {
                pct >= 80 -> "চমৎকার! তুমি অনেক ভালো করেছো!"
                pct >= 60 -> "বেশ ভালো! আরেকটু চেষ্টা করো।"
                pct >= 40 -> "ঠিক আছে। পাঠটি আরেকবার পড়ো।"
                else -> "হতাশ হয়ো না। আবার চেষ্টা করো!"
            },
            style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold, color = color
        )
        Spacer(Modifier.height(32.dp))
        Button(
            onClick  = onDone,
            modifier = Modifier.padding(horizontal = 32.dp).fillMaxWidth().height(52.dp),
            colors   = ButtonDefaults.buttonColors(containerColor = Primary),
            shape    = RoundedCornerShape(14.dp)
        ) {
            Text("পাঠে ফিরে যাও", fontWeight = FontWeight.Bold, fontSize = 16.sp)
        }
    }
}
