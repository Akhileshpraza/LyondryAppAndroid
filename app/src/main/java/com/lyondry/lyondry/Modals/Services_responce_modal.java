package com.lyondry.lyondry.Modals;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Services_responce_modal {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("ServiceList")
    @Expose
    private List<Services_data> serviceList = null;
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

    public List<Services_data> getServiceList() {
        return serviceList;
    }

    public void setServiceList(List<Services_data> serviceList) {
        this.serviceList = serviceList;
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



//    private int product_Img;
//    private String product_name;
//
//    public servicesModal(int product_Img, String product_name) {
//        this.product_Img = product_Img;
//        this.product_name = product_name;
//    }
//
//    public int getProduct_Img() {
//        return product_Img;
//    }
//
//    public void setProduct_Img(int product_Img) {
//        this.product_Img = product_Img;
//    }
//
//    public String getProduct_name() {
//        return product_name;
//    }
//
//    public void setProduct_name(String product_name) {
//        this.product_name = product_name;
//    }
}
