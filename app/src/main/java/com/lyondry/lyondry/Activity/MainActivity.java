package com.lyondry.lyondry.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.lyondry.lyondry.Adapters.OrderAdapter;
import com.lyondry.lyondry.Adapters.servicesAdapter;
import com.lyondry.lyondry.Adapters.sliderAdapter;
import com.lyondry.lyondry.BottomNavigationFragment;
import com.lyondry.lyondry.Classes.SharedPrefManager;
import com.lyondry.lyondry.Fragments.SchedulePickupBottomSheetDialog;
import com.lyondry.lyondry.Modals.Company.CompanyMaster_data;
import com.lyondry.lyondry.Modals.Company.CompanyMaster_responce_modal;
import com.lyondry.lyondry.Modals.GetAddress_data;
import com.lyondry.lyondry.Modals.Logout_data;
import com.lyondry.lyondry.Modals.Logout_responce_modal;
import com.lyondry.lyondry.Modals.Order.SelectOrder_data;
import com.lyondry.lyondry.Modals.Order.SelectOrder_respoce_modal;
import com.lyondry.lyondry.Modals.SelectSchedule.MainSelectSchedule;
import com.lyondry.lyondry.Modals.SelectSchedule.PickupSchedule;
import com.lyondry.lyondry.Modals.SelectSchedule.SelectSchedule_data;
import com.lyondry.lyondry.Modals.SelectSchedule.SelectSchedule_responce_modal;
import com.lyondry.lyondry.Modals.Services_data;
import com.lyondry.lyondry.Modals.Services_responce_modal;
import com.lyondry.lyondry.Modals.sliderModal;
import com.lyondry.lyondry.R;
import com.lyondry.lyondry.Retrofits.RetrofitClient;


import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.lyondry.lyondry.R.color.cyan_light;

public class MainActivity extends AppCompatActivity {
    BottomNavigationFragment bottomNavigationFragment;

    Button Btn_schedual;
    TextView name, mobile,invite;
    RecyclerView recyclerView;
    List<Services_responce_modal> servicesModalList;
    SharedPrefManager sharedPrefManager;
    String userMobileNumber, userName,token;
    String otpToken, getOTP;
    Logout_responce_modal logout_responce_modal;
    Logout_data logout_data;
    List<PickupSchedule> pickupSchedule;

    //*******************recend order stsus********************
    TextView Schedule ,Accepted, Pickup,PickupCompleted,Laundry, Delivery, Delivered;
    ImageView Check1,Check2,Check3,Check4,Check5,Check6,Check7;
    ImageView Proges1,Proges2,Proges3,Proges4,Proges5,Proges6;
    //*******************recend order stsus********************
    //**********************************banner_slider***************************
    ViewPager bannerSliderviewPager;
    sliderAdapter slideAdapter;
    List<sliderModal> sliderModalList;
    private int currentPage = 2;
    private Timer timer;
    final private long DELY_TIME = 3000;
    final private long PERIOD_TIME = 3000;
    List<Services_data> services_data;
    List<Services_responce_modal> services_responce_modals;
    Context context;
    int customerid;
    List<SelectOrder_data> selectOrder_data;
    OrderAdapter adapter;
    String PickupRequestStatus=null;


    //**********************************banner_slider******************************************
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setIcon(R.drawable.small_logo);
        name = findViewById(R.id.username);
        mobile = findViewById(R.id.usermobile);
        invite = findViewById(R.id.invite_friends);


        //*******************recend order stsus********************
        Schedule = findViewById(R.id.pending);
        Accepted = findViewById(R.id.accepted);
        Pickup = findViewById(R.id.pickup);
        PickupCompleted = findViewById(R.id.pickupcomplete);
        Laundry =findViewById(R.id.laundry);
        Delivery = findViewById(R.id.delivery_schedual);
        Delivered = findViewById(R.id.delivered);

