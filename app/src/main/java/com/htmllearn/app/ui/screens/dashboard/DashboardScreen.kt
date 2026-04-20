package com.htmllearn.app.ui.screens.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.htmllearn.app.ui.AppViewModel
import com.htmllearn.app.ui.navigation.Screen
import com.htmllearn.app.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(onNavigate: (String) -> Unit, vm: AppViewModel = viewModel()) {
    val stats    by vm.stats.collectAsStateWithLifecycle()
    val progress by vm.progress.collectAsStateWithLifecycle()
    val chapters  = vm.chapters

    val completedIds  = progress.filter { it.completed }.map { it.lessonId }.toSet()
    val totalLessons  = chapters.sumOf { it.lessons.size }
    val overallProg   = if (totalLessons > 0) completedIds.size.toFloat() / totalLessons else 0f

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BgDark)
            .verticalScroll(rememberScrollState())
    ) {
        // ── Header ────────────────────────────────────────────────────
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.linearGradient(
                        listOf(Color(0xFF1A1040), Color(0xFF0F0F2A)),
                        Offset.Zero, Offset(Float.POSITIVE_INFINITY, 300f)
                    )
                )
                .padding(24.dp, 48.dp, 24.dp, 32.dp)
        ) {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Column(Modifier.weight(1f)) {
                        Text("স্বাগতম,", style = MaterialTheme.typography.bodyMedium, color = TextSecondary)
                        Text(stats.name, style = MaterialTheme.typography.headlineMedium)
                    }
                    Surface(shape = CircleShape, color = Primary.copy(0.2f), modifier = Modifier.size(48.dp)) {
                        Box(contentAlignment = Alignment.Center) {
                            Text("${stats.level}", style = MaterialTheme.typography.headlineSmall,
                                color = Primary, fontWeight = FontWeight.ExtraBold)
                        }
                    }
                }
                Spacer(Modifier.height(20.dp))
                val (earned, needed) = vm.xpProgress(stats.totalXp)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Level ${stats.level}", style = MaterialTheme.typography.labelLarge, color = Primary)
                    Spacer(Modifier.weight(1f))
                    Text("$earned / $needed XP", style = MaterialTheme.typography.labelSmall, color = TextSecondary)
                }
                Spacer(Modifier.height(6.dp))
                val xpProg = if (needed > 0) (earned.toFloat() / needed).coerceIn(0f, 1f) else 0f
                LinearProgressIndicator(
                    progress    = xpProg,
                    modifier    = Modifier.fillMaxWidth().height(8.dp).clip(RoundedCornerShape(4.dp)),
                    color       = Primary,
                    trackColor  = BgElevated
                )
            }
        }

        Spacer(Modifier.height(16.dp))

        // ── Stats ─────────────────────────────────────────────────────
        Row(Modifier.fillMaxWidth().padding(horizontal = 16.dp), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            StatCard("${stats.streak}",           "দিনের ধারা",  Icons.Filled.Whatshot,     Warning, Modifier.weight(1f))
            StatCard("${stats.totalXp}",          "মোট XP",      Icons.Filled.Star,          Gold,    Modifier.weight(1f))
            StatCard("${stats.lessonsCompleted}",  "পাঠ সম্পন্ন", Icons.Filled.CheckCircle,   Accent,  Modifier.weight(1f))
        }

        Spacer(Modifier.height(16.dp))

        // ── Daily Goal ────────────────────────────────────────────────
        DailyGoalCard(stats.todayStudyMinutes, stats.studyGoalMinutes)

        Spacer(Modifier.height(16.dp))

        // ── Overall Progress ──────────────────────────────────────────
        Card(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            colors   = CardDefaults.cardColors(containerColor = BgCard),
            shape    = RoundedCornerShape(16.dp)
        ) {
            Column(Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Filled.TrendingUp, null, tint = Primary, modifier = Modifier.size(20.dp))
                    Spacer(Modifier.width(8.dp))
                    Text("সামগ্রিক অগ্রগতি", style = MaterialTheme.typography.titleMedium)
                    Spacer(Modifier.weight(1f))
                    Text("${(overallProg * 100).toInt()}%", style = MaterialTheme.typography.headlineSmall, color = Primary)
                }
                Spacer(Modifier.height(12.dp))
                LinearProgressIndicator(
                    progress   = overallProg,
                    modifier   = Modifier.fillMaxWidth().height(10.dp).clip(RoundedCornerShape(5.dp)),
                    color      = Accent,
                    trackColor = BgElevated
                )
                Spacer(Modifier.height(8.dp))
                Text("${completedIds.size} / $totalLessons টি পাঠ সম্পন্ন", style = MaterialTheme.typography.bodySmall)
            }
        }

        Spacer(Modifier.height(16.dp))

        // ── Quick Actions ─────────────────────────────────────────────
        Text("দ্রুত শুরু করুন", style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(horizontal = 16.dp))
        Spacer(Modifier.height(10.dp))
        Row(Modifier.fillMaxWidth().padding(horizontal = 16.dp), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            QuickActionCard("পাঠ দেখুন",  Icons.Filled.MenuBook,    Primary,   Modifier.weight(1f)) { onNavigate(Screen.Chapters.route) }
            QuickActionCard("কোড লিখুন", Icons.Filled.Code,         Secondary, Modifier.weight(1f)) { onNavigate(Screen.Editor.route) }
        }
        Spacer(Modifier.height(8.dp))
        Row(Modifier.fillMaxWidth().padding(horizontal = 16.dp), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            QuickActionCard("Tag Reference", Icons.Filled.LibraryBooks, Warning, Modifier.weight(1f)) { onNavigate(Screen.Reference.route) }
            QuickActionCard("Projects",      Icons.Filled.Folder,       Accent,  Modifier.weight(1f)) { onNavigate(Screen.Projects.route) }
        }

        Spacer(Modifier.height(16.dp))

        // ── Chapter Progress ──────────────────────────────────────────
        Text("চ্যাপ্টার অগ্রগতি", style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(horizontal = 16.dp))
        Spacer(Modifier.height(10.dp))

        chapters.forEach { chapter ->
            val chCompleted = completedIds.count { id -> chapter.lessons.any { it.id == id } }
            val chTotal     = chapter.lessons.size
            val chProg      = if (chTotal > 0) chCompleted.toFloat() / chTotal else 0f

            Card(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 4.dp),
                colors   = CardDefaults.cardColors(containerColor = BgCard),
                shape    = RoundedCornerShape(12.dp),
                onClick  = { onNavigate(Screen.Chapters.route) }
            ) {
                Row(Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        Modifier.size(40.dp).background(Color(chapter.color).copy(0.2f), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("${chapters.indexOf(chapter) + 1}", color = Color(chapter.color), fontWeight = FontWeight.Bold)
                    }
                    Spacer(Modifier.width(12.dp))
                    Column(Modifier.weight(1f)) {
                        Text(chapter.title, style = MaterialTheme.typography.titleMedium)
                        Spacer(Modifier.height(4.dp))
                        LinearProgressIndicator(
                            progress   = chProg,
                            modifier   = Modifier.fillMaxWidth().height(4.dp).clip(RoundedCornerShape(2.dp)),
                            color      = Color(chapter.color),
                            trackColor = BgElevated
                        )
                    }
                    Spacer(Modifier.width(8.dp))
                    Text("$chCompleted/$chTotal", style = MaterialTheme.typography.bodySmall)
                }
            }
        }
        Spacer(Modifier.height(24.dp))
    }
}

