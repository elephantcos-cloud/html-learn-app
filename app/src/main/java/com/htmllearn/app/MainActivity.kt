package com.htmllearn.app

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.htmllearn.app.ui.navigation.AppNavGraph
import com.htmllearn.app.ui.theme.HtmlLearnTheme
import com.htmllearn.app.ui.theme.BgDark

class MainActivity : ComponentActivity() {

    private val notifPermission = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            notifPermission.launch(android.Manifest.permission.POST_NOTIFICATIONS)
        }
        setContent {
            HtmlLearnTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = BgDark) {
                    AppNavGraph()
                }
            }
        }
    }
}
