package com.htmllearn.app.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.htmllearn.app.data.repository.AppRepository
import com.htmllearn.app.notification.NotificationHelper
import com.htmllearn.app.notification.ReminderMessages
import kotlinx.coroutines.flow.first
import java.util.Calendar

class ReminderWorker(ctx: Context, params: WorkerParameters) : CoroutineWorker(ctx, params) {
    override suspend fun doWork(): Result {
        val repo = AppRepository.get(applicationContext)
        val user = repo.userFlow().first() ?: return Result.success()

        if (!user.notificationsEnabled) return Result.success()

        val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        val (title, message) = ReminderMessages.getRandom(hour)

        // If user hasn't studied today, more urgent message
        val finalTitle = if (user.todayStudyMinutes < user.studyGoalMinutes) {
            "⚠️ $title"
        } else title

        NotificationHelper.showReminder(applicationContext, finalTitle, message)
        return Result.success()
    }
}
