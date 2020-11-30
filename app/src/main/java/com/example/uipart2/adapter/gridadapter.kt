package com.example.uipart2.adapter

import com.example.uipart2.R
import com.example.uipart2.albumArr

import android.content.Context

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class gridadapter(var mcontext: Context, var imgList: MutableList<albumArr>): BaseAdapter(){


    override fun getCount(): Int {
        return imgList.size
    }

    override fun getItem(position: Int): Any {
        return imgList.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = View.inflate(mcontext, R.layout.album_row,null)
        val image = view.findViewById<ImageView>(R.id.imgAlbum)
        val relDate = view.findViewById<TextView>(R.id.relDate)
        val title = view.findViewById<TextView>(R.id.titleAlbum)
        var imageIcon =imgList.get(position)
        var relDateText = imageIcon.date
        var titleText = imageIcon.title
        image.setImageResource(R.drawable.all_album)
        relDate.text = "Release Date: $relDateText"
        title.text = "Album Title: $titleText"
        return view
    }
}