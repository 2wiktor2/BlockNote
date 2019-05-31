package com.example.blocknotische.screens.noteInfo

import com.arellomobile.mvp.MvpView
import com.example.blocknotische.dataBase.NoteModel

interface NoteInfoView : MvpView {

    fun showMessageDelete()
    fun closeFragment()
    fun setIntentForSharing(title: String, body: String)
    fun setDataToFields(noteModel: NoteModel)
}