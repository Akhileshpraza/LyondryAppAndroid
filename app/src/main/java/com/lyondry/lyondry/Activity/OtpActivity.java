package com.lyondry.lyondry.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alimuzaffar.lib.pin.PinEntryEditText;
import com.lyondry.lyondry.Classes.SharedPrefManager;
import com.lyondry.lyondry.Classes.SharedPrefence_main;
import com.lyondry.lyondry.Modals.OtpResponceModal;
import com.lyondry.lyondry.OTP_Receiver;
import com.lyondry.lyondry.R;
import com.lyondry.lyondry.Retrofits.RetrofitClient;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OtpActivity extends AppCompatActivity {
    ImageView backbtn;
    TextView resendOtp;
    Button Btn_verify;
    PinEntryEditText pinEntry;
    String OtpType;
    String OtpMobileNo;
    String OtpNo;
    int OtpExpiryMinutes;
    String token;
    SharedPrefManager sharedPrefManager;
    OtpResponceModal otpResponceModal;
    SharedPreferences sharedPreferences;
    int AddressId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        backbtn = findViewById(R.id.back_arrow);
//       enterOtp = findViewById(R.id.ev_otp);
        resendOtp = findViewById(R.id.tvresend);
        Btn_verify = findViewById(R.id.btn_verify);

        sharedPrefManager = new SharedPrefManager(getApplicationContext());
        String monu = sharedPrefManager.getUser().getCustomerMobileNo();

        sharedPreferences =getSharedPreferences("MyAddress1", MODE_PRIVATE);
        AddressId = sharedPreferences.getInt("pickupid", 0);

        Log.i("mobilenumberotp", "***************mob*******************" + monu);

        requestSMSPermission();
        pinEntry = (PinEntryEditText) findViewById(R.id.txt_pin_entry);

        new OTP_Receiver().setPinEntry(pinEntry);


        pinOtp();
        resendOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        Btn_verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //pinOtp();
                Toast.makeText(OtpActivity.this, "Please Enter valid Otp", Toast.LENGTH_SHORT).show();

            }
        });

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void requestSMSPermission() {

        String permission = Manifest.permission.RECEIVE_SMS;

        int grant = ContextCompat.checkSelfPermission(this, permission);
        if (grant != PackageManager.PERMISSION_GRANTED) {
            String[] permission_list = new String[1];
            permission_list[0] = permission;

            ActivityCompat.requestPermissions(this, permission_list, 1);
        }
    }

    private void pinOtp() {
        OtpType = "R";
        //OtpMobileNo = getIntent().getStringExtra("mobile");
        OtpMobileNo = getIntent().getStringExtra("mobile");
        Log.i("mob", "&&&&&&&&&&&&&&&&&&&&&&&" + OtpMobileNo);
        OtpExpiryMinutes = 30;

        if (pinEntry != null) {
            pinEntry.setOnPinEnteredListener(new PinEntryEditText.OnPinEnteredListener() {
                @Override
                public void onPinEntered(CharSequence str) {
                    OtpNo = str.toString();
                    Log.i("otp", "*************otp****************" + OtpNo);
                    Call<OtpResponceModal> call = RetrofitClient
                            .getInstance()
                            .getApi()
                            .otprequest(OtpType, OtpMobileNo, OtpNo, OtpExpiryMinutes);

                    call.enqueue(new Callback<OtpResponceModal>() {
                        @Override
                        public void onResponse(@NotNull Call<OtpResponceModal> call, Response<OtpResponceModal> response) {


                            if (response.isSuccessful()) {
                                otpResponceModal = response.body();
                                token = otpResponceModal.getHttpResponseHeader();
                                Log.i("Token", "**************Token***************" + token);


                                if (otpResponceModal.getSuccess() == true) {
                                    sharedPrefManager.saveTokenOtp(otpResponceModal);

                                    if (AddressId==0){
                                        Intent intent = new Intent(OtpActivity.this, PickupAddress1Activity.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        intent.putExtra("token1",token);
                                        intent.putExtra("otp",OtpNo);
                                        startActivity(intent);
                                        finish();
                                        Toast.makeText(OtpActivity.this, "Please Add Address", Toast.LENGTH_SHORT).show();
                                    }
                                    else {
                                        Intent intent = new Intent(OtpActivity.this, MainActivity.class);
                                        intent.putExtra("token1",token);
                                        intent.putExtra("otp",OtpNo);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);
                                        finish();
                                        Toast.makeText(OtpActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();

                                    }



                                } else {
                                    Toast.makeText(OtpActivity.this, otpResponceModal.getErrorMessage(), Toast.LENGTH_SHORT).show();

                                }
                            } else {
                                Toast.makeText(OtpActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<OtpResponceModal> call, Throwable t) {

                            Toast.makeText(OtpActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}