package com.example.blocknotische.screens.editNote

import com.arellomobile.mvp.MvpView

interface EditNoteView : MvpView {

    fun closeFragment()
    fun showMessageAccess()
    fun showMessageFail()
    fun setInitialValues(title: String?, body: String?)
    fun setDataToFields(title: String?, body: String?)

}