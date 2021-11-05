package com.lab02.sqlite_projectlab.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
class DB_Helper(context: Context):SQLiteOpenHelper(context, dbname, factory, version) {
    companion object{
        internal val dbname = "SqlDB"
        internal val factory = null
        internal val version = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE IF NOT EXISTS hospital (id INT NOT NULL," +
                "name VARCHAR(50) NOT NULL," +
                "direction VARCHAR(80)," +
                "description VARCHAR(150)," +
                "longitude DECIMAL NOT NULL," +
                "latitude DECIMAL NOT NULL," +
                "PRIMARY KEY (id));")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun insertDB (id: Int, name:String, direction: String, description: String, longitude: Double, latitude: Double ){
        val db: SQLiteDatabase = writableDatabase
        val values: ContentValues = ContentValues()
        values.put("id", id)
        values.put("name", name)
        values.put("direction", direction)
        values.put("description", description)
        values.put("longitude", longitude)
        values.put("latitude", latitude)

        db.insert("hospital", null,values)
        db.close()
    }

}