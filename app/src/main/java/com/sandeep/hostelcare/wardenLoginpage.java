package com.sandeep.hostelcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class wardenLoginpage extends AppCompatActivity {
    private Button  wardenLoginBtn,wardenSignupBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warden_loginpage);

        wardenLoginBtn=findViewById(R.id.wardenLoginButton);
        wardenSignupBtn=findViewById(R.id.wardenSignupButton);

        wardenLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(wardenLoginpage.this,wardenLogin.class);
                startActivity(intent);
            }
        });

        wardenSignupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(wardenLoginpage.this,wardenSignup.class);
                startActivity(intent);
            }
        });
    }
}