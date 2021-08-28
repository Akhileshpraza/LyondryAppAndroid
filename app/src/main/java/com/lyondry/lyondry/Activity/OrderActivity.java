package com.lyondry.lyondry.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.lyondry.lyondry.Adapters.OrderAdapter;
import com.lyondry.lyondry.BottomNavigationFragment;
import com.lyondry.lyondry.Classes.SharedPrefManager;
import com.lyondry.lyondry.Modals.Order.SelectOrder_data;
import com.lyondry.lyondry.Modals.Order.SelectOrder_respoce_modal;
import com.lyondry.lyondry.Modals.Payment.Invoice_data;
import com.lyondry.lyondry.R;
import com.lyondry.lyondry.Retrofits.RetrofitClient;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderActivity extends AppCompatActivity {
    BottomNavigationFragment bottomNavigationFragment;
    ImageView backbtn;
    private TextView Invite;
    Spinner ShortByDate;
    RecyclerView recyclerView;
    OrderAdapter orderAdapter;
    List<SelectOrder_respoce_modal>orderModalList;
    SharedPrefManager sharedPrefManager;
    String userMobileNumber, userName,token;
    List<SelectOrder_data> selectOrder_data;
    List<SelectOrder_data> selectOrderData = new ArrayList<>();
    List<SelectOrder_data> SelectOrder_data_tems = new ArrayList<>();
    List<SelectOrder_data>  selectOrder_dataList = new ArrayList<>();
    SelectOrder_data orderData;

    String custormerId;
    OrderAdapter adapter;
    Context context;
    SharedPreferences sp;
    int customerid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Order");

        backbtn = findViewById(R.id.back_arrow);
        backbtn.setVisibility(View.GONE);
        Invite = findViewById(R.id.invite);
        ShortByDate = findViewById(R.id.sp_date);

        sharedPrefManager = new SharedPrefManager(getApplicationContext());
        userMobileNumber = sharedPrefManager.getUser().getCustomerMobileNo();
        custormerId = sharedPrefManager.getLoginUser().getCustomerName();
        token = sharedPrefManager.getTokenOtp().getHttpResponseHeader();
        customerid =sharedPrefManager.getUser().getCustomerId();
        Log.i("orderToken", "********************orderToken**************************" + token);
        Log.i("orderuserMobileNumber", "********************orderuserMobileNumber**************************" + userMobileNumber);
        Log.i("CustormerId", "********************CustormerId13**************************" + customerid);

//        sp =getSharedPreferences("MyAddress",Context.MODE_PRIVATE);
//        customerid = sp.getInt("CustomerId",0);
//        //Log.i("CustormerId", "********************CustormerId**************************" + customerid);


        //************************************BottomNavigationBar***************************************
        Fragment bottom_fragment = getSupportFragmentManager().findFragmentById(R.id.bottom_navigation_id);
        if (bottom_fragment instanceof Fragment){
            bottomNavigationFragment = (BottomNavigationFragment)bottom_fragment;
            bottomNavigationFragment.initailizeComponets();
        }
        //************************************BottomNavigationBar***************************************



        recyclerView = findViewById(R.id.order_recycleView);

//         orderAdapter = new OrderAdapter(orderModalList,getBaseContext());
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(orderAdapter);

      Invite();
     SelectOrder();

    }
    public void Invite(){
        Invite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderActivity.this,InviteFriendsActivity.class);
                startActivity(intent);
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

    private void SelectOrder(){

        Call<SelectOrder_respoce_modal> call = RetrofitClient
                .getInstance()
                .getApi()
                .SelectOrder(token, userMobileNumber, customerid);

        call.enqueue(new Callback<SelectOrder_respoce_modal>() {
            @Override
            public void onResponse(@NotNull Call<SelectOrder_respoce_modal> call, Response<SelectOrder_respoce_modal> response) {

                if (response.isSuccessful()) {

                    SelectOrder_respoce_modal selectOrder_respoce_modal = response.body();

                    if (selectOrder_respoce_modal.getSuccess()) {

//                        selectOrderData = selectOrder_respoce_modal.getPickupRequestList();
//                        adapter = new OrderAdapter(selectOrder_data, context);
//                        recyclerView.setLayoutManager(new LinearLayoutManager(context));
//                        recyclerView.setAdapter(adapter);


                        selectOrderData = selectOrder_respoce_modal.getPickupRequestList();

                        if (selectOrderData.isEmpty()) {
                            Toast.makeText(OrderActivity.this, "Order list is emplty", Toast.LENGTH_SHORT).show();
                        } else {

                            String[] pickupRequestDate = new String[selectOrderData.size()];
                            for (int i = 0; i < selectOrderData.size(); i++) {
                                orderData = (SelectOrder_data) selectOrderData.get(i);

                                int PickupRequestId = orderData.getPickupRequestId();
                                String PickupRequestDate = orderData.getPickupRequestDate();
                                pickupRequestDate[i] = PickupRequestDate;
                                String PickupRequestStatus = orderData.getPickupRequestStatus();

                                Log.i("PickupRequestId", "*******PickupRequestId**********" + PickupRequestId);
                                Log.i("PickupRequestDate", "*******PickupRequestDate**********" + PickupRequestDate);
                                Log.i("PickupRequestStatus", "*******PickupRequestStatus**********" + PickupRequestStatus);

                                SelectOrder_data_tems.add(new SelectOrder_data(PickupRequestId, PickupRequestDate, PickupRequestStatus));
                                adapter = new OrderAdapter(SelectOrder_data_tems, getApplicationContext());
                                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                recyclerView.setAdapter(adapter);
                                adapter.notifyDataSetChanged();

                            }
                            //***********************removedDuplicates*******************
                            Set<String> hashSet = new LinkedHashSet(Arrays.asList(pickupRequestDate));
                            ArrayList<String> removedDuplicatesDate = new ArrayList(hashSet);
                            removedDuplicatesDate.add("All Date");
                            Collections.reverse(removedDuplicatesDate);
                            //***********************removedDuplicates*******************


                            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, removedDuplicatesDate);
                            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            ShortByDate.setAdapter(dataAdapter);

                            ShortByDate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                                    if (ShortByDate.getSelectedItem().toString().equalsIgnoreCase("All Date")) {

                                        selectOrderData = selectOrder_respoce_modal.getPickupRequestList();
                                        adapter = new OrderAdapter(selectOrderData, getApplicationContext());
                                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                        recyclerView.setAdapter(adapter);
                                        adapter.notifyDataSetChanged();

                                    } else {
                                        filter(ShortByDate.getSelectedItem().toString());

                                    }
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {
                                }
                            });


                            //Toast.makeText(OrderActivity.this, "Select Order", Toast.LENGTH_SHORT).show();

                        }
                    }
                    else {
                        Toast.makeText(OrderActivity.this, selectOrder_respoce_modal.getErrorMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(OrderActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<SelectOrder_respoce_modal> call, Throwable t) {

                Toast.makeText(OrderActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    //****************filter location****************************
    private void filter(String text) {
        ArrayList<SelectOrder_data> filteredList = new ArrayList<>();

        for (SelectOrder_data shortByDate : SelectOrder_data_tems) {

            if (shortByDate.getPickupRequestDate().contains(text)) {

                filteredList.add(shortByDate);
            }
        }

        adapter.filterList(filteredList);

    }

    //****************filter location****************************
}