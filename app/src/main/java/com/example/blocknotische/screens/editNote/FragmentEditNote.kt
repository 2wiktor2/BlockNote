package com.example.blocknotische.screens.editNote

import android.os.Bundle
import android.view.*
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.blocknotische.MainActivity
import com.example.blocknotische.R
import com.example.blocknotische.dataBase.DbHelper
import kotlinx.android.synthetic.main.fragment_edit_note.*

class FragmentEditNote : MvpAppCompatFragment(), EditNoteView {

    // todo переместить в нужное место
    override fun editListOfNotes() {
    }

    @InjectPresenter
    lateinit var presenter: EditNotePresenter
    @ProvidePresenter
    fun providePresenter(): EditNotePresenter{
        return EditNotePresenter(dbHelper)
    }

    private var title: String? = null
    private var body: String? = null
    private var idOfNote: Int = 0
    private var color: Int = 0

      val dbHelper by lazy { DbHelper(context) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_edit_note, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val arg = arguments
        if (arg != null) {
            title = arg.getString(KEY_TITLE)
            body = arg.getString(KEY_BODY)
            color = arg.getInt(KEY_COLOR)
            idOfNote = arg.getInt(KEY_ID)
        }
        edit_note_title.setText(title)
        edit_note_body.setText(body)
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).apply {
            setMyTitle("Редактирование заметки")
            showArrow(true)
        }
    }

    private fun editRow() {
        val newTitle = edit_note_title.text.toString()
        val newBody = edit_note_body.text.toString()
        dbHelper.updateRow(newTitle, newBody, color, idOfNote)
    }


    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.toolbar_menu_for_edit_note, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.item_save -> {
                editRow()
                closeFragment()
                Toast.makeText(context, "заметка сохранена", Toast.LENGTH_SHORT).show()
            }
            R.id.item_cancel_changes -> {
                edit_note_title.setText(title)
                edit_note_body.setText(body)
            }
        }
        return false
    }

    override fun onStop() {
        super.onStop()
        dbHelper.close()
    }

    private fun closeFragment() {
        activity?.supportFragmentManager?.popBackStack()
    }

    companion object {
        private const val KEY_TITLE = "key_title"
        private const val KEY_BODY = "key_body"
        private const val KEY_COLOR = "key_color"
        private const val KEY_ID = "key_id"

        fun newInstance(title: String, body: String, color: Int, id: Int): FragmentEditNote {
            val fragmentEditNote = FragmentEditNote()
            val bundle = Bundle()
            bundle.apply {
                putString(KEY_TITLE, title)
                putString(KEY_BODY, body)
                putInt(KEY_COLOR, color)
                putInt(KEY_ID, id)
            }
            fragmentEditNote.arguments = bundle
            return fragmentEditNote
        }
    }

}