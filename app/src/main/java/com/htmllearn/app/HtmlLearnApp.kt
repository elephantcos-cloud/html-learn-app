package com.htmllearn.app

import android.app.Application
import com.htmllearn.app.notification.NotificationHelper

class HtmlLearnApp : Application() {
    override fun onCreate() {
        super.onCreate()
        NotificationHelper.createChannels(this)
    }
}
