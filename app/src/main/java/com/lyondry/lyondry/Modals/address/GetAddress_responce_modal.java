package com.lyondry.lyondry.Modals.address;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.lyondry.lyondry.Modals.GetAddress_data;

import java.util.List;

public class GetAddress_responce_modal {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("CustomerAddressList")
    @Expose
    private List<GetAddress_data> customerAddressList = null;
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

    public List<GetAddress_data> getCustomerAddressList() {
        return customerAddressList;
    }

    public void setCustomerAddressList(List<GetAddress_data> customerAddressList) {
        this.customerAddressList = customerAddressList;
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
