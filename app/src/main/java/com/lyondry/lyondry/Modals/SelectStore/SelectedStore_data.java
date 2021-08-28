package com.lyondry.lyondry.Modals.SelectStore;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SelectedStore_data {

    @SerializedName("StoreId")
    @Expose
    private Integer storeId;
    @SerializedName("StoreCompanyId")
    @Expose
    private Integer storeCompanyId;
    @SerializedName("StoreCode")
    @Expose
    private String storeCode;
    @SerializedName("StoreName")
    @Expose
    private String storeName;
    @SerializedName("StoreAddress")
    @Expose
    private String storeAddress;
    @SerializedName("StoreEmailId")
    @Expose
    private String storeEmailId;
    @SerializedName("StorePhone")
    @Expose
    private String storePhone;
    @SerializedName("StoreLocation")
    @Expose
    private String storeLocation;

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getStoreCompanyId() {
        return storeCompanyId;
    }

    public void setStoreCompanyId(Integer storeCompanyId) {
        this.storeCompanyId = storeCompanyId;
    }

    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public String getStoreEmailId() {
        return storeEmailId;
    }

    public void setStoreEmailId(String storeEmailId) {
        this.storeEmailId = storeEmailId;
    }

    public String getStorePhone() {
        return storePhone;
    }

    public void setStorePhone(String storePhone) {
        this.storePhone = storePhone;
    }

    public String getStoreLocation() {
        return storeLocation;
    }

    public void setStoreLocation(String storeLocation) {
        this.storeLocation = storeLocation;
    }

}
