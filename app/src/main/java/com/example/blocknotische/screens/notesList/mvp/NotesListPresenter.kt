package com.example.blocknotische.screens.notesList.mvp

import com.example.blocknotische.dataBase.DbHelper



class NotesListPresenter(
        var mView: NotesListFragment,
        val dbHelper: DbHelper) : NotesListMainContract.Presenter {


    override fun start (){
        val list = dbHelper.getData()
        mView.showListOfNotes(list)
    }
    override fun closeDatabase() {
        dbHelper.close()
    }
}