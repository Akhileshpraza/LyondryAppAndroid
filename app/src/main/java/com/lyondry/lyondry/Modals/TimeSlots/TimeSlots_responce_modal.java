package com.lyondry.lyondry.Modals.TimeSlots;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TimeSlots_responce_modal {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("TimeSlotList")
    @Expose
    private List<TimeSlots_data> timeSlotList = null;
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

    public List<TimeSlots_data> getTimeSlotList() {
        return timeSlotList;
    }

    public void setTimeSlotList(List<TimeSlots_data> timeSlotList) {
        this.timeSlotList = timeSlotList;
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
