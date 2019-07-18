package com.wiktor.blocknotische.screens.notesList.adapter

import com.wiktor.blocknotische.dataBase.NotesModel

interface ClickInterface {
    fun click(notesModel: NotesModel)
}