package com.lyondry.lyondry.Modals.SelectStore;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SelectedStore_responce_Modal {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("StoreList")
    @Expose
    private List<SelectedStore_data> storeList = null;
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

    public List<SelectedStore_data> getStoreList() {
        return storeList;
    }

    public void setStoreList(List<SelectedStore_data> storeList) {
        this.storeList = storeList;
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
