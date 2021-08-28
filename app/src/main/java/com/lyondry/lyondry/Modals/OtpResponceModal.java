package com.lyondry.lyondry.Modals;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OtpResponceModal {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private Otp_data data;
    @SerializedName("Error")
    @Expose
    private Boolean error;
    @SerializedName("ErrorMessage")
    @Expose
    private String errorMessage;
    @SerializedName("HttpResponseHeader")
    @Expose
    private String httpResponseHeader;

    public OtpResponceModal(String httpResponseHeader) {
        this.httpResponseHeader =httpResponseHeader;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Otp_data getData() {
        return data;
    }

    public void setData(Otp_data data) {
        this.data = data;
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

    public String getHttpResponseHeader() {
        return httpResponseHeader;

    }

    public void setHttpResponseHeader(String httpResponseHeader) {
        this.httpResponseHeader = httpResponseHeader;
    }

}
