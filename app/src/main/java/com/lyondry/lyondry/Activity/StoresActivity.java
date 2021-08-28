package com.lyondry.lyondry.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.lyondry.lyondry.Adapters.StoresAdapter;
import com.lyondry.lyondry.Adapters.servicesAdapter;
import com.lyondry.lyondry.BottomNavigationFragment;
import com.lyondry.lyondry.Modals.SelectStore.SelectedStore_data;
import com.lyondry.lyondry.Modals.SelectStore.SelectedStore_responce_Modal;
import com.lyondry.lyondry.Modals.Services_data;
import com.lyondry.lyondry.Modals.StoresModal;
import com.lyondry.lyondry.R;
import com.lyondry.lyondry.Retrofits.RetrofitClient;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StoresActivity extends AppCompatActivity {
    BottomNavigationFragment bottomNavigationFragment;
    ImageView backbtn;
    private TextView Invite;
    Spinner City;
    RecyclerView recyclerView;
    StoresAdapter storesAdapter;
    List<StoresModal>storesModalList;
    List<SelectedStore_data> selectedStore_data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stores);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Stores");

        backbtn = findViewById(R.id.back_arrow);
        backbtn.setVisibility(View.GONE);
        Invite = findViewById(R.id.invite);
        City = findViewById(R.id.sp_city);
        recyclerView = findViewById(R.id.stores_recycleView);
        Invite();
        SelectedStore();

        //************************************BottomNavigationBar***************************************
        Fragment bottom_fragment = getSupportFragmentManager().findFragmentById(R.id.bottom_navigation_id);
        if (bottom_fragment instanceof Fragment){
            bottomNavigationFragment = (BottomNavigationFragment)bottom_fragment;
            bottomNavigationFragment.initailizeComponets();
        }
        //************************************BottomNavigationBar***************************************

    }

    private void Invite(){
        Invite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StoresActivity.this, InviteFriendsActivity.class);
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


    private void SelectedStore() {

        Long num = Long.valueOf(0);
        Call<SelectedStore_responce_Modal> call = RetrofitClient
                .getInstance()
                .getApi()
                .SelectedStore(num);

        call.enqueue(new Callback<SelectedStore_responce_Modal>() {
            @Override
            public void onResponse(@NotNull Call<SelectedStore_responce_Modal> call, Response<SelectedStore_responce_Modal> response) {

                if (response.isSuccessful()) {
                    SelectedStore_responce_Modal selectedStore_responce_modal =response.body();
                    Log.i("selectItem","********************selectedStore_responce_modal"+selectedStore_responce_modal);

                    if (selectedStore_responce_modal.getSuccess()) {

                        selectedStore_data = selectedStore_responce_modal.getStoreList();
                        storesAdapter = new StoresAdapter(selectedStore_data,getApplication());
                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplication()));
                        recyclerView.setAdapter(storesAdapter);
                        for (int i = 0; i < selectedStore_data.size(); i++) {
                            SelectedStore_data selectedStore = (SelectedStore_data) selectedStore_data.get(i);
                            int StoreId = selectedStore.getStoreId() ;
                            int StoreCompanyId = selectedStore.getStoreCompanyId();
                            String StoreCode = selectedStore.getStoreCode();
                            String StoreName = selectedStore.getStoreName();
                            String StoreAddress = selectedStore.getStoreAddress();
                            String StoreEmailId = selectedStore.getStoreEmailId();
                            String StorePhone = selectedStore.getStorePhone();
                            String StoreLocation = selectedStore.getStoreLocation();

                            Log.i("StoreId", "***********StoreId***************" + StoreId);
                            Log.i("StoreCompanyId", "***********StoreCompanyId***************" + StoreCompanyId);
                            Log.i("StoreCode", "***********StoreCode***************" + StoreCode);
                            Log.i("StoreName", "***********StoreName***************" + StoreName);
                            Log.i("StoreAddress", "***********StoreAddress***************" + StoreAddress);
                            Log.i("StoreEmailId", "***********StoreEmailId***************" + StoreEmailId);
                            Log.i("StorePhone", "***********StorePhone***************" + StorePhone);
                            Log.i("StoreLocation", "***********StoreLocation***************" + StoreLocation);


                        }
                    }
                    else {
                        Toast.makeText(StoresActivity.this, selectedStore_responce_modal.getErrorMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(StoresActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<SelectedStore_responce_Modal> call, Throwable t) {

                Toast.makeText(StoresActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}