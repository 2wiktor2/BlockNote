package com.example.blocknotische.screens.newNote

interface NewNoteMainContract {
    interface Presenter{
        fun createNewNote(title: String, body: String)
        fun selectColor(i: Int)
        fun closeDatabase()
    }
    interface View{
        fun showAccessMessage()
        fun showFailMessage()
        fun closeFragment()
    }
}