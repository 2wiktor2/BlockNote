package com.example.blocknotische.screens.noteInfo

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.blocknotische.dataBase.DbHelper
import com.example.blocknotische.dataBase.NoteModel

@InjectViewState
class NoteInfoPresenter(val dbHelper: DbHelper, val noteModel: NoteModel?) : MvpPresenter<NoteInfoView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        noteModel?.let {
            viewState.setDataToFields(it)
        }
    }

    fun deleteNote() {
        noteModel?.id?.let { dbHelper.deleteRow(it) }
        viewState.closeFragment()
        viewState.showMessageDelete()
    }

    fun shareNote() {
        noteModel?.let { viewState.setIntentForSharing(it.title, it.body) }
    }

}