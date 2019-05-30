package com.example.blocknotische.notesList.mvp

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.blocknotische.dataBase.DbHelper


@InjectViewState
class NotesListPresenter( val dbHelper: DbHelper) : MvpPresenter<NotesListView>() {

/*    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
    }*/

    fun start (){
        val list = dbHelper.getData()
        viewState.showListOfNotes(list)
    }
}