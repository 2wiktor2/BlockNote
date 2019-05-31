package com.example.blocknotische.screens.newNote

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.blocknotische.dataBase.DbHelper

@InjectViewState
class NewNotePresenter(val dbHelper: DbHelper) : MvpPresenter<NewNoteView>() {

    var color = 1

    fun createNewNote(title: String, body: String) {

        if (title != "" && body != "") {
            dbHelper.createRow(title, body, color)
            viewState.closeFragment()
            viewState.showAccessMessage()

        } else {
            viewState.showFailMessage()
        }
    }


    fun selectColor(i: Int) {
        color = i
    }

    fun closeDatabase() {
        dbHelper.close()
    }
}


/*        if (et_new_note_title.text.toString() == "" || et_new_note_body.text.toString() == "") {
            Toast.makeText(context, "Заметка не должна быть пустой", Toast.LENGTH_LONG).show()
        } else {
            title = et_new_note_title.text.toString()
            body = et_new_note_body.text.toString()
            presenter.deleteNote(title, body, color)

            Toast.makeText(context, "заметка сохранена", Toast.LENGTH_SHORT).show()
            closeFragment()*/