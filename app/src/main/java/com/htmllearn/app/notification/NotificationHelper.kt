package com.htmllearn.app.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.htmllearn.app.MainActivity
import com.htmllearn.app.R

object NotificationHelper {
    const val CHANNEL_REMINDER = "reminder"
    const val CHANNEL_ACHIEVEMENT = "achievement"
    const val CHANNEL_STREAK = "streak"

    fun createChannels(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val nm = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            nm.createNotificationChannel(NotificationChannel(
                CHANNEL_REMINDER, "শেখার অনুস্মারক",
                NotificationManager.IMPORTANCE_HIGH
            ).apply { description = "প্রতিদিন HTML শেখার reminder" })

            nm.createNotificationChannel(NotificationChannel(
                CHANNEL_ACHIEVEMENT, "অর্জন",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply { description = "Badge ও Level up notification" })

            nm.createNotificationChannel(NotificationChannel(
                CHANNEL_STREAK, "Streak",
                NotificationManager.IMPORTANCE_HIGH
            ).apply { description = "Streak সংক্রান্ত notification" })
        }
    }

    fun showReminder(context: Context, title: String, message: String, id: Int = 1001) {
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pi = PendingIntent.getActivity(context, 0, intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)

        val notif = NotificationCompat.Builder(context, CHANNEL_REMINDER)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle(title)
            .setContentText(message)
            .setStyle(NotificationCompat.BigTextStyle().bigText(message))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .setContentIntent(pi)
            .build()

        val nm = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        nm.notify(id, notif)
    }

    fun showAchievement(context: Context, title: String, message: String) {
        val intent = Intent(context, MainActivity::class.java)
        val pi = PendingIntent.getActivity(context, 1, intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)

        val notif = NotificationCompat.Builder(context, CHANNEL_ACHIEVEMENT)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .setContentIntent(pi)
            .build()

        val nm = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        nm.notify((System.currentTimeMillis() % 10000).toInt(), notif)
    }
}
