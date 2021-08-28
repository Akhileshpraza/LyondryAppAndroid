package com.lyondry.lyondry.Classes;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.lyondry.lyondry.Modals.Data_result_odel;
import com.lyondry.lyondry.Modals.GetAddress_data;
import com.lyondry.lyondry.Modals.Login_result_model;
import com.lyondry.lyondry.Modals.OtpResponceModal;
import com.lyondry.lyondry.Modals.Payment.Invoice_data;
import com.lyondry.lyondry.Modals.Services_data;
import com.lyondry.lyondry.Modals.User;
import com.lyondry.lyondry.Modals.UserProfile_data;
import com.lyondry.lyondry.Modals.address.Address;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class SharedPrefManager {

    private static String SHARED_PREF_NAME="lyondry";
    private SharedPreferences sharedPreferences;
    Context context;
    private SharedPreferences.Editor editor;

    public SharedPrefManager(Context context) {
        this.context = context;
    }

    public void saveUserSignup(Data_result_odel data_result_odel){
        sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        editor=sharedPreferences.edit();
        editor.putInt("CustomerId",data_result_odel.getCustomerId());
        editor.putString("CustomerName",data_result_odel.getCustomerName());
        editor.putString("CustomerMobileNo",data_result_odel.getCustomerMobileNo());
        editor.putString("CustomerEmailId",data_result_odel.getCustomerEmailId());
        editor.putInt("CustomerGroupId", data_result_odel.getCustomerGroupId());
        editor.putBoolean("logged",true);
        editor.apply();
    }

    public User getUser(){
        sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        return new User(sharedPreferences.getInt("CustomerId",-1),
                sharedPreferences.getString("CustomerEmailId",null),
                sharedPreferences.getString("CustomerMobileNo",null),
                sharedPreferences.getString("CustomerName",null),
                sharedPreferences.getString("EntryStatus",null));
    }


    public void saveUserLogin(Login_result_model login_result_model){
        sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        editor=sharedPreferences.edit();
        editor.putString("CustomerName",login_result_model.getCustomerName());
        editor.putString("CustomerMobileNo",login_result_model.getCustomerMobileNo());
        editor.putString("CustomerEmailId",login_result_model.getCustomerEmailId());
        editor.putInt("CustomerId", login_result_model.getCustomerId());
        editor.putBoolean("logged",true);
        editor.apply();
    }

    public Login_result_model getLoginUser(){
        sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        return new Login_result_model(sharedPreferences.getInt("CustomerId",-1),
                sharedPreferences.getString("CustomerName",null),
                sharedPreferences.getString("CustomerMobileNo",null),
                sharedPreferences.getString("CustomerEmailId",null));

    }


    public void saveTokenOtp(OtpResponceModal otpResponceModal){
        sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        editor=sharedPreferences.edit();
        editor.putString("HttpResponseHeader", otpResponceModal.getHttpResponseHeader());
        editor.apply();
    }

    public void removeData(Context context){
        SharedPreferences preferences =context.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.remove(SHARED_PREF_NAME);
        editor.apply();
    }

    public OtpResponceModal getTokenOtp(){
        sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        return new OtpResponceModal(sharedPreferences.getString("HttpResponseHeader",null));

    }

    public boolean isLoggedIn(){
        sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        return sharedPreferences.getBoolean("logged",false);

    }



    public void saveUserProfile(UserProfile_data userProfile_data){
        sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        editor=sharedPreferences.edit();
        editor.putString("CustomerEmailId",userProfile_data.getCustomerEmailId());
        editor.putString("CustomerName",userProfile_data.getCustomerName());
        editor.putInt("CustomerGender", userProfile_data.getCustomerGender());
        editor.putInt("CustomerDOB", Integer.parseInt(userProfile_data.getCustomerDOB()));
        editor.apply();
    }

    public UserProfile_data getUserProfile(){
        sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        return new UserProfile_data(sharedPreferences.getString("CustomerEmailId",null),
                sharedPreferences.getString("CustomerName",null),
                sharedPreferences.getString("CustomerGender",null),
                sharedPreferences.getString("CustomerDOB",null));

    }


    public void saveServicesName(Services_data services_data){
        sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        editor=sharedPreferences.edit();
        editor.putString("ServiceName",services_data.getServiceName());
        editor.apply();
    }

    public Services_data getServicesName(){
        sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        return new Services_data(sharedPreferences.getString("ServiceName",null));

    }

//    public void saveAddressId(GetAddress_data getAddress_data) {
//        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
//        editor = sharedPreferences.edit();
//        editor.putInt("addressId", getAddress_data.getAddressId());
//        editor.putInt("customerAddressId", getAddress_data.getAddressCustomerId());
//        editor.apply();
//    }
public void saveAddressId(Integer addressId,Integer customerAddressId) {
    sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
    editor = sharedPreferences.edit();
    editor.putInt("addressId", addressId);
    editor.putInt("customerAddressId",customerAddressId);
    editor.apply();
}

    public GetAddress_data getAddressData() {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        return new GetAddress_data(
                sharedPreferences.getInt("addressId", 0),
                sharedPreferences.getInt("customerAddressId",0));
    }

    public void logout(){
        sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        editor=sharedPreferences.edit();
        editor.clear();
        editor.apply();

    }

    public void saveaddress(List<GetAddress_data> getAddressData){
        sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        editor=sharedPreferences.edit();
        for (int i=0; i<getAddressData.size(); i++) {
            editor.putString("addressId", String.valueOf(getAddressData.get(i).getAddressId()));
            editor.putString("customerAddress", String.valueOf(getAddressData.get(i).getAddressCustomerId()));
            editor.apply();
        }
    }

    public GetAddress_data getaddress(){
        sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        return new GetAddress_data(sharedPreferences.getString("addressId",null),
                sharedPreferences.getString("customerAddress",null));
    }


    public void saveSelectInvoiceStatus(String InvoiceNo,String InvoiceDateString,
                                        String InvoiceTotalAmount,String InvoiceNetAmount,
                                        String InvoiceStatus,String InvoicePickupDate,
                                        String InvoiceStoreName,String InvoiceOrderId,
                                        String InvoiceCouponAmount,String InvoicePoints ){
        sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        editor=sharedPreferences.edit();
            editor.putString("invoiceNo",InvoiceNo);
            editor.putString("InvoiceDateString",InvoiceDateString);
            editor.putString("InvoiceTotalAmount", String.valueOf(InvoiceTotalAmount));
            editor.putString("InvoiceNetAmount", String.valueOf(InvoiceNetAmount));
            editor.putString("InvoiceStatus",InvoiceStatus);
            editor.putString("InvoicePickupDate",InvoicePickupDate);
            editor.putString("InvoiceStoreName",InvoiceStoreName);
            editor.putString("InvoiceOrderId", String.valueOf(InvoiceOrderId));
            editor.putString("InvoiceCouponAmount", String.valueOf(InvoiceCouponAmount));
            editor.putString("InvoicePoints", String.valueOf(InvoicePoints));
            editor.apply();

    }

    public  String getInvoiceStatus(String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key, null);
    }
}
