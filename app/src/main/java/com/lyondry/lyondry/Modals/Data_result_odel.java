package com.lyondry.lyondry.Modals;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data_result_odel {
    @SerializedName("CustomerId")
    @Expose
    private Integer customerId;
    @SerializedName("CustomerName")
    @Expose
    private String customerName;
    @SerializedName("CustomerMobileNo")
    @Expose
    private String customerMobileNo;
    @SerializedName("CustomerEmailId")
    @Expose
    private String customerEmailId;
    @SerializedName("CustomerGroupId")
    @Expose
    private Integer customerGroupId;
    @SerializedName("EntryStatus")
    @Expose
    private String entryStatus;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerMobileNo() {
        return customerMobileNo;
    }

    public void setCustomerMobileNo(String customerMobileNo) {
        this.customerMobileNo = customerMobileNo;
    }

    public String getCustomerEmailId() {
        return customerEmailId;
    }

    public void setCustomerEmailId(String customerEmailId) {
        this.customerEmailId = customerEmailId;
    }

    public Integer getCustomerGroupId() {
        return customerGroupId;
    }

    public void setCustomerGroupId(Integer customerGroupId) {
        this.customerGroupId = customerGroupId;
    }

    public String getEntryStatus() {
        return entryStatus;
    }

    public void setEntryStatus(String entryStatus) {
        this.entryStatus = entryStatus;
    }

}
