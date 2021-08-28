package com.lyondry.lyondry.Modals.InvoiceMaster;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GridTran_data_invice {

    @SerializedName("InvoiceTranInvoiceNo")
    @Expose
    private String invoiceTranInvoiceNo;
    @SerializedName("InvoiceTranSlNo")
    @Expose
    private Integer invoiceTranSlNo;
    @SerializedName("InvoiceTranItemCode")
    @Expose
    private String invoiceTranItemCode;
    @SerializedName("InvoiceTranDescription")
    @Expose
    private String invoiceTranDescription;
    @SerializedName("InvoiceTranUOM")
    @Expose
    private String invoiceTranUOM;
    @SerializedName("InvoiceTranHSNSAC")
    @Expose
    private String invoiceTranHSNSAC;
    @SerializedName("InvoiceTranItemUnitPrice")
    @Expose
    private Double invoiceTranItemUnitPrice;
    @SerializedName("InvoiceTranItemUomId")
    @Expose
    private Integer invoiceTranItemUomId;
    @SerializedName("InvoiceTranItemNos")
    @Expose
    private Integer invoiceTranItemNos;
    @SerializedName("InvoiceTranAmount")
    @Expose
    private Double invoiceTranAmount;
    @SerializedName("InvoiceTranDiscPercentage")
    @Expose
    private Double invoiceTranDiscPercentage;
    @SerializedName("InvoiceTranDiscount")
    @Expose
    private Double invoiceTranDiscount;
    @SerializedName("InvoiceTranTaxPercentage")
    @Expose
    private Double invoiceTranTaxPercentage;
    @SerializedName("InvoiceTranCgst")
    @Expose
    private Double invoiceTranCgst;
    @SerializedName("InvoiceTranSgst")
    @Expose
    private Double invoiceTranSgst;
    @SerializedName("InvoiceTranIgst")
    @Expose
    private Double invoiceTranIgst;
    @SerializedName("InvoiceTranTotalTax")
    @Expose
    private Double invoiceTranTotalTax;
    @SerializedName("InvoiceTranNetAmount")
    @Expose
    private Double invoiceTranNetAmount;

    public GridTran_data_invice(String itemcode) {
        this.invoiceTranItemCode = itemcode;
    }

    public String getInvoiceTranInvoiceNo() {
        return invoiceTranInvoiceNo;
    }

    public void setInvoiceTranInvoiceNo(String invoiceTranInvoiceNo) {
        this.invoiceTranInvoiceNo = invoiceTranInvoiceNo;
    }

    public Integer getInvoiceTranSlNo() {
        return invoiceTranSlNo;
    }

    public void setInvoiceTranSlNo(Integer invoiceTranSlNo) {
        this.invoiceTranSlNo = invoiceTranSlNo;
    }

    public String getInvoiceTranItemCode() {
        return invoiceTranItemCode;
    }

    public void setInvoiceTranItemCode(String invoiceTranItemCode) {
        this.invoiceTranItemCode = invoiceTranItemCode;
    }

    public String getInvoiceTranDescription() {
        return invoiceTranDescription;
    }

    public void setInvoiceTranDescription(String invoiceTranDescription) {
        this.invoiceTranDescription = invoiceTranDescription;
    }

    public String getInvoiceTranUOM() {
        return invoiceTranUOM;
    }

    public void setInvoiceTranUOM(String invoiceTranUOM) {
        this.invoiceTranUOM = invoiceTranUOM;
    }

    public String getInvoiceTranHSNSAC() {
        return invoiceTranHSNSAC;
    }

    public void setInvoiceTranHSNSAC(String invoiceTranHSNSAC) {
        this.invoiceTranHSNSAC = invoiceTranHSNSAC;
    }

    public Double getInvoiceTranItemUnitPrice() {
        return invoiceTranItemUnitPrice;
    }

    public void setInvoiceTranItemUnitPrice(Double invoiceTranItemUnitPrice) {
        this.invoiceTranItemUnitPrice = invoiceTranItemUnitPrice;
    }

    public Integer getInvoiceTranItemUomId() {
        return invoiceTranItemUomId;
    }

    public void setInvoiceTranItemUomId(Integer invoiceTranItemUomId) {
        this.invoiceTranItemUomId = invoiceTranItemUomId;
    }

    public Integer getInvoiceTranItemNos() {
        return invoiceTranItemNos;
    }

    public void setInvoiceTranItemNos(Integer invoiceTranItemNos) {
        this.invoiceTranItemNos = invoiceTranItemNos;
    }

    public Double getInvoiceTranAmount() {
        return invoiceTranAmount;
    }

    public void setInvoiceTranAmount(Double invoiceTranAmount) {
        this.invoiceTranAmount = invoiceTranAmount;
    }

    public Double getInvoiceTranDiscPercentage() {
        return invoiceTranDiscPercentage;
    }

    public void setInvoiceTranDiscPercentage(Double invoiceTranDiscPercentage) {
        this.invoiceTranDiscPercentage = invoiceTranDiscPercentage;
    }

    public Double getInvoiceTranDiscount() {
        return invoiceTranDiscount;
    }

    public void setInvoiceTranDiscount(Double invoiceTranDiscount) {
        this.invoiceTranDiscount = invoiceTranDiscount;
    }

    public Double getInvoiceTranTaxPercentage() {
        return invoiceTranTaxPercentage;
    }

    public void setInvoiceTranTaxPercentage(Double invoiceTranTaxPercentage) {
        this.invoiceTranTaxPercentage = invoiceTranTaxPercentage;
    }

    public Double getInvoiceTranCgst() {
        return invoiceTranCgst;
    }

    public void setInvoiceTranCgst(Double invoiceTranCgst) {
        this.invoiceTranCgst = invoiceTranCgst;
    }

    public Double getInvoiceTranSgst() {
        return invoiceTranSgst;
    }

    public void setInvoiceTranSgst(Double invoiceTranSgst) {
        this.invoiceTranSgst = invoiceTranSgst;
    }

    public Double getInvoiceTranIgst() {
        return invoiceTranIgst;
    }

    public void setInvoiceTranIgst(Double invoiceTranIgst) {
        this.invoiceTranIgst = invoiceTranIgst;
    }

    public Double getInvoiceTranTotalTax() {
        return invoiceTranTotalTax;
    }

    public void setInvoiceTranTotalTax(Double invoiceTranTotalTax) {
        this.invoiceTranTotalTax = invoiceTranTotalTax;
    }

    public Double getInvoiceTranNetAmount() {
        return invoiceTranNetAmount;
    }

    public void setInvoiceTranNetAmount(Double invoiceTranNetAmount) {
        this.invoiceTranNetAmount = invoiceTranNetAmount;
    }
}
