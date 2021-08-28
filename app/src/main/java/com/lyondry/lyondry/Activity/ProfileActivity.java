package com.lyondry.lyondry.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.lyondry.lyondry.Adapters.AddAddressAdapter;
import com.lyondry.lyondry.Adapters.servicesAdapter;
import com.lyondry.lyondry.BottomNavigationFragment;
import com.lyondry.lyondry.Classes.SharedPrefManager;
import com.lyondry.lyondry.Classes.SharedPrefence_main;
import com.lyondry.lyondry.Modals.Company.CompanyMaster_data;
import com.lyondry.lyondry.Modals.Company.CompanyMaster_responce_modal;
import com.lyondry.lyondry.Modals.GetAddress_data;
import com.lyondry.lyondry.Modals.GetProfileImage_data;
import com.lyondry.lyondry.Modals.GetProfileImage_responce_Modal;
import com.lyondry.lyondry.Modals.GetUserProfile_responce;
import com.lyondry.lyondry.Modals.SelectItem.SelectItem_responce_modal;
import com.lyondry.lyondry.Modals.SelectItem.SelecteItem_data;
import com.lyondry.lyondry.Modals.SelectStore.SelectedStore_data;
import com.lyondry.lyondry.Modals.SelectStore.SelectedStore_responce_Modal;
import com.lyondry.lyondry.Modals.Services_data;
import com.lyondry.lyondry.Modals.Services_responce_modal;
import com.lyondry.lyondry.Modals.UserProfile_data;
import com.lyondry.lyondry.Modals.address.Address;
import com.lyondry.lyondry.Modals.address.GetAddress_responce_modal;
import com.lyondry.lyondry.R;
import com.lyondry.lyondry.Retrofits.RetrofitClient;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.text.TextUtils.indexOf;

public class ProfileActivity extends AppCompatActivity {
    ProgressDialog progressDialog;
    BottomNavigationFragment bottomNavigationFragment;
    ImageView backbtn;
    private TextView Invite;
    TextView homeAddress, officeAddress;
    ImageView Profile_Img, Qrcode;
    TextView Name, Mob_number, Edit, Fullname, EmailId, Gender, DOB, Default_address, Office_address;
    RecyclerView recyclerView;
    SharedPrefManager sharedPrefManager;
    String userMobileNumber, token, userEmailid, userName;
    String cname, cemail, cdob;
    int cgender;
    String picImage=null;
    String uemail=null;
    String date1;
    byte[] imageByteArray;
    UserProfile_data userProfile_data=null;
    SelectItem_responce_modal selectItem_responce_modal;
    List<SelecteItem_data> selecteItem_data;
    List<GetAddress_data> getAddress_data;
    List<SelectedStore_data> selectedStore_data;
    SharedPrefence_main sharedPrefence_main;
    SharedPreferences sp,sp1,sp2;
    int ugender=0;
    boolean gender=true;
    int addressId,customerAddressId;
    List<GetAddress_data>getAddressData;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Profile");
        backbtn = findViewById(R.id.back_arrow);
        backbtn.setVisibility(View.GONE);
        Invite = findViewById(R.id.invite);
        Profile_Img = findViewById(R.id.profilepic);
        Qrcode = findViewById(R.id.qrcode);
        Name = findViewById(R.id.tv_name);
        Mob_number = findViewById(R.id.tv_mobilenumber);
        Edit = findViewById(R.id.tv_profile_edit);
        Fullname = findViewById(R.id.tv_fullname);
        EmailId = findViewById(R.id.tv_emailid);
        Gender = findViewById(R.id.tv_gender);
        DOB = findViewById(R.id.tv_dob);
        recyclerView = findViewById(R.id.address_recycleView);
        
        sharedPrefManager = new SharedPrefManager(getApplicationContext());
        token = sharedPrefManager.getTokenOtp().getHttpResponseHeader();
        Log.i("Token11", "********************Token11**************************" + token);
        userMobileNumber = sharedPrefManager.getUser().getCustomerMobileNo();
        Log.i("userMobileNumber", "********************userMobileNumber**************************" + userMobileNumber);
        userEmailid = sharedPrefManager.getUser().getCustomerEmailId();
        userName = sharedPrefManager.getUser().getCustomerName();
        cname = sharedPrefManager.getUserProfile().getCustomerName();
        cemail = sharedPrefManager.getUserProfile().getCustomerEmailId();
        cdob = sharedPrefManager.getUserProfile().getCustomerDOB();
        cgender = sharedPrefManager.getUserProfile().getCustomerGender();

