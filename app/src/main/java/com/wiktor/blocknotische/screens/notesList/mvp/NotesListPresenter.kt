package com.wiktor.blocknotische.screens.notesList.mvp

import com.wiktor.blocknotische.dataBase.AppDataBase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class NotesListPresenter(
        var mView: NotesListFragment,
        db: AppDataBase) : NotesListMainContract.Presenter {

    var modelDao = db.modelDao()

    override fun start() {
        modelDao.getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    mView.showListOfNotes(it)
                }, {
                })
    }
}