package com.example.uipart2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.ListView

class CountryAlbum : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_album)
        val songNames = arrayListOf<String>("Amazed", "The Dance", "The Gambler", "Sixteen Tons", "I Hope", "I Hope You Dance")
        val songList = mutableListOf<stringArray>()
        for (i in songNames){
            songList.add(stringArray(i))
        }
        val image = findViewById<ImageView>(R.id.imgCountry)
        val listview = findViewById<ListView>(R.id.countrList)
        listview.adapter = listAdapter(this, R.layout.main_row, songList)
        image.setImageResource(R.drawable.country_album)
    }
}