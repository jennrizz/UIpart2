package com.example.uipart2.adapter

import com.example.uipart2.R
import com.example.uipart2.stringArray
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class listAdapter( var mcontext:Context, var resources:Int, var items:MutableList<stringArray> ): ArrayAdapter<stringArray>(mcontext, resources,items) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutinflater = LayoutInflater.from(mcontext)
        val view = layoutinflater.inflate(resources,null)
        val songTextView=view.findViewById<TextView>(R.id.song_name)
        val artistTextView = view.findViewById<TextView>(R.id.artist_name)
        val albumTextView = view.findViewById<TextView>(R.id.album_name)
        var arr_items = items[position]
        artistTextView.text = arr_items.artistName
        songTextView.text = arr_items.songName
        albumTextView.text = arr_items.albumName
        return view
    }

    override fun getCount(): Int {
        return items.size
    }
    override fun getItem(position: Int): stringArray? {
        return items[position]
    }
    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }
}
