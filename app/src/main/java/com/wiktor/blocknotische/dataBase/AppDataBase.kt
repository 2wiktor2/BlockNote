package com.wiktor.blocknotische.dataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(NotesModel::class), version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {

    abstract fun modelDao(): ModelDao

    companion object {

        private var INSTANCE: AppDataBase? = null
        fun getInstance(context: Context?): AppDataBase {
            if (INSTANCE == null) {
                if (context != null) {
                    INSTANCE = Room
                            .databaseBuilder(
                                    context.applicationContext,
                                    AppDataBase::class.java,
                                    "NotesModel")
                            .build()
                }
            }
            return INSTANCE as AppDataBase
        }
    }
}