        Name.setText(userName);
        Fullname.setText(userName);
        EmailId.setText(userEmailid);
        Mob_number.setText(userMobileNumber);

        //************************************BottomNavigationBar***************************************
        Fragment bottom_fragment = getSupportFragmentManager().findFragmentById(R.id.bottom_navigation_id);
        if (bottom_fragment instanceof Fragment) {
            bottomNavigationFragment = (BottomNavigationFragment) bottom_fragment;
            bottomNavigationFragment.initailizeComponets();
        }
        //************************************BottomNavigationBar***************************************

        Invite();
        Editprofile();
        GetUserProfileImage();
        GetUserProfiles();
        AddressRecycleView();
        //GetAddress();
        // CompanyMaster();
         //SelectItem();
        // SelectedStore();
    }

    public void Invite() {
        Invite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, InviteFriendsActivity.class);
                startActivity(intent);
               // Toast.makeText(getApplicationContext(), "Invite", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void Editprofile() {
        Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, EditActivity.class);
//                intent.putExtra("picImage",imageByteArray);
                Profile_Img.setDrawingCacheEnabled(true);
                Bitmap b=Profile_Img.getDrawingCache();
                intent.putExtra("picImage",b);
                v.getContext().startActivity(intent);
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

    private void GetUserProfileImage() {

        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.show();
        }

        Call<GetProfileImage_responce_Modal> call = RetrofitClient
                .getInstance()
                .getApi()
                .GetProfileImage(token, userMobileNumber);

        call.enqueue(new Callback<GetProfileImage_responce_Modal>() {
            @Override
            public void onResponse(@NotNull Call<GetProfileImage_responce_Modal> call, Response<GetProfileImage_responce_Modal> response) {

                if (response.isSuccessful()) {
                    if (progressDialog != null && progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    GetProfileImage_responce_Modal getProfileImage_responce_modal = response.body();
                    GetProfileImage_data getProfileImage_data = getProfileImage_responce_modal.getData();

                    if (getProfileImage_responce_modal.getSuccess()) {

                        picImage = getProfileImage_data.getCustomerProfilePicStr();
                        Log.i("picImage","************************"+picImage);
//                        if (picImage !=null){

                        if (picImage ==null){

                            Toast.makeText(ProfileActivity.this, "No profile Image ", Toast.LENGTH_SHORT).show();

                        }else {
                            imageByteArray = Base64.decode(picImage, Base64.DEFAULT);
                            Log.i("imageByteArray","******imageByteArray12*********"+imageByteArray);
                            Glide.with(getApplicationContext()).load(imageByteArray).into(Profile_Img);
                            // Toast.makeText(ProfileActivity.this, "get Pic", Toast.LENGTH_SHORT).show();
                        }
//                        }
//                        else {
//                            Profile_Img.setImageResource(R.drawable.ic_baseline_account_circle_24);
//                        }

                    } else {
                        Toast.makeText(ProfileActivity.this, getProfileImage_responce_modal.getErrorMessage(), Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(ProfileActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetProfileImage_responce_Modal> call, Throwable t) {

                Toast.makeText(ProfileActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void GetUserProfiles() {
        Call<GetUserProfile_responce> call = RetrofitClient
                .getInstance()
                .getApi()
                .getUserProfile(token, userMobileNumber);

        call.enqueue(new Callback<GetUserProfile_responce>() {
            @Override
            public void onResponse(@NotNull Call<GetUserProfile_responce> call, Response<GetUserProfile_responce> response) {

                if (response.isSuccessful()) {

                    GetUserProfile_responce getUserProfile_responce = response.body();
                    userProfile_data = getUserProfile_responce.getData();

                    if (getUserProfile_responce.getSuccess()) {

                        if (userProfile_data == null) {
                            Toast.makeText(ProfileActivity.this, "User Profile not found", Toast.LENGTH_SHORT).show();
                        } else {
                            uemail = userProfile_data.getCustomerEmailId();
                            String uname = userProfile_data.getCustomerName();
                            ugender = userProfile_data.getCustomerGender();
                            Log.i("uname", "*******************" + uname);
                            String udob = userProfile_data.getCustomerDOB();
                            Fullname.setText(uname);
                            EmailId.setText(uemail);
                            if (ugender==0) {
                                Gender.setText("Male");
                            } else {
                                Gender.setText("Female");
                            }
                            String udob1 = udob.substring(0, 10);

                            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
                            SimpleDateFormat spf = new SimpleDateFormat("dd-MM-yyyy");

                            try {
                                Date date = inputFormat.parse(udob1);
                                 date1 = spf.format(date);



                                sp2 = getSharedPreferences("Date", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor1 =sp2.edit();
                                editor1.putString("date1", date1);
                                editor1.commit();

                                DOB.setText(date1);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                    else {
                       // Toast.makeText(ProfileActivity.this, getUserProfile_responce.getErrorMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ProfileActivity.this, response.message(), Toast.LENGTH_SHORT).show();


                }

            }

            @Override
            public void onFailure(Call<GetUserProfile_responce> call, Throwable t) {

                Toast.makeText(ProfileActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void GetAddress() {
        Call<GetAddress_responce_modal> call = RetrofitClient
                .getInstance()
                .getApi()
                .getAddress(token, userMobileNumber);

        call.enqueue(new Callback<GetAddress_responce_modal>() {
            @Override
            public void onResponse(@NotNull Call<GetAddress_responce_modal> call, Response<GetAddress_responce_modal> response) {

                if (response.isSuccessful()) {
                    GetAddress_responce_modal getAddress_responce_modal =response.body();
                   // Log.i("getAddres","********************getAddress_responce_modal"+getAddress_responce_modal);

                    if (getAddress_responce_modal.getSuccess()){
                         getAddress_data = getAddress_responce_modal.getCustomerAddressList();
                        for(int i=0;i<getAddress_data.size();i++){
                            GetAddress_data  getAddressData = (GetAddress_data)getAddress_data.get(i);
                            addressId = getAddressData.getAddressId();
                            customerAddressId = getAddressData.getAddressCustomerId();
                            String HouseName = getAddressData.getAddressBuildingNoHouseName();
                            String AddressStreet = getAddressData.getAddressStreet();
                            String AddressCity = getAddressData.getAddressCity();
                            String AddressPin = getAddressData.getAddressPin();
                            String AddressLandmark =getAddressData.getAddressLandmark();
                            String AddressType =getAddressData.getAddressType();
                            sharedPrefManager.saveaddress(getAddress_responce_modal.getCustomerAddressList());

                            sp = getSharedPreferences("MyAddress", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor =sp.edit();
                            editor.putInt("addressId", addressId);
                            editor.putInt("customerAddressId", customerAddressId);
                            editor.putString("HouseName",HouseName);
                            editor.putString("AddressStreet",AddressStreet);
                            editor.putString("AddressCity",AddressCity);
                            editor.putString("AddressPin",AddressPin);
                            editor.putString("AddressLandmark",AddressLandmark);
                            editor.putString("AddressType",AddressType);

                            editor.commit();

                            sp1 = getSharedPreferences("MyAddress1", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor1 =sp.edit();
                            editor1.putInt("customerAddressId", customerAddressId);
                            editor1.putInt("addressId",addressId);
                            editor1.commit();
                            Log.i("addressId","***********addressId***************"+addressId);
                            Log.i("customerAddress","***********customerAddress***************"+customerAddressId);
//                            Log.i("HouseName","***********HouseName***************"+HouseName);
//                            Log.i("AddressStreet","***********AddressStreet***************"+AddressStreet);
//                            Log.i("AddressCity","***********AddressCity***************"+AddressCity);
//                            Log.i("AddressPin","***********AddressPin***************"+AddressPin);
//                            Log.i("AddressLandmark","***********AddressLandmark***************"+AddressLandmark);
//                            Log.i("AddressType","***********AddressType***************"+AddressType);
//                            Log.i("addressId","***********addressId***************"+addressId);
//                            Log.i("customerAddressid","***********customerAddressid***************"+customerAddressId);

                            if (AddressType.equals("1")){
                                //Default_address.setText(HouseName+" "+AddressStreet+" "+" "+AddressLandmark+" "+" "+AddressCity+""+" "+AddressPin+"");
                            }
                           else  {
                                //Office_address.setText(HouseName+" "+AddressStreet+" "+" "+AddressLandmark+" "+" "+AddressCity+""+" "+AddressPin+"");
                            }

                        }
                    }
                    else {
                        Toast.makeText(ProfileActivity.this, getAddress_responce_modal.getErrorMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ProfileActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<GetAddress_responce_modal> call, Throwable t) {
                Toast.makeText(ProfileActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void CompanyMaster(){
        Long num = Long.valueOf(1);
        Call<CompanyMaster_responce_modal> call = RetrofitClient
                .getInstance()
                .getApi()
                .SelectCompanyMaster(num);

        call.enqueue(new Callback<CompanyMaster_responce_modal>() {
            @Override
            public void onResponse(@NotNull Call<CompanyMaster_responce_modal> call, Response<CompanyMaster_responce_modal> response) {

                if (response.isSuccessful()) {

                    CompanyMaster_responce_modal companyMaster_responce_modal = response.body();
                    CompanyMaster_data companyMaster_data = companyMaster_responce_modal.getData();

                    if (companyMaster_responce_modal.getSuccess()) {

                        String CompanyCode = companyMaster_data.getCompanyCode();
                        String CompanyName = companyMaster_data.getCompanyName()    ;
                        String CompanyAddress = companyMaster_data.getCompanyAddress();
                        String CompanyEmailId = companyMaster_data.getCompanyEmailId();
                        String CompanyContactPerson = companyMaster_data.getCompanyContactPerson();
                        String CompanyContactPersonNo = companyMaster_data.getCompanyContactPersonNo();
                        String CompanyGstNo = companyMaster_data.getCompanyGstNo();
                        String CompanyLogo = (String) companyMaster_data.getCompanyLogo();
                        String CompanyLocation = companyMaster_data.getCompanyLocation();
                        Log.i("CompanyMaster","*******************"+CompanyCode+CompanyName+CompanyAddress
                        +CompanyEmailId+CompanyContactPerson+CompanyContactPersonNo+CompanyGstNo);

                        Toast.makeText(ProfileActivity.this, "CompanyMaster", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(ProfileActivity.this, companyMaster_responce_modal.getErrorMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ProfileActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<CompanyMaster_responce_modal> call, Throwable t) {

                Toast.makeText(ProfileActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void AddressRecycleView() {

        Long num = Long.valueOf(0);
        Call<GetAddress_responce_modal> call = RetrofitClient
                .getInstance()
                .getApi()
                .getAddress(token, userMobileNumber);

        call.enqueue(new Callback<GetAddress_responce_modal>() {
            @Override
            public void onResponse(@NotNull Call<GetAddress_responce_modal> call, Response<GetAddress_responce_modal> response) {

                if (response.isSuccessful()) {

                    GetAddress_responce_modal getAddress_responce_modal = response.body();

                    if (getAddress_responce_modal.getSuccess()) {
                        getAddressData = getAddress_responce_modal.getCustomerAddressList();
                        AddAddressAdapter adapter = new AddAddressAdapter(getAddressData, context);
                        recyclerView.setLayoutManager(new LinearLayoutManager(context));
                        recyclerView.setAdapter(adapter);

                        for (int i=0; i<getAddressData.size(); i++){
                            GetAddress_data data = (GetAddress_data) getAddressData.get(i);
                            String House = data.getAddressBuildingNoHouseName();
                            int aid = data.getAddressId();
                            int cid = data.getAddressCustomerId();
                            Log.i("Andressidgg",""+aid);
                            addressId = data.getAddressId();
                            customerAddressId = data.getAddressCustomerId();
                            //sharedPrefManager.saveAddressId(data);

//                            sp1 = getSharedPreferences("MyAddress1", Context.MODE_PRIVATE);
//                            SharedPreferences.Editor editor1 =sp.edit();
//                            editor1.putInt("customerAddressId", customerAddressId);
//                            editor1.putInt("addressId",addressId);
//                            editor1.commit();
                        }

                    } else {
                        Toast.makeText(ProfileActivity.this, getAddress_responce_modal.getErrorMessage(), Toast.LENGTH_SHORT).show();

                    }

                } else {
                    Toast.makeText(ProfileActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<GetAddress_responce_modal> call, Throwable t) {

                Toast.makeText(ProfileActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

}