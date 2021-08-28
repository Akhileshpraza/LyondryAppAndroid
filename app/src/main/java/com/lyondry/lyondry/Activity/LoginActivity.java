package com.lyondry.lyondry.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.lyondry.lyondry.Classes.SharedPrefManager;
import com.lyondry.lyondry.Modals.Login_responce;
import com.lyondry.lyondry.Modals.Login_result_model;
import com.lyondry.lyondry.R;
import com.lyondry.lyondry.Retrofits.RetrofitClient;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    EditText mobile_number;
    TextView signup;
    Button btn_Login;
    String mobile;
    AwesomeValidation awesomeValidation;
    SharedPrefManager sharedPrefManager;
    String status;
    SharedPreferences sp;
    private ArrayList<String> requirePermissions = new ArrayList<>();
    private ArrayList<String> permissionsToRequest = new ArrayList<>();
    final private int REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS = 124;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mobile_number = findViewById(R.id.et_number);
        signup = findViewById(R.id.tvSignup);
        btn_Login = findViewById(R.id.btn_login);


        mobile_number.addTextChangedListener(loginTextWatcher);
        sharedPrefManager = new SharedPrefManager(getApplicationContext());
        mobile = sharedPrefManager.getLoginUser().getCustomerMobileNo();
        Log.i("anc","####################mobile###################"+mobile);


        btnLogin();
        btnSingup();
        askPermissions();

    }
    private void btnSingup(){
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,SignupActivity.class);
                startActivity(intent);
            }
        });
    }


    //*******************************enable and disable button ***********************
    private TextWatcher loginTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String mobileNumberInput = mobile_number.getText().toString().trim();
            btn_Login.setEnabled(!mobileNumberInput.isEmpty());
        }
        @Override
        public void afterTextChanged(Editable s) {
        }
    };
//*******************************enable and disable button ***********************

    private void btnLogin() {
        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mobile = mobile_number.getText().toString().trim();
                Log.e("mobileNumber",""+mobile);
                if (mobile.isEmpty()) {
                    mobile_number.setError("Mobile number can't be blank");
                    mobile_number.requestFocus();
                    return;
                }
                if (mobile.length() <= 9) {
                    mobile_number.setError("Enter valid mobile number");
                    mobile_number.requestFocus();
                    return;
                }
                if (!Patterns.PHONE.matcher(mobile).matches()) {
                    mobile_number.setError("Enter valid mobile number");
                    mobile_number.requestFocus();
                }


                    Call<Login_responce> call = RetrofitClient
                            .getInstance()
                            .getApi()
                            .loginrequest(mobile);


                    call.enqueue(new Callback<Login_responce>() {
                        @Override
                        public void onResponse(@NotNull Call<Login_responce> call, Response<Login_responce> response) {


                            if (response.isSuccessful()) {

                                Login_responce loginResponceModal = response.body();
                                Login_result_model login_result_model=loginResponceModal.getLogin_result_model();
                                String getMobileNo=login_result_model.getCustomerMobileNo();
                                Log.i("anc","#######################################"+getMobileNo);

                                if (loginResponceModal.getSuccess()) {

                                    String Token = loginResponceModal.getHttpResponseHeader();
                                    String CustomerMobileNo = login_result_model.getCustomerMobileNo();
                                    String CustomerEmailId = login_result_model.getCustomerEmailId();
                                    String CustomerName = login_result_model.getCustomerName();
                                    int CustomerId = login_result_model.getCustomerId();
                                     status = login_result_model.getEntryStatus();

                                    sp = getSharedPreferences("MyAddress", Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor =sp.edit();
                                    editor.putInt("CustomerId", CustomerId);
                                    editor.commit();

                                    Log.i("Token", "***********Token***********************" + Token);
                                    Log.i("CustomerMobileNo", "***********CustomerMobileNo***********************" + CustomerMobileNo);
                                    Log.i("CustomerEmailId", "***********CustomerEmailId***********************" + CustomerEmailId);
                                    Log.i("CustomerName", "***********CustomerName***********************" + CustomerName);
                                    Log.i("CustomerId", "***********CustomerId***********************" + CustomerId);
                                    Log.i("status", "***********status***********************" + status);

                                    if (CustomerId == 0) {
                                        Toast.makeText(getApplicationContext(), "Please signup to login", Toast.LENGTH_SHORT).show();
                                    }
//                                    else  if (!status.equals("D")){
//                                        Toast.makeText(getApplicationContext(),"Registration  please Signup",Toast.LENGTH_SHORT).show();
//                                    }
                                    else {

                                        sharedPrefManager.saveUserLogin(login_result_model);
                                        Intent intent = new Intent(LoginActivity.this, LoginOTPActivity.class);
                                        intent.putExtra("mobile", mobile);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);
                                        finish();
                                        Toast.makeText(LoginActivity.this, "OTP Send Successfully", Toast.LENGTH_SHORT).show();

                                    }
                                }
                            } else {
                                Toast.makeText(LoginActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onFailure(Call<Login_responce> call, Throwable t) {

                            Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

            }
        });
    }


    private void Validations() {
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(this, R.id.et_number, RegexTemplate.NOT_EMPTY, R.string.mobileerror);
        awesomeValidation.addValidation(this, R.id.et_number, "^[2-9]{2}[0-9]{8}$", R.string.mobileerror);
        if (awesomeValidation.validate()) {
            Intent intent = new Intent(LoginActivity.this, OtpActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
            Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(LoginActivity.this, "Validated Faild", Toast.LENGTH_LONG).show();

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(sharedPrefManager.isLoggedIn()){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);


        }
        Log.i("isLoggedIn","********isLoggedIn***********"+sharedPrefManager.isLoggedIn());
//        else {
//            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            startActivity(intent);
        }
    //}

    private void askPermissions()
    {
        requirePermissions.add(Manifest.permission.CAMERA);
        requirePermissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        requirePermissions.add(Manifest.permission.ACCESS_COARSE_LOCATION);
        //requirePermissions.add(Manifest.permission.READ_SMS);
        requirePermissions.add(Manifest.permission.RECEIVE_SMS);
        //requirePermissions.add(Manifest.permission.WRITE_SMS);
        //requirePermissions.add(Manifest.permission.READ_PHONE_NUMBERS);


/*
        requirePermissions.add(Manifest.permission.READ_SMS);
        requirePermissions.add(Manifest.permission.READ_CONTACTS);
        requirePermissions.add(Manifest.permission.WRITE_CONTACTS);
        requirePermissions.add(Manifest.permission.READ_PHONE_STATE);
        requirePermissions.add(Manifest.permission.CALL_PHONE);
        requirePermissions.add(Manifest.permission.RECORD_AUDIO);
*/

        for (String permission : requirePermissions)
        {
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1)
            {
                if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED)
                {
                    permissionsToRequest.add(permission);
                }
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            if (permissionsToRequest.size() > 0) {
                ActivityCompat.requestPermissions(this, permissionsToRequest.toArray(new String[permissionsToRequest.size()]), REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS);
            } else {
//                launchLocation();
            }
        } else {
//            launchLocation();
        }
    }
}