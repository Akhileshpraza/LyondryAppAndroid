package com.lyondry.lyondry.Modals.address;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetAddress_data_Temp_model {


    private Integer addressId;
    private Integer addressCustomerId;
    private String addressBuildingNoHouseName;
    private String addressStreet;
    private String addressCity;
    private String addressPin;
    private String addressLandmark;
    private String addressLocationLaLo;
    private String addressType;
    private String addressDefault;
    private String addressActive;
    private Object sessionID;
    private String dateTime;
    private Object entryStatus;
    private String error;
    private String errorMessage;


    public GetAddress_data_Temp_model(String addressId, String customerAddress) {
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

    public String getAddressBuildingNoHouseName() {
        return addressBuildingNoHouseName;
    }

    public void setAddressBuildingNoHouseName(String addressBuildingNoHouseName) {
        this.addressBuildingNoHouseName = addressBuildingNoHouseName;
    }

    public String getAddressStreet() {
        return addressStreet;
    }

    public void setAddressStreet(String addressStreet) {
        this.addressStreet = addressStreet;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    public String getAddressPin() {
        return addressPin;
    }

    public void setAddressPin(String addressPin) {
        this.addressPin = addressPin;
    }

    public String getAddressLandmark() {
        return addressLandmark;
    }

    public void setAddressLandmark(String addressLandmark) {
        this.addressLandmark = addressLandmark;
    }

    public String getAddressLocationLaLo() {
        return addressLocationLaLo;
    }

    public void setAddressLocationLaLo(String addressLocationLaLo) {
        this.addressLocationLaLo = addressLocationLaLo;
    }

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    public String getAddressDefault() {
        return addressDefault;
    }

    public void setAddressDefault(String addressDefault) {
        this.addressDefault = addressDefault;
    }

    public String getAddressActive() {
        return addressActive;
    }

    public void setAddressActive(String addressActive) {
        this.addressActive = addressActive;
    }

    public Object getSessionID() {
        return sessionID;
    }

    public void setSessionID(Object sessionID) {
        this.sessionID = sessionID;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public Object getEntryStatus() {
        return entryStatus;
    }

    public void setEntryStatus(Object entryStatus) {
        this.entryStatus = entryStatus;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
