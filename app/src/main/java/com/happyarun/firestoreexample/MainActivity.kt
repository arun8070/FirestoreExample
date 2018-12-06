package com.happyarun.firestoreexample

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.Menu
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.activity_main.*
import android.view.MenuInflater
import android.view.MenuItem


class MainActivity : AppCompatActivity() {

    lateinit var firestoreRecyclerAdapter : FirestoreRecyclerAdapterClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var query = FirebaseFirestore.getInstance().collection("Notebook").orderBy("priority", Query.Direction.ASCENDING).limit(10)
        logRecyclerView(query)

        add_new_note.setOnClickListener {
            startActivity(Intent(this, Main2Activity::class.java))
        }
    }

    private fun logRecyclerView(query: Query) {

        firestoreRecyclerAdapter = FirestoreRecyclerAdapterClass(query)

        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                firestoreRecyclerAdapter?.deletItem(viewHolder.adapterPosition)
            }
        }).attachToRecyclerView(rview)

        (firestoreRecyclerAdapter as FirestoreRecyclerAdapter<Note, NoteViewHolder>).startListening()
        rview.layoutManager = LinearLayoutManager(this)
        rview.adapter = firestoreRecyclerAdapter

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean = when(item?.itemId) {

        R.id.asend -> {
            var query = FirebaseFirestore.getInstance().collection("Notebook").orderBy("priority", Query.Direction.ASCENDING).limit(30)
            logRecyclerView(query)
            true
        }
        R.id.desend -> {
            var query = FirebaseFirestore.getInstance().collection("Notebook").orderBy("priority", Query.Direction.DESCENDING).limit(30)
            logRecyclerView(query)
            true
        }

        else -> super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        firestoreRecyclerAdapter.stopListening()
        super.onDestroy()
    }
}
