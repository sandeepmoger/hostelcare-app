// announcementActivity.java
package com.sandeep.hostelcare;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class announcementActivity extends AppCompatActivity {

    private EditText announcementEditText;
    private Button announcementSubmitButton;
    private DatabaseReference wardenAnnouncementDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement);

        announcementEditText = findViewById(R.id.announcementEditText);
        announcementSubmitButton = findViewById(R.id.announcementSubmitButton);

        // Use a reference for warden's announcement data in the database
        wardenAnnouncementDatabase = FirebaseDatabase.getInstance().getReference("warden").child("announcements");

        announcementSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String announcementText = announcementEditText.getText().toString();

                if (!announcementText.isEmpty()) {
                    addAnnouncementToDatabase(announcementText);
                } else {
                    showToast("Please enter an announcement before submitting.");
                }
            }
        });
    }

    private void addAnnouncementToDatabase(String announcementText) {
        // Get the current timestamp
        long timestamp = System.currentTimeMillis();

        // Create an Announcement object with the provided data
        Announcement announcement = new Announcement(announcementText, timestamp);

        // Add the announcement to the database
        wardenAnnouncementDatabase.push().setValue(announcement)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        showToast("Announcement sent successfully");
                    } else {
                        showToast("Failed to send announcement. Please try again.");
                    }

                    // Clear the EditText after submitting the announcement
                    announcementEditText.getText().clear();
                });
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
