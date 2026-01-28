package com.example.students_app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class StudentDetailsActivity : AppCompatActivity() {

    private var studentIndex: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_details)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Student Details"

        studentIndex = intent.getIntExtra("student_index", -1)

        val editButton: Button = findViewById(R.id.detailsEditButton)

        editButton.setOnClickListener {
            val intent = Intent(this, EditStudentActivity::class.java)
            intent.putExtra("student_index", studentIndex)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        if (studentIndex != -1 && studentIndex < StudentRepository.shared.students.size) {
            val student = StudentRepository.shared.students[studentIndex]

            findViewById<TextView>(R.id.detailsNameTextView).text = "Name: ${student.name}"
            findViewById<TextView>(R.id.detailsIdTextView).text = "ID: ${student.id}"
            findViewById<TextView>(R.id.detailsPhoneTextView).text = "Phone: ${student.phone}"
            findViewById<TextView>(R.id.detailsAddressTextView).text = "Address: ${student.address}"
            findViewById<CheckBox>(R.id.detailsCheckBox).isChecked = student.isChecked
        } else {
            finish()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}