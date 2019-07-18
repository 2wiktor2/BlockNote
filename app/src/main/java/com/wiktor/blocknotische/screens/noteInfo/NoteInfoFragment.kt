package com.wiktor.blocknotische.screens.noteInfo

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.wiktor.blocknotische.MainActivity
import com.wiktor.blocknotische.R
import com.wiktor.blocknotische.dataBase.AppDataBase
import com.wiktor.blocknotische.dataBase.NotesModel
import com.wiktor.blocknotische.screens.editNote.EditNoteFragment
import kotlinx.android.synthetic.main.fragment_note_info.*

class NoteInfoFragment : Fragment(), NoteInfoMainContract.View {

    private lateinit var mPresenter: NoteInfoPresenter

    val db by lazy { AppDataBase.getInstance(context) }

    companion object {

        const val KEY_NOTE_MODEL = "key_note_model"
        fun newInstance(noteModel: NotesModel): NoteInfoFragment {
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

        val model = arguments?.getSerializable(KEY_NOTE_MODEL)

        mPresenter = NoteInfoPresenter(this, db, model as NotesModel?)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_note_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPresenter.setDataToFields()
    }

    override fun setDataToFields(noteModel: NotesModel) {
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
                    val model = it.getSerializable(KEY_NOTE_MODEL) as NotesModel
                    val fragmentEditNote =
                            EditNoteFragment.newInstance(
                                    model.title,
                                    model.body,
                                    model.importanceOfANote,
                                    model.id
                            )

                    val manager = fragmentManager
                    val transaction = manager?.beginTransaction()
                    transaction?.let { it ->
                        it.replace(R.id.recycler_view_container, fragmentEditNote)
                       // it.addToBackStack(null)
                        it.commit()
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

    override fun setIntentForSharing(title: String?, body: String?) {
        val message = """
            $title
            $body"""
        val bundleForSharing = Bundle()
        bundleForSharing.apply {
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

