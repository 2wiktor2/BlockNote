package com.example.blocknotische.screens.editNote

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.blocknotische.dataBase.DbHelper

@InjectViewState
class EditNotePresenter(
        val dbHelper: DbHelper,
        val title: String?,
        val body: String?,
        val color: Int?,
        val id: Long?) : MvpPresenter<EditNoteView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setDataToFields(title, body)

    }

    fun updateNote(newTitle: String, newBody: String) {
        if (newTitle == "" && newBody == "") {
            viewState.showMessageFail()
        } else {

            dbHelper.updateRow(newTitle, newBody, color, id)
            viewState.closeFragment()
            viewState.showMessageAccess()
        }
    }

    fun returnInitialValues() {
        viewState.setInitialValues(title, body)
    }

    fun closeDatabase() {
        dbHelper.close()
    }
}