package com.lyondry.lyondry.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Adapter;

import com.lyondry.lyondry.Adapters.servicesAdapter;
import com.lyondry.lyondry.Modals.servicesModal;
import com.lyondry.lyondry.R;

import java.util.ArrayList;
import java.util.List;

public class ServicesActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<servicesModal> servicesModalList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        recyclerView = findViewById(R.id.service_recycleView);

        servicesModalList = new ArrayList<servicesModal>();
        servicesModalList.add(new servicesModal(R.drawable.people,"Akhil"));
        servicesModalList.add(new servicesModal(R.drawable.people,"Akhil"));
        servicesModalList.add(new servicesModal(R.drawable.people,"Akhil"));
        servicesModalList.add(new servicesModal(R.drawable.people,"Akhil"));
        servicesModalList.add(new servicesModal(R.drawable.people,"Akhil"));
        servicesModalList.add(new servicesModal(R.drawable.people,"Akhil"));
        servicesModalList.add(new servicesModal(R.drawable.people,"Akhil"));
        servicesModalList.add(new servicesModal(R.drawable.people,"Akhil"));

        servicesAdapter adapter = new servicesAdapter(servicesModalList);
        recyclerView.setLayoutManager(new GridLayoutManager(this,4));
        recyclerView.setAdapter(adapter);


    }
}