        Check1 = findViewById(R.id.chech1);
        Check2 = findViewById(R.id.chech2);
        Check3 = findViewById(R.id.chech3);
        Check4 = findViewById(R.id.chech4);
        Check5 = findViewById(R.id.chech5);
        Check6 = findViewById(R.id.chech6);
        Check7 = findViewById(R.id.chech7);

        Proges1 = findViewById(R.id.proges1);
        Proges2 = findViewById(R.id.proges2);
        Proges3 = findViewById(R.id.proges3);
        Proges4 = findViewById(R.id.proges4);
        Proges5 = findViewById(R.id.proges5);
        Proges6 = findViewById(R.id.proges6);
        //*******************recend order stsus********************

        sharedPrefManager = new SharedPrefManager(getApplicationContext());
        userMobileNumber = sharedPrefManager.getUser().getCustomerMobileNo();
        userName = sharedPrefManager.getUser().getCustomerName();

        token = sharedPrefManager.getTokenOtp().getHttpResponseHeader();
        customerid =sharedPrefManager.getUser().getCustomerId();
        Log.i("Token11", "********************Token11**************************" + token);
        Log.i("userMobileNumber", "********************userMobileNumber**************************" + userMobileNumber);



        name.setText(userName);
        mobile.setText(userMobileNumber);

        //************************************BottomNavigationBar***************************************
        Fragment bottom_fragment = getSupportFragmentManager().findFragmentById(R.id.bottom_navigation_id);
        if (bottom_fragment instanceof Fragment) {
            bottomNavigationFragment = (BottomNavigationFragment) bottom_fragment;
            bottomNavigationFragment.initailizeComponets();
        }
        //************************************BottomNavigationBar***************************************

        recyclerView = findViewById(R.id.service_recycleView);
        Btn_schedual = findViewById(R.id.btn_schedular);

        Services();
        InviteFriend();
        SelectOrder();
       // PickupSchedule();
        //**********************************banner_slider******************************************
        bannerSliderviewPager = findViewById(R.id.banner_viewpager);

        sliderModalList = new ArrayList<sliderModal>();
        sliderModalList.add(new sliderModal(R.drawable.banner2));
        sliderModalList.add(new sliderModal(R.drawable.banner2));
        sliderModalList.add(new sliderModal(R.drawable.banner2));
        sliderModalList.add(new sliderModal(R.drawable.banner2));
        sliderModalList.add(new sliderModal(R.drawable.banner2));
        sliderModalList.add(new sliderModal(R.drawable.banner2));
        sliderModalList.add(new sliderModal(R.drawable.banner2));
        sliderModalList.add(new sliderModal(R.drawable.banner2));

        slideAdapter = new sliderAdapter(sliderModalList);
        bannerSliderviewPager.setAdapter(slideAdapter);
        bannerSliderviewPager.setClipToPadding(false);
        bannerSliderviewPager.setPageMargin(20);
        ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentPage = 1;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == ViewPager.SCROLL_STATE_IDLE) {
                    pageLooper();
                }
            }
        };
        bannerSliderviewPager.addOnPageChangeListener(onPageChangeListener);

        startbannerSliderShow();
        bannerSliderviewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                pageLooper();
                stopBannerSliderShow();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    startbannerSliderShow();
                }

                return false;
            }
        });
        //**********************************banner_slider******************************************

        //**********************************Btn Schedual******************************************

        Btn_schedual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SchedulePickupBottomSheetDialog bottomSheet = new SchedulePickupBottomSheetDialog();
                bottomSheet.show(getSupportFragmentManager(),
                        "ModalBottomSheet");
