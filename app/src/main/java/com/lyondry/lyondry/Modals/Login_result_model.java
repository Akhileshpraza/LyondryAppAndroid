package com.lyondry.lyondry.Modals;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Login_result_model {

    @SerializedName("CustomerId")
    @Expose
    private Integer customerId;
    @SerializedName("CustomerEmailId")
    @Expose
    private String customerEmailId;
    @SerializedName("CustomerMobileNo")
    @Expose
    private String customerMobileNo;
    @SerializedName("CustomerName")
    @Expose
    private String customerName;
    @SerializedName("EntryStatus")
    @Expose
    private String entryStatus;

    public Login_result_model(int customerId, String customerName, String customerMobileNo, String customerEmailId) {
    }


    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerEmailId() {
        return customerEmailId;
    }

    public void setCustomerEmailId(String customerEmailId) {
        this.customerEmailId = customerEmailId;
    }

    public String getCustomerMobileNo() {
        return customerMobileNo;
    }

    public void setCustomerMobileNo(String customerMobileNo) {
        this.customerMobileNo = customerMobileNo;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getEntryStatus() {
        return entryStatus;
    }

    public void setEntryStatus(String entryStatus) {
        this.entryStatus = entryStatus;
    }
}
