package com.sandeep.hostelcare;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

public class studentHomeFragment extends Fragment {

    private VideoView videoView;
    private TextView announcementTextView;
    private DatabaseReference announcementsDatabase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student_home, container, false);

        videoView = view.findViewById(R.id.videoView);
        announcementTextView = view.findViewById(R.id.announcementTextView);

        String videoPath = "android.resource://" + getActivity().getPackageName() + "/" + R.raw.announcevideo;
        Uri uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);
        videoView.start();
        videoView.setOnCompletionListener(mediaPlayer -> {
            videoView.seekTo(0);
            videoView.start();
        });

        announcementsDatabase = FirebaseDatabase.getInstance().getReference("warden").child("announcements");

        retrieveLatestAnnouncement();

        // Attach click listener to logout image
        ImageView logoutStudentImageView = view.findViewById(R.id.logoutStudentImageView);
        logoutStudentImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLogoutConfirmationDialog();
            }
        });

        return view;
    }

    private void retrieveLatestAnnouncement() {
        announcementsDatabase.orderByChild("timestamp").limitToLast(1).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot announcementSnapshot : dataSnapshot.getChildren()) {
                    Announcement latestAnnouncement = announcementSnapshot.getValue(Announcement.class);

                    if (latestAnnouncement != null) {
                        announcementTextView.setText(latestAnnouncement.getAnnouncementText());
                        showToast(" announcement reloaded!");
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                showToast("Failed to load announcement. Please try again.");
            }
        });
    }

    private void showLogoutConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setMessage("Are you sure you want to logout?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        logoutUser();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked No, do nothing
                    }
                });
        builder.create().show();
    }

    private void logoutUser() {
        // Clear session information (if any)
        // Add your code to clear any relevant session information here

        // Navigate to the login page
        Intent intent = new Intent(requireContext(), loginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void showToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

}
