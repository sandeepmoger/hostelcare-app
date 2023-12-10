package com.sandeep.hostelcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class wardenLogin extends AppCompatActivity {

    private EditText wardenLoginNameET;
    private EditText wardenLoginNumberET;
    private Button wardenFinalLoginBtn;
    private DatabaseReference wardenDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warden_login);

        wardenLoginNameET = findViewById(R.id.wardenLoginNameEdittext);
        wardenLoginNumberET = findViewById(R.id.wardenLoginNumberEdittext);
        wardenFinalLoginBtn = findViewById(R.id.wardenFinalLoginButton);

        wardenDatabase = FirebaseDatabase.getInstance().getReference("wardenUsers");

        wardenFinalLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String enteredName = wardenLoginNameET.getText().toString();
                String enteredNumber = wardenLoginNumberET.getText().toString();

                // Check if the enteredName and enteredNumber exist in the database
                checkLoginCredentials(enteredName, enteredNumber);
            }
        });
    }

    private void checkLoginCredentials(String enteredName, String enteredNumber) {
        // Query the database for the entered username and mobile number
        wardenDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                boolean loginSuccess = false;

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    User user = snapshot.getValue(User.class);

                    if (user != null && user.getUsername().equals(enteredName) && user.getMobileNumber().equals(enteredNumber)) {
                        loginSuccess = true;
                        break;
                    }
                }

                if (loginSuccess) {
                    // Login successful, navigate to the wardenHomePage
                    Intent intent = new Intent(wardenLogin.this, wardenHomePage.class);
                    intent.putExtra("USERNAME", enteredName); //takes name to
                    startActivity(intent);
                    finish(); // Finish the current activity to prevent going back to the login screen
                } else {
                    // Login unsuccessful, show an error message
                    Toast.makeText(wardenLogin.this, "Login unsuccessful", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle the error here
                Toast.makeText(wardenLogin.this, "Database Error: " + databaseError.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
