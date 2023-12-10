package com.sandeep.hostelcare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import javax.crypto.SecretKey;


public class wardenSignup extends AppCompatActivity {
    private EditText wardenNameET;
    private EditText wardenNumberET;
    private EditText secretKeyEt;
    private Button wardenSignupBtn;
    private DatabaseReference wardenDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warden_signup);

        wardenDatabase = FirebaseDatabase.getInstance().getReference("wardenUsers");

        Toast.makeText(wardenSignup.this,"enter the usaername, mobile number and secret key",Toast.LENGTH_LONG).show();

        wardenNameET=findViewById(R.id.wardenNameEdittext);
        wardenNumberET=findViewById(R.id.wardenNumberEdittext);
        secretKeyEt=findViewById(R.id.secretKeyEdittext);
        wardenSignupBtn=findViewById(R.id.wardenSignupBtn);


        String wardenName=wardenNameET.getText().toString();
        String wardenNumber=wardenNumberET.getText().toString();
        String secretKey=secretKeyEt.getText().toString();


        wardenSignupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //convert data to string
                String wardenName=wardenNameET.getText().toString();
                String wardenNumber=wardenNumberET.getText().toString();
                String secretKey=secretKeyEt.getText().toString();

                if(TextUtils.isEmpty(wardenName)){
                    Toast.makeText(wardenSignup.this,"username must be entered",Toast.LENGTH_LONG).show();
                }
                else if(TextUtils.isEmpty(wardenNumber)){
                    Toast.makeText(wardenSignup.this,"mobile number must be entered",Toast.LENGTH_LONG).show();
                }
                else if(TextUtils.isEmpty(secretKey)){
                    Toast.makeText(wardenSignup.this,"enter the secret Key",Toast.LENGTH_LONG).show();
                }
                else if(!"NMAMIT".equals(secretKey)){
                    Toast.makeText(wardenSignup.this," secret Key is incorrect!! cant register",Toast.LENGTH_LONG).show();
                }
                else if(secretKey.equals("NMAMIT")){
                    addUserToDatabase(wardenName, wardenNumber);

                }

            }
        });

    }

    private void addUserToDatabase(String name, String number) {
        // Create a unique key for the user using push
        String wardenID = wardenDatabase.push().getKey();

        // Create a User object with the provided data
        User user = new User(name, number);

        // Add the user to the database under the unique key
        wardenDatabase.child(wardenID).setValue(user);

        Toast.makeText(wardenSignup.this, "Registration successful", Toast.LENGTH_LONG).show();
    }
}