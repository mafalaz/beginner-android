package com.example.phonearticle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler


class SplashActivity : AppCompatActivity() {
    private val SPLASH_TIME_OUT: Long = 3000 // Waktu tampilan splash screen dalam milidetik
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()

        // Handler untuk mengganti activity setelah SPLASH_TIME_OUT
        @Suppress("DEPRECATION")
        Handler().postDelayed({
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)
            finish() // Tutup activity splash screen
        }, SPLASH_TIME_OUT)
    }
}