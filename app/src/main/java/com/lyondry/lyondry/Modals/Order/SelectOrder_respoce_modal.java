package com.lyondry.lyondry.Modals.Order;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SelectOrder_respoce_modal {
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("PickupRequestList")
    @Expose
    private List<SelectOrder_data> pickupRequestList = null;
    @SerializedName("Error")
    @Expose
    private Object error;
    @SerializedName("ErrorMessage")
    @Expose
    private Object errorMessage;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<SelectOrder_data> getPickupRequestList() {
        return pickupRequestList;
    }

    public void setPickupRequestList(List<SelectOrder_data> pickupRequestList) {
        this.pickupRequestList = pickupRequestList;
    }

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }

    public Object getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(Object errorMessage) {
        this.errorMessage = errorMessage;
    }
}
