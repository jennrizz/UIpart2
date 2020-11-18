package com.example.uipart2

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater

import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class gridAdapter(var mcontext: Context, var imgList: ArrayList<imgArray>): BaseAdapter(){


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
        var view = View.inflate(mcontext,R.layout.album_row,null)
        var image = view.findViewById<ImageView>(R.id.imgAlbum)
        var imageIcon =imgList.get(position)
        image.setImageResource(imageIcon.img)
        return view
    }
}