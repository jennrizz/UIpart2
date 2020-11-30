package com.example.uipart2

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper

class queueTableHandler(var context: Context): SQLiteOpenHelper(context, db_name,null, 1) {
    companion object {
        private val db_name = "queue_database"
        private val tbl_name = "queued_songs"
        private val col_id = "id"
        private val col_title = "song_title"
        private val col_artist= "artist_name"
        private val col_album ="album_name"
    }
    override fun onCreate(db: SQLiteDatabase?) {
        val query =  "CREATE TABLE $tbl_name ($col_id INTEGER PRIMARY KEY, $col_title TEXT, $col_artist TEXT, $col_album TEXT)"
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL( "Drop Table if exist "+tbl_name)
        onCreate(db)
    }
    fun create(mcont: stringArray){
        val db = this.writableDatabase
        val contVal = ContentValues()
        contVal.put(col_title,mcont.songName)
        contVal.put(col_album,mcont.albumName)
        contVal.put(col_artist, mcont.artistName)
        db.insert(tbl_name,null,contVal)
    }
    fun readAll() : MutableList<stringArray>{
        val db = this.readableDatabase
        val albumList = ArrayList<stringArray>()
        val query ="Select * from "+ tbl_name
        var cursor : Cursor? = null
        try{
            cursor = db.rawQuery(query, null)
        } catch (e: SQLiteException){
            return albumList
        }
        var id : Int
        var title:String
        var artist :String
        var album : String
        if(cursor.moveToFirst()){
            do{
                id = cursor.getInt(cursor.getColumnIndex(col_id))
                title = cursor.getString(cursor.getColumnIndex(col_title))
                artist = cursor.getString(cursor.getColumnIndex(col_artist))
                album = cursor.getString(cursor.getColumnIndex(col_album))
                var album = stringArray(id, title, artist, album)
                albumList.add(album)
            }while (cursor.moveToNext())
        }
        return  albumList
    }
    fun readOne(id : Int) : stringArray{
        val db = this.readableDatabase
        val albumList = stringArray(0, "", "","" )
        val query ="Select * from $tbl_name where id = $id"
        var cursor : Cursor? = null
        try{
            cursor = db.rawQuery(query, null)
        } catch (e: SQLiteException){
            return albumList
        }
        var id : Int
        var title:String
        var artist :String
        var album : String
        if(cursor.moveToFirst()){
            id = cursor.getInt(cursor.getColumnIndex(col_id))
            title = cursor.getString(cursor.getColumnIndex(col_title))
            artist = cursor.getString(cursor.getColumnIndex(col_artist))
            album = cursor.getString(cursor.getColumnIndex(col_album))
            val albumList = stringArray(id, title, artist, album)
        }
        return  albumList
    }
    fun delete(mcont: stringArray){
        val db = this.writableDatabase
        db.delete(tbl_name, "id= "+mcont.id,null)
    }
    fun update(mcont: stringArray) {
        val db = this.writableDatabase
        val contVal = ContentValues()
        contVal.put(col_title, mcont.songName)
        contVal.put(col_artist,mcont.artistName)
        contVal.put(col_artist,mcont.albumName)
        db.update(tbl_name, contVal, "id= "+mcont.id, null)
    }
}