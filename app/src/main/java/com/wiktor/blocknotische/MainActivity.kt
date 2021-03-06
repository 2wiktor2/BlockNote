package com.wiktor.blocknotische

import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.wiktor.blocknotische.screens.notesList.mvp.NotesListFragment
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

    fun setMyTitle(title: String?) {
        my_toolbar.title = title
    }

    override fun setTitleColor(color: Int) {
        when (color) {
            Importance.UNIMPORTANT.importance -> my_toolbar.setTitleTextColor(Color.GRAY)
            Importance.POORLY_IMPORTANT.importance -> my_toolbar.setTitleTextColor(Color.GREEN)
            Importance.MEDIUM_IMPORTANT.importance -> my_toolbar.setTitleTextColor(Color.YELLOW)
            Importance.VERY_IMPORTANT.importance -> my_toolbar.setTitleTextColor(Color.RED)
        }
    }
}