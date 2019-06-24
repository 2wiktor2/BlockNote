package com.example.blocknotische.screens.editNote

import android.util.Log
import com.example.blocknotische.dataBase.AppDataBase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class EditNotePresenter(
        var mView: EditNoteFragment,
        private val db: AppDataBase?,
        val title: String?,
        var body: String?,
        var importanceOfANote: Int?,
        var id: Long?) : EditNOteMainContract.Presenter {

    var modelDao = db?.modelDao()


    override fun setDataToFields() {
        mView.setDataToFields(title, body)
    }


    override fun updateNote(newTitle: String, newBody: String) {

        if (newTitle == "" && newBody == "") {
            mView.showMessageFail()
        } else modelDao?.updateRow(newTitle, newBody, importanceOfANote, id)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({
                    mView.closeFragment()
                    mView.showMessageAccess()
                }, {
                })
    }

    override fun returnInitialValues() {
        mView.setInitialValues(title, body)
    }

    override fun closeDatabase() {
        db?.close()
    }
}



