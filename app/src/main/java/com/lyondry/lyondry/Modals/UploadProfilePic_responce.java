package com.lyondry.lyondry.Modals;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;



//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.net.Uri;
//import android.provider.MediaStore;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.util.Base64;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageView;
//
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//public class MainActivity extends AppCompatActivity {
//
//    Bitmap bitmap;
//    ImageView imageView;
//    Button selectImg,uploadImg;
//    EditText imgTitle;
//    private  static final int IMAGE = 100;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        imageView = (ImageView) findViewById(R.id.imageView);
//        selectImg = (Button) findViewById(R.id.selectImg);
//        uploadImg = (Button) findViewById(R.id.uploadImg);
//        imgTitle = (EditText) findViewById(R.id.imgTitle);
//
//
//        selectImg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                selectImage();
//
//            }
//        });
//
//        uploadImg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                uploadImage();
//
//            }
//        });
//
//    }
//
//
//    private void selectImage() {
//        Intent intent = new Intent();
//        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(intent, IMAGE);
//    }
//
//    private String convertToString()
//    {
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
//        byte[] imgByte = byteArrayOutputStream.toByteArray();
//        return Base64.encodeToString(imgByte,Base64.DEFAULT);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode== IMAGE && resultCode==RESULT_OK && data!=null)
//        {
//            Uri path = data.getData();
//
//            try {
//                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),path);
//                imageView.setImageBitmap(bitmap);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    private void uploadImage(){
//
//        String image = convertToString();
//        String imageName = imgTitle.getText().toString();
//        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
//        Call<Img_Pojo> call = apiInterface.uploadImage(imageName,image);
//
//        call.enqueue(new Callback<Img_Pojo>() {
//            @Override
//            public void onResponse(Call<Img_Pojo> call, Response<Img_Pojo> response) {
//
//                Img_Pojo img_pojo = response.body();
//                Log.d("Server Response",""+img_pojo.getResponse());
//
//            }
//
//            @Override
//            public void onFailure(Call<Img_Pojo> call, Throwable t) {
//                Log.d("Server Response",""+t.toString());
//
//            }
//        });
//
//    }
//
//}
//
//
//









public class UploadProfilePic_responce {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private UploadProfilePic_data_modal data;
    @SerializedName("Error")
    @Expose
    private String error;
    @SerializedName("ErrorMessage")
    @Expose
    private String errorMessage;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public UploadProfilePic_data_modal getData() {
        return data;
    }

    public void setData(UploadProfilePic_data_modal data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
