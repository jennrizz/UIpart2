package com.example.uipart2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.ListView

class popAlbum : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pop_album)
        val songNames = arrayListOf<String>("Giant Steps", "Only Human", "Don't Start Now", "7 Rings", "Sucker", "I Don't Care", "Bad Guy", "Juice", "Talk",
            "Wow", "Fancy")
        val songList = mutableListOf<stringArray>()
        for (i in songNames){
            songList.add(stringArray(i))
        }
        val image = findViewById<ImageView>(R.id.imgPop)
        val listview = findViewById<ListView>(R.id.popList)
        listview.adapter = listAdapter(this, R.layout.main_row, songList)
        image.setImageResource(R.drawable.jazz_album)
    }
}