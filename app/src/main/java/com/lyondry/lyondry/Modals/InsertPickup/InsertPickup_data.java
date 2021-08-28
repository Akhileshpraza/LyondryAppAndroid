package com.lyondry.lyondry.Modals.InsertPickup;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InsertPickup_data {

    @SerializedName("PickupRequestId")
    @Expose
    private Integer pickupRequestId;
    @SerializedName("PickupRequestStoreName")
    @Expose
    private String pickupRequestStoreName;
    @SerializedName("PickupRequestServiceType")
    @Expose
    private String pickupRequestServiceType;
    @SerializedName("PickupRequestDate")
    @Expose
    private String pickupRequestDate;
    @SerializedName("PickupRequestTime")
    @Expose
    private String pickupRequestTime;
    @SerializedName("PickupRequestAddress")
    @Expose
    private String pickupRequestAddress;
    @SerializedName("PickupRequestStatus")
    @Expose
    private String pickupRequestStatus;

    public Integer getPickupRequestId() {
        return pickupRequestId;
    }

    public void setPickupRequestId(Integer pickupRequestId) {
        this.pickupRequestId = pickupRequestId;
    }

    public String getPickupRequestStoreName() {
        return pickupRequestStoreName;
    }

    public void setPickupRequestStoreName(String pickupRequestStoreName) {
        this.pickupRequestStoreName = pickupRequestStoreName;
    }

    public String getPickupRequestServiceType() {
        return pickupRequestServiceType;
    }

    public void setPickupRequestServiceType(String pickupRequestServiceType) {
        this.pickupRequestServiceType = pickupRequestServiceType;
    }

    public String getPickupRequestDate() {
        return pickupRequestDate;
    }

    public void setPickupRequestDate(String pickupRequestDate) {
        this.pickupRequestDate = pickupRequestDate;
    }

    public String getPickupRequestTime() {
        return pickupRequestTime;
    }

    public void setPickupRequestTime(String pickupRequestTime) {
        this.pickupRequestTime = pickupRequestTime;
    }

    public String getPickupRequestAddress() {
        return pickupRequestAddress;
    }

    public void setPickupRequestAddress(String pickupRequestAddress) {
        this.pickupRequestAddress = pickupRequestAddress;
    }

    public String getPickupRequestStatus() {
        return pickupRequestStatus;
    }

    public void setPickupRequestStatus(String pickupRequestStatus) {
        this.pickupRequestStatus = pickupRequestStatus;
    }
}
