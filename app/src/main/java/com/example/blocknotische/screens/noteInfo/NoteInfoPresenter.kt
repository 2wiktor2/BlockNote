package com.example.blocknotische.screens.noteInfo

import com.example.blocknotische.dataBase.AppDataBase
import com.example.blocknotische.dataBase.NotesModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class NoteInfoPresenter(
        private val mView: NoteInfoFragment,
        db: AppDataBase,
        private val notesModel: NotesModel?
) : NoteInfoMainContract.Presenter {

    var modelDao = db.modelDao()


    override fun setDataToFields() {
        notesModel?.let {
            mView.setDataToFields(it)
        }
    }

    override fun deleteNote() {
        notesModel?.id?.let {
            modelDao.deleteRow(it)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        mView.closeFragment()
                        mView.showMessageDelete()
                    }, {
                    })
        }

    }

    override fun shareNote() {
        notesModel.let { mView.setIntentForSharing(it?.title, it?.body) }
    }

}