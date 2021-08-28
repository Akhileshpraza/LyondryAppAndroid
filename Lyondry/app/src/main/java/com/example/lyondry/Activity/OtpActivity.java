package com.lyondry.lyondry.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.lyondry.lyondry.R;

public class OtpActivity extends AppCompatActivity {
    ImageView backbtn;
    EditText enterOtp;
    TextView resendOtp;
    Button Btn_verify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        backbtn = findViewById(R.id.back_arrow);
       enterOtp = findViewById(R.id.ev_otp);
       resendOtp = findViewById(R.id.tvresend);
       Btn_verify = findViewById(R.id.btn_verify);

       resendOtp.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

           }
       });

       Btn_verify.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent =new Intent(OtpActivity.this,MainActivity.class);
               startActivity(intent);
           }
       });

       backbtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               onBackPressed();
           }
       });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}