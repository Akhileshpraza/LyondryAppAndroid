package com.lyondry.lyondry.Modals.InsertPickup;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.lyondry.lyondry.Modals.InsertAddress_data;

public class InsertPickup_responce_modal {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private InsertPickup_data data;
    @SerializedName("Error")
    @Expose
    private String error;
    @SerializedName("ErrorMessage")
    @Expose
    private String errorMessage;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public InsertPickup_data getData() {
        return data;
    }

    public void setData(InsertPickup_data data) {
        this.data = data;
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
