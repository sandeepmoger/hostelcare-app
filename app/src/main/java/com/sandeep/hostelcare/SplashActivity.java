package com.sandeep.hostelcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {
    private static final int SPLASH_TIMEOUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app's main activity (in this case, LoginActivity)
                Intent i = new Intent(SplashActivity.this, loginActivity.class);
                startActivity(i);

                // Close this activity
                finish();
            }
        }, SPLASH_TIMEOUT);
    }
}