package com.pbws.islami.ui.screens.radio

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.media3.common.util.UnstableApi
import com.pbws.islami.R
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("RestrictedApi")
@UnstableApi
@AndroidEntryPoint
class AudioService : Service() {

    private var mediaPlayer: MediaPlayer? = null
    private val channelId = "AudioServiceChannel"
    private var isPlaying = false

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val contentText = intent?.getStringExtra("contentText")
        val audioUrl = intent?.getStringExtra("audioUrl")

        when (intent?.action) {
            "play" -> {
                if (audioUrl != null && contentText != null) {
                    playMusic(audioUrl, contentText)
                }
            }

            "stop" -> stopMusic()
            "seek_forward" -> seekForward()
            "seek_backward" -> seekBackward()
        }
        return START_STICKY
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                channelId,
                "Audio Service Channel",
                NotificationManager.IMPORTANCE_LOW
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(serviceChannel)
        }
    }

    private fun playMusic(audioUrl: String, contentText: String) {
        // Stop any currently playing audio
        if (mediaPlayer?.isPlaying == true) {
            mediaPlayer?.stop()
            mediaPlayer?.reset()
        } else {
            mediaPlayer = MediaPlayer()
        }

        try {
            mediaPlayer?.apply {
                setDataSource(audioUrl)
                setOnPreparedListener {
                    start()
                    startForeground(1, createNotification(contentText, isPlaying))
                }
                prepareAsync()
            }
            isPlaying = true
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun stopMusic() {
        mediaPlayer?.stop()
        mediaPlayer?.reset()
        isPlaying = false
        stopForeground(true)
        stopSelf()
    }

    private fun seekForward() {
        mediaPlayer?.let {
            val newPosition = it.currentPosition + 5000 // Seek forward 5 seconds
            it.seekTo(newPosition.coerceAtMost(it.duration))
        }
    }

    private fun seekBackward() {
        mediaPlayer?.let {
            val newPosition = it.currentPosition - 5000 // Seek backward 5 seconds
            it.seekTo(newPosition.coerceAtLeast(0))
        }
    }

    private fun createNotification(contentText: String, isPlaying: Boolean): Notification {
        return NotificationCompat.Builder(this, channelId)
            .setContentTitle("\u200Fإسلامى")
            .setContentText("\u200F$contentText")
            .setSmallIcon(R.drawable.onboarding_secound)
            .build()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}
