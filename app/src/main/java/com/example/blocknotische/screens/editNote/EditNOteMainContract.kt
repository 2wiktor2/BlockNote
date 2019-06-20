package com.example.blocknotische.screens.editNote

interface EditNOteMainContract {

    interface Presenter{
        fun setDataToFields()
        fun updateNote(newTitle: String, newBody: String)
        fun returnInitialValues()
        fun closeDatabase()
    }
    interface View{
        fun closeFragment()
        fun showMessageAccess()
        fun showMessageFail()
        fun setInitialValues(title: String?, body: String?)
        fun setDataToFields(title: String?, body: String?)

    }

}