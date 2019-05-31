package com.example.blocknotische.screens.notesList.mvp

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.blocknotische.dataBase.DbHelper


@InjectViewState
class NotesListPresenter( val dbHelper: DbHelper) : MvpPresenter<NotesListView>() {

    fun start (){
        val list = dbHelper.getData()
        viewState.showListOfNotes(list)
    }
    fun closeDatabase() {
        dbHelper.close()
    }
}