package com.example.blocknotische.screens.notesList.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.blocknotische.R
import com.example.blocknotische.dataBase.NoteModel

class NotesAdapter(private val list: List<NoteModel>, private val clickInterface: ClickInterface) : RecyclerView.Adapter<NotesAdapter.ViewHolder>() {


    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val inflater = LayoutInflater.from(viewGroup.context)
        val view = inflater.inflate(R.layout.list_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.bind(list[i])
    }

    override fun getItemCount() = list.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val title: TextView = itemView.findViewById(R.id.tv_item_title)
        private val indicator: Button = itemView.findViewById(R.id.b_item_indicator)

        init {
            itemView.setOnClickListener {
                val model = list[adapterPosition]
                clickInterface.click(model)
            }
        }

        @SuppressLint("ResourceAsColor")
        fun bind(model: NoteModel) {
            title.text = model.title
            when (model.color) {
                1 -> indicator.setBackgroundColor(Color.GRAY)
                2 -> indicator.setBackgroundColor(Color.GREEN)
                3 -> indicator.setBackgroundColor(Color.YELLOW)
                4 -> indicator.setBackgroundColor(Color.RED)
            }
        }
    }
}