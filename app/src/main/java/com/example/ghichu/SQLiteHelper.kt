package com.example.ghichu

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.sql.Date

class SQLiteHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "ghichu"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "gc"
        private const val COL_ID = "id"
        private const val COL_TIEUDE = "tieude"
        private const val COL_NOIDUNG = "noidung"
        private const val COL_DAY = "day"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = ("CREATE TABLE $TABLE_NAME ("
                + "$COL_ID INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "$COL_TIEUDE TEXT,"
                + "$COL_NOIDUNG TEXT,"
                + "$COL_DAY TEXT)")
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun insertModel(model: Model): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_TIEUDE, model.tieude)
        contentValues.put(COL_NOIDUNG, model.noidung)
        contentValues.put(COL_DAY, model.day.time)

        return db.insert(TABLE_NAME, null, contentValues)
    }

    fun updateModel(id: Int, tieude: String, noidung: String, day: Date): Boolean {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COL_TIEUDE, tieude)
            put(COL_NOIDUNG, noidung)
            put(COL_DAY, day.time)
        }
        val result = db.update(TABLE_NAME, values, "$COL_ID=?", arrayOf(id.toString()))
        db.close()
        return result > 0
    }

    fun deleteModel(id: Int): Boolean {
        val db = writableDatabase
        val result = db.delete(TABLE_NAME, "$COL_ID=?", arrayOf(id.toString()))
        db.close()
        return result > 0
    }

    fun getAllModels(): List<Model> {
        val modelList = ArrayList<Model>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)

        if (cursor.moveToFirst()) {
            do {
                val model = Model(
                    id = cursor.getInt(cursor.getColumnIndexOrThrow(COL_ID)),
                    tieude = cursor.getString(cursor.getColumnIndexOrThrow(COL_TIEUDE)),
                    noidung = cursor.getString(cursor.getColumnIndexOrThrow(COL_NOIDUNG)),
                    day = Date(cursor.getLong(cursor.getColumnIndexOrThrow(COL_DAY)))
                )
                modelList.add(model)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return modelList
    }
}
