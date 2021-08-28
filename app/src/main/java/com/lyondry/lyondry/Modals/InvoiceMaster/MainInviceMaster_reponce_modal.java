package com.lyondry.lyondry.Modals.InvoiceMaster;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MainInviceMaster_reponce_modal {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("invoiceMaster")
    @Expose
    private InvoiceMaster_resonce_modal invoiceMaster;
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

    public InvoiceMaster_resonce_modal getInvoiceMaster() {
        return invoiceMaster;
    }

    public void setInvoiceMaster(InvoiceMaster_resonce_modal invoiceMaster) {
        this.invoiceMaster = invoiceMaster;
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
