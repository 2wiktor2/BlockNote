package com.example.blocknotische.screens.newNote

import android.util.Log
import com.example.blocknotische.dataBase.AppDataBase
import com.example.blocknotische.dataBase.NotesModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class NewNotePresenter(
        var mView: NewNoteFragment,
        db: AppDataBase?) : NewNoteMainContract.Presenter {

    var modelDao = db?.modelDao()

    var color = 1


    override fun createNewNote(title: String, body: String) {
        val model = NotesModel(title, body, color)
        if (title != "" && body != "") {
            modelDao?.createRow(model)?.subscribeOn(Schedulers.io())
                    ?.observeOn(AndroidSchedulers.mainThread())
                    ?.subscribe(
                            {
                                mView.closeFragment()
                                mView.showAccessMessage()
                                Log.d("qwerty", "Ок")
                            },
                            {
                                Log.d("qwerty", "Not Ok")
                            }
                    )
        } else {
            mView.showFailMessage()
        }
    }


    override fun selectColor(i: Int) {
        color = i
    }

    override fun closeDatabase() {
//        dbHelper.close()
    }
}


