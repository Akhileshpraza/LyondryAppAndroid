package com.lyondry.lyondry.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lyondry.lyondry.Adapters.PuschaseofferAdapter;
import com.lyondry.lyondry.Modals.PurchaseofferModal;
import com.lyondry.lyondry.R;

import java.util.ArrayList;
import java.util.List;


public class PurchaseofferFragment extends Fragment {


    public PurchaseofferFragment() {
        // Required empty public constructor
    }
    RecyclerView recyclerView;
    PuschaseofferAdapter adapter;
    List<PurchaseofferModal> purchaseofferModalList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_completed,container,false);

        recyclerView = view.findViewById(R.id.completed_recycleview);

//        purchaseofferModalList = new ArrayList<PurchaseofferModal>();
//        purchaseofferModalList.add(new PurchaseofferModal("offer101","50% OFF on First wash","Upto Rs.250"));
//        purchaseofferModalList.add(new PurchaseofferModal("offer101","50% OFF on First wash","Upto Rs.250"));
//        purchaseofferModalList.add(new PurchaseofferModal("offer101","50% OFF on First wash","Upto Rs.250"));
//        purchaseofferModalList.add(new PurchaseofferModal("offer101","50% OFF on First wash","Upto Rs.250"));
//        purchaseofferModalList.add(new PurchaseofferModal("offer101","50% OFF on First wash","Upto Rs.250"));
//        purchaseofferModalList.add(new PurchaseofferModal("offer101","50% OFF on First wash","Upto Rs.250"));
//        purchaseofferModalList.add(new PurchaseofferModal("offer101","50% OFF on First wash","Upto Rs.250"));
//        purchaseofferModalList.add(new PurchaseofferModal("offer101","50% OFF on First wash","Upto Rs.250"));
//        purchaseofferModalList.add(new PurchaseofferModal("offer101","50% OFF on First wash","Upto Rs.250"));
//        purchaseofferModalList.add(new PurchaseofferModal("offer101","50% OFF on First wash","Upto Rs.250"));
//        purchaseofferModalList.add(new PurchaseofferModal("offer101","50% OFF on First wash","Upto Rs.250"));
//        purchaseofferModalList.add(new PurchaseofferModal("offer101","50% OFF on First wash","Upto Rs.250"));
//
//
//        adapter = new PuschaseofferAdapter(purchaseofferModalList,getContext());
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        recyclerView.setAdapter(adapter);

        return view;
    }
}