package com.example.blocknotische.notesList.mvp

import com.arellomobile.mvp.MvpView
import com.example.blocknotische.dataBase.NoteModel

interface NotesListView : MvpView{

    fun showListOfNotes(list : ArrayList<NoteModel>)
}