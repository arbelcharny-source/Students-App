package com.example.students_app

class StudentRepository private constructor() {

    val students: MutableList<Student> = ArrayList()

    companion object {
        val shared = StudentRepository()
    }

    fun addStudent(student: Student) {
        students.add(student)
    }

    fun removeStudent(index: Int) {
        students.removeAt(index)
    }

    fun updateStudent(index: Int, student: Student) {
        students[index] = student
    }
}
