package com.lyondry.lyondry.Modals;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Login_responce {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private Login_result_model login_result_model;
    @SerializedName("Error")
    @Expose
    private Boolean error;
    @SerializedName("ErrorMessage")
    @Expose
    private String errorMessage;
    @SerializedName("HttpResponseHeader")
    @Expose
    private String httpResponseHeader;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Login_result_model getLogin_result_model() {
        return login_result_model;
    }

    public void setLogin_result_model(Login_result_model login_result_model) {
        this.login_result_model = login_result_model;
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
