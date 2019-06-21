package com.example.blocknotische.screens.notesList.mvp

import com.example.blocknotische.dataBase.NotesModel

interface NotesListMainContract {
    interface Presenter{
        fun start ()
        fun closeDatabase()
    }

    interface View{
        fun showListOfNotes(list: List<NotesModel>)
    }
}