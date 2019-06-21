package com.example.blocknotische.screens.notesList.mvp

import android.util.Log
import com.example.blocknotische.dataBase.AppDataBase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class NotesListPresenter(
        var mView: NotesListFragment,
        db: AppDataBase?) : NotesListMainContract.Presenter {

    var modelDao = db?.modelDao()


    override fun start() {
        modelDao?.getData()
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({
                    mView.showListOfNotes(it)
                }, {
                    Log.d("qwerty", "Not Ok  $it")
                })


    }

    override fun closeDatabase() {
        // dbHelper.close()
    }
}