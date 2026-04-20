package com.htmllearn.app.ui.screens.settings

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.htmllearn.app.data.repository.AppRepository
import com.htmllearn.app.notification.NotificationHelper
import com.htmllearn.app.ui.theme.*
import com.htmllearn.app.worker.WorkScheduler
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@Composable
fun SettingsScreen(onBack: () -> Unit) {
    val ctx   = LocalContext.current
    val repo  = remember { AppRepository.get(ctx) }
    val scope = rememberCoroutineScope()
    val user  by repo.userFlow().collectAsStateWithLifecycle(null)

    var name          by remember { mutableStateOf("") }
    var notifEnabled  by remember { mutableStateOf(true) }
    var goalMinutes   by remember { mutableIntStateOf(30) }
    var reminderHour  by remember { mutableIntStateOf(20) }

    LaunchedEffect(user) {
        user?.let {
            name         = it.name
            notifEnabled = it.notificationsEnabled
            goalMinutes  = it.studyGoalMinutes
            reminderHour = it.reminderHour
        }
    }

    Column(Modifier.fillMaxSize().background(BgDark)) {
        Surface(color = BgSurface, tonalElevation = 2.dp) {
            Row(Modifier.fillMaxWidth().padding(4.dp, 40.dp, 16.dp, 8.dp),
                verticalAlignment = Alignment.CenterVertically) {
                IconButton(onBack) { Icon(Icons.Filled.ArrowBack, null, tint = TextPrimary) }
                Text("সেটিংস", style = MaterialTheme.typography.headlineSmall)
            }
        }

        Column(
            Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Name
            Card(colors = CardDefaults.cardColors(BgCard), shape = RoundedCornerShape(14.dp)) {
                Column(Modifier.padding(16.dp)) {
                    Text("ব্যক্তিগত তথ্য", style = MaterialTheme.typography.titleMedium, color = Primary)
                    Spacer(Modifier.height(12.dp))
                    OutlinedTextField(
                        value          = name,
                        onValueChange  = { name = it },
                        label          = { Text("তোমার নাম") },
                        modifier       = Modifier.fillMaxWidth(),
                        colors         = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor   = Primary,
                            unfocusedBorderColor = Divider,
                            focusedContainerColor   = BgCard,
                            unfocusedContainerColor = BgCard
                        ),
                        shape = RoundedCornerShape(10.dp)
                    )
                }
            }

            // Study goal
            Card(colors = CardDefaults.cardColors(BgCard), shape = RoundedCornerShape(14.dp)) {
                Column(Modifier.padding(16.dp)) {
                    Text("দৈনিক লক্ষ্য", style = MaterialTheme.typography.titleMedium, color = Secondary)
                    Spacer(Modifier.height(8.dp))
                    Text("প্রতিদিন কতক্ষণ শিখতে চাও?", style = MaterialTheme.typography.bodyMedium)
                    Slider(
                        value         = goalMinutes.toFloat(),
                        onValueChange = { goalMinutes = it.toInt() },
                        valueRange    = 10f..120f,
                        steps         = 10,
                        colors        = SliderDefaults.colors(thumbColor = Secondary, activeTrackColor = Secondary)
                    )
                    Text("$goalMinutes মিনিট", style = MaterialTheme.typography.titleLarge, color = Secondary)
                }
            }

            // Notifications
            Card(colors = CardDefaults.cardColors(BgCard), shape = RoundedCornerShape(14.dp)) {
                Column(Modifier.padding(16.dp)) {
                    Text("Notification", style = MaterialTheme.typography.titleMedium, color = Warning)
                    Spacer(Modifier.height(8.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Column(Modifier.weight(1f)) {
                            Text("শেখার অনুস্মারক", style = MaterialTheme.typography.bodyMedium)
                            Text("প্রতি ৩ ঘণ্টায় reminder পাবে",
                                style = MaterialTheme.typography.bodySmall, color = TextHint)
                        }
                        Switch(
                            checked         = notifEnabled,
                            onCheckedChange = { notifEnabled = it },
                            colors          = SwitchDefaults.colors(
                                checkedThumbColor = Warning, checkedTrackColor = Warning.copy(0.4f)
                            )
                        )
                    }
                    if (notifEnabled) {
                        Spacer(Modifier.height(12.dp))
                        Text("মূল reminder সময়: $reminderHour:00",
                            style = MaterialTheme.typography.bodySmall)
                        Slider(
                            value         = reminderHour.toFloat(),
                            onValueChange = { reminderHour = it.toInt() },
                            valueRange    = 6f..23f,
                            steps         = 16,
                            colors        = SliderDefaults.colors(thumbColor = Warning, activeTrackColor = Warning)
                        )
                    }
                }
            }

            // Test notification
            Card(colors = CardDefaults.cardColors(BgCard), shape = RoundedCornerShape(14.dp)) {
                Column(Modifier.padding(16.dp)) {
                    Text("Notification পরীক্ষা",
                        style = MaterialTheme.typography.titleMedium, color = Accent)
                    Spacer(Modifier.height(8.dp))
                    OutlinedButton(
                        onClick  = {
                            NotificationHelper.showReminder(
                                ctx,
                                "পরীক্ষামূলক Notification!",
                                "Notification ঠিকঠাক কাজ করছে।"
                            )
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape    = RoundedCornerShape(10.dp),
                        border   = BorderStroke(1.dp, Accent)
                    ) {
                        Icon(Icons.Filled.Notifications, null, tint = Accent)
                        Spacer(Modifier.width(8.dp))
                        Text("Test Notification পাঠাও", color = Accent)
                    }
                }
            }

            // Save
            Button(
                onClick  = {
                    scope.launch {
                        // ✅ fix: .first() instead of .value
                        val u = repo.userFlow().first() ?: return@launch
                        repo.updateSettings(
                            u.copy(
                                name                 = name.ifBlank { "শিক্ষার্থী" },
                                studyGoalMinutes     = goalMinutes,
                                notificationsEnabled = notifEnabled,
                                reminderHour         = reminderHour
                            )
                        )
                        if (notifEnabled) WorkScheduler.scheduleDailyReminder(ctx)
                        else WorkScheduler.cancelAll(ctx)
                        onBack()
                    }
                },
                modifier = Modifier.fillMaxWidth().height(52.dp),
                colors   = ButtonDefaults.buttonColors(containerColor = Primary),
                shape    = RoundedCornerShape(14.dp)
            ) {
                Icon(Icons.Filled.Save, null)
                Spacer(Modifier.width(8.dp))
                Text("সংরক্ষণ করো", style = MaterialTheme.typography.titleMedium)
            }

            Spacer(Modifier.height(8.dp))
        }
    }
}
