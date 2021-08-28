package com.lyondry.lyondry.Modals;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetProfileImage_data {
    @SerializedName("CustomerProfilePicStr")
    @Expose
    private String customerProfilePicStr;

    public String getCustomerProfilePicStr() {
        return customerProfilePicStr;
    }

    public void setCustomerProfilePicStr(String customerProfilePicStr) {
        this.customerProfilePicStr = customerProfilePicStr;
    }
}
