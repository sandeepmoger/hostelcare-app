package com.sandeep.hostelcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class addStudentFragment extends Fragment {

    private EditText studentNameET;
    private EditText studentUsnET;
    private Button submitButton;
    private DatabaseReference studentDatabase;
    private TextView announcementTextView; // Add TextView reference

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_student, container, false);

        studentNameET = view.findViewById(R.id.studentNameEdittext);
        studentUsnET = view.findViewById(R.id.studentUsnEdittext);
        submitButton = view.findViewById(R.id.studentStoringButton);
        announcementTextView = view.findViewById(R.id.announcementTV); // Initialize TextView

        // Use a reference for student data in the database
        studentDatabase = FirebaseDatabase.getInstance().getReference("students");

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String studentName = studentNameET.getText().toString();
                String studentUsn = studentUsnET.getText().toString();

                if (studentName.isEmpty() || studentUsn.isEmpty()) {
                    Toast.makeText(getActivity(), "Please enter both student name and USN", Toast.LENGTH_SHORT).show();
                } else {
                    addStudentToDatabase(studentName, studentUsn);
                }
            }
        });

        // Set click listener for the announcementTextView
        announcementTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to announcementActivity
                Intent intent = new Intent(getActivity(), announcementActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    private void addStudentToDatabase(String name, String usn) {
        // Create a Student object with the provided data
        Student student = new Student(name, usn);

        // Add the student to the database
        studentDatabase.push().setValue(student);

        Toast.makeText(getActivity(), "Student details added successfully", Toast.LENGTH_SHORT).show();
    }
}