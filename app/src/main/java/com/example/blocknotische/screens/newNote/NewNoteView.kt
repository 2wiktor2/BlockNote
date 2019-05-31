package com.example.blocknotische.screens.newNote

import com.arellomobile.mvp.MvpView

interface NewNoteView : MvpView{

    fun showAccessMessage()
    fun showFailMessage()
    fun closeFragment()
}