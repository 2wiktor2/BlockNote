package com.example.blocknotische.dataBase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
class NotesModel(
        @ColumnInfo(name = "title")
        val title: String?,
        @ColumnInfo(name = "body")
        val body: String?,
        @ColumnInfo(name = "color")
        val color: Int = 1,
        @PrimaryKey(autoGenerate = true)
        val id: Long = 0
) : Serializable