//                Intent intent = new Intent(MainActivity.this, SechualActivity.class);
//                startActivity(intent);

            }
        });
        //**********************************Btn Schedual******************************************

    }

    private void InviteFriend(){
        invite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,InviteFriendsActivity.class);
                startActivity(intent);
            }
        });

    }

    private void Services() {

        Long num = Long.valueOf(0);
        Call<Services_responce_modal> call = RetrofitClient
                .getInstance()
                .getApi()
                .SelectService(num);

        call.enqueue(new Callback<Services_responce_modal>() {
            @Override
            public void onResponse(@NotNull Call<Services_responce_modal> call, Response<Services_responce_modal> response) {

                if (response.isSuccessful()) {

                    Services_responce_modal services_responce_modal = response.body();

                    if (services_responce_modal.getSuccess()) {
                        services_data = services_responce_modal.getServiceList();
                        servicesAdapter adapter = new servicesAdapter(services_data, context);
                        recyclerView.setLayoutManager(new GridLayoutManager(context, 3));
                        recyclerView.setAdapter(adapter);

                        for (int i=0; i<services_data.size(); i++){

                            Services_data servicesData = (Services_data) services_data.get(i);

                            String name = servicesData.getServiceName();
                            int id = servicesData.getServiceId();

                            //Log.i("Service_Name","**************Service_Name***************"+name);
                        }

                    } else {
                        Toast.makeText(MainActivity.this, services_responce_modal.getErrorMessage(), Toast.LENGTH_SHORT).show();

                    }

                } else {
                    Toast.makeText(MainActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Services_responce_modal> call, Throwable t) {

                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.qrcode:
                Toast.makeText(getApplicationContext(), "Coming soon", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.notification:
                Toast.makeText(getApplicationContext(), "Coming soon", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.about:
                Intent intent = new Intent(getApplicationContext(), AboutActivity.class);
                startActivity(intent);
                //Toast.makeText(getApplicationContext(), "more_vertical", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.privacy_policy:
//                Intent intent = new Intent(this,AboutActivity.class);
//                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Coming Soon", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.term_services:
//                Intent intent = new Intent(this,AboutActivity.class);
//                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Coming Soon", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.logout:
//                Intent intent = new Intent(this,AboutActivity.class);
//                startActivity(intent);
                Logout();
                //Toast.makeText(getApplicationContext(), "Coming Soon", Toast.LENGTH_SHORT).show();
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
            @RequiresApi(api = Build.VERSION_CODES.FROYO)
            @Override
            public void onResponse(@NotNull Call<SelectOrder_respoce_modal> call, Response<SelectOrder_respoce_modal> response) {

                if (response.isSuccessful()) {

                    SelectOrder_respoce_modal selectOrder_respoce_modal = response.body();

                    if (selectOrder_respoce_modal.getSuccess()) {

                        selectOrder_data = selectOrder_respoce_modal.getPickupRequestList();
                        Log.i("selectOrder_data","*******selectOrder_data*************"+selectOrder_data);
                        for (int i = 0; i < selectOrder_data.size(); i++) {
                            SelectOrder_data orderData = (SelectOrder_data) selectOrder_data.get(i);

                            PickupRequestStatus = orderData.getPickupRequestStatus();

                            Log.e("PickupRequestStatus", "*******PickupRequestStatus1**********" + PickupRequestStatus);

                        }

                        if (selectOrder_data.isEmpty()){
                            Schedule.setTextColor(Color.parseColor("#31396B"));
                            Accepted.setTextColor(Color.parseColor("#31396B"));
                            Pickup.setTextColor(Color.parseColor("#31396B"));
                            PickupCompleted.setTextColor(Color.parseColor("#31396B"));
                            Laundry.setTextColor(Color.parseColor("#31396B"));
                            Delivery.setTextColor(Color.parseColor("#31396B"));
                            Delivered.setTextColor(Color.parseColor("#31396B"));
                            Check1.setImageResource(R.drawable.gray_clock);
                            Check2.setImageResource(R.drawable.gray_clock);
                            Check3.setImageResource(R.drawable.gray_clock);
                            Check4.setImageResource(R.drawable.gray_clock);
                            Check5.setImageResource(R.drawable.gray_clock);
                            Check6.setImageResource(R.drawable.gray_clock);
                            Check7.setImageResource(R.drawable.gray_clock);
                            Proges1.setColorFilter(Color.parseColor("#31396B"));
                            Proges2.setColorFilter(Color.parseColor("#31396B"));
                            Proges3.setColorFilter(Color.parseColor("#31396B"));
                            Proges4.setColorFilter(Color.parseColor("#31396B"));
                            Proges5.setColorFilter(Color.parseColor("#31396B"));
                            Proges6.setColorFilter(Color.parseColor("#31396B"));
                        }
                        else {

                            if (PickupRequestStatus.equals(null)){
                                Schedule.setTextColor(Color.parseColor("#31396B"));
                                Accepted.setTextColor(Color.parseColor("#31396B"));
                                Pickup.setTextColor(Color.parseColor("#31396B"));
                                PickupCompleted.setTextColor(Color.parseColor("#31396B"));
                                Laundry.setTextColor(Color.parseColor("#31396B"));
                                Delivery.setTextColor(Color.parseColor("#31396B"));
                                Delivered.setTextColor(Color.parseColor("#31396B"));
                                Check1.setImageResource(R.drawable.gray_clock);
                                Check2.setImageResource(R.drawable.gray_clock);
                                Check3.setImageResource(R.drawable.gray_clock);
                                Check4.setImageResource(R.drawable.gray_clock);
                                Check5.setImageResource(R.drawable.gray_clock);
                                Check6.setImageResource(R.drawable.gray_clock);
                                Check7.setImageResource(R.drawable.gray_clock);
                                Proges1.setColorFilter(Color.parseColor("#31396B"));
                                Proges2.setColorFilter(Color.parseColor("#31396B"));
                                Proges3.setColorFilter(Color.parseColor("#31396B"));
                                Proges4.setColorFilter(Color.parseColor("#31396B"));
                                Proges5.setColorFilter(Color.parseColor("#31396B"));
                                Proges6.setColorFilter(Color.parseColor("#31396B"));
//
                            }
                            else if (PickupRequestStatus.equals("Schedule")){
                                //Toast.makeText(getApplicationContext(),"pending",Toast.LENGTH_SHORT).show();
                                Schedule.setText(PickupRequestStatus);

                                Schedule.setTextColor(Color.parseColor("#0ECFD1"));
                                Accepted.setTextColor(Color.parseColor("#31396B"));
                                Pickup.setTextColor(Color.parseColor("#31396B"));
                                PickupCompleted.setTextColor(Color.parseColor("#31396B"));
                                Laundry.setTextColor(Color.parseColor("#31396B"));
                                Delivery.setTextColor(Color.parseColor("#31396B"));
                                Delivered.setTextColor(Color.parseColor("#31396B"));

                                Check1.setImageResource(R.drawable.green_checked);
                                Check2.setImageResource(R.drawable.gray_clock);
                                Check3.setImageResource(R.drawable.gray_clock);
                                Check4.setImageResource(R.drawable.gray_clock);
                                Check5.setImageResource(R.drawable.gray_clock);
                                Check6.setImageResource(R.drawable.gray_clock);
                                Check7.setImageResource(R.drawable.gray_clock);

                                Proges1.setColorFilter(Color.parseColor("#31396B"));
                                Proges2.setColorFilter(Color.parseColor("#31396B"));
                                Proges3.setColorFilter(Color.parseColor("#31396B"));
                                Proges4.setColorFilter(Color.parseColor("#31396B"));
                                Proges5.setColorFilter(Color.parseColor("#31396B"));
                                Proges6.setColorFilter(Color.parseColor("#31396B"));
                            }

                            else if (PickupRequestStatus.equals("Accepted")){
                               // Toast.makeText(getApplicationContext(),"Accepted",Toast.LENGTH_SHORT).show();
                                Accepted.setText(PickupRequestStatus);

                                Schedule.setTextColor(Color.parseColor("#0ECFD1"));
                                Accepted.setTextColor(Color.parseColor("#0ECFD1"));
                                Pickup.setTextColor(Color.parseColor("#31396B"));
                                PickupCompleted.setTextColor(Color.parseColor("#31396B"));
                                Laundry.setTextColor(Color.parseColor("#31396B"));
                                Delivery.setTextColor(Color.parseColor("#31396B"));
                                Delivered.setTextColor(Color.parseColor("#31396B"));

                                Check1.setImageResource(R.drawable.green_checked);
                                Check2.setImageResource(R.drawable.green_checked);
                                Check3.setImageResource(R.drawable.gray_clock);
                                Check4.setImageResource(R.drawable.gray_clock);
                                Check5.setImageResource(R.drawable.gray_clock);
                                Check6.setImageResource(R.drawable.gray_clock);
                                Check7.setImageResource(R.drawable.gray_clock);

                                Proges1.setColorFilter(Color.parseColor("#0ECFD1"));
                                Proges2.setColorFilter(Color.parseColor("#31396B"));
                                Proges3.setColorFilter(Color.parseColor("#31396B"));
                                Proges4.setColorFilter(Color.parseColor("#31396B"));
                                Proges5.setColorFilter(Color.parseColor("#31396B"));
                                Proges6.setColorFilter(Color.parseColor("#31396B"));
                            }

                            else if (PickupRequestStatus.equals("Pickup")){
                                //Toast.makeText(getApplicationContext(),"Pickup",Toast.LENGTH_SHORT).show();
                                Pickup.setText(PickupRequestStatus);

                                Schedule.setTextColor(Color.parseColor("#0ECFD1"));
                                Accepted.setTextColor(Color.parseColor("#0ECFD1"));
                                Pickup.setTextColor(Color.parseColor("#0ECFD1"));
                                PickupCompleted.setTextColor(Color.parseColor("#31396B"));
                                Laundry.setTextColor(Color.parseColor("#31396B"));
                                Delivery.setTextColor(Color.parseColor("#31396B"));
                                Delivered.setTextColor(Color.parseColor("#31396B"));

                                Check1.setImageResource(R.drawable.green_checked);
                                Check2.setImageResource(R.drawable.green_checked);
                                Check3.setImageResource(R.drawable.green_checked);
                                Check4.setImageResource(R.drawable.gray_clock);
                                Check5.setImageResource(R.drawable.gray_clock);
                                Check6.setImageResource(R.drawable.gray_clock);
                                Check7.setImageResource(R.drawable.gray_clock);

                                Proges1.setColorFilter(Color.parseColor("#0ECFD1"));
                                Proges2.setColorFilter(Color.parseColor("#0ECFD1"));
                                Proges3.setColorFilter(Color.parseColor("#31396B"));
                                Proges4.setColorFilter(Color.parseColor("#31396B"));
                                Proges5.setColorFilter(Color.parseColor("#31396B"));
                                Proges6.setColorFilter(Color.parseColor("#31396B"));
                            }

                            else if (PickupRequestStatus.equals("Pickup")){
                                //Toast.makeText(getApplicationContext(),"Pickup",Toast.LENGTH_SHORT).show();
                                PickupCompleted.setText(PickupRequestStatus);

                                Schedule.setTextColor(Color.parseColor("#0ECFD1"));
                                Accepted.setTextColor(Color.parseColor("#0ECFD1"));
                                Pickup.setTextColor(Color.parseColor("#0ECFD1"));
                                PickupCompleted.setTextColor(Color.parseColor("#0ECFD1"));
                                Laundry.setTextColor(Color.parseColor("#31396B"));
                                Delivery.setTextColor(Color.parseColor("#31396B"));
                                Delivered.setTextColor(Color.parseColor("#31396B"));

                                Check1.setImageResource(R.drawable.green_checked);
                                Check2.setImageResource(R.drawable.green_checked);
                                Check3.setImageResource(R.drawable.green_checked);
                                Check7.setImageResource(R.drawable.green_checked);
                                Check4.setImageResource(R.drawable.gray_clock);
                                Check5.setImageResource(R.drawable.gray_clock);
                                Check6.setImageResource(R.drawable.gray_clock);


                                Proges1.setColorFilter(Color.parseColor("#0ECFD1"));
                                Proges2.setColorFilter(Color.parseColor("#0ECFD1"));
                                Proges3.setColorFilter(Color.parseColor("#31396B"));
                                Proges4.setColorFilter(Color.parseColor("#31396B"));
                                Proges5.setColorFilter(Color.parseColor("#31396B"));
                                Proges6.setColorFilter(Color.parseColor("#0ECFD1"));
                            }

                            else if (PickupRequestStatus.equals("Laundry")){
                                //Toast.makeText(getApplicationContext(),"Laundry",Toast.LENGTH_SHORT).show();
                                Laundry.setText(PickupRequestStatus);

                                Schedule.setTextColor(Color.parseColor("#0ECFD1"));
                                Accepted.setTextColor(Color.parseColor("#0ECFD1"));
                                Pickup.setTextColor(Color.parseColor("#0ECFD1"));
                                PickupCompleted.setTextColor(Color.parseColor("#0ECFD1"));
                                Laundry.setTextColor(Color.parseColor("#0ECFD1"));
                                Delivery.setTextColor(Color.parseColor("#31396B"));
                                Delivered.setTextColor(Color.parseColor("#31396B"));

                                Check1.setImageResource(R.drawable.green_checked);
                                Check2.setImageResource(R.drawable.green_checked);
                                Check3.setImageResource(R.drawable.green_checked);
                                Check4.setImageResource(R.drawable.green_checked);
                                Check7.setImageResource(R.drawable.green_checked);
                                Check5.setImageResource(R.drawable.gray_clock);
                                Check6.setImageResource(R.drawable.gray_clock);

                                Proges1.setColorFilter(Color.parseColor("#0ECFD1"));
                                Proges2.setColorFilter(Color.parseColor("#0ECFD1"));
                                Proges3.setColorFilter(Color.parseColor("#0ECFD1"));
                                Proges6.setColorFilter(Color.parseColor("#0ECFD1"));
                                Proges4.setColorFilter(Color.parseColor("#31396B"));
                                Proges5.setColorFilter(Color.parseColor("#31396B"));

                            }

                            else if (PickupRequestStatus.equals("Delivery Scheduled")){
                                //Toast.makeText(getApplicationContext(),"Delivery Scheduled",Toast.LENGTH_SHORT).show();
                                Delivery.setText(PickupRequestStatus);

                                Schedule.setTextColor(Color.parseColor("#0ECFD1"));
                                Accepted.setTextColor(Color.parseColor("#0ECFD1"));
                                Pickup.setTextColor(Color.parseColor("#0ECFD1"));
                                PickupCompleted.setTextColor(Color.parseColor("#0ECFD1"));
                                Laundry.setTextColor(Color.parseColor("#0ECFD1"));
                                Delivery.setTextColor(Color.parseColor("#0ECFD1"));
                                Delivered.setTextColor(Color.parseColor("#31396B"));

                                Check1.setImageResource(R.drawable.green_checked);
                                Check2.setImageResource(R.drawable.green_checked);
                                Check3.setImageResource(R.drawable.green_checked);
                                Check7.setImageResource(R.drawable.green_checked);
                                Check4.setImageResource(R.drawable.green_checked);
                                Check5.setImageResource(R.drawable.green_checked);
                                Check6.setImageResource(R.drawable.gray_clock);

                                Proges1.setColorFilter(Color.parseColor("#0ECFD1"));
                                Proges2.setColorFilter(Color.parseColor("#0ECFD1"));
                                Proges3.setColorFilter(Color.parseColor("#0ECFD1"));
                                Proges6.setColorFilter(Color.parseColor("#0ECFD1"));
                                Proges4.setColorFilter(Color.parseColor("#0ECFD1"));
                                Proges5.setColorFilter(Color.parseColor("#31396B"));
                            }

                            else if (PickupRequestStatus.equals("Delivered")){
                                //Toast.makeText(getApplicationContext(),"Delivered",Toast.LENGTH_SHORT).show();
                                Delivered.setText(PickupRequestStatus);

                                Schedule.setTextColor(Color.parseColor("#0ECFD1"));
                                Accepted.setTextColor(Color.parseColor("#0ECFD1"));
                                Pickup.setTextColor(Color.parseColor("#0ECFD1"));
                                PickupCompleted.setTextColor(Color.parseColor("#0ECFD1"));
                                Laundry.setTextColor(Color.parseColor("#0ECFD1"));
                                Delivery.setTextColor(Color.parseColor("#0ECFD1"));
                                Delivered.setTextColor(Color.parseColor("#0ECFD1"));

                                Check1.setImageResource(R.drawable.green_checked);
                                Check2.setImageResource(R.drawable.green_checked);
                                Check3.setImageResource(R.drawable.green_checked);
                                Check7.setImageResource(R.drawable.green_checked);
                                Check4.setImageResource(R.drawable.green_checked);
                                Check5.setImageResource(R.drawable.green_checked);
                                Check6.setImageResource(R.drawable.green_checked);


                                Proges1.setColorFilter(Color.parseColor("#0ECFD1"));
                                Proges2.setColorFilter(Color.parseColor("#0ECFD1"));
                                Proges3.setColorFilter(Color.parseColor("#0ECFD1"));
                                Proges6.setColorFilter(Color.parseColor("#0ECFD1"));
                                Proges4.setColorFilter(Color.parseColor("#0ECFD1"));
                                Proges5.setColorFilter(Color.parseColor("#0ECFD1"));
                            }

                        }



//

                        //*******************recend order stsus********************
                       // Toast.makeText(MainActivity.this, "Select Order", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(MainActivity.this, ""+selectOrder_respoce_modal.getErrorMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<SelectOrder_respoce_modal> call, Throwable t) {

                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }



    private void Logout() {
//        sharedPrefManager.logout();
//        Intent intent = new Intent(MainActivity.this,LoginActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        startActivity(intent);
//        Toast.makeText(this,"You have been logged out",Toast.LENGTH_SHORT).show();

        userMobileNumber = sharedPrefManager.getUser().getCustomerMobileNo();
        Log.i("umobile", "&&&&&&&&&&&&&&&&&&&&&&&&&&&&" + userMobileNumber);


        Call<Logout_responce_modal> call = RetrofitClient
                .getInstance()
                .getApi()
                .logout(userMobileNumber);

        call.enqueue(new Callback<Logout_responce_modal>() {
            @Override
            public void onResponse(@NotNull Call<Logout_responce_modal> call, Response<Logout_responce_modal> response) {

                try {
                    if (response.isSuccessful()) {
                        JSONObject jsonObject = new JSONObject(new Gson().toJson(response.body()));
                       // Log.i("JesonObject ", "***********jsonObject*******************" + jsonObject);

                        Logout_responce_modal logout_responce_modal = response.body();
                        Logout_data logout_data = logout_responce_modal.getLogout_data();

                        if (logout_responce_modal.getSuccess()) {


                            sharedPrefManager.removeData(getApplicationContext());
                            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                            finish();

                            Toast.makeText(MainActivity.this, "Logout Successfully", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(MainActivity.this, logout_responce_modal.getError().toString(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Logout_responce_modal> call, Throwable t) {

                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    //**********************************banner_slider******************************************
    private void pageLooper() {
        if (currentPage == sliderModalList.size() - 2) {
            currentPage = 2;
            bannerSliderviewPager.setCurrentItem(currentPage, false);
        }
        if (currentPage == 1) {
            currentPage = sliderModalList.size() - 3;
            bannerSliderviewPager.setCurrentItem(currentPage, false);
        }
    }

    private void startbannerSliderShow() {
        Handler handler = new Handler();
        Runnable update = new Runnable() {
            @Override
            public void run() {
                if (currentPage >= sliderModalList.size()) {
                    currentPage = 1;
                }
                bannerSliderviewPager.setCurrentItem(currentPage++, true);
            }
        };
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(update);
            }
        }, DELY_TIME, PERIOD_TIME);
    }

    private void stopBannerSliderShow() {
        timer.cancel();
    }
    //**********************************banner_slider******************************************
}