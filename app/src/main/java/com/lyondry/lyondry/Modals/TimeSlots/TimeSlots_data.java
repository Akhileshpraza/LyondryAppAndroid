package com.lyondry.lyondry.Modals.TimeSlots;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TimeSlots_data {
    @SerializedName("TimeSlotId")
    @Expose
    private Integer timeSlotId;
    @SerializedName("TimeSlotStoreId")
    @Expose
    private Integer timeSlotStoreId;
    @SerializedName("TimeSlotValue")
    @Expose
    private String timeSlotValue;

    public Integer getTimeSlotId() {
        return timeSlotId;
    }

    public void setTimeSlotId(Integer timeSlotId) {
        this.timeSlotId = timeSlotId;
    }

    public Integer getTimeSlotStoreId() {
        return timeSlotStoreId;
    }

    public void setTimeSlotStoreId(Integer timeSlotStoreId) {
        this.timeSlotStoreId = timeSlotStoreId;
    }

    public String getTimeSlotValue() {
        return timeSlotValue;
    }

    public void setTimeSlotValue(String timeSlotValue) {
        this.timeSlotValue = timeSlotValue;
    }
}
