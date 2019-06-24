package com.example.blocknotische.screens.newNote

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.blocknotische.Importance
import com.example.blocknotische.MainActivity
import com.example.blocknotische.R
import com.example.blocknotische.dataBase.AppDataBase
import kotlinx.android.synthetic.main.fragment_new_note.*

class NewNoteFragment : Fragment(), View.OnClickListener, NewNoteMainContract.View {

    private lateinit var mPresenter: NewNotePresenter
    private val db by lazy { context?.let { AppDataBase.getInstance(it) } }
    var model = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        mPresenter = NewNotePresenter(this, db)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_new_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        b_unimportant.setOnClickListener(this)
        b_porly_important.setOnClickListener(this)
        b_medium_important.setOnClickListener(this)
        b_very_important.setOnClickListener(this)
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
        mPresenter.closeDatabase()
    }

    override fun closeFragment() {
        activity?.supportFragmentManager?.popBackStack()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu_for_new_note, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_save -> {
                val title = et_new_note_title.text.toString()
                val body = et_new_note_body.text.toString()
                mPresenter.createNewNote(title, body)
                return true
            }
        }
        return false
    }

    @SuppressLint("ResourceAsColor")
    override fun onClick(v: View) {
        if (activity != null) {
            when (v.id) {
                R.id.b_unimportant -> {
                    mPresenter.selectColor(Importance.UNIMPORTANT.importance)
                    (activity as MainActivity).titleColor = Importance.UNIMPORTANT.importance
                }
                R.id.b_porly_important -> {
                    mPresenter.selectColor(Importance.POORLY_IMPORTANT.importance)
                    (activity as MainActivity).titleColor = Importance.POORLY_IMPORTANT.importance
                }
                R.id.b_medium_important -> {
                    mPresenter.selectColor(Importance.MEDIUM_IMPORTANT.importance)
                    (activity as MainActivity).titleColor = Importance.MEDIUM_IMPORTANT.importance
                }
                R.id.b_very_important -> {
                    mPresenter.selectColor(Importance.VERY_IMPORTANT.importance)
                    (activity as MainActivity).titleColor = Importance.VERY_IMPORTANT.importance
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


