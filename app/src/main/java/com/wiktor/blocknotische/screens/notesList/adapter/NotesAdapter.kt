package com.wiktor.blocknotische.screens.notesList.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wiktor.blocknotische.Importance
import com.wiktor.blocknotische.R
import com.wiktor.blocknotische.dataBase.NotesModel

class NotesAdapter(private val list: List<NotesModel>, private val clickInterface: ClickInterface) : RecyclerView.Adapter<NotesAdapter.ViewHolder>() {


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
        private val body: TextView = itemView.findViewById(R.id.tv_item_body)

        init {
            itemView.setOnClickListener {
                val model = list[adapterPosition]
                clickInterface.click(model)
            }
        }

        @SuppressLint("ResourceAsColor")
        fun bind(notesModel: NotesModel) {
            title.text = notesModel.title
            body.text = notesModel.body
            when (notesModel.importanceOfANote) {
                Importance.UNIMPORTANT.importance -> indicator.setBackgroundColor(Color.GRAY)
                Importance.POORLY_IMPORTANT.importance -> indicator.setBackgroundColor(Color.GREEN)
                Importance.MEDIUM_IMPORTANT.importance -> indicator.setBackgroundColor(Color.YELLOW)
                Importance.VERY_IMPORTANT.importance -> indicator.setBackgroundColor(Color.RED)
            }
        }
    }
}