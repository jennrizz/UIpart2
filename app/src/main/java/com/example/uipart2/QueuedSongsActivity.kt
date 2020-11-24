package com.example.uipart2

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_UPDATE_CURRENT
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import androidx.annotation.RequiresApi

class QueuedSongsActivity : AppCompatActivity() {
    lateinit var adapter: listAdapter
    val list = arrayListOf<stringArray>()
    lateinit var notificationManager : NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder : Notification.Builder
    private val channelId = "i.apps.notifications"
    private val description = "Test Notification"
    lateinit var songNames : ArrayList<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_queued_songs)
        setTitle("Queued Songs")
        var bundle = intent.getBundleExtra("bundle") as Bundle
        songNames = bundle.getSerializable("songNames") as ArrayList<String>
        val queueList = findViewById<ListView>(R.id.queued_list)
        for (i in songNames){
            list.add(stringArray(i))
        }
        adapter = listAdapter(this,R.layout.main_row, list)
        if (adapter.count == 0){
            notification()
        }
        queueList.adapter = adapter
        registerForContextMenu(queueList)
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.delete_menu,menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        val songPosition = info.position
        return when(item.itemId) {
            R.id.remove -> {
                songNames.removeAt(songPosition)
                list.removeAt(songPosition)
                adapter.notifyDataSetChanged()
                if (adapter.count == 0){
                    notification()
                }
                Toast.makeText(this, R.string.delete, Toast.LENGTH_SHORT).show()
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    fun notification() {
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val intent = Intent(this, MainActivity::class.java)
        val pendingIntent =  PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT)
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel = NotificationChannel(channelId,description, NotificationManager.IMPORTANCE_DEFAULT)
            notificationChannel.enableVibration(false)
            notificationChannel.enableLights(true)
            notificationManager.createNotificationChannel(notificationChannel)

            builder = Notification.Builder (this, channelId)
                    .setContentTitle("Notification")
                    .setContentText("QUEUE IS EMPTY")
                    .setContentIntent(pendingIntent)
                    .setSmallIcon(R.drawable.ic_launcher_background)
        }
        else{
            builder = Notification.Builder (this)
                    .setContentTitle("Notification")
                    .setContentText("QUEUE IS EMPTY")
                    .setContentIntent(pendingIntent)
                    .setSmallIcon(R.drawable.ic_launcher_background)
        }
        notificationManager.notify(1234,builder.build())
    }
}
