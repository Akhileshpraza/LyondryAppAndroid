package com.lyondry.lyondry.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.lyondry.lyondry.Adapters.servicesAdapter;
import com.lyondry.lyondry.Modals.Services_responce_modal;
import com.lyondry.lyondry.R;

import java.util.ArrayList;
import java.util.List;

public class ServicesActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Services_responce_modal> servicesModalList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        recyclerView = findViewById(R.id.service_recycleView);

//        servicesModalList = new ArrayList<Services_responce_modal>();
////        servicesModalList.add(new Services_responce_modal(R.drawable.people,"Akhil"));
////        servicesModalList.add(new Services_responce_modal(R.drawable.people,"Akhil"));
////        servicesModalList.add(new Services_responce_modal(R.drawable.people,"Akhil"));
////        servicesModalList.add(new Services_responce_modal(R.drawable.people,"Akhil"));
////        servicesModalList.add(new Services_responce_modal(R.drawable.people,"Akhil"));
////        servicesModalList.add(new Services_responce_modal(R.drawable.people,"Akhil"));
////        servicesModalList.add(new Services_responce_modal(R.drawable.people,"Akhil"));
////        servicesModalList.add(new Services_responce_modal(R.drawable.people,"Akhil"));
//
//        servicesAdapter adapter = new servicesAdapter(servicesModalList);
//        recyclerView.setLayoutManager(new GridLayoutManager(this,4));
//        recyclerView.setAdapter(adapter);


    }
}