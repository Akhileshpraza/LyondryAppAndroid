package com.lyondry.lyondry.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.lyondry.lyondry.Adapters.FeedbackItemsAdapter;
import com.lyondry.lyondry.Adapters.InviceAdapter;
import com.lyondry.lyondry.Adapters.LocalDataBaseAdapter;
import com.lyondry.lyondry.Classes.SharedPrefManager;
import com.lyondry.lyondry.Fragments.CameraFragment;
import com.lyondry.lyondry.Hnadler.DataBaseHandler;
import com.lyondry.lyondry.Modals.Feedbacke.Feedback_data;
import com.lyondry.lyondry.Modals.Feedbacke.Feedbacke_responce_Modal;
import com.lyondry.lyondry.Modals.InvoiceMaster.GridTran_data_invice;
import com.lyondry.lyondry.Modals.InvoiceMaster.InvoiceMaster_resonce_modal;
import com.lyondry.lyondry.Modals.InvoiceMaster.MainInviceMaster_reponce_modal;
import com.lyondry.lyondry.Modals.LocalResponse;
import com.lyondry.lyondry.R;
import com.lyondry.lyondry.Retrofits.RetrofitClient;

import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.text.TextUtils.indexOf;

public class Feedback_RegisrationActivity extends AppCompatActivity implements FeedbackItemsAdapter.ItemsCheckInterface {
    TextView Cancel,InviceNo,Upload,CurrentDate;
    Button Next;
    Spinner spDate;
    EditText Discription;
    RecyclerView recyclerView;
    FeedbackItemsAdapter feedbackItemsAdapter;
    SharedPrefManager sharedPrefManager;
    String token, MobileNo;
    InvoiceMaster_resonce_modal invoiceMaster_resonce_modals;
    GridTran_data_invice gridTran_data_invice1;
    List<GridTran_data_invice> gridTran_data_invice = new ArrayList<>();
    Feedback_data feedback_data;
    String OrderId,InvoiceNo;
    String currentDateandTime;
    String ItemCode="";
    String corrnetDate,feedbackDescription;
    Context context;


    private static final int CAMERA_REQUEST = 1888;
    TextView Btn_add;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    //Bitmap photo;
    String photo;
    DataBaseHandler databaseHandler;
    SQLiteDatabase db;
    Bitmap clickImage;

    RecyclerView recyclerView1;
    private DataBaseHandler myDatabase;
    private ArrayList singleRowArrayList ;
    private LocalResponse localResponse;
    String image;
    int uid;
    int itemid;
    Cursor cursor;
    String ItemsCode;
    String ITEMCODE="";
    String ITEMCODES="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback__regisration);
        CurrentDate = findViewById(R.id.currentDate);
        InviceNo = findViewById(R.id.tv_invoicenumber);
        Discription = findViewById(R.id.tv_discription);
        Upload = findViewById(R.id.btn_uploade);
        Cancel = findViewById(R.id.btn_cancle_feedback);
        Next = findViewById(R.id.btn_next_feedback);
        recyclerView = findViewById(R.id.feedback_recycleview_items);

        sharedPrefManager = new SharedPrefManager(getApplicationContext());
        token = sharedPrefManager.getTokenOtp().getHttpResponseHeader();
        MobileNo = sharedPrefManager.getUser().getCustomerMobileNo();
        OrderId = getIntent().getStringExtra("orderId");
        InvoiceNo = getIntent().getStringExtra("InvoiceNo");
        InviceNo.setText(InvoiceNo);

        //Btn_add = findViewById(R.id.btn_add_img);
        //recyclerView1 = findViewById(R.id.recyclerview1);
        myDatabase = new DataBaseHandler(getApplicationContext());
        db = myDatabase.getWritableDatabase();
       // setData();

