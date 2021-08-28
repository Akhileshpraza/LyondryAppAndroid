package com.lyondry.lyondry.Modals.address;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddressDelete_responce_modal {
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private AddressDelete_data data;
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

    public AddressDelete_data getData() {
        return data;
    }

    public void setData(AddressDelete_data data) {
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
