package com.example.blocknotische.dataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(NotesModel::class), version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun modelDao(): ModelDao


    companion object {

        @Volatile private var INSTANCE: AppDataBase? = null

        fun getInstance(context: Context): AppDataBase =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
                }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(context.applicationContext,
                        AppDataBase::class.java, "NotesModel")
                        .build()
    }
}




