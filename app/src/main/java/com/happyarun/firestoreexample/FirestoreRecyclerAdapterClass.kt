package com.happyarun.firestoreexample

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.Query

class FirestoreRecyclerAdapterClass(query: Query) : FirestoreRecyclerAdapter<Note, NoteViewHolder>(FirestoreRecyclerOptions.Builder<Note>().setQuery(query, Note::class.java).build()){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): NoteViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.list_item, p0, false)

        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int, model: Note) {
        Log.d("Arun",model.toString())
        holder.title.text =model.title
        holder.description.text = model.description
        holder.priority.text = model.priority.toString()
    }

    fun deletItem(position: Int) {
        snapshots.getSnapshot(position).reference.delete()
    }

}
