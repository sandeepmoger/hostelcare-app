package com.sandeep.hostelcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class loginActivity extends AppCompatActivity {
    private Button studentBtn,wardenBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        wardenBtn=findViewById(R.id.wardenButton);
        studentBtn=findViewById(R.id.studentButton);

        wardenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open Warden Login Activity
                Intent intent = new Intent(loginActivity.this, wardenLoginpage.class);
                startActivity(intent);
            }
        });

        studentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open Student Login Activity
                Intent intent = new Intent(loginActivity.this, studentLogin.class);
                startActivity(intent);
            }
        });



    }
}