package com.sandeep.hostelcare;
import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class studentHomePage extends AppCompatActivity {

    private BottomNavigationView studBottomNavigationView;
    private FrameLayout studFrameLayout;

    private studentHomeFragment homeFragment;
    private studentComplaintsFragment complaintsFragment;
    private studentComplaintStatusFragment complaintStatusFragment;
    private studentRulesFragment leaveFragment;

    private Fragment activeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home_page);

        studBottomNavigationView = findViewById(R.id.studentBottomNavView);
        studFrameLayout = findViewById(R.id.studentFrameLayout);

        homeFragment = new studentHomeFragment();
        complaintsFragment = new studentComplaintsFragment();
        complaintStatusFragment = new studentComplaintStatusFragment();
        leaveFragment = new studentRulesFragment();

        activeFragment = homeFragment;

        // Get FCM token after the activity is created


        // Load the studentHomeFragment initially
        loadFragment(activeFragment, false);

        studBottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            Fragment selectedFragment = null;

            int itemId = item.getItemId();

            if (itemId == R.id.studentNavHome) {
                selectedFragment = homeFragment;
            } else if (itemId == R.id.navAddComplaints) {
                selectedFragment = complaintsFragment;

            } else if (itemId == R.id.navLeave) {
                selectedFragment = leaveFragment;
            }

            loadFragment(selectedFragment, false);
            return true;
        });
    }

    private void loadFragment(Fragment fragment, boolean addToBackStack) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.studentFrameLayout, fragment);

        if (addToBackStack) {
            fragmentTransaction.addToBackStack(null);
        }

        fragmentTransaction.commit();
        activeFragment = fragment;
    }

    @Override
    public void onBackPressed() {
        if (activeFragment == activeFragment) {
            // Navigate to home fragment when pressing back, but don't exit the app
            loadFragment(activeFragment, false);
        } else {
            // Handle back press in the home fragment (optional)
            super.onBackPressed();
        }
    }



}

