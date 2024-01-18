package app.kotlin.unscramble.services

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.media.session.PlaybackState.ACTION_PLAY
import android.os.IBinder
import app.kotlin.unscramble.R
import org.jetbrains.annotations.Nullable

class AudioService : Service(), MediaPlayer.OnPreparedListener {
    private var audioPlayer: MediaPlayer? = null

    @Nullable
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        audioPlayer = MediaPlayer.create(this, R.raw.happy_day)
        audioPlayer?.isLooping = true
    }
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            ACTION_PLAY.toString() -> {
                audioPlayer?.apply {
                    setOnPreparedListener(this@AudioService)
                    prepare()
                }
            }
        }
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        audioPlayer?.stop()
        audioPlayer?.release()
        audioPlayer = null
    }

    override fun onPrepared(audioPlayer: MediaPlayer?) {
        audioPlayer?.start()
    }
}