package com.example.blocknotische.screens.notesList.mvp

import android.graphics.Color
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.blocknotische.MainActivity
import com.example.blocknotische.R
import com.example.blocknotische.dataBase.AppDataBase
import com.example.blocknotische.dataBase.NotesModel
import com.example.blocknotische.screens.newNote.NewNoteFragment
import com.example.blocknotische.screens.noteInfo.NoteInfoFragment
import com.example.blocknotische.screens.notesList.adapter.ClickInterface
import com.example.blocknotische.screens.notesList.adapter.NotesAdapter
import kotlinx.android.synthetic.main.fragment_notes_list.*

class NotesListFragment : Fragment(), ClickInterface, NotesListMainContract.View {

    private lateinit var mPresenter: NotesListPresenter

    val db by lazy { AppDataBase.getInstance(context)  }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        mPresenter = NotesListPresenter(this, db)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_notes_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mPresenter.start()

    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).apply {
            setMyTitle(getString(R.string.app_name))
            showArrow(false)
            titleColor = Color.WHITE
        }
    }
    override fun click(notesModel: NotesModel) {
        val fragmentNoteInfo = NoteInfoFragment.newInstance(notesModel)
        //  val manager = fragmentManager ?: return
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        transaction?.apply {
            replace(R.id.recycler_view_container, fragmentNoteInfo)
            addToBackStack("")
            commit()
        }
    }

    private fun openFragmentNewNote() {
        val fragmentNewNote = NewNoteFragment()
        val fragmentTransaction = activity?.supportFragmentManager?.beginTransaction()
        fragmentTransaction?.apply {
            replace(R.id.recycler_view_container, fragmentNewNote)
            addToBackStack("")
            commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.toolbar_menu_for_note_list, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.new_note -> openFragmentNewNote()
        }
        return false
    }

    override fun showListOfNotes(list: List<NotesModel>) {
        my_recycler_view.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = NotesAdapter(list, this@NotesListFragment)
        }
    }
}