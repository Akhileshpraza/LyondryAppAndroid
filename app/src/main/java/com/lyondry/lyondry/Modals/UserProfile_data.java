package com.lyondry.lyondry.Modals;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserProfile_data {
    @SerializedName("CustomerEmailId")
    @Expose
    private String customerEmailId;
    @SerializedName("CustomerName")
    @Expose
    private String customerName;
    @SerializedName("CustomerGender")
    @Expose
    private int customerGender;
    @SerializedName("CustomerDOB")
    @Expose
    private String customerDOB;


    public UserProfile_data(String customerEmailId, String customerName, String customerGender, String customerDOB) {

    }

    public String getCustomerEmailId() {
        return customerEmailId;
    }

    public void setCustomerEmailId(String customerEmailId) {
        this.customerEmailId = customerEmailId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getCustomerGender() {
        return customerGender;
    }

    public void setCustomerGender(int customerGender) {
        this.customerGender = customerGender;
    }

    public String getCustomerDOB() {
        return customerDOB;
    }

    public void setCustomerDOB(String customerDOB) {
        this.customerDOB = customerDOB;
    }
}
