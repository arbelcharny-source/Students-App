package com.example.students_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StudentRecyclerAdapter(
    private val students: List<Student>,
    private val onItemClick: (Int) -> Unit,
    private val onCheckChanged: (Int) -> Unit
) : RecyclerView.Adapter<StudentRecyclerAdapter.StudentViewHolder>() {

    class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.rowStudentName)
        val idTextView: TextView = itemView.findViewById(R.id.rowStudentId)
        val checkBox: CheckBox = itemView.findViewById(R.id.rowStudentCheckBox)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.student_row, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = students[position]

        holder.nameTextView.text = student.name
        holder.idTextView.text = "ID: ${student.id}"
        holder.checkBox.isChecked = student.isChecked

        holder.itemView.setOnClickListener {
            onItemClick(position)
        }

        holder.checkBox.setOnClickListener {
            onCheckChanged(position)
        }
    }

    override fun getItemCount(): Int = students.size
}
