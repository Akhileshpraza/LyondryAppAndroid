package com.lyondry.lyondry.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.lyondry.lyondry.Activity.MainActivity;
import com.lyondry.lyondry.Activity.Schduail2Activity;
import com.lyondry.lyondry.Activity.SignupActivity;
import com.lyondry.lyondry.Classes.SharedPrefManager;
import com.lyondry.lyondry.Modals.InsertPickup.InsertPickup_data;
import com.lyondry.lyondry.Modals.InsertPickup.InsertPickup_responce_modal;
import com.lyondry.lyondry.R;
import com.lyondry.lyondry.Retrofits.RetrofitClient;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TermsAndConditionsBottomSheetDialog extends BottomSheetDialogFragment {
    TextView cancle,Btn_submit;
    CheckBox checkbox;
    SharedPrefManager sharedPrefManager;
    String token, userMobileNumber;
    String selectedType="";
    String serviceId;
    String storeId;
    String timeSlotId;
    int addressId,customerAddressId;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottomsheet_terms_and_conditions, container, false);

        cancle =view.findViewById(R.id.btn_cancle);
        Btn_submit =view.findViewById(R.id.txt_submit);
        checkbox = view.findViewById(R.id.checkBox1);

        sharedPrefManager = new SharedPrefManager(getActivity());
        token = sharedPrefManager.getTokenOtp().getHttpResponseHeader();
        Log.i("Token11", "********************Token11**************************" + token);
        userMobileNumber = sharedPrefManager.getUser().getCustomerMobileNo();
        Log.i("userMobileNumber", "********************userMobileNumber**************************" + userMobileNumber);

        SharedPreferences sp = getContext().getSharedPreferences("MyAddress", Context.MODE_PRIVATE);
        addressId = sp.getInt("addressId", 0);
        customerAddressId = sp.getInt("customerAddressId", 0);

        Log.i("addressId","*************addressId12*************"+addressId);
        customerAddressId = sp.getInt("customerAddressId", 0);
        Log.i("customerAddressId","*************customerAddressId12*************"+customerAddressId);


        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), MainActivity.class);
//                startActivity(intent);
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                dismiss();
            }
        });

        Btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                dismiss();
            }
        });

        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    Btn_submit.setEnabled(true);
                }
                else {
                    Btn_submit.setEnabled(false);
                }
            }
        });

    return view;
    }

    private void InsertPickup() {

        Call<InsertPickup_responce_modal> call = RetrofitClient
                .getInstance()
                .getApi()
                .insertPickup(token,userMobileNumber,customerAddressId, Integer.parseInt(timeSlotId), Integer.parseInt(serviceId), Integer.parseInt(storeId),addressId,1,selectedType);

        call.enqueue(new Callback<InsertPickup_responce_modal>() {
            @Override
            public void onResponse(@NotNull Call<InsertPickup_responce_modal> call, Response<InsertPickup_responce_modal> response) {

                if (response.isSuccessful()) {
                    InsertPickup_responce_modal insertPickup_responce_modal =response.body();
                    InsertPickup_data insertPickup_data = insertPickup_responce_modal.getData();
                    Log.i("selectItem","********************selectedStore_responce_modal"+insertPickup_responce_modal);

                    if (insertPickup_responce_modal.getSuccess()) {
                        InsertPickup_data insertAddress_data = insertPickup_responce_modal.getData();

                        String PickupRequestId = insertAddress_data.getPickupRequestAddress();
                        String PickupRequestStoreName = insertAddress_data.getPickupRequestStoreName();
                        String PickupRequestServiceType = insertAddress_data.getPickupRequestServiceType();
                        String PickupRequestDate = insertAddress_data.getPickupRequestDate();
                        String PickupRequestTime = insertAddress_data.getPickupRequestTime();
                        String PickupRequestAddress = insertAddress_data.getPickupRequestAddress();
                        String PickupRequestStatus = insertAddress_data.getPickupRequestStatus();

                        Log.i("PickupRequestId","***********PickupRequestId************"+PickupRequestId);
                        Log.i("PickupRequestStoreName","***********PickupRequestStoreName************"+PickupRequestStoreName);
                        Log.i("PickupServiceType","***********PickupRequestServiceType************"+PickupRequestServiceType);
                        Log.i("PickupRequestDate","***********PickupRequestDate************"+PickupRequestDate);
                        Log.i("PickupRequestTime","***********PickupRequestTime************"+PickupRequestTime);
                        Log.i("PickupRequestAddress","***********PickupRequestAddress************"+PickupRequestAddress);
                        Log.i("PickupRequestStatus","***********PickupRequestStatus************"+PickupRequestStatus);

                    }
                    else {
                        Toast.makeText(getContext(), insertPickup_responce_modal.getErrorMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), response.message(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<InsertPickup_responce_modal> call, Throwable t) {

                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
