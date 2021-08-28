package com.lyondry.lyondry.Fragments;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.lyondry.lyondry.Activity.Feedback_RegisrationActivity;
import com.lyondry.lyondry.Adapters.LocalDataBaseAdapter;
import com.lyondry.lyondry.Hnadler.DataBaseHandler;
import com.lyondry.lyondry.Modals.LocalResponse;
import com.lyondry.lyondry.R;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class CameraFragment extends Fragment {

    public CameraFragment() {
        // Required empty public constructor
    }
    private static final int CAMERA_REQUEST = 1888;
    TextView Btn_add;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    //Bitmap photo;
    String photo;
    DataBaseHandler databaseHandler;
    SQLiteDatabase db;
    Bitmap clickImage;

    RecyclerView recyclerView;
    private DataBaseHandler myDatabase;
    private ArrayList singleRowArrayList ;
    private LocalResponse localResponse;
    String image;
    int uid;
    String OrderId;
    Cursor cursor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.camera_fragment,container,false);

        Btn_add = view.findViewById(R.id.btn_add_img);
        recyclerView = view.findViewById(R.id.recyclerview);
        myDatabase = new DataBaseHandler(getContext());
        db = myDatabase.getWritableDatabase();
        setData();

        databaseHandler = new DataBaseHandler(getContext());
         Btn_add.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {

                if (getActivity().checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
                {
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
                }
                else {
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);
                }
            }
        });
        return view;
    }

    private void setDataToDataBase() {
        db = databaseHandler.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DataBaseHandler.KEY_IMG_URL,photo);

        //itemid = Integer.parseInt(getArguments().getString("ID"));

       // System.out.println("***************imgid******************"+itemid);
//        SqliteDatabase databaseHelperClass = new SqliteDatabase(getContext());
//        localResponse = new LocalResponse(photo,itemid,uid);
//        databaseHelperClass.imgAdd(localResponse);

        long id = db.insert(DataBaseHandler.TABLE_NAME, null, cv);
        if (id < 0) {
            Toast.makeText(getContext(), "Something went wrong. Please try again later...", Toast.LENGTH_LONG).show();
        }

        else
            {
          ((Feedback_RegisrationActivity) getActivity()).loadFragment(new CameraFragment(), true);

          Toast.makeText(getContext(), "Add successful", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(getActivity(), "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
            else
            {
                Toast.makeText(getActivity(), "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK)
        {
            clickImage = (Bitmap) data.getExtras().get("data");
            photo=getEncodedString(clickImage);

            Log.i("Photo","**************Photo***************"+photo+",hello");
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
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        singleRowArrayList = new ArrayList<LocalResponse>();
        String[] columns = {DataBaseHandler.KEY_ID, DataBaseHandler.KEY_IMG_URL};
        cursor = db.query(DataBaseHandler.TABLE_NAME, columns, null, null, null, null, null);
        while (cursor.moveToNext()) {

            int index1 = cursor.getColumnIndex(DataBaseHandler.KEY_ID);
            int index2 = cursor.getColumnIndex(DataBaseHandler.KEY_IMG_URL);
            //int index3 = cursor.getColumnIndex(DataBaseHandler.KEY_Order_No);

            uid = cursor.getInt(index1);
            image = cursor.getString(index2);
           // OrderId = cursor.getString(index3);
            Log.i("uid",""+uid);
            //Log.i("image",""+image);
           // Log.i("itemid","****************"+itemid);


            localResponse = new LocalResponse(image,uid);
            singleRowArrayList.add(localResponse);
        }
        if (singleRowArrayList.size() == 0) {
            //empty.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            LocalDataBaseAdapter localDataBaseResponse = new LocalDataBaseAdapter(getContext(), singleRowArrayList, db, myDatabase);
            recyclerView.setAdapter(localDataBaseResponse);
        }
    }
}