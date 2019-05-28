package com.example.blocknotische.dataBase

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.util.ArrayList

class DbHelper(context: Context?) : SQLiteOpenHelper(context, "DBForNotes", null, 1) {
    private val COLUMN_ID = "ColumnId"
    private val COLUMN_TITLE = "ColumnTitle"
    private val COLUMN_BODY = "ColumnBody"
    private val COLUMN_COLOR = "ColumnColor"

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(" Create table " + NAME_OF_TABLE + " ( " +
                COLUMN_ID + " integer primary key autoincrement, " +
                COLUMN_TITLE + " text, " +
                COLUMN_BODY + " text, " +
                COLUMN_COLOR + " integer); ")

    }

    fun createRow(title: String, body: String, color: Int) {
        val database = writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_TITLE, title)
        contentValues.put(COLUMN_BODY, body)
        contentValues.put(COLUMN_COLOR, color)

        val rowIg = database.insert(NAME_OF_TABLE, null, contentValues)
    }

    fun updateRow(title: String, body: String, color: Int, id: Int) {
        val database = writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_TITLE, title)
        contentValues.put(COLUMN_BODY, body)
        contentValues.put(COLUMN_COLOR, color)

        val strFilter = "$COLUMN_ID = $id"

        database.update(NAME_OF_TABLE, contentValues, strFilter, null)
    }

    fun deleteRow(id: Int) {
        val database = writableDatabase
        val delCount = database.delete(NAME_OF_TABLE, "$COLUMN_ID = $id", null)
    }


    fun getData(arrayListOFNoteModels: ArrayList<NoteModel>): ArrayList<NoteModel> {
        arrayListOFNoteModels.clear()

        val database = writableDatabase
        val columns = arrayOf(COLUMN_ID, COLUMN_TITLE, COLUMN_BODY, COLUMN_COLOR)

        val cursor = database.query(NAME_OF_TABLE, columns, null, null, null, null, null)
        if (cursor.moveToNext()) {
            val idFromTable = cursor.getColumnIndex(COLUMN_ID)
            val titleFromTable = cursor.getColumnIndex(COLUMN_TITLE)
            val bodyFromTable = cursor.getColumnIndex(COLUMN_BODY)
            val colorFromTable = cursor.getColumnIndex(COLUMN_COLOR)
            do {
                val notes = NoteModel(
                        cursor.getInt(idFromTable).toLong(),
                        cursor.getString(titleFromTable),
                        cursor.getString(bodyFromTable),
                        cursor.getInt(colorFromTable))
                arrayListOFNoteModels.add(notes)
            } while (cursor.moveToNext())
            cursor.close()
        }
        return arrayListOFNoteModels
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

    }

    companion object {
        const val NAME_OF_TABLE = "SavesNotes"
    }
}