package com.lyondry.lyondry.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lyondry.lyondry.R;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity {
    EditText mobile_number;
    TextView signup;
    Button btn_Lohin;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mobile_number = findViewById(R.id.ednumber);
        signup = findViewById(R.id.tvSignup);
        btn_Lohin = findViewById(R.id.btn_login);

        btn_Lohin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent = new Intent(LoginActivity.this,BannerActivity.class);
            startActivity(intent);
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,SignupActivity.class);
                startActivity(intent);
            }
        });
    }
}