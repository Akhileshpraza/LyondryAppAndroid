package com.lyondry.lyondry.Modals.address;

public class MainCustomerAddressList {
    String CustomerMobileNo;
    private CustomerAddressList[] customerAddressList;

    public MainCustomerAddressList(String customerMobileNo, CustomerAddressList[] customerAddressList) {
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

    public void setCustomerAddressListClass(CustomerAddressList[] customerAddressListClass) {
        this.customerAddressList = customerAddressListClass;
    }
}
