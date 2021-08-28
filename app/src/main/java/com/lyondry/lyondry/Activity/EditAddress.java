package com.lyondry.lyondry.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.lyondry.lyondry.Classes.SharedPrefManager;
import com.lyondry.lyondry.Modals.InsertAddress_data;
import com.lyondry.lyondry.Modals.InsertAddress_responce;
import com.lyondry.lyondry.Modals.address.CustomerAddressList;
import com.lyondry.lyondry.Modals.address.EditAddress_date;
import com.lyondry.lyondry.Modals.address.EditAddress_responce_modal;
import com.lyondry.lyondry.Modals.address.MainCustomerAddressList;
import com.lyondry.lyondry.Modals.address.MainDeleteAddressList;
import com.lyondry.lyondry.Modals.address.MainEditAddressList;
import com.lyondry.lyondry.R;
import com.lyondry.lyondry.Retrofits.RetrofitClient;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditAddress extends AppCompatActivity {
    TextView Btn_cancle;
    Button Btn_save;
    EditText houseName,street,landmark,pincode;
    Spinner city;
    CheckBox selectCheckBox;
    Spinner AddressType;
    SharedPrefManager sharedPrefManager;
    CustomerAddressList[] customerAddressList = new CustomerAddressList[1];

    MainEditAddressList mainEditAddressList;
    String token,userMobileNumber,HouseName,Street,Landmark,Pincode;
    String[] usercity = {"Bangalore"};
    String[] addresstype = { "Home", "Office"};
    String select_city,Address_type;
    int addressId,customerAddressId;
    String HouseName1,AddressStreet,AddressCity,AddressPin,AddressLandmark;
    String AddressDefault="0";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_address);
        houseName = findViewById(R.id.evhousenumber1);
        street = findViewById(R.id.et_street1);
        landmark = findViewById(R.id.et_landmark1);
        pincode = findViewById(R.id.et_pincode1);
        city = findViewById(R.id.sp_city1);
        AddressType = findViewById(R.id.sp_addressTpye1);
        selectCheckBox = findViewById(R.id.editcheckBox);
        Btn_cancle = findViewById(R.id.btn_cancle2);
        Btn_save = findViewById(R.id.btn_save2);

        sharedPrefManager = new SharedPrefManager(getApplicationContext());
        token = sharedPrefManager.getTokenOtp().getHttpResponseHeader();
        userMobileNumber = sharedPrefManager.getUser().getCustomerMobileNo();
        
        addressId =getIntent().getExtras().getInt("addressId");
        customerAddressId =getIntent().getExtras().getInt("customerAddressId");
        HouseName1 =getIntent().getExtras().getString("home");
        AddressStreet =getIntent().getExtras().getString("street");
        AddressLandmark =getIntent().getExtras().getString("landmark");
       // AddressCity =getIntent().getExtras().getString("city");
        AddressPin =getIntent().getExtras().getString("pincode");
        AddressDefault = getIntent().getExtras().getString("addressDefaults");

        if (AddressDefault.equals("1")){
            selectCheckBox.setChecked(true);
        }
        else{
            selectCheckBox.setChecked(false);
        }

        houseName.setText(HouseName1);
        street.setText(AddressStreet);
        landmark.setText(AddressLandmark);
        pincode.setText(AddressPin);

        BtnCancle();
        BtnSave();
        sp_City();
        sp_AddressType();
    }

    private void BtnCancle(){
        Btn_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditAddress.this,ProfileActivity.class);
                startActivity(intent);
            }
        });
    }

    private void BtnSave(){
        Btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditsAddress();
            }
        });
    }

    private void sp_City(){
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, usercity);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        city.setAdapter(dataAdapter);
        city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(PickupAddress1Activity.this,""+city.getSelectedItemPosition(),Toast.LENGTH_SHORT).show();
                select_city = String.valueOf(city.getSelectedItem());
                Log.i("select_city","**********select_city*************"+select_city);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void sp_AddressType(){
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, addresstype);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        AddressType.setAdapter(dataAdapter);
        AddressType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Address_type = String.valueOf(AddressType.getSelectedItemPosition());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void EditsAddress() {
        HouseName = houseName.getText().toString().trim();
        Street = street.getText().toString().trim();
        Landmark = landmark.getText().toString().trim();
        Pincode = pincode.getText().toString().trim();

        if (HouseName.isEmpty()) {
            houseName.setError("HouseName can't be blank");
            houseName.requestFocus();
            return;
        } else if (Street.isEmpty()) {
            street.setError("Street can't be blank");
            street.requestFocus();
            return;
        } else if (Landmark.isEmpty()) {
            landmark.setError("Landmark can't be blank");
            landmark.requestFocus();
            return;
        } else if (Pincode.isEmpty()) {
            pincode.setError("Landmark can't be black");
            pincode.requestFocus();
            return;
        } else if (Pincode.length() <= 5) {
            pincode.setError("Enter A valid Pincode");
            pincode.requestFocus();
            return;
        }

        customerAddressList[0] = new CustomerAddressList(addressId,(long) customerAddressId,HouseName,Street,select_city,Pincode,Landmark,"lalo",Address_type,AddressDefault,"1","A");
        //customerAddressList[0] =new CustomerAddressList((long) 0,"Lyondry","HSR Layout","Bengaluru","560092","HSR BDM","","1","1","0","A");
        mainEditAddressList = new MainEditAddressList(userMobileNumber, customerAddressList);
        mainEditAddressList.setCustomerMobileNo(userMobileNumber);
        mainEditAddressList.setCustomerAddressList(customerAddressList);

        Call<EditAddress_responce_modal> call = RetrofitClient
                .getInstance()
                .getApi()
                .EditAddress(token, mainEditAddressList);

        call.enqueue(new Callback<EditAddress_responce_modal>() {
            @Override
            public void onResponse(@NotNull Call<EditAddress_responce_modal> call, Response<EditAddress_responce_modal> response) {

                if (response.isSuccessful()) {

                    EditAddress_responce_modal editAddress_responce_modal = response.body();

                    EditAddress_date editAddress_date = editAddress_responce_modal.getData();
                    boolean successed = editAddress_responce_modal.getSuccess();
                     if (editAddress_responce_modal.getSuccess()) {

                        Intent intent = new Intent(EditAddress.this, ProfileActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                        Toast.makeText(EditAddress.this, "Edit Address Successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(EditAddress.this, editAddress_responce_modal.getErrorMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(EditAddress.this, response.message(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<EditAddress_responce_modal> call, Throwable t) {

                Toast.makeText(EditAddress.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        //}

    }
}