package com.lyondry.lyondry.Modals.SelectItem;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SelectItem_responce_modal {
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("ItemList")
    @Expose
    private List<SelecteItem_data> itemList ;
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

    public List<SelecteItem_data> getItemList() {
        return itemList;
    }

    public void setItemList(List<SelecteItem_data> itemList) {
        this.itemList = itemList;
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
