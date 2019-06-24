package com.example.blocknotische.screens.editNote

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.blocknotische.MainActivity
import com.example.blocknotische.R
import com.example.blocknotische.dataBase.AppDataBase
import kotlinx.android.synthetic.main.fragment_edit_note.*

class EditNoteFragment : Fragment(), EditNOteMainContract.View {

    private lateinit var mPresenter: EditNotePresenter

    companion object {
        private const val KEY_TITLE = "key_title"
        private const val KEY_BODY = "key_body"
        private const val KEY_COLOR = "key_color"
        private const val KEY_ID = "key_id"

        fun newInstance(title: String?, body: String?, color: Int, id: Long): EditNoteFragment {
            val fragmentEditNote = EditNoteFragment()
            val bundle = Bundle()
            bundle.apply {
                putString(KEY_TITLE, title)
                putString(KEY_BODY, body)
                putInt(KEY_COLOR, color)
                putLong(KEY_ID, id)
            }
            fragmentEditNote.arguments = bundle
            return fragmentEditNote
        }
    }

    val db = AppDataBase.getInstance(context)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val title = arguments?.getString(KEY_TITLE)
        val body = arguments?.getString(KEY_BODY)
        val color = arguments?.getInt(KEY_COLOR)
        val id = arguments?.getLong(KEY_ID)


        mPresenter = EditNotePresenter(this, db, title, body, color, id)

        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_edit_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPresenter.setDataToFields()

    }

    override fun setDataToFields(title: String?, body: String?) {
        edit_note_title.setText(title)
        edit_note_body.setText(body)
    }


    override fun onResume() {
        super.onResume()
        (activity as MainActivity).apply {
            setMyTitle(getString(R.string.edit_note))
            showArrow(true)
        }
    }

    override fun onStop() {
        super.onStop()
        mPresenter.closeDatabase()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu_for_edit_note, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_save -> {
                editRow()
            }
            R.id.item_cancel_changes -> {
                mPresenter.returnInitialValues()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun setInitialValues(title: String?, body: String?) {
        edit_note_title.setText(title)
        edit_note_body.setText(body)
    }

    override fun closeFragment() {
        activity?.supportFragmentManager?.popBackStack()
    }

    private fun editRow() {
        val newTitle = edit_note_title.text.toString()
        val newBody = edit_note_body.text.toString()
        mPresenter.updateNote(newTitle, newBody)
    }

    override fun showMessageAccess() {
        Toast.makeText(context, R.string.note_was_saved, Toast.LENGTH_SHORT).show()
    }

    override fun showMessageFail() {
        Toast.makeText(context, R.string.note_was_not_saved, Toast.LENGTH_LONG).show()
    }
}
