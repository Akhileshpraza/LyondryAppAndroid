package com.lyondry.lyondry.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lyondry.lyondry.Adapters.InviceAdapter;
import com.lyondry.lyondry.Classes.SharedPrefManager;
import com.lyondry.lyondry.Modals.InviceModal;
import com.lyondry.lyondry.Modals.InvoiceMaster.GridTran_data_invice;
import com.lyondry.lyondry.Modals.InvoiceMaster.InvoiceMaster_resonce_modal;
import com.lyondry.lyondry.Modals.InvoiceMaster.MainInviceMaster_reponce_modal;
import com.lyondry.lyondry.R;
import com.lyondry.lyondry.Retrofits.RetrofitClient;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class InviceActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    InviceAdapter inviceAdapter;
    List<InviceModal>inviceModalList;
    Context context;
    Button buttonPDF;
    // declaring width and height
    // for our PDF file.
    int pageHeight = 1120;
    int pagewidth = 850;

    // creating a bitmap variable
    // for storing our images
    Bitmap bmp, scaledbmp;

    // constant code for runtime permissions
    private static final int PERMISSION_REQUEST_CODE = 200;

    TextView CustomerName,GstInviceNo,GstInviceDate,Address,City,State,DiscountPercentage;
    TextView CgstPersentage,SgstPersantage,IgstPersantage,GstPesantage;
    TextView PackageQuantity,BalanceQuantity,PackageStartDate,PackageExpiryDate;
    TextView DiscountAmount,AfterDiscountAmount,CGST,SGST,IGST,GST,RoundOff,TotalAmountAfterTax;
    TextView EstimateDeliveyDate,GSTINUniqueID,ContactNo,EmailId,ServiceBy,TotalItems,TotalNetAmount;
    SharedPrefManager sharedPrefManager;
    String token, MobileNo;
    InvoiceMaster_resonce_modal invoiceMaster_resonce_modals;
    GridTran_data_invice gridTran_data_invice1;
    List<GridTran_data_invice> gridTran_data_invice = new ArrayList<>();
    String OrderId;
    String customerName,customerEmailid;
    int TotalQuantitys =0;
    LinearLayout mainLinearLayout;
    private Bitmap bitmap;
    String InvoiceCustomerCode,InvoiceOrderNo,InvoiceDueDate,InvoicePickupBy;
    String InvoiceNo,InvoiceCustomerAddress,InvoiceDateString,InvoiceCustomerMobileNo,InvoiceCustomerGstNo;
    int InvoiceCustomerId,InvoiceRequestId;
    Double InvoiceTotalAmount,InvoiceDiscountPercentage,InvoiceDiscountAmount,InvoiceTaxPercentage,InvoiceCgst,InvoiceSgst,InvoiceIgst,InvoiceTotalTax,InvoiceRoundOff,InvoiceNetAmount;
    String InvoiceTranDescription,hsnSac,uom;
    int InvoiceTranItemNos;
    Double InvoiceTranItemUnitPrice,InvoiceTranAmount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invice);
        CustomerName = findViewById(R.id.customerName);
        GstInviceNo = findViewById(R.id.gstInviceNo);
        GstInviceDate = findViewById(R.id.gstInviceDate);
        Address = findViewById(R.id.address);
        City = findViewById(R.id.city);
        State = findViewById(R.id.state);
        EstimateDeliveyDate = findViewById(R.id.estimateDeliveyDate);
        GSTINUniqueID = findViewById(R.id.GSTINUniqueID);
        ContactNo = findViewById(R.id.contactNo);
        EmailId = findViewById(R.id.emailId);
        ServiceBy = findViewById(R.id.serivceId);
        TotalItems = findViewById(R.id.total_items);
        TotalNetAmount = findViewById(R.id.total_amount);

        DiscountAmount = findViewById(R.id.discountAmount);
        DiscountPercentage =findViewById(R.id.discountPersentega);
        CgstPersentage = findViewById(R.id.cgstPersentage);
        SgstPersantage = findViewById(R.id.sgstPersantage);
        IgstPersantage = findViewById(R.id.igstPersantage);
        GstPesantage = findViewById(R.id.gstPesantage);
        AfterDiscountAmount = findViewById(R.id.afterDiscountAmount);
        CGST = findViewById(R.id.cgst);
        SGST = findViewById(R.id.sgst);
        IGST = findViewById(R.id.igst);
        GST = findViewById(R.id.gst);
        RoundOff = findViewById(R.id.roundOff);
        TotalAmountAfterTax = findViewById(R.id.totalAmountAfterTax);
        PackageQuantity = findViewById(R.id.packageQuantity);
        BalanceQuantity = findViewById(R.id.balanceQuantity);
        PackageStartDate = findViewById(R.id.packageStartDate);
        PackageExpiryDate = findViewById(R.id.packageExpiryDate);
        mainLinearLayout = findViewById(R.id.lyout_invoice);

        sharedPrefManager = new SharedPrefManager(getApplicationContext());
        token = sharedPrefManager.getTokenOtp().getHttpResponseHeader();
        MobileNo = sharedPrefManager.getUser().getCustomerMobileNo();
        customerName = sharedPrefManager.getUser().getCustomerName();
        customerEmailid = sharedPrefManager.getUser().getCustomerEmailId();
        OrderId = getIntent().getStringExtra("OrderId");
        Log.e("orderid",""+OrderId);

        recyclerView = findViewById(R.id.invice_item_recycleview);
        buttonPDF = findViewById(R.id.idBtnGeneratePDF);

        InviceMaster();
        // initializing our variables.

       // bmp = BitmapFactory.decodeResource(getResources(), R.drawable.logo_icon);
       // scaledbmp = Bitmap.createScaledBitmap(bmp, 140, 140, false);

        // below code is used for
        // checking our permissions.
        if (checkPermission()) {
            Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
        } else {
            requestPermission();
        }

        buttonPDF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calling method to
                // generate our PDF file.
                generatePDF();

            }
        });

    }

    private void generatePDF() {
        // creating an object variable
        // for our PDF document.
        PdfDocument pdfDocument = new PdfDocument();

        // two variables for paint "paint" is used
        // for drawing shapes and we will use "title"
        // for adding text in our PDF file.
        Paint paint = new Paint();
        Paint title = new Paint();

        // we are adding page info to our PDF file
        // in which we will be passing our pageWidth,
        // pageHeight and number of pages and after that
        // we are calling it to create our PDF.
        PdfDocument.PageInfo mypageInfo = new PdfDocument.PageInfo.Builder(598, 824, 2).create();

        // below line is used for setting
        // start page for our PDF file.
        PdfDocument.Page myPage = pdfDocument.startPage(mypageInfo);

        // creating a variable for canvas
        // from our page of PDF.
        Canvas canvas = myPage.getCanvas();

        // below line is used to draw our image on our PDF file.
        // the first parameter of our drawbitmap method is
        // our bitmap
        // second parameter is position from left
        // third parameter is position from top and last
        // one is our variable for paint.
        //canvas.drawBitmap(scaledbmp, 56, 40, paint);

        // below line is used for adding typeface for
        // our text which we will be adding in our PDF file.
        title.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));

        // below line is used for setting text size
        // which we will be displaying in our PDF file.
        title.setTextSize(15);

        // below line is sued for setting color
        // of our text inside our PDF file.
        title.setColor(ContextCompat.getColor(this, R.color.black));




        // below line is used to draw text in our PDF file.
        // the first parameter is our text, second parameter
        // is position from start, third parameter is position from top
        // and then we are passing our variable of paint which is title.
        canvas.drawText("TAX INVOICE", 230, 40, title);
        canvas.drawText("LYONDRY (A Unit of Bapuji Surgicals).",150 , 60, title);
        canvas.drawText("Hygiene Fabric & Shoe Care", 180, 80, title);
        canvas.drawText("#301, Lyon Square Ground Floor, 14th B Cross, 7th Main, 6th Sector,",80,100,title);
        canvas.drawText("HSR Layout, Bangalore - 560102, Karnataka, India.", 140, 120, title);
        canvas.drawText("PH No. : +91 6364248484 GSTIN No. : 29AIVPA2986J1ZJ PAN No. : AIVPA2986J", 30, 140, title);
        //create line
        Bitmap bitmap = Bitmap.createBitmap(4, 10, Bitmap.Config.ARGB_8888);
        Canvas canvass = new Canvas(bitmap);
        canvass.drawColor(Color.BLACK);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3);
        paint.setAntiAlias(true);
        int offset = 20;
        canvas.drawLine(offset, 150, canvas.getWidth() - offset, 150, paint);
        //setvalue to Api here
        canvas.drawText("Customer:", 30, 170, title);
        canvas.drawText(InvoiceCustomerCode, 150, 170, title);
        canvas.drawText("GST Invoice No:", 300, 170, title);
        canvas.drawText(InvoiceNo, 470, 170, title);

        canvas.drawText("Address:", 30, 190, title);
        canvas.drawText(InvoiceCustomerAddress, 150, 190, title);
        canvas.drawText("GST Invoice Date:", 300, 190, title);
        canvas.drawText(InvoiceDateString, 470, 190, title);

        canvas.drawText("City:", 30, 210, title);
        canvas.drawText("Bangalore", 150, 210, title);
        canvas.drawText("State:", 300, 210, title);
        canvas.drawText("Karnataka", 470, 210, title);

        canvas.drawText("GSTIN/Unique ID:", 30, 230, title);
        canvas.drawText(InvoiceCustomerGstNo, 150, 230, title);
        canvas.drawText("Estimated Delivery Date:", 300, 230, title);
        canvas.drawText(InvoiceDueDate, 470, 230, title);

        canvas.drawText("Contact No:", 30, 250, title);
        canvas.drawText(InvoiceCustomerMobileNo, 150, 250, title);
        canvas.drawText("Serviced by:", 300, 250, title);
        canvas.drawText(InvoicePickupBy, 470, 250, title);

        canvas.drawText("E-Mail ID:", 30, 270, title);
        canvas.drawText(customerEmailid, 150, 270, title);


        canvas.drawLine(offset, 280, canvas.getWidth() - offset, 280, paint);
        //canvas.drawLine(offset, 280, canvas.getWidth() - offset, 280, paint);
        canvas.drawText("SI. No", 30, 300, title);
        canvas.drawText("Description Of Service", 80, 300, title);
        canvas.drawText("HSN/SAC", 250, 300, title);
        canvas.drawText("UOM", 325, 300, title);
        canvas.drawText("Quantity", 370, 300, title);
        canvas.drawText("Price", 440, 300, title);
        canvas.drawText("Taxable Value", 480, 300, title);
        canvas.drawLine(offset, 310, canvas.getWidth() - offset, 310, paint);

        canvas.drawText("1", 30, 330, title);
        canvas.drawText("", 80, 330, title);
        canvas.drawText(hsnSac, 250, 330, title);
        canvas.drawText(uom, 325, 330, title);
        canvas.drawText(String.valueOf(InvoiceTranItemNos), 390, 330, title);
        canvas.drawText(String.valueOf(InvoiceTranItemUnitPrice), 440, 330, title);
        canvas.drawText(String.valueOf(InvoiceTranAmount), 520, 330, title);




        // similarly we are creating another text and in this
        // we are aligning this text to center of our PDF file.
        title.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        title.setColor(ContextCompat.getColor(this, R.color.black));
        title.setTextSize(15);

        // below line is used for setting
        // our text to center of PDF.
        title.setTextAlign(Paint.Align.CENTER);
        canvas.drawText("", 396, 560, title);

        // after adding all attributes to our
        // PDF file we will be finishing our page.
        pdfDocument.finishPage(myPage);

        // below line is used to set the name of
        // our PDF file and its path.
        File file = new File(Environment.getExternalStorageDirectory(), "TAX INVOICE.pdf");

        try {
            // after creating a file name we will
            // write our PDF file to that location.
            pdfDocument.writeTo(new FileOutputStream(file));

            // below line is to print toast message
            // on completion of PDF generation.
            Toast.makeText(InviceActivity.this, "PDF file generated succesfully.", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            // below line is used
            // to handle error
            e.printStackTrace();
        }
        // after storing our pdf to that
        // location we are closing our PDF file.
        pdfDocument.close();
    }

    private boolean checkPermission() {
        // checking of permissions.
        int permission1 = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);
        int permission2 = ContextCompat.checkSelfPermission(getApplicationContext(), READ_EXTERNAL_STORAGE);
        return permission1 == PackageManager.PERMISSION_GRANTED && permission2 == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {
        // requesting permissions if not provided.
        ActivityCompat.requestPermissions(this, new String[]{WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0) {

                // after requesting permissions we are showing
                // users a toast message of permission granted.
                boolean writeStorage = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                boolean readStorage = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                if (writeStorage && readStorage) {
                    Toast.makeText(this, "Permission Granted..", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Permission Denined.", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
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
                        InvoiceNo = invoiceMaster_resonce_modals.getInvoiceNo();
                        InvoiceDateString =invoiceMaster_resonce_modals.getInvoiceDateString();
                        InvoiceDueDate = invoiceMaster_resonce_modals.getInvoiceDueDate();
                        InvoiceCustomerId =invoiceMaster_resonce_modals.getInvoiceCustomerId();
                        InvoiceCustomerCode =invoiceMaster_resonce_modals.getInvoiceCustomerCode();
                        InvoiceCustomerAddress = invoiceMaster_resonce_modals.getInvoiceCustomerAddress();
                        InvoiceCustomerMobileNo = invoiceMaster_resonce_modals.getInvoiceCustomerMobileNo();
                        InvoiceCustomerGstNo =invoiceMaster_resonce_modals.getInvoiceCustomerGstNo();
                        InvoiceRequestId =invoiceMaster_resonce_modals.getInvoiceRequestId();
                        InvoiceOrderNo =invoiceMaster_resonce_modals.getInvoiceOrderNo();
                        InvoiceTotalAmount =invoiceMaster_resonce_modals.getInvoiceTotalAmount();
                        InvoiceDiscountPercentage =invoiceMaster_resonce_modals.getInvoiceDiscountPercentage();
                        InvoiceDiscountAmount =invoiceMaster_resonce_modals.getInvoiceDiscountAmount();
                        InvoiceTaxPercentage =invoiceMaster_resonce_modals.getInvoiceTaxPercentage();
                        InvoiceCgst =invoiceMaster_resonce_modals.getInvoiceCgst();
                        InvoiceSgst =invoiceMaster_resonce_modals.getInvoiceSgst();
                        InvoiceIgst =invoiceMaster_resonce_modals.getInvoiceIgst();
                        InvoiceTotalTax =invoiceMaster_resonce_modals.getInvoiceTotalTax();
                        InvoiceRoundOff =invoiceMaster_resonce_modals.getInvoiceRoundOff();
                        InvoiceNetAmount =invoiceMaster_resonce_modals.getInvoiceNetAmount();
                        InvoicePickupBy =invoiceMaster_resonce_modals.getInvoicePickupBy();

                        CustomerName.setText(InvoiceCustomerCode);
                        GstInviceNo.setText(InvoiceNo);
                        GstInviceDate.setText(InvoiceDateString);
                        Address.setText(InvoiceCustomerAddress);
                        EstimateDeliveyDate.setText(InvoiceDueDate);
                        GSTINUniqueID.setText(InvoiceCustomerGstNo);
                        ContactNo.setText(InvoiceCustomerMobileNo);
                        EmailId.setText(customerEmailid);
                        ServiceBy.setText(InvoicePickupBy);
                        TotalNetAmount.setText(String.valueOf(InvoiceTotalAmount));
                        DiscountAmount.setText(String.valueOf(InvoiceDiscountAmount));
                        DiscountPercentage.setText(String.valueOf(InvoiceDiscountPercentage+"%"));
                        Double AfterDisc = InvoiceTotalAmount-InvoiceDiscountAmount;
                        AfterDiscountAmount.setText(String.valueOf(AfterDisc));
                        CGST.setText(String.valueOf(InvoiceCgst));
                        SGST.setText(String.valueOf(InvoiceSgst));
                        IGST.setText(String.valueOf(InvoiceIgst));
                        GST.setText(String.valueOf(InvoiceTotalTax));
                        RoundOff.setText(String.valueOf(InvoiceRoundOff));
                        TotalAmountAfterTax.setText(String.valueOf(InvoiceNetAmount));
                        gridTran_data_invice =  invoiceMaster_resonce_modals.getGridTrans();

                        CgstPersentage.setText(String.valueOf(InvoiceTaxPercentage/2+"%"));
                        SgstPersantage.setText(String.valueOf(InvoiceTaxPercentage/2+"%"));
                        if (InvoiceIgst<0){
                            IgstPersantage.setText(String.valueOf(InvoiceTaxPercentage));
                        }
                        else {
                            IgstPersantage.setText("0%");
                        }

                        GstPesantage.setText(String.valueOf(InvoiceTaxPercentage+"%"));

                        PackageQuantity.setText("0");
                        BalanceQuantity.setText("0");
                        PackageStartDate.setText("null");
                        PackageExpiryDate.setText("null");

                        for (int i =0; i<gridTran_data_invice.size(); i++){
                            gridTran_data_invice1 = (GridTran_data_invice)gridTran_data_invice.get(i);
                            String InvoiceTranInvoiceNo = gridTran_data_invice1.getInvoiceTranInvoiceNo();
                            int InvoiceTranSlNo = gridTran_data_invice1.getInvoiceTranSlNo();
                            String InvoiceTranItemCode = gridTran_data_invice1.getInvoiceTranItemCode();
                            InvoiceTranItemUnitPrice = gridTran_data_invice1.getInvoiceTranItemUnitPrice();
                            int InvoiceTranItemUomId = gridTran_data_invice1.getInvoiceTranItemUomId();
                            InvoiceTranItemNos = gridTran_data_invice1.getInvoiceTranItemNos();
                            InvoiceTranAmount = gridTran_data_invice1.getInvoiceTranAmount();
                            Double InvoiceTranDiscPercentage = gridTran_data_invice1.getInvoiceTranDiscPercentage();
                            Double InvoiceTranDiscount = gridTran_data_invice1.getInvoiceTranDiscount() ;
                            Double InvoiceTranTaxPercentage = gridTran_data_invice1.getInvoiceTranTaxPercentage();
                            Double InvoiceTranCgst = gridTran_data_invice1.getInvoiceTranCgst();
                            Double InvoiceTranSgst = gridTran_data_invice1.getInvoiceTranSgst();
                            Double InvoiceTranIgst = gridTran_data_invice1.getInvoiceTranIgst();
                            Double InvoiceTranTotalTax = gridTran_data_invice1.getInvoiceTranTotalTax();
                            Double InvoiceTranNetAmount = gridTran_data_invice1.getInvoiceTranNetAmount();
                            InvoiceTranDescription = gridTran_data_invice1.getInvoiceTranDescription();
                            hsnSac = gridTran_data_invice1.getInvoiceTranHSNSAC();
                            //uom = gridTran_data_invice1.getInvoiceTranUOM();

                            TotalQuantitys = TotalQuantitys +InvoiceTranItemNos;
                            Log.e("TotalQuantitys",""+TotalQuantitys);
                            TotalItems.setText(String.valueOf(TotalQuantitys));

                            inviceAdapter = new InviceAdapter(gridTran_data_invice,context);
                            recyclerView.setHasFixedSize(true);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getApplication()));
                            recyclerView.setAdapter(inviceAdapter);
                            inviceAdapter.notifyDataSetChanged();

                        }
                        uom = gridTran_data_invice1.getInvoiceTranUOM();
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
}