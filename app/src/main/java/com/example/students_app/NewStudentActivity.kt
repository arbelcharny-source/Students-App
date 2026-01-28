package com.example.students_app

import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class NewStudentActivity : AppCompatActivity() {

    private var selectedImageUri: Uri? = null

    private val pickImageLauncher = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri != null) {
            selectedImageUri = uri
            findViewById<ImageView>(R.id.newStudentImageView).setImageURI(uri)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_student)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "New Student"

        val imageView: ImageView = findViewById(R.id.newStudentImageView)
        val nameEditText: EditText = findViewById(R.id.newStudentNameEditText)
        val idEditText: EditText = findViewById(R.id.newStudentIdEditText)
        val phoneEditText: EditText = findViewById(R.id.newStudentPhoneEditText)
        val addressEditText: EditText = findViewById(R.id.newStudentAddressEditText)
        val checkBox: CheckBox = findViewById(R.id.newStudentCheckBox)
        val saveButton: Button = findViewById(R.id.newStudentSaveButton)
        val cancelButton: Button = findViewById(R.id.newStudentCancelButton)

        imageView.setOnClickListener {
            pickImageLauncher.launch("image/*")
        }

        saveButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val id = idEditText.text.toString()
            val phone = phoneEditText.text.toString()
            val address = addressEditText.text.toString()
            val isChecked = checkBox.isChecked

            val newStudent = Student(
                id = id,
                name = name,
                phone = phone,
                address = address,
                isChecked = isChecked,
                imageUri = selectedImageUri?.toString()
            )
            StudentRepository.shared.addStudent(newStudent)
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
