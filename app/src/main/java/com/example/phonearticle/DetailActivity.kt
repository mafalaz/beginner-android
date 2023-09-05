package com.example.phonearticle

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*


class DetailActivity : AppCompatActivity() {
    private lateinit var tvDetailName:TextView
    private lateinit var tvDescription:TextView
    private lateinit var imgPhoto:ImageView
    private lateinit var tvPrice:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        tvDetailName = findViewById(R.id.tv_detail_name)
        tvDescription = findViewById(R.id.tv_detail_description)
        imgPhoto = findViewById(R.id.img_item_photo)
        tvPrice = findViewById(R.id.tv_item_price)

        val receivedData = if (Build.VERSION.SDK_INT >= 33) {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Phone>("DATA")
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Phone>("DATA")
        }
        if (receivedData != null) {
            supportActionBar?.title = "Detail"
            val photo = receivedData.photo
            val text = receivedData.name
            val deskripsi = receivedData.detailDescription
            val price = receivedData.price

            imgPhoto.setImageResource(photo)
            tvDetailName.text = text
            tvDescription.text = deskripsi
            tvPrice.text = "Mulai Dari: $price"
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_detail_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                // Kembali ke MainActivity
                @Suppress("DEPRECATION")
                onBackPressed()
                return true
            }
            R.id.share_button -> {
                val textToShare = "${tvPrice.text}\n\n${tvDetailName.text}\n\n${tvDescription.text}"

                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, textToShare) // Mengisi parameter putExtra dengan data dari tvDetailName dan tvDescription
                    type = "text/plain"
                }

                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}