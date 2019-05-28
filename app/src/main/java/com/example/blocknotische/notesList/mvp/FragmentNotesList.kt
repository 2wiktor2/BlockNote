package com.example.blocknotische.notesList.mvp

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import com.example.blocknotische.MainActivity
import com.example.blocknotische.R
import com.example.blocknotische.dataBase.DbHelper
import com.example.blocknotische.dataBase.NoteModel
import com.example.blocknotische.newNote.FragmentNewNote
import com.example.blocknotische.noteInfo.FragmentNoteInfo
import com.example.blocknotische.notesList.adapter.ClickInterface
import com.example.blocknotische.notesList.adapter.NotesAdapter
import kotlinx.android.synthetic.main.fragment_notes_list.*
import java.util.ArrayList

class FragmentNotesList : Fragment(), ClickInterface {

    internal lateinit var dbHelper: DbHelper
    internal var noteModels = ArrayList<NoteModel>()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_notes_list, container, false)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        dbHelper = DbHelper(context!!)
        showNoteList()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onResume() {
        super.onResume()
        noteModels = dbHelper.getData(noteModels)
        (activity as MainActivity).apply {
            setMyTitle("BlockNotiSche")
            showArrow(false)
            titleColor = Color.WHITE
        }
    }

    override fun click(model: NoteModel) {
        val fragmentNoteInfo = FragmentNoteInfo.newInstance(model)
        val manager = fragmentManager ?: return
        val transaction = manager.beginTransaction()
        transaction.apply {
            replace(R.id.recycler_view_container, fragmentNoteInfo)
            addToBackStack(null)
            commit()
        }
    }

    private fun showNoteList() {
        my_recycler_view.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = NotesAdapter(noteModels, this@FragmentNotesList)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.toolbar_menu_for_note_list, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.new_note -> openFragmentNewNote()
        }
        return false
    }

    private fun openFragmentNewNote() {
        val fragmentNewNote = FragmentNewNote()
        val fragmentTransaction = activity?.supportFragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.recycler_view_container, fragmentNewNote)
        fragmentTransaction?.addToBackStack("")
        fragmentTransaction?.commit()
    }
}