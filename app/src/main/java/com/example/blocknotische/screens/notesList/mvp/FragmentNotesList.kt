package com.example.blocknotische.screens.notesList.mvp

import android.graphics.Color
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.blocknotische.MainActivity
import com.example.blocknotische.R
import com.example.blocknotische.dataBase.DbHelper
import com.example.blocknotische.dataBase.NoteModel
import com.example.blocknotische.screens.newNote.NewNoteFragment
import com.example.blocknotische.screens.noteInfo.NoteInfoFragment
import com.example.blocknotische.screens.notesList.adapter.ClickInterface
import com.example.blocknotische.screens.notesList.adapter.NotesAdapter
import kotlinx.android.synthetic.main.fragment_notes_list.*

class FragmentNotesList : MvpAppCompatFragment(), ClickInterface, NotesListView {


    @InjectPresenter
    lateinit var presenter: NotesListPresenter

    @ProvidePresenter
    fun providePresenter(): NotesListPresenter {
        return NotesListPresenter(dbHelper)
    }

    val dbHelper by lazy { DbHelper(context) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_notes_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter.start()

    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).apply {
            setMyTitle(getString(R.string.app_name))
            showArrow(false)
            titleColor = Color.WHITE
        }
    }

    override fun onStop() {
        super.onStop()
        presenter.closeDatabase()
    }

    override fun click(model: NoteModel) {
        val fragmentNoteInfo = NoteInfoFragment.newInstance(model)
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

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        menu?.clear()
        inflater?.inflate(R.menu.toolbar_menu_for_note_list, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.new_note -> openFragmentNewNote()
        }
        return false
    }

    override fun showListOfNotes(list: ArrayList<NoteModel>) {
        my_recycler_view.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = NotesAdapter(list, this@FragmentNotesList)
        }
    }
}