@Composable
fun StatCard(value: String, label: String, icon: ImageVector, color: Color, modifier: Modifier = Modifier) {
    Card(modifier = modifier, colors = CardDefaults.cardColors(containerColor = BgCard), shape = RoundedCornerShape(14.dp)) {
        Column(Modifier.padding(12.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(icon, null, tint = color, modifier = Modifier.size(22.dp))
            Spacer(Modifier.height(6.dp))
            Text(value, style = MaterialTheme.typography.headlineSmall, color = color, fontWeight = FontWeight.ExtraBold)
            Text(label, style = MaterialTheme.typography.labelSmall)
        }
    }
}

@Composable
fun DailyGoalCard(todayMins: Int, goalMins: Int) {
    val prog = if (goalMins > 0) (todayMins.toFloat() / goalMins).coerceIn(0f, 1f) else 0f
    Card(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
        colors   = CardDefaults.cardColors(containerColor = BgCard),
        shape    = RoundedCornerShape(16.dp)
    ) {
        Column(Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Filled.Timer, null, tint = Secondary, modifier = Modifier.size(20.dp))
                Spacer(Modifier.width(8.dp))
                Text("আজকের লক্ষ্য", style = MaterialTheme.typography.titleMedium)
                Spacer(Modifier.weight(1f))
                Text("$todayMins / $goalMins মিনিট", style = MaterialTheme.typography.bodySmall, color = Secondary)
            }
            Spacer(Modifier.height(10.dp))
            LinearProgressIndicator(
                progress   = prog,
                modifier   = Modifier.fillMaxWidth().height(8.dp).clip(RoundedCornerShape(4.dp)),
                color      = Secondary,
                trackColor = BgElevated
            )
            if (todayMins >= goalMins) {
                Spacer(Modifier.height(6.dp))
                Text("আজকের লক্ষ্য পূরণ হয়েছে!", style = MaterialTheme.typography.bodySmall, color = Accent)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuickActionCard(title: String, icon: ImageVector, color: Color, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Card(modifier = modifier, colors = CardDefaults.cardColors(containerColor = BgCard),
        shape = RoundedCornerShape(14.dp), onClick = onClick) {
        Row(Modifier.padding(14.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(Modifier.size(36.dp).background(color.copy(0.2f), RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center) {
                Icon(icon, null, tint = color, modifier = Modifier.size(20.dp))
            }
            Spacer(Modifier.width(10.dp))
            Text(title, style = MaterialTheme.typography.titleMedium, color = TextPrimary)
        }
    }
}
