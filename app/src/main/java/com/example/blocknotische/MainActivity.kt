package com.example.blocknotische

import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.blocknotische.screens.notesList.mvp.NotesListFragment
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        openFragmentNotesList()

        setSupportActionBar(my_toolbar)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun openFragmentNotesList() {
        val notesListFragment = NotesListFragment()
        val fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().replace(R.id.recycler_view_container, notesListFragment).commit()
    }

    fun showArrow(show: Boolean) {
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(show)
            it.setDisplayShowHomeEnabled(show)
        }
    }

    fun setMyTitle(title: String) {
        my_toolbar.title = title
    }

    override fun setTitleColor(color: Int) {
        when (color) {
            1 -> my_toolbar.setTitleTextColor(Color.GRAY)
            2 -> my_toolbar.setTitleTextColor(Color.GREEN)
            3 -> my_toolbar.setTitleTextColor(Color.YELLOW)
            4 -> my_toolbar.setTitleTextColor(Color.RED)
        }
    }
}