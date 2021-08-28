package com.lyondry.lyondry.Modals.address;

public class MainDeleteAddressList {
    String CustomerMobileNo;
    private CustomerAddressList[] customerAddressList;

    public MainDeleteAddressList(String customerMobileNo, CustomerAddressList[] customerAddressList) {
        CustomerMobileNo = customerMobileNo;
        this.customerAddressList = customerAddressList;
    }

    public String getCustomerMobileNo() {
        return CustomerMobileNo;
    }

    public void setCustomerMobileNo(String customerMobileNo) {
        CustomerMobileNo = customerMobileNo;
    }

    public CustomerAddressList[] getCustomerAddressList() {
        return customerAddressList;
    }

    public void setCustomerAddressList(CustomerAddressList[] customerAddressList) {
        this.customerAddressList = customerAddressList;
    }
}


