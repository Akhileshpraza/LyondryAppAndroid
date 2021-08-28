package com.lyondry.lyondry.Modals;

public class User {

    int CustomerId ;
    String CustomerEmailId;
    String CustomerMobileNo;
    String CustomerName;
    String EntryStatus;

    public User(int customerId, String customerEmailId, String customerMobileNo, String customerName, String entryStatus) {
        CustomerId = customerId;
        CustomerEmailId = customerEmailId;
        CustomerMobileNo = customerMobileNo;
        CustomerName = customerName;
        EntryStatus = entryStatus;
    }

    public User(int customerId, String customerEmailId, String customerMobileNo, String customerName) {
    }

    public int getCustomerId() {
        return CustomerId;
    }


    public void setCustomerId(int customerId) {
        CustomerId = customerId;
    }

    public String getCustomerEmailId() {
        return CustomerEmailId;
    }

    public void setCustomerEmailId(String customerEmailId) {
        CustomerEmailId = customerEmailId;
    }

    public String getCustomerMobileNo() {
        return CustomerMobileNo;
    }

    public void setCustomerMobileNo(String customerMobileNo) {
        CustomerMobileNo = customerMobileNo;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getEntryStatus() {
        return EntryStatus;
    }

    public void setEntryStatus(String entryStatus) {
        EntryStatus = entryStatus;
    }
}
