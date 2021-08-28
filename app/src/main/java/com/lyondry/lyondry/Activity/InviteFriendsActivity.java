package com.lyondry.lyondry.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lyondry.lyondry.BottomNavigationFragment;
import com.lyondry.lyondry.Classes.SharedPrefManager;
import com.lyondry.lyondry.R;

public class InviteFriendsActivity extends AppCompatActivity {
    BottomNavigationFragment bottomNavigationFragment;
    ImageView backbtn;
    Button btnInvite;
    TextView Invite,MobileNumber,Emailid;
    SharedPrefManager sharedPrefManager;
    String userMobileNumber, userEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite_friends);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        MobileNumber = findViewById(R.id.et_mnumber);
        Emailid = findViewById(R.id.ed_email);
        btnInvite = findViewById(R.id.btn_invite);

        sharedPrefManager = new SharedPrefManager(getApplicationContext());
        userMobileNumber = sharedPrefManager.getUser().getCustomerMobileNo();
        userEmail = sharedPrefManager.getUser().getCustomerEmailId();
        Log.i("mobile","*******************"+userMobileNumber);

        MobileNumber.setText(userMobileNumber);
        Emailid.setText(userEmail);

        backbtn = findViewById(R.id.back_arrow);
        Invite = findViewById(R.id.invite);

        //************************************BottomNavigationBar***************************************
        Fragment bottom_fragment = getSupportFragmentManager().findFragmentById(R.id.bottom_navigation_id);
        if (bottom_fragment instanceof Fragment){
            bottomNavigationFragment = (BottomNavigationFragment)bottom_fragment;
            bottomNavigationFragment.initailizeComponets();
        }
        //************************************BottomNavigationBar***************************************

        invited();
        back_button();
    }

    private void invited() {
        Invite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Invite", Toast.LENGTH_SHORT).show();
            }
        });
    }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.order_menu, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            int id = item.getItemId();
            switch (id) {
                case R.id.notification:
                    Toast.makeText(getApplicationContext(), "Notification", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.more_vertical:
                    Toast.makeText(getApplicationContext(), "More_vertical", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.invite:
                    Toast.makeText(getApplicationContext(), "Invite", Toast.LENGTH_SHORT).show();
                    return true;
                default:
                    return super.onOptionsItemSelected(item);
            }
    }

    public void back_button(){
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