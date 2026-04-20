package com.htmllearn.app.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.htmllearn.app.worker.WorkScheduler

class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_BOOT_COMPLETED) {
            WorkScheduler.scheduleDailyReminder(context)
        }
    }
}
