package com.lab02.sqlite_projectlab.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class DB_Helper(var context: Context):SQLiteOpenHelper(context, dbname, factory, version) {

    companion object{
        internal val dbname = "SqlDB"
        internal val factory = null
        internal val version = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        /*
        db?.execSQL("CREATE TABLE IF NOT EXISTS hospital (" +
                "id INT NOT NULL AUTO_INCREMENT," +
                "name VARCHAR(50) NOT NULL," +
                "address VARCHAR(80)," +
                "description VARCHAR(150)," +
                "latitude DECIMAL NOT NULL," +
                "longitude DECIMAL NOT NULL," +
                "PRIMARY KEY (id));")

        */
        val createTable =  "CREATE TABLE hospital (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name VARCHAR(50) NOT NULL," +
                "address VARCHAR(80) NOT NULL," +
                "description VARCHAR(150)," +
                "latitude DECIMAL NOT NULL," +
                "longitude DECIMAL NOT NULL)"

        db?.execSQL(createTable)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun insertDB (_hospital : Hospital){
        val db: SQLiteDatabase = writableDatabase
        val values = ContentValues()
        values.put("name", _hospital.getName())
        values.put("address", _hospital.getAddress())
        values.put("description", _hospital.getDescription())
        values.put("latitude", _hospital.getLatitude())
        values.put("longitude", _hospital.getLongitude())
        var result = db.insert("hospital", null, values)
        if(result == -1.toLong())
            Toast.makeText(context, "Error en el ingreso de hospital", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context, "Ingreso correcto de hospital", Toast.LENGTH_SHORT).show()
        db.close()
    }

    fun updateData(_id: Int, _hospital: Hospital): Int{
        val values = ContentValues()
        values.put("name", _hospital.getName())
        values.put("address", _hospital.getAddress())
        values.put("description", _hospital.getDescription())
        values.put("latitude", _hospital.getLatitude())
        values.put("longitude", _hospital.getLongitude())
        val whereClause = "id = ?"
        val whereargs = arrayOf(_id.toString())
        return this.writableDatabase.update("hospital", values, whereClause, whereargs)
        /*
        val db = this.writableDatabase
        val query = "UPDATE hospital " +
                "SET name = '${_hospital.getName()}'," +
                "address = '${_hospital.getAddress()}'," +
                "description = '${_hospital.getDescription()}'," +
                "latitude = '${_hospital.getLatitude()}'," +
                "longitude = '${_hospital.getLongitude()}' " +
                "WHERE id = $_id"
        val result = db.rawQuery(query, null)
        result.close()
        db.close()
        */
    }

    fun readData(): MutableList<Hospital>{
        var list: MutableList<Hospital> = ArrayList()
        val db = this.readableDatabase
        val query = "Select * from hospital"
        val result = db.rawQuery(query, null)

        if(result.moveToFirst()){
            do{
                var hospital = Hospital()
                hospital.setId(result.getString(0).toInt())
                hospital.setName(result.getString(1))
                hospital.setAddress(result.getString(2))
                hospital.setDescription(result.getString(3))
                hospital.setLatitude(result.getString(4).toFloat())
                hospital.setLongitude(result.getString(5).toFloat())
                list.add(hospital)
            }
            while (result.moveToNext())
        }

        result.close()
        db.close()
        return list
    }

    fun readOneId(_id: String): Hospital? {
        val db = this.readableDatabase
        val query = "Select * from hospital WHERE id = ?"
        db.rawQuery(query, arrayOf(_id)).use{
            if(it.moveToFirst()) {
                val hospital = Hospital()
                hospital.setId(it.getString(0).toInt())
                hospital.setName(it.getString(1))
                hospital.setAddress(it.getString(2))
                hospital.setDescription(it.getString(3))
                hospital.setLatitude(it.getString(4).toFloat())
                hospital.setLongitude(it.getString(5).toFloat())
                return hospital
            }
        }
        db.close()
        return null
    }


    fun deleteItem(_id: Int): Boolean {
        val db = this.writableDatabase
        val _success = db.delete("hospital", "id" + "=?", arrayOf(_id.toString())).toLong()
        db.close()
        return Integer.parseInt("$_success") != -1
    }

}