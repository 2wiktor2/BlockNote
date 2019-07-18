package com.wiktor.blocknotische.dataBase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.wiktor.blocknotische.Importance
import java.io.Serializable

@Entity
class NotesModel(
        @ColumnInfo(name = "title")
        val title: String,
        @ColumnInfo(name = "body")
        val body: String,
        @ColumnInfo(name = "importanceOfANote")
        val importanceOfANote: Int = Importance.UNIMPORTANT.importance,
        @PrimaryKey(autoGenerate = true)
        val id: Long = 0
) : Serializable
