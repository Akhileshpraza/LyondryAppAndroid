package com.lyondry.lyondry.Modals.SelectSchedule;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SelectSchedule_responce_modal {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("PickupScheduleList")
    @Expose
    private List<PickupSchedule> pickupScheduleList = null;
    @SerializedName("Error")
    @Expose
    private Boolean error;
    @SerializedName("ErrorMessage")
    @Expose
    private String errorMessage;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<PickupSchedule> getPickupScheduleList() {
        return pickupScheduleList;
    }

    public void setPickupScheduleList(List<PickupSchedule> pickupScheduleList) {
        this.pickupScheduleList = pickupScheduleList;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
