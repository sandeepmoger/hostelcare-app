package com.sandeep.hostelcare;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class rulesActivity extends AppCompatActivity {

    private EditText rulesEditText;
    private Button submitRulesButton;
    private DatabaseReference studentRulesDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);

        rulesEditText = findViewById(R.id.rulesEditText);
        submitRulesButton = findViewById(R.id.submitRulesButton);

        // Use a reference for student rules data in the database
        studentRulesDatabase = FirebaseDatabase.getInstance().getReference("warden").child("rules");

        submitRulesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String rulesText = rulesEditText.getText().toString();

                if (!rulesText.isEmpty()) {
                    addRulesToDatabase(rulesText);
                } else {
                    showToast("Please enter rules before submitting.");
                }
            }
        });
    }

    private void addRulesToDatabase(String rulesText) {
        // Get the current timestamp
        long timestamp = System.currentTimeMillis();

        // Create a Rules object with the provided data
        Ruless ruless= new Ruless(rulesText, timestamp);

        // Add the rules to the database
        studentRulesDatabase.push().setValue(ruless)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        showToast("Rules updated successfully");
                    } else {
                        showToast("Failed to update rules. Please try again.");
                    }

                    // Clear the EditText after submitting the rules
                    rulesEditText.getText().clear();
                });
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
