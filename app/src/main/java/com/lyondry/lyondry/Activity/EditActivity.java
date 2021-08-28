package com.lyondry.lyondry.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.lyondry.lyondry.Classes.SharedPrefManager;
import com.lyondry.lyondry.Classes.SharedPrefence_main;
import com.lyondry.lyondry.Modals.UploadProfilePic_responce;
import com.lyondry.lyondry.Modals.UserUpdate_data;
import com.lyondry.lyondry.Modals.UserUpdate_responce_Modal;
import com.lyondry.lyondry.R;
import com.lyondry.lyondry.Retrofits.RetrofitClient;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;

import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditActivity extends AppCompatActivity {
    ProgressDialog progressDialog;
    ImageView userpic;
    private static final int GalleryPick = 1;
    private static final int CAMERA_REQUEST = 100;
    private static final int STORAGE_REQUEST = 200;
    private static final int IMAGEPICK_GALLERY_REQUEST = 300;
    private static final int IMAGE_PICKCAMERA_REQUEST = 400;
    String cameraPermission[];
    String storagePermission[];
    private int IMG_REQUEST = 21;
    Uri imageuri;
    Bitmap bitmap =null;
    String encodeImgae;

    TextView Btn_selectImage;
    Button Next;
    EditText Fullname, Emailid;
    Spinner gender;
    String fname, mobile, email;
    String dateofbirths;
    TextView Btn_uploade, Btn_cancel, dob, Mobilenumber, Btn_select, address;

    ImageView SetImg;
    ImageView date_cal;
    AwesomeValidation awesomeValidation;
    DatePickerDialog picker;
    private int mYear, mMonth, mDay;
    SharedPrefManager sharedPrefManager;
    String token, userMobileNumber, userEmailid, userName;
    SpinnerAdapter spinnerAdapter;
    String[] userGender = {"Male", "Female"};
    String select_gender;
    UserUpdate_responce_Modal userUpdate_responce_modal;
    UserUpdate_data userUpdate_data;
    String DOBDate, uGender;
    SharedPreferences sp2;
    String Dob;
    String passDOB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Next = findViewById(R.id.btn_next);
        Fullname = findViewById(R.id.et_fullname);
        Mobilenumber = findViewById(R.id.et_mobile);
        Emailid = findViewById(R.id.et_emailid);
        dob = findViewById(R.id.tv_dob1);
        date_cal = findViewById(R.id.iv_calendar);
        gender = findViewById(R.id.sp_gender);
        address = findViewById(R.id.btn_addnew_address);
        Btn_uploade = findViewById(R.id.btn_uploade);
        SetImg = findViewById(R.id.setImage);
        Btn_cancel = findViewById(R.id.btn_cancle);
        Btn_selectImage = findViewById(R.id.btn_chouse_image);
        userpic = findViewById(R.id.set_profile_image);

        sharedPrefManager = new SharedPrefManager(getApplicationContext());
        token = sharedPrefManager.getTokenOtp().getHttpResponseHeader();
        userMobileNumber = sharedPrefManager.getUser().getCustomerMobileNo();
        userEmailid = sharedPrefManager.getUser().getCustomerEmailId();
        userName = sharedPrefManager.getUser().getCustomerName();
        Fullname.setText(userName);
        Mobilenumber.setText(userMobileNumber);
        Emailid.setText(userEmailid);
        Bitmap profilePick=getIntent().getParcelableExtra("picImage");
        Log.e("profilePick",""+profilePick);

        userpic.setImageBitmap(profilePick);
        sp2 = getSharedPreferences("Date", Context.MODE_PRIVATE);
        Dob =sp2.getString("date1","");
        Log.e("Dob","****************Dob*******************"+Dob);

        //dob.setText(Dob);
        SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd ");

        try {
            Date date = inputFormat.parse(Dob);
            passDOB = spf.format(date);
            Log.e("passDate", "************passDate**************" + passDOB);
            dob.setText(Dob);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        SelectImages();
        User_Update();
        genders();
        Address();
        date_calender();
        btncancle();
        btnupload();

        // allowing permissions of gallery and camera
        cameraPermission = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        storagePermission = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
    }

    private void SelectImages() {
        Btn_selectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showImagePicDialog();
            }
        });
    }

    private void genders() {
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, userGender);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender.setAdapter(dataAdapter);
        gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Toast.makeText(EditActivity.this, "" + gender.getSelectedItemPosition(), Toast.LENGTH_SHORT).show();
                select_gender = String.valueOf(gender.getSelectedItemPosition());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void btncancle() {
        Btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // GetUserProfiles();
                Intent intent = new Intent(EditActivity.this, ProfileActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();

            }
        });
    }

    private void btnupload() {
        Btn_uploade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bitmap ==null) {
                    Toast.makeText(getApplicationContext(), "Please Select Image", Toast.LENGTH_SHORT).show();
                } else {

                    uploadImage();
                }
            }
        });
    }

    private void Address() {
        address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditActivity.this, PickupAddress1Activity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });
    }

    private void date_calender() {
        date_cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(EditActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                // dob.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                                // dob.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                                String corrnetDate = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                                SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
                                SimpleDateFormat spf = new SimpleDateFormat("dd-MM-yyyy");

                                try {
                                    Date date = inputFormat.parse(corrnetDate);
                                    String date1 = spf.format(date);
                                    Date date3 = inputFormat.parse(corrnetDate);
                                    dateofbirths = inputFormat.format(date3);
                                    Log.e("dateofbirths",""+dateofbirths);
                                    passDOB =dateofbirths;
                                    dob.setText(date1);
                                    Dob=date1;
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }

                            }
                        }, year, month, day);
                picker.show();
            }
        });
    }

    private void User_Update() {

        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fname = Fullname.getText().toString().trim();
                mobile = Mobilenumber.getText().toString().trim();
                email = Emailid.getText().toString().trim();
                //dateofbirths = dob.getText().toString().trim();
                Dob = dob.getText().toString().trim();
                Log.e("Fname",""+fname);
                Log.e("mobile",""+mobile);
                Log.e("email",""+email);
                Log.e("dateofbirths",""+dateofbirths);
                Log.e("select_gender",""+select_gender);
                Log.e("DobDob",""+Dob);

                if (fname.isEmpty()) {
                    Fullname.setError("Full Name can't be blank");
                    Fullname.findFocus();
                    return;
                } else if (mobile.isEmpty()) {
                    Mobilenumber.setError("Mobile number can't be blank");
                    Mobilenumber.requestFocus();
                    return;
                } else if (mobile.length() <= 9) {
                    Mobilenumber.setError("Enter valid mobile number");
                    Mobilenumber.requestFocus();
                    return;
                } else if (!Patterns.PHONE.matcher(mobile).matches()) {
                    Mobilenumber.setError("Enter valid mobile number");
                    Mobilenumber.requestFocus();
                    return;
                } else if (email.isEmpty()) {
                    Emailid.setError("Email id can't be blank ");
                    Emailid.requestFocus();
                    return;
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Emailid.setError("Enter a valid email id");
                    Emailid.requestFocus();
                    return;
                }
                else if (Dob.isEmpty()) {
                    dob.setError("Enter valid DOB");
                    dob.requestFocus();
                    return;
                }
                else if (Dob==null || Dob.equals("dd-mm-yyyy")){
                    dob.setError("Selecte DOB");
                    dob.requestFocus();
                    return;
                }
                //Validation();

                Call<UserUpdate_responce_Modal> call = RetrofitClient
                        .getInstance()
                        .getApi()
                        .userUpdate(token, email, fname, select_gender, passDOB, mobile);

                call.enqueue(new Callback<UserUpdate_responce_Modal>() {
                    @Override
                    public void onResponse(@NotNull Call<UserUpdate_responce_Modal> call, Response<UserUpdate_responce_Modal> response) {

                        if (response.isSuccessful()) {

                            userUpdate_responce_modal = response.body();
                            assert userUpdate_responce_modal != null;
                            userUpdate_data = userUpdate_responce_modal.getData();
                            boolean succ = userUpdate_responce_modal.getSuccess();

                            Log.i("succ", "*************succ************" + succ);

                            if (userUpdate_responce_modal.getSuccess()) {
                                Intent intent = new Intent(EditActivity.this, ProfileActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                                finish();
                                dob.setText("");
                                Toast.makeText(EditActivity.this, "Update Successfully", Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(EditActivity.this, userUpdate_responce_modal.getErrorMessage().toString(), Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(EditActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<UserUpdate_responce_Modal> call, Throwable t) {

                        Toast.makeText(EditActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });

    }

    private void Validation() {
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(this, R.id.et_fullname, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.nameerror);
        awesomeValidation.addValidation(this, R.id.et_emailid, Patterns.EMAIL_ADDRESS, R.string.emaileerror);
        awesomeValidation.addValidation(this, R.id.et_mobile, "^[2-9]{2}[0-9]{8}$", R.string.mobileerror);
//        if (awesomeValidation.validate()) {
//            // Toast.makeText(this, " Successfull", Toast.LENGTH_LONG).show();
//            Intent intent = new Intent(EditActivity.this, PickupAddress1Activity.class);
//            startActivity(intent);
//        } else {
//            Toast.makeText(this, "Faild", Toast.LENGTH_LONG).show();
//        }
    }

    // ***********************upload image and set the Image ulr in ImageView**************

    private void uploadImage() {


        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, byteArrayOutputStream);
        byte[] imgInByte = byteArrayOutputStream.toByteArray();
        encodeImgae = Base64.encodeToString(imgInByte, Base64.DEFAULT);
        Log.i("encodeImgae", "***********encodeImgae******************" + encodeImgae);

            Call<UploadProfilePic_responce> call = RetrofitClient
                    .getInstance()
                    .getApi()
                    .uploadProfilePic(token, userMobileNumber, encodeImgae);

            call.enqueue(new Callback<UploadProfilePic_responce>() {
                @Override
                public void onResponse(@NotNull Call<UploadProfilePic_responce> call, Response<UploadProfilePic_responce> response) {

                    try {

                        if (response.isSuccessful()) {

                            UploadProfilePic_responce uploadProfilePic_responce = response.body();

                            Log.d("Server Response", "****************" + uploadProfilePic_responce.getSuccess());

                            if (uploadProfilePic_responce.getSuccess()) {

                                Toast.makeText(EditActivity.this, "Profile Pick Upload Successfully", Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(EditActivity.this, uploadProfilePic_responce.getErrorMessage().toString(), Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(EditActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                        }

                    } catch (Exception e) {
                        Toast.makeText(EditActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();

                    }
                }

                @Override
                public void onFailure(Call<UploadProfilePic_responce> call, Throwable t) {

                    if (t instanceof IOException) {
                        Toast.makeText(EditActivity.this, "This is an actual network failure :( inform the user and possibly retry)", Toast.LENGTH_SHORT).show();
                        // logging probably not necessary
                    } else {
                        Log.e("conversion issue", t.getMessage());
                        Toast.makeText(EditActivity.this, "Please Check your Internet Connection....", Toast.LENGTH_SHORT).show();
                    }

                    //  Toast.makeText(EditActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

    }

    //*********************************************ImageCropand profile pick tack**********************************
    private void showImagePicDialog() {
        String options[] = {"Camera", "Gallery"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Pick Image From Camera & Gallery");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    if (!checkCameraPermission()) {
                        requestCameraPermission();
                    } else {
                        pickFromGallery();
                    }
                } else if (which == 1) {
                    if (!checkStoragePermission()) {
                        requestStoragePermission();
                    } else {
                        pickFromGallery();
                    }
                }
            }
        });
        builder.create().show();
    }

    // checking storage permissions
    private Boolean checkStoragePermission() {
        boolean result = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);
        return result;
    }

    // Requesting  gallery permission
    private void requestStoragePermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(storagePermission, STORAGE_REQUEST);
        }
    }

    // checking camera permissions
    private Boolean checkCameraPermission() {
        boolean result = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == (PackageManager.PERMISSION_GRANTED);
        boolean result1 = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);
        return result && result1;
    }

    // Requesting camera permission
    private void requestCameraPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(cameraPermission, CAMERA_REQUEST);
        }
    }

    // Requesting camera and gallery
    // permission if not given
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case CAMERA_REQUEST: {
                if (grantResults.length > 0) {
                    boolean camera_accepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean writeStorageaccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    if (camera_accepted && writeStorageaccepted) {
                        pickFromGallery();
                    } else {
                        Toast.makeText(this, "Please Enable Camera and Storage Permissions", Toast.LENGTH_LONG).show();
                    }
                }
            }
            break;
            case STORAGE_REQUEST: {
                if (grantResults.length > 0) {
                    boolean writeStorageaccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if (writeStorageaccepted) {
                        pickFromGallery();
                    } else {
                        Toast.makeText(this, "Please Enable Storage Permissions", Toast.LENGTH_LONG).show();
                    }
                }
            }
            break;
        }
    }

    // Here we will pick image from gallery or camera
    private void pickFromGallery() {
        CropImage.activity().start(EditActivity.this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), resultUri);

                    userpic.setImageBitmap(bitmap);
                    // Picasso.with(this).load(resultUri).into(userpic);
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        }
    }

    private byte[] getBytesFromBitmap(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
        return stream.toByteArray();
    }

    //*********************************************ImageCropand profile pick tack**********************************
}

