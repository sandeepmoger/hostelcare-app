package com.sandeep.hostelcare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class studentLogin extends AppCompatActivity {

    private EditText studNameLoginET;
    private EditText studUsnLoginET;
    private Button studLoginBtn;
    private DatabaseReference studentDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);

        studNameLoginET = findViewById(R.id.studNameLoginEdittext);
        studUsnLoginET = findViewById(R.id.studUsnLoginEdittext);
        studLoginBtn = findViewById(R.id.studLoginButton);

        studentDatabase = FirebaseDatabase.getInstance().getReference("students");

        studLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String studentName = studNameLoginET.getText().toString().trim();
                String studentUsn = studUsnLoginET.getText().toString().trim();

                if (!studentName.isEmpty() && !studentUsn.isEmpty()) {
                    checkLoginCredentials(studentName, studentUsn);
                } else {
                    Toast.makeText(studentLogin.this, "Enter both username and USN", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void checkLoginCredentials(String username, String usn) {
        studentDatabase.orderByChild("studentName").equalTo(username).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // User found, check if the USN matches
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Student student = snapshot.getValue(Student.class);
                        if (student != null && student.getStudentUsn().equals(usn)) {
                            // Login successful
                            Toast.makeText(studentLogin.this, "Login successful", Toast.LENGTH_SHORT).show();

                            // Navigate to StudentHomePage
                            Intent intent = new Intent(studentLogin.this, studentHomePage.class);
                            startActivity(intent);

                            // Finish the current activity to prevent coming back to the login screen using the back button
                            finish();
                            return;
                        }
                    }
                }

                // Login failed
                Toast.makeText(studentLogin.this, "Login failed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(studentLogin.this, "Error checking credentials", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
