package com.lyondry.lyondry.Modals.SelectSchedule;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SelectSchedule_data {

    @SerializedName("PickupScheduleList")
    @Expose
    private List<PickupSchedule> pickupScheduleList = null;

    public List<PickupSchedule> getPickupScheduleList() {
        return pickupScheduleList;
    }

    public void setPickupScheduleList(List<PickupSchedule> pickupScheduleList) {
        this.pickupScheduleList = pickupScheduleList;
    }
}
