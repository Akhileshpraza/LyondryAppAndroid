package com.lyondry.lyondry.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
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

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.lyondry.lyondry.Classes.SharedPrefManager;
import com.lyondry.lyondry.Modals.InsertAddress_data;
import com.lyondry.lyondry.Modals.InsertAddress_responce;
import com.lyondry.lyondry.Modals.UserUpdate_responce_Modal;
import com.lyondry.lyondry.Modals.address.CustomerAddressList;
import com.lyondry.lyondry.Modals.address.MainCustomerAddressList;
import com.lyondry.lyondry.R;
import com.lyondry.lyondry.Retrofits.RetrofitClient;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PickupAddress1Activity extends AppCompatActivity {

    TextView Btn_cancle;
    Button Btn_save;
    EditText houseName,street,landmark,pincode;
    Spinner city;
    CheckBox selectCheckBox;
    CustomerAddressList[] customerAddressList = new CustomerAddressList[1];
    //CustomerAddressList[] customerAddressList;
    MainCustomerAddressList mainCustomerAddressList;
    SharedPrefManager sharedPrefManager;
    String token,userMobileNumber,HouseName,Street,Landmark,Pincode;
    String[] usercity = {"Bangalore"};
    String[] addresstype = { "Home", "Office"};
    String select_city,Address_type;
    Spinner AddressType;
    String AddressDefault ="0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pickup_address1);
        houseName = findViewById(R.id.evhousenumber);
        street = findViewById(R.id.et_street);
        landmark = findViewById(R.id.et_landmark);
        pincode = findViewById(R.id.et_pincode);
        city = findViewById(R.id.sp_city);
        AddressType = findViewById(R.id.sp_addressTpye);
        selectCheckBox = findViewById(R.id.checkBox_address);
        Btn_cancle = findViewById(R.id.btn_cancle1);
        Btn_save = findViewById(R.id.btn_save);

        sharedPrefManager = new SharedPrefManager(getApplicationContext());
        token = sharedPrefManager.getTokenOtp().getHttpResponseHeader();
        userMobileNumber = sharedPrefManager.getUser().getCustomerMobileNo();
        Log.i("Token1", "********************Token1**************************" + token);
        Log.i("mobile", "********************mobile**************************" + userMobileNumber);
        Log.i("AddressDefault","**********AddressDefault***************"+AddressDefault);

        selectCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    AddressDefault="1";
                    Log.i("AddressDefault","**********checkboxaddressdefault***************"+AddressDefault);
                }
                else {
                    AddressDefault ="0";
                    Log.i("AddressDefault","**********checkboxaddressdefault***************"+AddressDefault);
                }
            }
        });
        sp_City();
        sp_AddressType();
        BtnSave();
        BtnCancle();


        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        AutocompleteFilter filter = new AutocompleteFilter.Builder()
                .setCountry("IN")
                .setTypeFilter(AutocompleteFilter.TYPE_FILTER_CITIES)
                .build();
        autocompleteFragment.setFilter(filter);
        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
               // txtVw.setText(place.getName());
            }
            @Override
            public void onError(Status status) {
                //txtVw.setText(status.toString());
            }
        });

    }

    private void BtnCancle(){
        Btn_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PickupAddress1Activity.this,EditActivity.class);
                startActivity(intent);
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
               // Toast.makeText(PickupAddress1Activity.this,""+AddressType.getSelectedItemPosition(),Toast.LENGTH_SHORT).show();
               // Address_type = String.valueOf(AddressType.getSelectedItem());
                Address_type = String.valueOf(AddressType.getSelectedItemPosition());
                Log.i("Address_type","**************Address_type12133**********"+Address_type);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void BtnSave(){
        Btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAddress();
            }
        });
    }
    private void saveAddress() {
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

            customerAddressList[0] = new CustomerAddressList((long) 0, HouseName, Street, select_city, Pincode, Landmark, "lalo", Address_type, AddressDefault, "1", "A");
            //customerAddressList[0] =new CustomerAddressList((long) 0,"Lyondry","HSR Layout","Bengaluru","560092","HSR BDM","","1","1","0","A");
            mainCustomerAddressList = new MainCustomerAddressList(userMobileNumber, customerAddressList);
            mainCustomerAddressList.setCustomerMobileNo(userMobileNumber);
            mainCustomerAddressList.setCustomerAddressListClass(customerAddressList);

        Call<InsertAddress_responce> call = RetrofitClient
                .getInstance()
                .getApi()
                .InsertAddress(token, mainCustomerAddressList);

        call.enqueue(new Callback<InsertAddress_responce>() {
            @Override
            public void onResponse(@NotNull Call<InsertAddress_responce> call, Response<InsertAddress_responce> response) {

                if (response.isSuccessful()) {

                    InsertAddress_responce insertAddress_responce = response.body();
                    InsertAddress_data insertAddress_data = insertAddress_responce.getData();
                    boolean successed = insertAddress_responce.getSuccess();
                    Log.i("succ", "*************succ************" + successed);
                    Log.i("insertAddress_data", "*************insertAddress_data************" + insertAddress_data);

                    if (insertAddress_responce.getSuccess()) {

                        Intent intent = new Intent(PickupAddress1Activity.this, ProfileActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                        Toast.makeText(PickupAddress1Activity.this, "Insert Address Successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(PickupAddress1Activity.this, insertAddress_responce.getErrorMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(PickupAddress1Activity.this, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<InsertAddress_responce> call, Throwable t) {

                Toast.makeText(PickupAddress1Activity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    //}

    }
}