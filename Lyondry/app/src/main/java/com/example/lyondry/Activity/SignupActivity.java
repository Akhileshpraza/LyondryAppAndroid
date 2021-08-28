package com.lyondry.lyondry.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lyondry.lyondry.R;

public class SignupActivity extends AppCompatActivity {
    EditText Name, Mobile,Emailid;
    TextView Login;
    Button Signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Name = findViewById(R.id.evname);
        Mobile =findViewById(R.id.evm_number);
        Emailid = findViewById(R.id.ev_email);
        Login = findViewById(R.id.tvLogin);
        Signup = findViewById(R.id.btn_signup);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent = new Intent(SignupActivity.this,OtpActivity.class);
            startActivity(intent);
            }
        });
    }
}