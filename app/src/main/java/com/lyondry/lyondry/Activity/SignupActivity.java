package com.lyondry.lyondry.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.google.gson.Gson;
import com.lyondry.lyondry.Classes.SharedPrefManager;
import com.lyondry.lyondry.Modals.Data_result_odel;
import com.lyondry.lyondry.Modals.Signup_responce_model;
import com.lyondry.lyondry.Modals.address.Address;
import com.lyondry.lyondry.R;
import com.lyondry.lyondry.Retrofits.RetrofitClient;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {
    EditText Name, Mobile, Emailid;
    TextView Login;
    Button Signup;
    CheckBox checkBox;
    AwesomeValidation awesomeValidation;
    String name, email, mobile;
    int CustomerGroupId;
    String EntryStatus;
    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Name = findViewById(R.id.et_name);
        Mobile = findViewById(R.id.et_number);
        Emailid = findViewById(R.id.et_email);
        checkBox = findViewById(R.id.checkbox_signup);
        Login = findViewById(R.id.tvLogin);
        Signup = findViewById(R.id.btn_signup);

        Name.addTextChangedListener(signupTextWatcher);
        Mobile.addTextChangedListener(signupTextWatcher);
        Emailid.addTextChangedListener(signupTextWatcher);

        sharedPrefManager = new SharedPrefManager(getApplicationContext());

        Loging();
        signup();
    }
    //*******************************enable and disable button ***********************

    private TextWatcher signupTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

//            String nameInput = Name.getText().toString().trim();
//            String mobileInput = Mobile.getText().toString().trim();
//            String emailidInput = Emailid.getText().toString().trim();
            checkBoxvalidation();

//            Signup.setEnabled(!nameInput.isEmpty() && !mobileInput.isEmpty() && !emailidInput.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    public void checkBoxvalidation() {
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    String nameInput = Name.getText().toString().trim();
                    String mobileInput = Mobile.getText().toString().trim();
                    String emailidInput = Emailid.getText().toString().trim();
                    Signup.setEnabled(!nameInput.isEmpty() && !mobileInput.isEmpty() && !emailidInput.isEmpty()&& true);

                } else {
                    Signup.setEnabled(false);
                }
            }
        });
    }
//*******************************enable and disable button ***********************

    private void signup() {
        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name = Name.getText().toString().trim();
                mobile = Mobile.getText().toString().trim();
                email = Emailid.getText().toString().trim();
                CustomerGroupId = 1;
                EntryStatus = "P";

                if (name.isEmpty()) {
                    Name.setError("Name can't be blank");
                    Name.requestFocus();
                    return;
                } else if (mobile.isEmpty()) {
                    Mobile.setError("Mobile number can't be blank");
                    Mobile.requestFocus();
                    return;
                } else if (mobile.length() <= 9) {
                    Mobile.setError("Enter valid mobile number");
                    Mobile.requestFocus();
                    return;
                } else if (!Patterns.PHONE.matcher(mobile).matches()) {
                    Mobile.setError("Enter valid mobile number");
                    Mobile.requestFocus();
                    return;
                } else if (email.isEmpty()) {
                    Emailid.setError("Email id can't be blank ");
                    Emailid.requestFocus();
                    return;
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Emailid.setError("Enter a valid email id");
                    Emailid.requestFocus();
                    return;
                }
                   // Validation();

                Call<Signup_responce_model> call = RetrofitClient
                        .getInstance()
                        .getApi()
                        .register(CustomerGroupId, name, mobile, email, EntryStatus);

                call.enqueue(new Callback<Signup_responce_model>() {
                    @Override
                    public void onResponse(@NotNull Call<Signup_responce_model> call, Response<Signup_responce_model> response) {

                        try {
                            if (response.isSuccessful()) {
                                JSONObject jsonObject = new JSONObject(new Gson().toJson(response.body()));
                                Log.i("JesonObject ", "***********jsonObject*******************" + jsonObject);

                                Signup_responce_model signup_responce_model = response.body();
                                Data_result_odel data_result_odel = signup_responce_model.getData();

//
                                    if (signup_responce_model.getSuccess() == true) {
                                        String mobile = data_result_odel.getCustomerMobileNo();
                                        String email = data_result_odel.getCustomerEmailId();
                                        String name = data_result_odel.getCustomerName();
                                        int customerId = data_result_odel.getCustomerId();

                                        sharedPrefManager.saveUserSignup(signup_responce_model.getData());

                                            Intent intent = new Intent(SignupActivity.this, OtpActivity.class);
                                            intent.putExtra("mobile", mobile);
                                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            startActivity(intent);
                                            finish();
                                            Toast.makeText(SignupActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();

                                    } else {
                                        Toast.makeText(SignupActivity.this, signup_responce_model.getErrorMessage().toString(), Toast.LENGTH_SHORT).show();
                                    }

                            } else {
                                Toast.makeText(SignupActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<Signup_responce_model> call, Throwable t) {

                        Toast.makeText(SignupActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void Loging() {
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void Validation() {
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(this, R.id.et_name, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.nameerror);
        awesomeValidation.addValidation(this, R.id.et_email, Patterns.EMAIL_ADDRESS, R.string.emaileerror);
        awesomeValidation.addValidation(this, R.id.et_number, "^[2-9]{2}[0-9]{8}$", R.string.mobileerror);
        if (awesomeValidation.validate()) {
            Toast.makeText(this, "Signup Successfull", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(SignupActivity.this, OtpActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Signup Faild", Toast.LENGTH_LONG).show();
        }
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        if(sharedPrefManager.isLoggedIn()){
//            Intent intent = new Intent(SignupActivity.this, MainActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            startActivity(intent);
//        }
//    }
}