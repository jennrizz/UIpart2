package com.example.uipart2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class listAdapter( var mcontext:Context, var resources:Int, var items:List<stringArray> ): ArrayAdapter<stringArray>(mcontext, resources,items) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutinflater = LayoutInflater.from(mcontext)
        val view = layoutinflater.inflate(resources,null)
        val songTextView=view.findViewById<TextView>(R.id.song_name)
        var arr_items = items[position]
        songTextView.text = arr_items.songName
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
    fun hey(): Boolean{
        return true
    }
}
