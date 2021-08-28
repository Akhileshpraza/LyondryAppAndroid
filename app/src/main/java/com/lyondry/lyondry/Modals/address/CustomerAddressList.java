package com.lyondry.lyondry.Modals.address;

import android.util.Log;

public class CustomerAddressList {
    int AddressId;
    Long AddressCustomerId;
    String AddressBuildingNoHouseName;
    String AddressStreet;
    String AddressCity;
    String AddressPin;
    String AddressLandmark;
    String AddressLocationLaLo;
    String AddressType;
    String AddressDefault;
    String AddressActive;
    String EntryStatus;

    public CustomerAddressList(Long addressCustomerId, String addressBuildingNoHouseName, String addressStreet, String addressCity, String addressPin, String addressLandmark, String addressLocationLaLo, String addressType, String addressDefault, String addressActive, String entryStatus) {
        AddressCustomerId = addressCustomerId;
        AddressBuildingNoHouseName = addressBuildingNoHouseName;
        AddressStreet = addressStreet;
        AddressCity = addressCity;
        AddressPin = addressPin;
        AddressLandmark = addressLandmark;
        AddressLocationLaLo = addressLocationLaLo;
        AddressType = addressType;
        AddressDefault = addressDefault;
        AddressActive = addressActive;
        EntryStatus = entryStatus;
    }


    public CustomerAddressList(int addressId,Long addressCustomerId, String addressBuildingNoHouseName, String addressStreet, String addressCity, String addressPin, String addressLandmark, String addressLocationLaLo, String addressType, String addressDefault, String addressActive, String entryStatus) {
        AddressId = addressId;
        AddressCustomerId = addressCustomerId;
        AddressBuildingNoHouseName = addressBuildingNoHouseName;
        AddressStreet = addressStreet;
        AddressCity = addressCity;
        AddressPin = addressPin;
        AddressLandmark = addressLandmark;
        AddressLocationLaLo = addressLocationLaLo;
        AddressType = addressType;
        AddressDefault = addressDefault;
        AddressActive = addressActive;
        EntryStatus = entryStatus;

    }



    public CustomerAddressList(int addressId,Long addressCustomerId) {
        AddressId = addressId;
        AddressCustomerId = addressCustomerId;

    }

    public int getAddressId() {
        return AddressId;
    }

    public void setAddressId(int addressId) {
        AddressId = addressId;
    }

    public Long getAddressCustomerId() {
        return AddressCustomerId;
    }

    public void setAddressCustomerId(Long addressCustomerId) {
        AddressCustomerId = addressCustomerId;
    }

    public String getAddressBuildingNoHouseName() {
        return AddressBuildingNoHouseName;
    }

    public void setAddressBuildingNoHouseName(String addressBuildingNoHouseName) {
        AddressBuildingNoHouseName = addressBuildingNoHouseName;
    }

    public String getAddressStreet() {
        return AddressStreet;
    }

    public void setAddressStreet(String addressStreet) {
        AddressStreet = addressStreet;
    }

    public String getAddressCity() {
        return AddressCity;
    }

    public void setAddressCity(String addressCity) {
        AddressCity = addressCity;
    }

    public String getAddressPin() {
        return AddressPin;
    }

    public void setAddressPin(String addressPin) {
        AddressPin = addressPin;
    }

    public String getAddressLandmark() {
        return AddressLandmark;
    }

    public void setAddressLandmark(String addressLandmark) {
        AddressLandmark = addressLandmark;
    }

    public String getAddressLocationLaLo() {
        return AddressLocationLaLo;
    }

    public void setAddressLocationLaLo(String addressLocationLaLo) {
        AddressLocationLaLo = addressLocationLaLo;
    }

    public String getAddressType() {
        return AddressType;
    }

    public void setAddressType(String addressType) {
        AddressType = addressType;
    }

    public String getAddressDefault() {
        return AddressDefault;
    }

    public void setAddressDefault(String addressDefault) {
        AddressDefault = addressDefault;
    }

    public String getAddressActive() {
        return AddressActive;
    }

    public void setAddressActive(String addressActive) {
        AddressActive = addressActive;
    }

    public String getEntryStatus() {
        return EntryStatus;
    }

    public void setEntryStatus(String entryStatus) {
        EntryStatus = entryStatus;
    }
}
