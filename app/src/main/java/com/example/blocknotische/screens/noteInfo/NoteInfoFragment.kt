package com.example.blocknotische.screens.noteInfo

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.blocknotische.MainActivity
import com.example.blocknotische.R
import com.example.blocknotische.dataBase.DbHelper
import com.example.blocknotische.dataBase.NoteModel
import com.example.blocknotische.screens.editNote.EditNoteFragment
import kotlinx.android.synthetic.main.fragment_note_info.*

class NoteInfoFragment : Fragment(), NoteInfoMainContract.View {

    private lateinit var mPresenter: NoteInfoPresenter

    val dbHelper by lazy { DbHelper(context) }

    companion object {

        const val KEY_NOTE_MODEL = "key_note_model"
        fun newInstance(noteModel: NoteModel): NoteInfoFragment {
            val fragmentNoteInfo = NoteInfoFragment()
            val bundle = Bundle()
            bundle.putSerializable(KEY_NOTE_MODEL, noteModel)
            fragmentNoteInfo.arguments = bundle
            return fragmentNoteInfo
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        val noteModel: NoteModel? =
                arguments?.getSerializable(KEY_NOTE_MODEL) as NoteModel

        mPresenter = NoteInfoPresenter(this, dbHelper, noteModel)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_note_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPresenter.setDataToFields()
    }

    override fun setDataToFields(noteModel: NoteModel) {
        note_info_title.text = noteModel.title
        note_info_body.text = noteModel.body
        (activity as MainActivity).apply {
            setMyTitle(noteModel.title)
            showArrow(true)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.toolbar_menu_note_info, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_edit -> {
                arguments?.let {
                    val noteModel = it.getSerializable(KEY_NOTE_MODEL) as NoteModel
                    val fragmentEditNote =
                            EditNoteFragment.newInstance(
                                    noteModel.title,
                                    noteModel.body,
                                    noteModel.color,
                                    noteModel.id
                            )

                    val manager = fragmentManager
                    val transaction = manager?.beginTransaction()
                    transaction?.let { fragmentTransaction ->
                        fragmentTransaction.replace(R.id.recycler_view_container, fragmentEditNote)
                        fragmentTransaction.addToBackStack(null)
                        fragmentTransaction.commit()
                    }
                }
            }

            R.id.item_delete -> {
                mPresenter.deleteNote()
            }
            R.id.item_share -> {
                mPresenter.shareNote()
            }
        }
        return false
    }

    override fun setIntentForSharing(title: String, body: String) {
        val message = """
            $title
            |$body"""
        val bundleForSharing = Bundle()
        bundleForSharing.apply {
            //            putString(Intent.EXTRA_TITLE, "Заголовок")
//            putString(Intent.EXTRA_TEXT, "$title\n$body")

            putString(Intent.EXTRA_TEXT, message)
        }
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtras(bundleForSharing)
            type = "text/plain"
        }
        startActivity(sendIntent)
    }


    override fun closeFragment() {
        activity?.supportFragmentManager?.popBackStack()
    }

    override fun showMessageDelete() {
        Toast.makeText(context, R.string.delete_note, Toast.LENGTH_SHORT).show()

    }

}

