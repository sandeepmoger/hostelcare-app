package com.sandeep.hostelcare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class wardenHomePage extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private FrameLayout frameLayout;
    private WardenViewModel wardenViewModel;

    private Fragment activeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warden_home_page);

        bottomNavigationView = findViewById(R.id.bottomNavView);
        frameLayout = findViewById(R.id.frameLayout);

        wardenViewModel = new ViewModelProvider(this).get(WardenViewModel.class);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.navHome) {
                loadFragment(new HomeFragment(), false);
            } else if (itemId == R.id.navAddStudent) {
                loadFragment(new addStudentFragment(), false);
            } else if (itemId == R.id.navComplaints) {
                loadFragment(new complaintsFragment(), false);
            } else {
                loadFragment(new somethingFragment(), false);
            }

            return true;
        });

        // Check if warden name is set, if not, set it
        if (wardenViewModel.getWardenName() == null) {
            // Retrieve warden name from Intent (initial login) or SharedPreferences (if stored)
            String wardenName = getIntent().getStringExtra("USERNAME");
            wardenViewModel.setWardenName(wardenName);
        }

        // Load the HomeFragment initially
        loadFragment(new HomeFragment(), true);
    }

    private void loadFragment(Fragment fragment, boolean isAppInitialized) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (isAppInitialized) {
            Bundle args = new Bundle();
            args.putString("WARDEN_NAME", wardenViewModel.getWardenName());
            fragment.setArguments(args);
            fragmentTransaction.replace(R.id.frameLayout, fragment);
            activeFragment = fragment;
        } else {
            fragmentTransaction.replace(R.id.frameLayout, fragment);
            activeFragment = fragment;
        }

        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
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
