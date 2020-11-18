package com.example.uipart2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ListView
import android.widget.Toast

class QueuedSongsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_queued_songs)
        setTitle("Queued Songs")
        var bundle = intent.getBundleExtra("bundle") as Bundle
        val songNames = bundle.getSerializable("songNames") as ArrayList<String>
        val queueList = findViewById<ListView>(R.id.queued_list)
        val list = arrayListOf<stringArray>()
        for (i in songNames){
            list.add(stringArray(i))
        }
        queueList.adapter = listAdapter(this,R.layout.main_row, list)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu_bar, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.queue -> {
                true
            }
            R.id.songs -> {
                startActivity(Intent(this, MainActivity::class.java))
                true
            }
            R.id.albums -> {
                startActivity(Intent(this, AlbumsActivity::class.java))
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }

    }
}
