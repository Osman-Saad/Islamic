package com.pbws.islami

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.pbws.islami.ui.screens.commoncomposable.mainscreen.MainScreen
import com.pbws.islami.utils.GpsStatusReceiver
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainScreen()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(GpsStatusReceiver())
    }
}