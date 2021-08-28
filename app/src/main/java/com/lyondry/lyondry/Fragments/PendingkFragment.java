package com.lyondry.lyondry.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.lyondry.lyondry.Adapters.PendingAdapter;
import com.lyondry.lyondry.Classes.SharedPrefManager;
import com.lyondry.lyondry.Modals.Payment.Invoice_data;
import com.lyondry.lyondry.Modals.Payment.SelectInvoiceStatus_responce_modal;
import com.lyondry.lyondry.R;
import com.lyondry.lyondry.Retrofits.RetrofitClient;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PendingkFragment extends Fragment {


    public PendingkFragment() {
        // Required empty public constructor
    }

    RecyclerView recyclerView;
    PendingAdapter adapter;
    List<Invoice_data>pendingModalList;
    List<Invoice_data>pendingModalList_temp = new ArrayList<>();
    SharedPrefManager sharedPrefManager;
    String token, userMobileNumber;
    List<Invoice_data>  invoice_dates = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_pendingk,container,false);

        recyclerView = view.findViewById(R.id.pending_recycleView);
        sharedPrefManager = new SharedPrefManager(getContext());
        token = sharedPrefManager.getTokenOtp().getHttpResponseHeader();
        Log.i("Token1", "********************Token1**************************" + token);
        userMobileNumber = sharedPrefManager.getUser().getCustomerMobileNo();

        //PaymentPending();
        return view;
    }


    private void PaymentPending() {
        Call<SelectInvoiceStatus_responce_modal> call = RetrofitClient
                .getInstance()
                .getApi()
                .SelectInvoiceStatus(token,userMobileNumber);

        call.enqueue(new Callback<SelectInvoiceStatus_responce_modal>() {
            @Override
            public void onResponse(@NotNull Call<SelectInvoiceStatus_responce_modal> call, Response<SelectInvoiceStatus_responce_modal> response) {

                if (response.isSuccessful()) {

                    SelectInvoiceStatus_responce_modal selectInvoiceStatus_responce_modal = response.body();
                    invoice_dates= selectInvoiceStatus_responce_modal.getInvoiceList();
                    if (selectInvoiceStatus_responce_modal.getSuccess()) {
                        pendingModalList =selectInvoiceStatus_responce_modal.getInvoiceList();


                        if (invoice_dates.isEmpty()){
                            Toast.makeText(getContext(), "Invoice is not generated", Toast.LENGTH_SHORT).show();
                        }
                        else {

                            pendingModalList = selectInvoiceStatus_responce_modal.getInvoiceList();
                            for (int i=0; i<pendingModalList.size(); i++){
                                Invoice_data invoiceData =(Invoice_data) pendingModalList.get(i);
                                String InvoiceNo = invoiceData.getInvoiceNo();
                                String InvoiceDateString = invoiceData.getInvoiceDateString();
                                int InvoiceTotalAmount = invoiceData.getInvoiceTotalAmount();
                                int InvoiceNetAmount = invoiceData.getInvoiceNetAmount();
                                String InvoiceStatus =invoiceData.getInvoiceStatus();
                                String InvoicePickupDate =invoiceData.getInvoicePickupDate();
                                String InvoiceStoreName = invoiceData.getInvoiceStoreName();
                                int InvoiceOrderId = invoiceData.getInvoiceOrderId();
                                int InvoiceCouponAmount = invoiceData.getInvoiceCouponAmount();
                                int InvoicePoints = invoiceData.getInvoicePoints();


                                if (InvoiceStatus.equals("Delivery Scheduled")){
                                    pendingModalList_temp.add(new Invoice_data(InvoiceNo,InvoiceDateString,InvoiceTotalAmount,InvoiceNetAmount,InvoiceStatus,InvoicePickupDate,InvoiceStoreName,InvoiceOrderId,InvoiceCouponAmount,InvoicePoints));
                                    adapter = new PendingAdapter(pendingModalList_temp,getContext());
                                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                                    recyclerView.setAdapter(adapter);
                                    adapter.notifyDataSetChanged();
                                }
                            }

                        }



                    } else {
                        Toast.makeText(getContext(), selectInvoiceStatus_responce_modal.getErrorMessage(), Toast.LENGTH_SHORT).show();

                    }

                } else {
                    Toast.makeText(getContext(), response.message(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<SelectInvoiceStatus_responce_modal> call, Throwable t) {

                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}