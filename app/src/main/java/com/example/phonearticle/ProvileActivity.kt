package com.example.phonearticle

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button

class ProvileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_provile)
        supportActionBar?.title = "Profile"
        val buttonOpenWeb = findViewById<Button>(R.id.portfolio)
        buttonOpenWeb.setOnClickListener {
            // Alamat web yang akan dibuka
            val url = "https://mafalaz.netlify.app/"

            // Buat Intent untuk membuka browser
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))

            // Jalankan Intent
            startActivity(intent)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                // Kembali ke MainActivity
                @Suppress("DEPRECATION")
                onBackPressed()
                return true
            }

        }
        return super.onOptionsItemSelected(item)
    }
}