package com.example.uipart2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import com.example.uipart2.listAdapter

class MainActivity : AppCompatActivity() {
    var queuedList = arrayListOf<String>()
    val songNames = arrayListOf<String>("So What", "Fly me to the Moon", "Mood Indigo", "Take Five", "Strange Fruit",
        "Round Midnight", "Giant Steps", "Only Human", "Don't Start Now", "7 Rings", "Sucker", "I Don't Care", "Bad Guy", "Juice", "Talk",
        "Wow", "Fancy", "Amazed", "The Dance", "The Gambler", "Sixteen Tons", "I Hope", "I Hope You Dance")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setTitle("UIElement_HOME")
        var list = mutableListOf<stringArray>()
        val songListView = findViewById<ListView>(R.id.song_list)
        for (i in songNames){
            list.add(stringArray(i))
        }
        songListView.adapter =  listAdapter(this,R.layout.main_row, list)
        registerForContextMenu(songListView)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu_bar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.queue -> {
                val intent1 = Intent(this, QueuedSongsActivity::class.java)
                val bundle = Bundle()
                bundle.putSerializable("songNames",queuedList)
                intent1.putExtra("bundle", bundle)
                startActivity(intent1)
                true
            }
            R.id.songs -> {
                true
            }
            R.id.albums -> {
                startActivity(Intent(this, AlbumsActivity::class.java))
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }

    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.queue_menu,menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        val songPosition = info.position
        return when(item.itemId){
            R.id.add_queue -> {
                val song = songNames.get(songPosition)
                queuedList.add(song)
                true
                }
            else -> return super.onContextItemSelected(item)
        }
    }
}