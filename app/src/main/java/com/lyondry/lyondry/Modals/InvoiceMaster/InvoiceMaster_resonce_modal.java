package com.lyondry.lyondry.Modals.InvoiceMaster;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class InvoiceMaster_resonce_modal {
    @SerializedName("InvoiceNo")
    @Expose
    private String invoiceNo;
    @SerializedName("InvoiceDateString")
    @Expose
    private String invoiceDateString;
    @SerializedName("InvoiceDueDate")
    @Expose
    private String invoiceDueDate;
    @SerializedName("InvoiceCustomerId")
    @Expose
    private Integer invoiceCustomerId;
    @SerializedName("InvoiceCustomerCode")
    @Expose
    private String invoiceCustomerCode;
    @SerializedName("InvoiceCustomerAddress")
    @Expose
    private String InvoiceCustomerAddress;

    @SerializedName("InvoiceCustomerMobileNo")
    @Expose
    private String invoiceCustomerMobileNo;
    @SerializedName("InvoiceCustomerGstNo")
    @Expose
    private String invoiceCustomerGstNo;
    @SerializedName("InvoiceRequestId")
    @Expose
    private Integer invoiceRequestId;
    @SerializedName("InvoiceOrderNo")
    @Expose
    private String invoiceOrderNo;
    @SerializedName("InvoiceTotalAmount")
    @Expose
    private Double invoiceTotalAmount;
    @SerializedName("InvoiceDiscountPercentage")
    @Expose
    private Double invoiceDiscountPercentage;
    @SerializedName("InvoiceDiscountAmount")
    @Expose
    private Double invoiceDiscountAmount;
    @SerializedName("InvoiceTaxPercentage")
    @Expose
    private Double invoiceTaxPercentage;
    @SerializedName("InvoiceCgst")
    @Expose
    private Double invoiceCgst;
    @SerializedName("InvoiceSgst")
    @Expose
    private Double invoiceSgst;
    @SerializedName("InvoiceIgst")
    @Expose
    private Double invoiceIgst;
    @SerializedName("InvoiceTotalTax")
    @Expose
    private Double invoiceTotalTax;
    @SerializedName("InvoiceRoundOff")
    @Expose
    private Double invoiceRoundOff;
    @SerializedName("InvoiceNetAmount")
    @Expose
    private Double invoiceNetAmount;
    @SerializedName("InvoicePickupBy")
    @Expose
    private String invoicePickupBy;
    @SerializedName("GridTrans")
    @Expose
    private List<GridTran_data_invice> gridTrans = null;

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

    public String getInvoiceDueDate() {
        return invoiceDueDate;
    }

    public void setInvoiceDueDate(String invoiceDueDate) {
        this.invoiceDueDate = invoiceDueDate;
    }

    public Integer getInvoiceCustomerId() {
        return invoiceCustomerId;
    }

    public void setInvoiceCustomerId(Integer invoiceCustomerId) {
        this.invoiceCustomerId = invoiceCustomerId;
    }

    public String getInvoiceCustomerCode() {
        return invoiceCustomerCode;
    }

    public void setInvoiceCustomerCode(String invoiceCustomerCode) {
        this.invoiceCustomerCode = invoiceCustomerCode;
    }

    public String getInvoiceCustomerAddress() {
        return InvoiceCustomerAddress;
    }

    public void setInvoiceCustomerAddress(String invoiceCustomerAddress) {
        InvoiceCustomerAddress = invoiceCustomerAddress;
    }

    public String getInvoiceCustomerMobileNo() {
        return invoiceCustomerMobileNo;
    }

    public void setInvoiceCustomerMobileNo(String invoiceCustomerMobileNo) {
        this.invoiceCustomerMobileNo = invoiceCustomerMobileNo;
    }

    public String getInvoiceCustomerGstNo() {
        return invoiceCustomerGstNo;
    }

    public void setInvoiceCustomerGstNo(String invoiceCustomerGstNo) {
        this.invoiceCustomerGstNo = invoiceCustomerGstNo;
    }

    public Integer getInvoiceRequestId() {
        return invoiceRequestId;
    }

    public void setInvoiceRequestId(Integer invoiceRequestId) {
        this.invoiceRequestId = invoiceRequestId;
    }

    public String getInvoiceOrderNo() {
        return invoiceOrderNo;
    }

    public void setInvoiceOrderNo(String invoiceOrderNo) {
        this.invoiceOrderNo = invoiceOrderNo;
    }

    public Double getInvoiceTotalAmount() {
        return invoiceTotalAmount;
    }

    public void setInvoiceTotalAmount(Double invoiceTotalAmount) {
        this.invoiceTotalAmount = invoiceTotalAmount;
    }

    public Double getInvoiceDiscountPercentage() {
        return invoiceDiscountPercentage;
    }

    public void setInvoiceDiscountPercentage(Double invoiceDiscountPercentage) {
        this.invoiceDiscountPercentage = invoiceDiscountPercentage;
    }

    public Double getInvoiceDiscountAmount() {
        return invoiceDiscountAmount;
    }

    public void setInvoiceDiscountAmount(Double invoiceDiscountAmount) {
        this.invoiceDiscountAmount = invoiceDiscountAmount;
    }

    public Double getInvoiceTaxPercentage() {
        return invoiceTaxPercentage;
    }

    public void setInvoiceTaxPercentage(Double invoiceTaxPercentage) {
        this.invoiceTaxPercentage = invoiceTaxPercentage;
    }

    public Double getInvoiceCgst() {
        return invoiceCgst;
    }

    public void setInvoiceCgst(Double invoiceCgst) {
        this.invoiceCgst = invoiceCgst;
    }

    public Double getInvoiceSgst() {
        return invoiceSgst;
    }

    public void setInvoiceSgst(Double invoiceSgst) {
        this.invoiceSgst = invoiceSgst;
    }

    public Double getInvoiceIgst() {
        return invoiceIgst;
    }

    public void setInvoiceIgst(Double invoiceIgst) {
        this.invoiceIgst = invoiceIgst;
    }

    public Double getInvoiceTotalTax() {
        return invoiceTotalTax;
    }

    public void setInvoiceTotalTax(Double invoiceTotalTax) {
        this.invoiceTotalTax = invoiceTotalTax;
    }

    public Double getInvoiceRoundOff() {
        return invoiceRoundOff;
    }

    public void setInvoiceRoundOff(Double invoiceRoundOff) {
        this.invoiceRoundOff = invoiceRoundOff;
    }

    public Double getInvoiceNetAmount() {
        return invoiceNetAmount;
    }

    public void setInvoiceNetAmount(Double invoiceNetAmount) {
        this.invoiceNetAmount = invoiceNetAmount;
    }

    public String getInvoicePickupBy() {
        return invoicePickupBy;
    }

    public void setInvoicePickupBy(String invoicePickupBy) {
        this.invoicePickupBy = invoicePickupBy;
    }

    public List<GridTran_data_invice> getGridTrans() {
        return gridTrans;
    }

    public void setGridTrans(List<GridTran_data_invice> gridTrans) {
        this.gridTrans = gridTrans;
    }
}
