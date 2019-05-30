package com.example.blocknotische.screens.newNote

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import android.widget.Toast
import com.example.blocknotische.MainActivity
import com.example.blocknotische.R
import com.example.blocknotische.dataBase.DbHelper
import kotlinx.android.synthetic.main.fragment_new_note.*

class FragmentNewNote : Fragment(), View.OnClickListener {

    private lateinit var dbHelper: DbHelper
    private var color = 1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_new_note, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        b_new_color_1.setOnClickListener(this)
        b_new_color_2.setOnClickListener(this)
        b_new_color_3.setOnClickListener(this)
        b_new_color_4.setOnClickListener(this)

        dbHelper = DbHelper(context)
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).apply {
            setMyTitle("Новая заметка")
            showArrow(true)
        }
    }

    private fun closeFragment() {
        activity?.supportFragmentManager?.popBackStack()
    }

    private fun addRow() {
        if (et_new_note_title.text.toString() == "" || et_new_note_body.text.toString() == "") {
            Toast.makeText(context, "Заметка не должна быть пустой", Toast.LENGTH_LONG).show()
        } else {
            val title = et_new_note_title.text.toString()
            val body = et_new_note_body.text.toString()
            dbHelper.createRow(title, body, color)
            Toast.makeText(context, "заметка сохранена", Toast.LENGTH_SHORT).show()
            closeFragment()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.toolbar_menu_for_new_note, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.item_save -> {
                addRow()
            }
        }
        return false
    }

    override fun onStop() {
        super.onStop()
        dbHelper.close()
    }

    @SuppressLint("ResourceAsColor")
    override fun onClick(v: View) {
        if (activity != null) {
            when (v.id) {
                R.id.b_new_color_1 -> {
                    color = 1
                    (activity as MainActivity).titleColor = color
                }
                R.id.b_new_color_2 -> {
                    color = 2
                    (activity as MainActivity).titleColor = color
                }
                R.id.b_new_color_3 -> {
                    color = 3
                    (activity as MainActivity).titleColor = color
                }
                R.id.b_new_color_4 -> {
                    color = 4
                    (activity as MainActivity).titleColor = color
                }
            }
        }
    }
}
