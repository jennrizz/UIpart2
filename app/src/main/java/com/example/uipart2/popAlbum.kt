package com.example.uipart2

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class popAlbum : AppCompatActivity() {
    val songNames = arrayListOf<String>("Giant Steps", "Only Human", "Don't Start Now", "7 Rings", "Sucker", "I Don't Care", "Bad Guy", "Juice", "Talk",
            "Wow", "Fancy")
    val songList = mutableListOf<stringArray>()
    lateinit var adapter: listAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pop_album)

        val songList = mutableListOf<stringArray>()
        for (i in songNames){
            songList.add(stringArray(i))
        }
        val image = findViewById<ImageView>(R.id.imgPop)
        val listview = findViewById<ListView>(R.id.popList)
        adapter = listAdapter(this, R.layout.main_row, songList)
        listview.adapter = adapter
        image.setImageResource(R.drawable.pop_album)

        registerForContextMenu(listview)
    }
    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.delete_menu,menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        val songPosition = info.position
        val song = songNames.get(songPosition)
        return when(item.itemId) {
            R.id.remove -> {
                val dialogbuilder = AlertDialog.Builder(this)
                dialogbuilder.setMessage("$song")
                        .setCancelable(false)
                        .setPositiveButton("Yes", DialogInterface.OnClickListener { dialogInterface, i ->
                            songList.removeAt(songPosition)
                            adapter.notifyDataSetChanged()
                            Toast.makeText(this, R.string.delete, Toast.LENGTH_SHORT).show()
                        }).setNegativeButton("No", DialogInterface.OnClickListener { dialogInterface, i ->
                            dialogInterface.cancel()
                        })
                val alert = dialogbuilder.create()
                alert.setTitle("Are you sure you want to delete this sing?")
                alert.show()
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}