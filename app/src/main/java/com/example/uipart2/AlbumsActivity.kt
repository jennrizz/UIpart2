package com.example.uipart2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.GridView
import android.widget.Toast

class AlbumsActivity : AppCompatActivity(){
    lateinit var gridview : GridView
    var arrayList = arrayListOf<imgArray>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_albums)
        setTitle("Song Albms")
        arrayList = data()
        gridview = findViewById(R.id.albumGrid)
        val adapter = gridAdapter(this, arrayList)
        gridview.adapter = adapter
        gridview.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, l ->
                val imgPos = parent.getItemAtPosition(position)
                val pos1 = parent.getItemAtPosition(0)
                val pos2 = parent.getItemAtPosition(1)
                val pos3 = parent.getItemAtPosition(2)
                if (imgPos == pos1){
                    startActivity(Intent(this, CountryAlbum::class.java))
                }
                else if (imgPos == pos2){
                    startActivity(Intent(this, popAlbum::class.java))
                }
                else if (imgPos == pos3){
                    startActivity(Intent(this, jazzAlbum::class.java))
                }
                else true
            }


    }
    private fun data(): ArrayList<imgArray> {
        var arrayList = arrayListOf<imgArray>()
        arrayList.add(imgArray(R.drawable.country_album))
        arrayList.add(imgArray(R.drawable.pop_album))
        arrayList.add(imgArray(R.drawable.jazz_album))
        return  arrayList
    }
}