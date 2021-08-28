package com.lyondry.lyondry.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lyondry.lyondry.Classes.SharedPrefManager;
import com.lyondry.lyondry.Modals.SelectSchedule.PickupSchedule;
import com.lyondry.lyondry.Modals.SelectSchedule.SelectSchedule_responce_modal;
import com.lyondry.lyondry.R;
import com.lyondry.lyondry.Retrofits.RetrofitClient;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderDetailsActivity extends AppCompatActivity {
    ImageView backbtn;
    Button Btn_feedback,BtnInvoice;
    TextView orderId,orderDate,pickupByName,totalAmount,paymentStatus,orderStore,orderStatus;
    ImageView pickupByMobile;
    String PickupByName,TotalAmount,PaymentStatus;
    ImageView paymentChecked;
    TextView InvoiceNumber;


    //*******************recend order stsus********************
    TextView Pending ,Accepted, Pickup,PickupCompleted,Laundry, Delivery, Delivered;
    ImageView Check1,Check2,Check3,Check4,Check5,Check6,Check7;
    ImageView Proges1,Proges2,Proges3,Proges4,Proges5,Proges6;
    //*******************recend order stsus********************

    SharedPrefManager sharedPrefManager;
    String userMobileNumber, userName,token;
    List<PickupSchedule> pickupSchedule;
    int customerid;
    String StoreName;
    AlertDialog.Builder builder;
    String phoneNumber=null,date,OrderId;
    String Status=null;
    String pickupbyMobile;
    String InvoiceNo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
//        getSupportActionBar().setTitle("Order");
//        getSupportActionBar().setIcon(R.drawable.arrow_left);

        backbtn = findViewById(R.id.back_arrow);
        Btn_feedback = findViewById(R.id.btn_feedback);
        orderId = findViewById(R.id.tv_order_id);
        orderDate = findViewById(R.id.tv_order_date);
        pickupByName = findViewById(R.id.tv_pickupby);
        totalAmount = findViewById(R.id.tv_order_total_amount);
        paymentStatus = findViewById(R.id.tv_order_payment_status);
        orderStore = findViewById(R.id.tv_order_store);
        orderStatus =findViewById(R.id.tv_status);
        pickupByMobile = findViewById(R.id.tv_call);
        BtnInvoice = findViewById(R.id.btn_invoice);
        paymentChecked = findViewById(R.id.tv_order_payment_IconChecked);
        InvoiceNumber = findViewById(R.id.invoiceNo);


        sharedPrefManager = new SharedPrefManager(getApplicationContext());
        userMobileNumber = sharedPrefManager.getUser().getCustomerMobileNo();
        token = sharedPrefManager.getTokenOtp().getHttpResponseHeader();
        customerid = sharedPrefManager.getUser().getCustomerId();
        Log.i("orderToken", "********************orderToken**************************" + token);
        Log.i("orderuserMobileNumber", "********************orderuserMobileNumber**************************" + userMobileNumber);
        Log.i("Cid", "********************Cid**************************" + customerid);

         date = getIntent().getStringExtra("Date");
         OrderId = getIntent().getStringExtra("OrderID");
         Status = getIntent().getStringExtra("Status");
        Log.i("date","*******date**********"+date);
        Log.i("OrderId","*******OrderId**********"+OrderId);
        Log.i("Status","*******Status124**********"+Status);
        orderDate.setText(date);
        orderStatus.setText(Status);

        status();
        PickupSchedule();
        Invoice();
        BtnFeedbacke();


        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    private void BtnFeedbacke(){

        if (Status.equals("Delivered")){
            Btn_feedback.setEnabled(true);
        Btn_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderDetailsActivity.this,Feedback_RegisrationActivity.class);
                intent.putExtra("orderId",OrderId);
                intent.putExtra("InvoiceNo",InvoiceNo);

                startActivity(intent);
            }
        });
        }
        else {
            Btn_feedback.setEnabled(false);
        }
    }

    private void Invoice(){

        if (Status.equals("Delivered")){
            BtnInvoice.setEnabled(true);
            BtnInvoice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(),InviceActivity.class);
                    intent.putExtra("OrderId",OrderId);
                    startActivity(intent);
                }
            });
        }
        else {
            BtnInvoice.setEnabled(false);
        }

    }

    private void status(){
        Pending = findViewById(R.id.pending);
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

        if (Status.equals(null)){
            Pending.setTextColor(Color.parseColor("#31396B"));
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
        else if (Status.equals("Schedule")){
           // Toast.makeText(getApplicationContext(),"pending",Toast.LENGTH_SHORT).show();
            Pending.setText(Status);

            Pending.setTextColor(Color.parseColor("#0ECFD1"));
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

        else if (Status.equals("Accepted")){
            //Toast.makeText(getApplicationContext(),"Accepted",Toast.LENGTH_SHORT).show();
            Accepted.setText(Status);

            Pending.setTextColor(Color.parseColor("#0ECFD1"));
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

        else if (Status.equals("Pickup")){
            //Toast.makeText(getApplicationContext(),"Pickup",Toast.LENGTH_SHORT).show();
            Pickup.setText(Status);

            Pending.setTextColor(Color.parseColor("#0ECFD1"));
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

        else if (Status.equals("Pickup Completed")){
            //Toast.makeText(getApplicationContext(),"Pickup",Toast.LENGTH_SHORT).show();
            PickupCompleted.setText(Status);

            Pending.setTextColor(Color.parseColor("#0ECFD1"));
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
            Proges3.setColorFilter(Color.parseColor("#0ECFD1"));
            Proges4.setColorFilter(Color.parseColor("#31396B"));
            Proges5.setColorFilter(Color.parseColor("#31396B"));
            Proges6.setColorFilter(Color.parseColor("#31396B"));
        }

        else if (Status.equals("Laundry")){
           // Toast.makeText(getApplicationContext(),"Laundry",Toast.LENGTH_SHORT).show();
            Laundry.setText(Status);

            Pending.setTextColor(Color.parseColor("#0ECFD1"));
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

        else if (Status.equals("Delivery Scheduled")){
            //Toast.makeText(getApplicationContext(),"Delivery Scheduled",Toast.LENGTH_SHORT).show();
            Delivery.setText(Status);

            Pending.setTextColor(Color.parseColor("#0ECFD1"));
            Accepted.setTextColor(Color.parseColor("#0ECFD1"));
            Pickup.setTextColor(Color.parseColor("#0ECFD1"));
            PickupCompleted.setTextColor(Color.parseColor("#0ECFD1"));
            Laundry.setTextColor(Color.parseColor("#0ECFD1"));
            Delivery.setTextColor(Color.parseColor("#0ECFD1"));
            Delivered.setTextColor(Color.parseColor("#31396B"));

            Check1.setImageResource(R.drawable.green_checked);
            Check2.setImageResource(R.drawable.green_checked);
            Check3.setImageResource(R.drawable.green_checked);
            Check4.setImageResource(R.drawable.green_checked);
            Check7.setImageResource(R.drawable.green_checked);
            Check5.setImageResource(R.drawable.green_checked);
            Check6.setImageResource(R.drawable.gray_clock);

            Proges1.setColorFilter(Color.parseColor("#0ECFD1"));
            Proges2.setColorFilter(Color.parseColor("#0ECFD1"));
            Proges3.setColorFilter(Color.parseColor("#0ECFD1"));
            Proges6.setColorFilter(Color.parseColor("#0ECFD1"));
            Proges4.setColorFilter(Color.parseColor("#0ECFD1"));
            Proges5.setColorFilter(Color.parseColor("#31396B"));
        }

        else if (Status.equals("Delivered")){
           // Toast.makeText(getApplicationContext(),"Delivered",Toast.LENGTH_SHORT).show();
            Delivered.setText(Status);

            Pending.setTextColor(Color.parseColor("#0ECFD1"));
            Accepted.setTextColor(Color.parseColor("#0ECFD1"));
            Pickup.setTextColor(Color.parseColor("#0ECFD1"));
            PickupCompleted.setTextColor(Color.parseColor("#0ECFD1"));
            Laundry.setTextColor(Color.parseColor("#0ECFD1"));
            Delivery.setTextColor(Color.parseColor("#0ECFD1"));
            Delivered.setTextColor(Color.parseColor("#0ECFD1"));

            Check1.setImageResource(R.drawable.green_checked);
            Check2.setImageResource(R.drawable.green_checked);
            Check3.setImageResource(R.drawable.green_checked);
            Check4.setImageResource(R.drawable.green_checked);
            Check7.setImageResource(R.drawable.green_checked);
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
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


    private void PickupSchedule() {

        Call<SelectSchedule_responce_modal> call = RetrofitClient
                .getInstance()
                .getApi()
                .SelectSchedule(token,userMobileNumber, Integer.parseInt(OrderId));

        call.enqueue(new Callback<SelectSchedule_responce_modal>() {
            @Override
            public void onResponse(@NotNull Call<SelectSchedule_responce_modal> call, Response<SelectSchedule_responce_modal> response) {

                    if (response.isSuccessful()) {

                        SelectSchedule_responce_modal selectSchedule_responce_modal = response.body();
                         String error = selectSchedule_responce_modal.getErrorMessage();

//                         if (error.equals("SchedulePending")){
////                             orderDate.setText(date);
////                             orderId.setText(OrderId);
////                             orderStatus.setText(Status);
//                             //pickupByName.setText(PickupByName);
//                             //orderStore.setText(StoreName);
//
//                             Toast.makeText(getApplicationContext(),"SchedulePending",Toast.LENGTH_SHORT).show();
//                         }
//                         else {


                             if (selectSchedule_responce_modal.getSuccess()) {
                                 pickupSchedule = selectSchedule_responce_modal.getPickupScheduleList();
                                 Log.i("pickupSchedule", "*******************" + pickupSchedule);

                                 for (int i = 0; i < pickupSchedule.size(); i++) {
                                     PickupSchedule pickupdata = (PickupSchedule) pickupSchedule.get(i);

                                     int PickupScheduleId = pickupdata.getPickupScheduleId();
                                     int PickupSchedulePickupRequestId = pickupdata.getPickupSchedulePickupRequestId();
                                     int PickupScheduleDeliveryBoyId = pickupdata.getPickupScheduleDeliveryBoyId();
                                     int PickupScheduleStatusId = pickupdata.getPickupScheduleStatusId();
                                     InvoiceNo = pickupdata.getInvoiceNo();
                                     String PickupStatusName = pickupdata.getPickupStatusName();
                                     PickupByName = pickupdata.getPickupByName();
                                     phoneNumber = pickupdata.getPickupByMobile();
                                     TotalAmount = String.valueOf(pickupdata.getTotalAmount());
                                     PaymentStatus = pickupdata.getPaymentStatus();
                                     Log.e("PaymentStatus",""+paymentStatus);
                                     StoreName = pickupdata.getStoreName();

                                     pickupByName.setText(PickupByName);
                                     totalAmount.setText(TotalAmount);
                                     paymentStatus.setText(PaymentStatus);
                                     InvoiceNumber.setText(InvoiceNo);

                                     orderStore.setText(StoreName);
                                     orderId.setText(OrderId);
                                     if (Double.valueOf(TotalAmount)<=0){
                                         paymentChecked.setImageResource(R.drawable.issueicon);
                                     }
                                     else {
                                         paymentChecked.setImageResource(R.drawable.green_checked);
                                     }

                                     pickupByMobile.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null));
                                             startActivity(intent);
                                         }
                                     });

                                 }

                             } else {
                                 Toast.makeText(OrderDetailsActivity.this, ""+selectSchedule_responce_modal.getErrorMessage(), Toast.LENGTH_SHORT).show();
                             }
                         //}
                    } else {
                        Toast.makeText(OrderDetailsActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                    }

            }

            @Override
            public void onFailure(Call<SelectSchedule_responce_modal> call, Throwable t) {

                Toast.makeText(OrderDetailsActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}