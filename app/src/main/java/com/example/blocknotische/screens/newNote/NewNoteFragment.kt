package com.example.blocknotische.screens.newNote

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.blocknotische.MainActivity
import com.example.blocknotische.R
import com.example.blocknotische.dataBase.DbHelper
import kotlinx.android.synthetic.main.fragment_new_note.*

class NewNoteFragment : MvpAppCompatFragment(), View.OnClickListener, NewNoteView {
    @InjectPresenter
    lateinit var presenter: NewNotePresenter


    @ProvidePresenter
    fun providePresenter(): NewNotePresenter {
        return NewNotePresenter(dbHelper)
    }

    val dbHelper by lazy { DbHelper(context) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_new_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        b_new_color_1.setOnClickListener(this)
        b_new_color_2.setOnClickListener(this)
        b_new_color_3.setOnClickListener(this)
        b_new_color_4.setOnClickListener(this)
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).apply {
            setMyTitle(getString(R.string.new_note))
            showArrow(true)
        }
    }

    override fun onStop() {
        super.onStop()
        presenter.closeDatabase()
    }

    override fun closeFragment() {
        activity?.supportFragmentManager?.popBackStack()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.toolbar_menu_for_new_note, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.item_save -> {
                val title = et_new_note_title.text.toString()
                val body = et_new_note_body.text.toString()
                presenter.createNewNote(title, body)
            }
        }
        return false
    }

    @SuppressLint("ResourceAsColor")
    override fun onClick(v: View) {
        if (activity != null) {
            when (v.id) {
                R.id.b_new_color_1 -> {
                    presenter.selectColor(1)
                    (activity as MainActivity).titleColor = 1
                }
                R.id.b_new_color_2 -> {
                    presenter.selectColor(2)
                    (activity as MainActivity).titleColor = 2
                }
                R.id.b_new_color_3 -> {
                    presenter.selectColor(3)
                    (activity as MainActivity).titleColor = 3
                }
                R.id.b_new_color_4 -> {
                    presenter.selectColor(4)
                    (activity as MainActivity).titleColor = 4
                }
            }
        }
    }

    override fun showAccessMessage() {
        Toast.makeText(context, R.string.note_was_saved, Toast.LENGTH_SHORT).show()
    }

    override fun showFailMessage() {
        Toast.makeText(context, R.string.note_was_not_saved, Toast.LENGTH_LONG).show()
    }
}


