package com.example.blocknotische.screens.editNote

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.blocknotische.dataBase.DbHelper


@InjectViewState
class EditNotePresenter(dbHelper: DbHelper) : MvpPresenter<EditNoteView>(){

    fun start (){
        viewState.editListOfNotes()
    }
}