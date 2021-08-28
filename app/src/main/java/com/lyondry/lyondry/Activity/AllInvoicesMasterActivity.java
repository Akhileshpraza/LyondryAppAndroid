package com.lyondry.lyondry.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.lyondry.lyondry.Classes.SharedPrefManager;
import com.lyondry.lyondry.Modals.InvoiceMaster.GridTran_data_invice;
import com.lyondry.lyondry.Modals.InvoiceMaster.InvoiceMaster_resonce_modal;
import com.lyondry.lyondry.Modals.InvoiceMaster.MainInviceMaster_reponce_modal;
import com.lyondry.lyondry.R;
import com.lyondry.lyondry.Retrofits.RetrofitClient;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllInvoicesMasterActivity extends AppCompatActivity {

    SharedPrefManager sharedPrefManager;
    String token, MobileNo;
    InvoiceMaster_resonce_modal invoiceMaster_resonce_modals;
    GridTran_data_invice gridTran_data_invice1;
    List<GridTran_data_invice> gridTran_data_invice = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_invoices_master);


    }



}