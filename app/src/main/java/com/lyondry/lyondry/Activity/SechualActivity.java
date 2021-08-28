package com.lyondry.lyondry.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lyondry.lyondry.R;

public class SechualActivity extends AppCompatActivity {

    Button Btn_next;
    TextView cancle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sechual);
        cancle = findViewById(R.id.btn_cancle1);
        Btn_next = (Button) findViewById(R.id.btn_next1);

        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SechualActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        Btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SechualActivity.this,Schduail2Activity.class);
                startActivity(intent);
            }
        });
    }
}