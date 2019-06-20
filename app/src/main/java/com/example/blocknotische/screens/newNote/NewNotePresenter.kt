package com.example.blocknotische.screens.newNote


import com.example.blocknotische.dataBase.DbHelper

class NewNotePresenter(
        var mView: NewNoteFragment,
        val dbHelper: DbHelper) : NewNoteMainContract.Presenter {


    var color = 1

    override fun createNewNote(title: String, body: String) {

        if (title != "" && body != "") {
            dbHelper.createRow(title, body, color)
            mView.closeFragment()
            mView.showAccessMessage()

        } else {
            mView.showFailMessage()
        }
    }


    override fun selectColor(i: Int) {
        color = i
    }

    override fun closeDatabase() {
        dbHelper.close()
    }
}


