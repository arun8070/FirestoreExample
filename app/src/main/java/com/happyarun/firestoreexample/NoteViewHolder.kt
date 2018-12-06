package com.happyarun.firestoreexample

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView

class NoteViewHolder (itemview: View) : RecyclerView.ViewHolder(itemview){

    var title: TextView
    var priority: TextView
    var description: TextView

    init {
        title = itemview.findViewById(R.id.title_view)
        priority = itemview.findViewById(R.id.view_priority)
        description = itemview.findViewById(R.id.description)
    }

}