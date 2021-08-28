package com.lyondry.lyondry.Modals.address;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EditAddress_date {
    @SerializedName("Status")
    @Expose
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
