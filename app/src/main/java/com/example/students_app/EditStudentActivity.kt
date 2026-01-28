package com.example.students_app

import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class EditStudentActivity : AppCompatActivity() {

    private var selectedImageUri: Uri? = null

    private val pickImageLauncher = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri != null) {
            selectedImageUri = uri
            findViewById<ImageView>(R.id.editStudentImageView).setImageURI(uri)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_student)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Edit Student"

        val index = intent.getIntExtra("student_index", -1)

        val imageView: ImageView = findViewById(R.id.editStudentImageView)
        val nameEditText: EditText = findViewById(R.id.editStudentNameEditText)
        val idEditText: EditText = findViewById(R.id.editStudentIdEditText)
        val phoneEditText: EditText = findViewById(R.id.editStudentPhoneEditText)
        val addressEditText: EditText = findViewById(R.id.editStudentAddressEditText)
        val checkBox: CheckBox = findViewById(R.id.editStudentCheckBox)

        val saveButton: Button = findViewById(R.id.editStudentSaveButton)
        val deleteButton: Button = findViewById(R.id.editStudentDeleteButton)
        val cancelButton: Button = findViewById(R.id.editStudentCancelButton)

        if (index != -1 && index < StudentRepository.shared.students.size) {
            val student = StudentRepository.shared.students[index]
            nameEditText.setText(student.name)
            idEditText.setText(student.id)
            phoneEditText.setText(student.phone)
            addressEditText.setText(student.address)
            checkBox.isChecked = student.isChecked

            student.imageUri?.let { uriString ->
                selectedImageUri = Uri.parse(uriString)
                imageView.setImageURI(selectedImageUri)
            }
        }

        imageView.setOnClickListener {
            pickImageLauncher.launch("image/*")
        }

        saveButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val id = idEditText.text.toString()
            val phone = phoneEditText.text.toString()
            val address = addressEditText.text.toString()
            val isChecked = checkBox.isChecked

            val updatedStudent = Student(
                id = id,
                name = name,
                phone = phone,
                address = address,
                isChecked = isChecked,
                imageUri = selectedImageUri?.toString()
            )
            StudentRepository.shared.updateStudent(index, updatedStudent)
            finish()
        }

        deleteButton.setOnClickListener {
            StudentRepository.shared.removeStudent(index)
            finish()
        }

        cancelButton.setOnClickListener {
            finish()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
