package com.example.uipart2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.ListView

class jazzAlbum : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jazz_album)
        val songNames = arrayListOf<String>("So What", "Fly me to the Moon", "Mood Indigo", "Take Five", "Strange Fruit",
            "Round Midnight")
        val songList = mutableListOf<stringArray>()
        for (i in songNames){
            songList.add(stringArray(i))
        }
        val image = findViewById<ImageView>(R.id.imgJazz)
        val listview = findViewById<ListView>(R.id.jazzList)
        listview.adapter = listAdapter(this, R.layout.main_row, songList)
        image.setImageResource(R.drawable.jazz_album)
    }
}