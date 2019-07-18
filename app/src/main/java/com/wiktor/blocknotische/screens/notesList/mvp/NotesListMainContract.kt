package com.wiktor.blocknotische.screens.notesList.mvp

import com.wiktor.blocknotische.dataBase.NotesModel

interface NotesListMainContract {
    interface Presenter {
        fun start()
    }

    interface View {
        fun showListOfNotes(list: List<NotesModel>)
    }
}