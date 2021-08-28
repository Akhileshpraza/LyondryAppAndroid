package com.lyondry.lyondry.Modals.Payment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Invoice_data {
    @SerializedName("InvoiceNo")
    @Expose
    private String invoiceNo;
    @SerializedName("InvoiceDateString")
    @Expose
    private String invoiceDateString;
    @SerializedName("InvoiceTotalAmount")
    @Expose
    private Integer invoiceTotalAmount;
    @SerializedName("InvoiceNetAmount")
    @Expose
    private Integer invoiceNetAmount;
    @SerializedName("InvoiceStatus")
    @Expose
    private String invoiceStatus;
    @SerializedName("InvoicePickupDate")
    @Expose
    private String invoicePickupDate;
    @SerializedName("InvoiceStoreName")
    @Expose
    private String invoiceStoreName;
    @SerializedName("InvoiceOrderId")
    @Expose
    private Integer invoiceOrderId;
    @SerializedName("InvoiceCouponAmount")
    @Expose
    private Integer invoiceCouponAmount;
    @SerializedName("InvoicePoints")
    @Expose
    private Integer invoicePoints;

    public Invoice_data(String invoiceNo, String invoiceDateString, int invoiceTotalAmount, int invoiceNetAmount, String invoiceStatus, String invoicePickupDate, String invoiceStoreName, int invoiceOrderId, int invoiceCouponAmount, int invoicePoints) {
        this.invoiceNo = invoiceNo;
        this.invoiceDateString = invoiceDateString;
        this.invoiceTotalAmount = invoiceTotalAmount;
        this.invoiceNetAmount = invoiceNetAmount;
        this.invoiceStatus = invoiceStatus;
        this.invoicePickupDate =invoicePickupDate;
        this.invoiceStoreName = invoiceStoreName;
        this.invoiceOrderId = invoiceOrderId;
        this.invoiceCouponAmount = invoiceCouponAmount;
        this.invoicePoints = invoicePoints;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getInvoiceDateString() {
        return invoiceDateString;
    }

    public void setInvoiceDateString(String invoiceDateString) {
        this.invoiceDateString = invoiceDateString;
    }

    public Integer getInvoiceTotalAmount() {
        return invoiceTotalAmount;
    }

    public void setInvoiceTotalAmount(Integer invoiceTotalAmount) {
        this.invoiceTotalAmount = invoiceTotalAmount;
    }

    public Integer getInvoiceNetAmount() {
        return invoiceNetAmount;
    }

    public void setInvoiceNetAmount(Integer invoiceNetAmount) {
        this.invoiceNetAmount = invoiceNetAmount;
    }

    public String getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(String invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    public String getInvoicePickupDate() {
        return invoicePickupDate;
    }

    public void setInvoicePickupDate(String invoicePickupDate) {
        this.invoicePickupDate = invoicePickupDate;
    }

    public String getInvoiceStoreName() {
        return invoiceStoreName;
    }

    public void setInvoiceStoreName(String invoiceStoreName) {
        this.invoiceStoreName = invoiceStoreName;
    }

    public Integer getInvoiceOrderId() {
        return invoiceOrderId;
    }

    public void setInvoiceOrderId(Integer invoiceOrderId) {
        this.invoiceOrderId = invoiceOrderId;
    }

    public Integer getInvoiceCouponAmount() {
        return invoiceCouponAmount;
    }

    public void setInvoiceCouponAmount(Integer invoiceCouponAmount) {
        this.invoiceCouponAmount = invoiceCouponAmount;
    }

    public Integer getInvoicePoints() {
        return invoicePoints;
    }

    public void setInvoicePoints(Integer invoicePoints) {
        this.invoicePoints = invoicePoints;
    }

}
