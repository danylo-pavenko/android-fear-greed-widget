package com.depsoftware.fgindex

import android.appwidget.AppWidgetManager
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.depsoftware.fgindex.ui.screen.FearGreedScreen
import com.depsoftware.fgindex.ui.theme.FearGreedIndexWidgetTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FearGreedIndexWidgetTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FearGreedScreen()
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        val intent = Intent(this, FearGreedIndexWidget::class.java)
        intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE)
        sendBroadcast(intent)
    }
}
