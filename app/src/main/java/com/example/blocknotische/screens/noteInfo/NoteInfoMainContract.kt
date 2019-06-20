package com.example.blocknotische.screens.noteInfo

import com.example.blocknotische.dataBase.NoteModel

interface NoteInfoMainContract {

    interface Presenter{
        fun deleteNote()
        fun shareNote()
        fun setDataToFields()
    }


    interface View {
        fun showMessageDelete()
        fun closeFragment()
        fun setIntentForSharing(title: String, body: String)
        fun setDataToFields(noteModel: NoteModel)
    }
}