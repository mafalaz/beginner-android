package com.example.phonearticle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvPhone: RecyclerView
    private val list = ArrayList<Phone>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvPhone = findViewById(R.id.rv_phone)
        rvPhone.setHasFixedSize(true)

        list.addAll(getListPhone())

        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.action_profile -> {
                // Launch the ProfilActivity here
                val intent = Intent(this@MainActivity, ProvileActivity::class.java)
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getListPhone(): ArrayList<Phone> {
        val dataName = resources.getStringArray(R.array.data_name)
        val maxDescriptionLength = 25
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val dataPrice = resources.getStringArray(R.array.data_price)
        val detailDescription = resources.getStringArray(R.array.data_description)
        val listPhone = ArrayList<Phone>()
        for (i in dataName.indices) {
            val description = if (dataDescription[i].length > maxDescriptionLength) {
               val truncatedDescription = dataDescription[i].substring(0, maxDescriptionLength)
                "$truncatedDescription... Read More"
            } else {
                dataDescription[i]
            }
            val phone = Phone(dataName[i], description, detailDescription[i], dataPhoto.getResourceId(i, -1), dataPrice[i])
            listPhone.add(phone)
        }

        return listPhone
    }

    private fun showRecyclerList() {
        val layoutManager = LinearLayoutManager(this)
        rvPhone.layoutManager = layoutManager
        val listPhoneAdapter = ListPhoneAdapter(list)
        rvPhone.adapter = listPhoneAdapter

        listPhoneAdapter.setOnItemClickCallback(object : ListPhoneAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Phone) {
                val intentToDetail = Intent(this@MainActivity, DetailActivity::class.java)
                intentToDetail.putExtra("DATA", data)
                startActivity(intentToDetail)
                showSelectedHero(data)
            }
        })
    }

    private fun showSelectedHero(phone: Phone) {
        Toast.makeText(this, "Kamu memilih " + phone.name, Toast.LENGTH_SHORT).show()
    }

}