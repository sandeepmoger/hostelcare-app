package com.sandeep.hostelcare;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class loginActivity extends AppCompatActivity {
    private RadioGroup accountType;
    private RadioGroup.OnCheckedChangeListener listener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            Intent intent = null;
            if (checkedId == R.id.studentAccount) {
                intent = new Intent(loginActivity.this, studentLogin.class);
            } else if (checkedId == R.id.wardenAccount) {
                intent = new Intent(loginActivity.this, wardenLoginpage.class);
            }
            if (intent != null) {
                startActivity(intent);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        accountType = findViewById(R.id.accountType);
        accountType.setOnCheckedChangeListener(listener);

    }

    @Override
    protected void onResume() {
        super.onResume();
        accountType.setOnCheckedChangeListener(null);
        accountType.clearCheck();
        accountType.setOnCheckedChangeListener(listener);
    }
}