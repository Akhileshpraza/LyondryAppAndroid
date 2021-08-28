package com.lyondry.lyondry.Modals;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetAddress_data {


//    public GetAddress_data(Integer addressId, Integer addressCustomerId, String addressBuildingNoHouseName, String addressStreet, String addressCity, String addressPin, String addressLandmark, String addressLocationLaLo, String addressType, String addressDefault, String addressActive, Object sessionID, String dateTime, Object entryStatus, String error, String errorMessage, int check_staus) {
//        this.addressId = addressId;
//        this.addressCustomerId = addressCustomerId;
//        this.addressBuildingNoHouseName = addressBuildingNoHouseName;
//        this.addressStreet = addressStreet;
//        this.addressCity = addressCity;
//        this.addressPin = addressPin;
//        this.addressLandmark = addressLandmark;
//        this.addressLocationLaLo = addressLocationLaLo;
//        this.addressType = addressType;
//        this.addressDefault = addressDefault;
//        this.addressActive = addressActive;
//        this.sessionID = sessionID;
//        this.dateTime = dateTime;
//        this.entryStatus = entryStatus;
//        this.error = error;
//        this.errorMessage = errorMessage;
//        this.check_staus = check_staus;
//    }


    @SerializedName("AddressId")
    @Expose
    private Integer addressId;
    @SerializedName("AddressCustomerId")
    @Expose
    private Integer addressCustomerId;
    @SerializedName("AddressBuildingNoHouseName")
    @Expose
    private String addressBuildingNoHouseName;
    @SerializedName("AddressStreet")
    @Expose
    private String addressStreet;
    @SerializedName("AddressCity")
    @Expose
    private String addressCity;
    @SerializedName("AddressPin")
    @Expose
    private String addressPin;
    @SerializedName("AddressLandmark")
    @Expose
    private String addressLandmark;
    @SerializedName("AddressLocationLaLo")
    @Expose
    private String addressLocationLaLo;
    @SerializedName("AddressType")
    @Expose
    private String addressType;
    @SerializedName("AddressDefault")
    @Expose
    private String addressDefault;
    @SerializedName("AddressActive")
    @Expose
    private String addressActive;
    @SerializedName("SessionID")
    @Expose
    private Object sessionID;
    @SerializedName("DateTime")
    @Expose
    private String dateTime;
    @SerializedName("EntryStatus")
    @Expose
    private Object entryStatus;
    @SerializedName("Error")
    @Expose
    private String error;
    @SerializedName("ErrorMessage")
    @Expose
    private String errorMessage;



    public GetAddress_data(Integer addressId, Integer addressCustomerId) {
        this.addressId = addressId;
        this.addressCustomerId = addressCustomerId;
    }

    public int getCheck_staus() {
        return check_staus;
    }

    public void setCheck_staus(int check_staus) {
        this.check_staus = check_staus;
    }

    int check_staus;
    public GetAddress_data(String addressId, String customerAddress) {
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
