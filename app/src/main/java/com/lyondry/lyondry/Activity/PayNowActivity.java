package com.lyondry.lyondry.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lyondry.lyondry.BottomNavigationFragment;
import com.lyondry.lyondry.Classes.SharedPrefManager;
import com.lyondry.lyondry.R;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

public class PayNowActivity extends AppCompatActivity implements PaymentResultListener {
    private static final String TAG = PaymentActivity.class.getSimpleName();
    BottomNavigationFragment bottomNavigationFragment;
    ImageView backbtn;
    TextView Invite,Btninvice;
    Button Paynow;
    TextView InvoiceNo,PickupDate,Store,OrderId,TotalAmount,CouponDiscount,NetAmount,PointRedeem;
    EditText CouponCode;
    Button BtnApplyCoupon,BtnRedeemNow;
    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_now);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        backbtn = findViewById(R.id.back_arrow);
        Invite = findViewById(R.id.invite);
        Paynow = findViewById(R.id.btn_payment);
        Btninvice = findViewById(R.id.btn_invicepdf);
        InvoiceNo = findViewById(R.id.tv_invoiceno);
        PickupDate = findViewById(R.id.pickupdate);
        Store = findViewById(R.id.store);
        OrderId = findViewById(R.id.orderid);
        TotalAmount = findViewById(R.id.tv_total_amount);
        CouponDiscount = findViewById(R.id.coupondiscount);
        NetAmount = findViewById(R.id.netAmount);
        CouponCode = findViewById(R.id.ev_coupon_code);
        BtnApplyCoupon = findViewById(R.id.btn_ApplyCoupon);
        PointRedeem =findViewById(R.id.tv_point_redeem);
        BtnRedeemNow = findViewById(R.id.btn_redeem_now);

        sharedPrefManager = new SharedPrefManager(getApplicationContext());

        String Invoiceno = getIntent().getExtras().getString("InvoiceNo");
        String InvoiceDateString = getIntent().getExtras().getString("InvoiceDateString");
        String InvoiceTotalAmount = getIntent().getExtras().getString("InvoiceTotalAmount");
        String InvoiceNetAmount = getIntent().getExtras().getString("InvoiceNetAmount");
        String InvoicePickupDate = getIntent().getExtras().getString("InvoicePickupDate");
        String InvoiceStoreName = getIntent().getExtras().getString("InvoiceStoreName");
        String InvoiceOrderId = getIntent().getExtras().getString("InvoiceOrderId");
        String InvoiceCouponAmount = getIntent().getExtras().getString("InvoiceCouponAmount");
        String InvoicePoints = getIntent().getExtras().getString("InvoicePoints");

       // String InvoiceNos = sharedPrefManager.getInvoiceStatus("invoiceNo",getApplicationContext());
       // String InvoiceDateString = sharedPrefManager.getInvoiceStatus("InvoiceDateString",getApplicationContext());
        InvoiceNo.setText(Invoiceno);
        PickupDate.setText(InvoicePickupDate);
        Store.setText(InvoiceStoreName);
        OrderId.setText(InvoiceOrderId);
        TotalAmount.setText(InvoiceTotalAmount);
        CouponDiscount.setText(InvoiceCouponAmount);
        NetAmount.setText(InvoiceNetAmount);
        PointRedeem.setText(InvoicePoints);
        //PickupDate.setText(InvoiceDateString);



        Btninvice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PayNowActivity.this, InviceActivity.class);
                startActivity(intent);
            }
        });

        //************************************BottomNavigationBar***************************************
        Fragment bottom_fragment = getSupportFragmentManager().findFragmentById(R.id.bottom_navigation_id);
        if (bottom_fragment instanceof Fragment){
            bottomNavigationFragment = (BottomNavigationFragment)bottom_fragment;
            bottomNavigationFragment.initailizeComponets();
        }
        //************************************BottomNavigationBar***************************************

        invited();
        back_button();
        payments();
    }

    private void invited() {
        Invite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Invite", Toast.LENGTH_SHORT).show();
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

    public void back_button(){
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void payments(){
        Paynow .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPayment();
            }
        });
    }

    private void startPayment() {

        final Activity activity = this;

        final Checkout co = new Checkout();
            //co.setKeyID("vAPkFLVx0rT5ADLHj9u3Man3");
            co.setImage(R.drawable.logo_icon);

        try {
            JSONObject options = new JSONObject();
            options.put("name", "Lyondry");
            //options.put("description", "Demoing Charges");
            options.put("send_sms_hash",true);
           // options.put("allow_rotation", true);
            //You can omit the image option to fetch the image from dashboard
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            options.put("currency", "INR");
//            double total = Double.parseDouble();
//            total = total * 100;
            options.put("amount", "100");

            JSONObject preFill = new JSONObject();
            preFill.put("email", "prazapatiakhilesh2@gmail.com");
            preFill.put("contact", "8521727158");

            options.put("prefill", preFill);

            co.open(activity, options);
        } catch (Exception e) {
            Toast.makeText(activity, "Error in payment: " + e.getMessage(), Toast.LENGTH_SHORT)
                    .show();
            e.printStackTrace();
        }
    }



    @SuppressWarnings("unused")
    @Override
    public void onPaymentSuccess(String razorpayPaymentID) {
        try {

            Toast.makeText(this, "Payment Successful: " + razorpayPaymentID, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e(TAG, "Exception in onPaymentSuccess", e);
        }
    }

    @SuppressWarnings("unused")
    @Override
    public void onPaymentError(int code, String response) {
        try {
            Toast.makeText(this, "Payment failed: " + code + " " + response, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e(TAG, "Exception in onPaymentError", e);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}