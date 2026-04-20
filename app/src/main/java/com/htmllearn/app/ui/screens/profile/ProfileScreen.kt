package com.htmllearn.app.ui.screens.profile

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.htmllearn.app.ui.AppViewModel
import com.htmllearn.app.ui.theme.*

@Composable
fun ProfileScreen(onSettings: () -> Unit, vm: AppViewModel = viewModel()) {
    val stats       by vm.stats.collectAsStateWithLifecycle()
    val badges      by vm.badges.collectAsStateWithLifecycle()
    val badgeDefs    = vm.badgeDefs
    val earnedIds    = badges.map { it.id }.toSet()

    Column(Modifier.fillMaxSize().background(BgDark).verticalScroll(rememberScrollState())) {
        // Header
        Box(
            Modifier.fillMaxWidth()
                .background(Brush.verticalGradient(listOf(Color(0xFF1A1040), BgDark)))
                .padding(24.dp, 48.dp, 24.dp, 24.dp)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
                Box(Modifier.size(80.dp).background(Primary.copy(0.25f), CircleShape),
                    contentAlignment = Alignment.Center) {
                    Text(stats.name.take(1), style = MaterialTheme.typography.headlineLarge,
                        color = Primary, fontWeight = FontWeight.ExtraBold)
                }
                Spacer(Modifier.height(12.dp))
                Text(stats.name, style = MaterialTheme.typography.headlineMedium)
                Text("Level ${stats.level} শিক্ষার্থী", style = MaterialTheme.typography.bodyMedium, color = Primary)
                Spacer(Modifier.height(16.dp))
                val (earned, needed) = vm.xpProgress(stats.totalXp)
                val xpProg = if (needed > 0) earned.toFloat() / needed else 0f
                Text("${stats.totalXp} XP", style = MaterialTheme.typography.titleMedium, color = Gold)
                Spacer(Modifier.height(6.dp))
                LinearProgressIndicator(
                    progress   = xpProg,
                    modifier   = Modifier.fillMaxWidth(0.8f).height(8.dp).clip(RoundedCornerShape(4.dp)),
                    color      = Gold,
                    trackColor = BgElevated
                )
                Text("$earned/$needed XP → Level ${stats.level + 1}",
                    style = MaterialTheme.typography.labelSmall, color = TextHint)
            }
            IconButton(onSettings, modifier = Modifier.align(Alignment.TopEnd)) {
                Icon(Icons.Filled.Settings, null, tint = TextSecondary)
            }
        }

        // Stats
        Text("পরিসংখ্যান", style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(16.dp, 8.dp, 16.dp, 8.dp))
        val statItems = listOf(
            Triple("${stats.streak}",           "দিনের ধারা",  Warning),
            Triple("${stats.lessonsCompleted}",  "পাঠ সম্পন্ন", Accent),
            Triple("${stats.quizAccuracy}%",     "Quiz সঠিকতা", Secondary),
            Triple("${earnedIds.size}",           "ব্যাজ অর্জিত",Gold)
        )
        Row(Modifier.fillMaxWidth().padding(horizontal = 16.dp), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            statItems.take(2).forEach { (v, l, c) ->
                Card(Modifier.weight(1f), colors = CardDefaults.cardColors(BgCard), shape = RoundedCornerShape(14.dp)) {
                    Column(Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(v, style = MaterialTheme.typography.headlineMedium, color = c, fontWeight = FontWeight.ExtraBold)
                        Text(l, style = MaterialTheme.typography.labelSmall)
                    }
                }
            }
        }
        Spacer(Modifier.height(10.dp))
        Row(Modifier.fillMaxWidth().padding(horizontal = 16.dp), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            statItems.takeLast(2).forEach { (v, l, c) ->
                Card(Modifier.weight(1f), colors = CardDefaults.cardColors(BgCard), shape = RoundedCornerShape(14.dp)) {
                    Column(Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(v, style = MaterialTheme.typography.headlineMedium, color = c, fontWeight = FontWeight.ExtraBold)
                        Text(l, style = MaterialTheme.typography.labelSmall)
                    }
                }
            }
        }

        Spacer(Modifier.height(20.dp))

        // Badges
        Text("ব্যাজ সংগ্রহ (${earnedIds.size}/${badgeDefs.size})",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(horizontal = 16.dp))
        Spacer(Modifier.height(10.dp))
        LazyRow(contentPadding = PaddingValues(horizontal = 16.dp), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            items(badgeDefs) { badge ->
                val earned = badge.id in earnedIds
                Card(
                    Modifier.width(100.dp),
                    colors = CardDefaults.cardColors(containerColor = if (earned) Gold.copy(0.1f) else BgCard),
                    border = if (earned) BorderStroke(1.dp, Gold.copy(0.5f)) else null,
                    shape  = RoundedCornerShape(12.dp)
                ) {
                    Column(Modifier.padding(10.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                        Box(Modifier.size(40.dp).background(
                            if (earned) Gold.copy(0.2f) else BgElevated, CircleShape),
                            contentAlignment = Alignment.Center) {
                            Text(if (earned) "★" else "○",
                                color = if (earned) Gold else TextHint,
                                style = MaterialTheme.typography.headlineSmall)
                        }
                        Spacer(Modifier.height(6.dp))
                        Text(badge.title, style = MaterialTheme.typography.labelSmall,
                            color = if (earned) Gold else TextHint,
                            maxLines = 2, textAlign = TextAlign.Center)
                    }
                }
            }
        }

        Spacer(Modifier.height(20.dp))

        // Chapter list
        Text("চ্যাপ্টার অগ্রগতি", style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(horizontal = 16.dp))
        Spacer(Modifier.height(8.dp))
        vm.chapters.forEach { ch ->
            val chColor = Color(ch.color)
            Card(Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 4.dp),
                colors = CardDefaults.cardColors(BgCard), shape = RoundedCornerShape(12.dp)) {
                Row(Modifier.padding(14.dp), verticalAlignment = Alignment.CenterVertically) {
                    Box(Modifier.size(10.dp).background(chColor, CircleShape))
                    Spacer(Modifier.width(10.dp))
                    Text(ch.title, style = MaterialTheme.typography.titleMedium, modifier = Modifier.weight(1f))
                    Text("${ch.lessons.size} পাঠ", style = MaterialTheme.typography.labelSmall, color = chColor)
                }
            }
        }
        Spacer(Modifier.height(24.dp))
    }
}