        databaseHandler = new DataBaseHandler(getApplicationContext());
        InviceMaster();
        ShowcurrentDate();
        loadFragment(new CameraFragment(), false);

        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InsertFeedback();
                Intent intent = new Intent(Feedback_RegisrationActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Feedback_RegisrationActivity.this,OrderActivity.class);
                startActivity(intent);
            }
        });

    }

    //********************************** Camera Image Picker*********Pick Image Clicked Items***********************

    public void loadFragment(Fragment fragment, Boolean bool) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayout, fragment);
        if (bool)
            transaction.addToBackStack(null);
        transaction.commit();
    }
    //********************************** Camera Image Picke*********Pick Image Clicked Items***********************


    private void ShowcurrentDate(){
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        currentDateandTime = inputFormat.format(new Date());
        corrnetDate = currentDateandTime;
        SimpleDateFormat spf= new SimpleDateFormat("dd-MM-yyyy");

        try {
            Date date = inputFormat.parse(corrnetDate);
            String date1 =spf.format(date);
            CurrentDate.setText(date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void InviceMaster() {

        Call<MainInviceMaster_reponce_modal> call = RetrofitClient
                .getInstance()
                .getApi()
                .InviceMaster(token, MobileNo, Integer.parseInt(OrderId));

        call.enqueue(new Callback<MainInviceMaster_reponce_modal>() {
            @Override
            public void onResponse(@NotNull Call<MainInviceMaster_reponce_modal> call, Response<MainInviceMaster_reponce_modal> response) {

                if (response.isSuccessful()) {
                    MainInviceMaster_reponce_modal  mainInviceMaster_reponce_modal = response.body();

                    if (mainInviceMaster_reponce_modal.getSuccess()) {
                        invoiceMaster_resonce_modals = mainInviceMaster_reponce_modal.getInvoiceMaster();

                        gridTran_data_invice =  invoiceMaster_resonce_modals.getGridTrans();

                        for (int i =0; i<gridTran_data_invice.size(); i++){
                            gridTran_data_invice1 = (GridTran_data_invice)gridTran_data_invice.get(i);
                            String InvoiceItemName = gridTran_data_invice1.getInvoiceTranDescription();

                            ItemCode =ItemCode + gridTran_data_invice1.getInvoiceTranItemCode() +",";
                            //ItemCode =ItemCode +","+ gridTran_data_invice1.getInvoiceTranItemCode();
                            Log.e("InvoiceItemName","***************"+InvoiceItemName);
                            Log.e("ItemCode","***************"+ItemCode);

                            feedbackItemsAdapter = new FeedbackItemsAdapter(gridTran_data_invice,Feedback_RegisrationActivity.this::onItemChecked, getApplication());
                            recyclerView.setHasFixedSize(true);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getApplication()));
                            recyclerView.setAdapter(feedbackItemsAdapter);
                            feedbackItemsAdapter.notifyDataSetChanged();

                        }

                    }
                    else {
                        Toast.makeText(getApplicationContext(), mainInviceMaster_reponce_modal.getErrorMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MainInviceMaster_reponce_modal> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setDataToDataBase() {
        db = databaseHandler.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DataBaseHandler.KEY_IMG_URL,photo);

        DataBaseHandler databaseHelperClass = new DataBaseHandler(Feedback_RegisrationActivity.this);
        LocalResponse localResponse = new LocalResponse(image,Integer.valueOf(OrderId));
        databaseHelperClass.imgAdd(localResponse);

        //itemid = Integer.parseInt(getArguments().getString("ID"));

        // System.out.println("***************imgid******************"+itemid);
//        SqliteDatabase databaseHelperClass = new SqliteDatabase(getContext());
//        localResponse = new LocalResponse(photo,itemid,uid);
//        databaseHelperClass.imgAdd(localResponse);

        long id = db.insert(DataBaseHandler.TABLE_NAME, null, cv);
        if (id < 0) {
            Toast.makeText(getApplicationContext(), "Something went wrong. Please try again later...", Toast.LENGTH_LONG).show();
        }

        else
        {
            ((Feedback_RegisrationActivity) getApplicationContext()).loadFragment(new CameraFragment(), true);

            Toast.makeText(getApplicationContext(), "Add successful", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(getApplicationContext(), "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
            else
            {
                Toast.makeText(getApplicationContext(), "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            clickImage = (Bitmap) data.getExtras().get("data");
            photo = getEncodedString(clickImage);

            Log.i("Photo", "**************Photo***************" + photo + ",hello");
            //itemid = Integer.parseInt(getArguments().getString("data"));
            // itemid = Integer.parseInt(data.getExtras().getString("data"));
            setDataToDataBase();

        }
    }

    private String getEncodedString(Bitmap bitmap){

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100, os);

      /* or use below if you want 32 bit images
       bitmap.compress(Bitmap.CompressFormat.PNG, (0–100 compression), os);*/

//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
//        byte[] imageInByte = baos.toByteArray();
        byte[] imageArr = os.toByteArray();
        return Base64.encodeToString(imageArr, Base64.URL_SAFE);
    }

    private void setData() {
        db = myDatabase.getWritableDatabase();
        recyclerView1.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));
        singleRowArrayList = new ArrayList<LocalResponse>();
        String[] columns = {DataBaseHandler.KEY_ID, DataBaseHandler.KEY_IMG_URL,DataBaseHandler.KEY_Order_No};
        cursor = db.query(DataBaseHandler.TABLE_NAME, columns, null, null, null, null, null);
        while (cursor.moveToNext()) {

            int index1 = cursor.getColumnIndex(DataBaseHandler.KEY_ID);
            int index2 = cursor.getColumnIndex(DataBaseHandler.KEY_IMG_URL);
            int index3 = cursor.getColumnIndex(DataBaseHandler.KEY_Order_No);

            uid = cursor.getInt(index1);
            image = cursor.getString(index2);
            //itemid = cursor.getInt(index3);
            Log.i("uid",""+uid);
            //Log.i("image",""+image);
            // Log.i("itemid","****************"+itemid);


            localResponse = new LocalResponse(image,uid);
            singleRowArrayList.add(localResponse);
        }
        if (singleRowArrayList.size() == 0) {
            //empty.setVisibility(View.VISIBLE);
            recyclerView1.setVisibility(View.GONE);
        } else {
            LocalDataBaseAdapter localDataBaseResponse = new LocalDataBaseAdapter(getApplicationContext(), singleRowArrayList, db, myDatabase);
            recyclerView1.setAdapter(localDataBaseResponse);
        }
    }

    private void InsertFeedback() {

        feedbackDescription = Discription.getText().toString().trim();
        Log.e("feedbackDescription",""+feedbackDescription);

        Call<Feedbacke_responce_Modal> call = RetrofitClient
                .getInstance()
                .getApi()
                .InsertFeedback(token, MobileNo,corrnetDate,InvoiceNo,ITEMCODES,feedbackDescription,image);

        call.enqueue(new Callback<Feedbacke_responce_Modal>() {
            @Override
            public void onResponse(@NotNull Call<Feedbacke_responce_Modal> call, Response<Feedbacke_responce_Modal> response) {

                if (response.isSuccessful()) {
                    Feedbacke_responce_Modal  feedbacke_responce_modal = response.body();

                    if (feedbacke_responce_modal.getSuccess()) {
                        feedback_data = feedbacke_responce_modal.getData();
                    }
                    else {
                        Toast.makeText(getApplicationContext(), ""+feedbacke_responce_modal.getErrorMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Feedbacke_responce_Modal> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onItemChecked(GridTran_data_invice gridTranDataInvice) {
        ITEMCODE =ITEMCODE + gridTranDataInvice.getInvoiceTranItemCode() +",";
        int startPosition=indexOf(ITEMCODE,",",0);
        ITEMCODES = ITEMCODE.substring(0,ITEMCODE.length()-1);
        Toast.makeText(this, ""+ITEMCODES, Toast.LENGTH_SHORT).show();
    }
}