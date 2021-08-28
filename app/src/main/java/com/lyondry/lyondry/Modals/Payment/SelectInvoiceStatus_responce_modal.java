package com.lyondry.lyondry.Modals.Payment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SelectInvoiceStatus_responce_modal {
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("invoiceList")
    @Expose
    private List<Invoice_data> invoiceList = null;
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

    public List<Invoice_data> getInvoiceList() {
        return invoiceList;
    }

    public void setInvoiceList(List<Invoice_data> invoiceList) {
        this.invoiceList = invoiceList;
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
