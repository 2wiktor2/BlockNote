package com.example.blocknotische.screens.editNote

import com.example.blocknotische.dataBase.DbHelper

class EditNotePresenter(
        var mView: EditNoteFragment,
        val dbHelper: DbHelper,
        val title: String?,
        var body: String?,
        var color: Int?,
        var id: Long?) : EditNOteMainContract.Presenter {


    override fun setDataToFields() {
        mView.setDataToFields(title, body)
    }

    override fun updateNote(newTitle: String, newBody: String) {

        if (newTitle == "" && newBody == "") {
            mView.showMessageFail()
        } else {
            dbHelper.updateRow(newTitle, newBody, color, id)
            mView.closeFragment()
            mView.showMessageAccess()
        }
    }

    override fun returnInitialValues() {
        mView.setInitialValues(title, body)
    }

    override fun closeDatabase() {
        dbHelper.close()
    }
}



