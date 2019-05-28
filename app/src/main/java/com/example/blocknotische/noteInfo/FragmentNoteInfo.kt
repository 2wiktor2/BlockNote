package com.example.blocknotische.noteInfo

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import android.widget.Toast
import com.example.blocknotische.MainActivity
import com.example.blocknotische.R
import com.example.blocknotische.dataBase.DbHelper
import com.example.blocknotische.dataBase.NoteModel
import com.example.blocknotische.editNote.FragmentEditNote
import kotlinx.android.synthetic.main.fragment_note_info.*

class FragmentNoteInfo : Fragment() {



    private lateinit var title: String
    private lateinit var body: String
    private var color: Int = 0
    private var idOfNote: Int = 0

    private lateinit var dbHelper: DbHelper

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_note_info, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getDataFomBundle()
        note_title.text = title
        note_body.text = body

        dbHelper = DbHelper(context!!)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).apply {
            setMyTitle(title as String)
            showArrow(true)
            titleColor = color
        }

    }


    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater!!.inflate(R.menu.toolbar_menu_note_info, menu)
        menu!!.setGroupVisible(R.menu.toolbar_menu_for_note_list, false)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun deleteRow() {
        dbHelper.deleteRow(idOfNote)
    }

    private fun closeFragment() {
        activity!!.supportFragmentManager.popBackStack()
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.item_edit -> {
                val fragmentEditNote = FragmentEditNote.newInstance(title, body, color, idOfNote)

                val manager = fragmentManager!!
                val transaction = manager.beginTransaction()
                transaction.replace(R.id.recycler_view_container, fragmentEditNote)
                transaction.addToBackStack(null)
                transaction.commit()
                Toast.makeText(context, "Edit", Toast.LENGTH_SHORT).show()
            }

            R.id.item_delete -> {
                deleteRow()
                closeFragment()
                Toast.makeText(context, "заметка удалена", Toast.LENGTH_SHORT).show()
            }
        }
        return false
    }

    private fun getDataFomBundle() {
        val arg = arguments
        if (arg != null) {
            val noteModel = arg.getSerializable(KEY_NOTE_MODEL) as NoteModel
            title = noteModel.title
            body = noteModel.body
            color = noteModel.color
            idOfNote = noteModel.id.toInt()
        }
    }

    companion object {
        const val KEY_NOTE_MODEL = "key_note_model"

        fun newInstance(noteModel: NoteModel): FragmentNoteInfo {
            val fragmentNoteInfo = FragmentNoteInfo()
            val bundle = Bundle()
            bundle.putSerializable(KEY_NOTE_MODEL, noteModel)
            fragmentNoteInfo.arguments = bundle
            return fragmentNoteInfo
        }
    }

}
