package com.example.blocknotische.screens.noteInfo

import com.example.blocknotische.dataBase.DbHelper
import com.example.blocknotische.dataBase.NoteModel


class NoteInfoPresenter (
        private val mView: NoteInfoFragment,
        private val dbHelper: DbHelper,
        private val noteModel: NoteModel?
) : NoteInfoMainContract.Presenter {


    override fun setDataToFields() {
        noteModel?.let {
            mView.setDataToFields(it)
        }
    }

    override fun deleteNote() {
        noteModel?.id?.let { dbHelper.deleteRow(it) }
        mView.closeFragment()
        mView.showMessageDelete()
    }

    override fun shareNote() {
        noteModel?.let { mView.setIntentForSharing(it.title, it.body) }
    }

}