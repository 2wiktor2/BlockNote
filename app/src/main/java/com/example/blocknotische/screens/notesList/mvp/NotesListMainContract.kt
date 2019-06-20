package com.example.blocknotische.screens.notesList.mvp

import com.example.blocknotische.dataBase.NoteModel

interface NotesListMainContract {
    interface Presenter{
        fun start ()
        fun closeDatabase()
    }

    interface View{
        fun showListOfNotes(list : ArrayList<NoteModel>)
    }
}