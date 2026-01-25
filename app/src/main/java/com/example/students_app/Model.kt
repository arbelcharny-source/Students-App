package com.example.students_app

class Model private constructor() {

    val students: MutableList<Student> = ArrayList()

    companion object {
        val shared = Model()
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