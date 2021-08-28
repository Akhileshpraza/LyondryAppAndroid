package com.lyondry.lyondry.Modals.SelectSchedule;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PickupSchedule {

    @SerializedName("PickupScheduleId")
    @Expose
    private Integer pickupScheduleId;
    @SerializedName("PickupSchedulePickupRequestId")
    @Expose
    private Integer pickupSchedulePickupRequestId;
    @SerializedName("PickupScheduleDeliveryBoyId")
    @Expose
    private Integer pickupScheduleDeliveryBoyId;
    @SerializedName("PickupScheduleStatusId")
    @Expose
    private Integer pickupScheduleStatusId;
    @SerializedName("PickupStatusName")
    @Expose
    private String pickupStatusName;
    @SerializedName("PickupByName")
    @Expose
    private String pickupByName;
    @SerializedName("PickupByMobile")
    @Expose
    private String pickupByMobile;
    @SerializedName("TotalAmount")
    @Expose
    private Double totalAmount;
    @SerializedName("InvoiceNo")
    @Expose
    private String invoiceNo;
    @SerializedName("PaymentStatus")
    @Expose
    private String paymentStatus;
    @SerializedName("StoreName")
    @Expose
    private String storeName;
    @SerializedName("SessionID")
    @Expose
    private Object sessionID;
    @SerializedName("DateTime")
    @Expose
    private String dateTime;
    @SerializedName("EntryStatus")
    @Expose
    private Object entryStatus;
    @SerializedName("PickupRequest")
    @Expose
    private Object pickupRequest;
    @SerializedName("Error")
    @Expose
    private Object error;
    @SerializedName("ErrorMessage")
    @Expose
    private Object errorMessage;

    public Integer getPickupScheduleId() {
        return pickupScheduleId;
    }

    public void setPickupScheduleId(Integer pickupScheduleId) {
        this.pickupScheduleId = pickupScheduleId;
    }

    public Integer getPickupSchedulePickupRequestId() {
        return pickupSchedulePickupRequestId;
    }

    public void setPickupSchedulePickupRequestId(Integer pickupSchedulePickupRequestId) {
        this.pickupSchedulePickupRequestId = pickupSchedulePickupRequestId;
    }

    public Integer getPickupScheduleDeliveryBoyId() {
        return pickupScheduleDeliveryBoyId;
    }

    public void setPickupScheduleDeliveryBoyId(Integer pickupScheduleDeliveryBoyId) {
        this.pickupScheduleDeliveryBoyId = pickupScheduleDeliveryBoyId;
    }

    public Integer getPickupScheduleStatusId() {
        return pickupScheduleStatusId;
    }

    public void setPickupScheduleStatusId(Integer pickupScheduleStatusId) {
        this.pickupScheduleStatusId = pickupScheduleStatusId;
    }

    public String getPickupStatusName() {
        return pickupStatusName;
    }

    public void setPickupStatusName(String pickupStatusName) {
        this.pickupStatusName = pickupStatusName;
    }

    public String getPickupByName() {
        return pickupByName;
    }

    public void setPickupByName(String pickupByName) {
        this.pickupByName = pickupByName;
    }

    public String getPickupByMobile() {
        return pickupByMobile;
    }

    public void setPickupByMobile(String pickupByMobile) {
        this.pickupByMobile = pickupByMobile;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Object getSessionID() {
        return sessionID;
    }

    public void setSessionID(Object sessionID) {
        this.sessionID = sessionID;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public Object getEntryStatus() {
        return entryStatus;
    }

    public void setEntryStatus(Object entryStatus) {
        this.entryStatus = entryStatus;
    }

    public Object getPickupRequest() {
        return pickupRequest;
    }

    public void setPickupRequest(Object pickupRequest) {
        this.pickupRequest = pickupRequest;
    }

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }

    public Object getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(Object errorMessage) {
        this.errorMessage = errorMessage;
    }
}
