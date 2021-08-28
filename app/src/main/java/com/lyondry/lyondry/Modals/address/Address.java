package com.lyondry.lyondry.Modals.address;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Address {
     Integer addressId;
     Integer addressCustomerId;
     String addressBuildingNoHouseName;
     String addressStreet;
     String addressCity;
     String addressPin;
     String addressLandmark;
     String addressLocationLaLo;
     String addressType;
     String addressDefault;
     String addressActive;
     Object sessionID;
     String dateTime;
     Object entryStatus;
     String error;
     String errorMessage;


    public Address(Integer addressId, Integer addressCustomerId) {
        this.addressId = addressId;
        this.addressCustomerId = addressCustomerId;
    }


    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public Integer getAddressCustomerId() {
        return addressCustomerId;
    }

    public void setAddressCustomerId(Integer addressCustomerId) {
        this.addressCustomerId = addressCustomerId;
    }
}


