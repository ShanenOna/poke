package com.gabriel.pokems

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class StartActivity : AppCompatActivity() {

    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        val pressStartText = findViewById<TextView>(R.id.press_start)

        // Make "PRESS START" blink
        val anim = AlphaAnimation(0.0f, 1.0f)
        anim.duration = 600
        anim.repeatMode = Animation.REVERSE
        anim.repeatCount = Animation.INFINITE
        pressStartText.startAnimation(anim)

        // Setup background music
        try {
            // Looking for res/raw/lake_theme.mp3
            val resId = resources.getIdentifier("lake_theme", "raw", packageName)
            if (resId != 0) {
                mediaPlayer = MediaPlayer.create(this, resId)
                mediaPlayer?.isLooping = true
                mediaPlayer?.start()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        // Start game on click anywhere
        findViewById<android.view.View>(R.id.start_background).setOnClickListener {
            startActivity(Intent(this, GameActivity::class.java))
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}