package com.happyarun.firestoreexample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close)
        title = "Add Note"

        num_priority.minValue = 0
        num_priority.maxValue = 10

        save_data.setOnClickListener {

            if (title_edt.text.toString().trim().isEmpty() || description_edt.text.toString().trim().isEmpty())
            {
                Toast.makeText(this, "Please insert a title and description..", Toast.LENGTH_LONG).show()

            }

            else {
                var notebookRef = FirebaseFirestore.getInstance().collection("Notebook")
                notebookRef.add(Note(title_edt.text.toString(), description_edt.text.toString(), num_priority.value.toLong()))
                finish()
            }

        }
    }
}
