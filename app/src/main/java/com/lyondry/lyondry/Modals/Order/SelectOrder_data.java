package com.lyondry.lyondry.Modals.Order;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SelectOrder_data {

    @SerializedName("PickupRequestId")
    @Expose
    private Integer pickupRequestId;
    @SerializedName("PickupRequestDate")
    @Expose
    private String pickupRequestDate;
    @SerializedName("PickupRequestStatus")
    @Expose
    private String pickupRequestStatus;


    public SelectOrder_data(int pickupRequestId, String pickupRequestDate,String pickupRequestStatus) {
        this.pickupRequestId = pickupRequestId;
        this.pickupRequestDate = pickupRequestDate;
        this.pickupRequestStatus = pickupRequestStatus;

    }

    public Integer getPickupRequestId() {
        return pickupRequestId;
    }

    public void setPickupRequestId(Integer pickupRequestId) {
        this.pickupRequestId = pickupRequestId;
    }

    public String getPickupRequestDate() {
        return pickupRequestDate;
    }

    public void setPickupRequestDate(String pickupRequestDate) {
        this.pickupRequestDate = pickupRequestDate;
    }

    public String getPickupRequestStatus() {
        return pickupRequestStatus;
    }

    public void setPickupRequestStatus(String pickupRequestStatus) {
        this.pickupRequestStatus = pickupRequestStatus;
    }

}
