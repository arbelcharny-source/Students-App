package com.example.students_app

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: StudentRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.studentsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val fab: FloatingActionButton = findViewById(R.id.addStudentFab)
        fab.setOnClickListener {
            val intent = Intent(this, NewStudentActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        adapter = StudentRecyclerAdapter(
            students = StudentRepository.shared.students,
            onItemClick = { position ->
                val intent = Intent(this, StudentDetailsActivity::class.java)
                intent.putExtra("student_index", position)
                startActivity(intent)
            },
            onCheckChanged = { position ->
                val student = StudentRepository.shared.students[position]
                student.isChecked = !student.isChecked
                adapter.notifyItemChanged(position)
            }
        )
        recyclerView.adapter = adapter
    }
}
