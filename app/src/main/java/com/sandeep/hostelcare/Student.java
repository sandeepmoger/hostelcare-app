package com.sandeep.hostelcare;

public class Student {
    private String studentName;
    private String studentUsn;

    public Student() {
        // Default constructor required for calls to DataSnapshot.getValue(Student.class)
    }

    public Student(String studentName, String studentUsn) {
        this.studentName = studentName;
        this.studentUsn = studentUsn;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getStudentUsn() {
        return studentUsn;
    }

    public String getUsn() {
        return studentUsn;
    }
}
